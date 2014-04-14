/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.lexer.CoffeeScriptNativeLexer;
import coffeescript.lexer.CoffeeScriptNativeLexerException;
import coffeescript.nb.antlr.parser.CoffeeScriptParserGrammar;
import coffeescript.nb.antlr.parser.CoffeeScriptTokenStream;
import coffeescript.nb.antlr.parser.CompletionListener;
import coffeescript.nb.antlr.parser.definitions.BlockDefinition;
import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.openide.util.Exceptions;

/**
 *
 * @author milos
 */
public class ParseAction implements Callable<ParserResult> {
    private final Snapshot snapshot;

    public ParseAction(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public ParserResult call() throws Exception {
        CoffeeScriptFileDefinition grammarDesc = new CoffeeScriptFileDefinition();  
        ParserErrorListener listener = new ParserErrorListener();

        try {
            CoffeeScriptNativeLexer lexer = new CoffeeScriptNativeLexer(snapshot.getText().toString());
            TokenStream tokens = new CoffeeScriptTokenStream(lexer.tokenize(true), CoffeeScriptLanguage.getTypeMap());               
            CoffeeScriptParserGrammar parser = new CoffeeScriptParserGrammar(tokens);
            parser.removeErrorListeners();  
            parser.addErrorListener(listener);
            ParseTree tree = parser.root();  
            List<ClassDefinition> classes = new ArrayList<ClassDefinition>();
            BlockDefinition rootBlock = new BlockDefinition();
            ParseTreeWalker walker = new ParseTreeWalker();
            final StyledDocument doc = (StyledDocument) snapshot.getSource().getDocument(false);
            walker.walk(new CompletionListener(classes, rootBlock, doc), tree);
            grammarDesc.setRootBlock(rootBlock);
            grammarDesc.setClasses(classes);
            SwingUtilities.invokeLater(new AutocompilationTask(snapshot));
        } catch (CoffeeScriptNativeLexerException e) {            
            listener.addSingleError(e.getLine() + 1, e.getColumn() + 1, e.getMessage());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        } finally {
             return new ParsingResult(snapshot,grammarDesc,listener.getErrors());
        }
    }    
}
