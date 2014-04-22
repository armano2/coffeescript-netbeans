/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.antlr.parser.ParserErrorListener;
import coffeescript.lexer.CoffeeScriptNativeLexer;
import coffeescript.lexer.CoffeeScriptNativeLexerException;
import coffeescript.nb.antlr.parser.CoffeeScriptParserGrammar;
import coffeescript.nb.antlr.parser.CoffeeScriptParseTreeListener;
import coffeescript.nb.antlr.parser.CoffeeScriptParserTokenSource;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import java.util.concurrent.Callable;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
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
            TokenSource tokenSource = new CoffeeScriptParserTokenSource(lexer.tokenize(true), CoffeeScriptLanguage.getTypeMap());       
            TokenStream tokens = new CommonTokenStream(tokenSource);
            CoffeeScriptParserGrammar parser = new CoffeeScriptParserGrammar(tokens);
            parser.removeErrorListeners();  
            parser.addErrorListener(listener);
            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            parser.setTrimParseTree(true);
            ParseTree tree = parser.root();  
            ParseTreeWalker walker = new ParseTreeWalker();            
            final StyledDocument doc = (StyledDocument) snapshot.getSource().getDocument(true);
            CoffeeScriptParseTreeListener parseTreeListener = new CoffeeScriptParseTreeListener(doc);
            walker.walk(parseTreeListener, tree);
            
            grammarDesc.setRootClasses(parseTreeListener.getRootClasses());
            grammarDesc.setRootFields(parseTreeListener.getRootVariables());
            grammarDesc.setRootMethods(parseTreeListener.getRootMethods());
            grammarDesc.setFields(parseTreeListener.getVariables());
            grammarDesc.setMethods(parseTreeListener.getMethods());
            
            SwingUtilities.invokeLater(new CoffeeScriptAutocompileTask(snapshot));
        } catch (CoffeeScriptNativeLexerException e) {            
            listener.addSingleError(e.getLine(), e.getColumn(), e.getMessage());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        } finally {
             return new ParsingResult(snapshot,grammarDesc,listener.getErrors());
        }
    }    
}
