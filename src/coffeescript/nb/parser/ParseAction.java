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
import coffeescript.nb.core.CoffeeScriptDataObject;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.navigator.GrammarDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.modules.parsing.api.Snapshot;
import org.openide.loaders.DataObject;
import org.netbeans.modules.csl.api.Error;

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
        GrammarDescriptor grammarDesc = new GrammarDescriptor();  
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
            DataObject dataObject = NbEditorUtilities.getDataObject(doc);
            if (dataObject instanceof CoffeeScriptDataObject) {
                CoffeeScriptDataObject abnfDataObject = (CoffeeScriptDataObject) dataObject;
                abnfDataObject.publishGrammarDescriptor(grammarDesc);
            }
        } catch (CoffeeScriptNativeLexerException e) {            
            listener.addSingleError(e.getLine() + 1, e.getColumn() + 1, e.getMessage());
        } catch (Exception ex) {
        } finally {
             return new ParsingResult(snapshot,grammarDesc,listener.getErrors());
//                     CoffeeScriptProject project = (CoffeeScriptProject) FileOwnerQuery.getOwner(snapshot.getSource().getFileObject());
//                     FileDescriptorMap map = project.getLookup().lookup(FileDescriptorMap.class);
//                     map.replace(grammarDesc);
        }
    }
    
    public class ParsingResult extends ParserResult {
        
        private GrammarDescriptor gd;
        private List<ErrorDescription> errors;
               
        public ParsingResult(Snapshot snapshot, GrammarDescriptor gd, List<ErrorDescription> errors) {
            super(snapshot);
            this.gd = gd;
            this.errors = errors;
            gd.setFileName(snapshot.getSource().getFileObject().getName());
        }

        @Override
        protected void invalidate() {
        }

        public GrammarDescriptor getGd() {
            return gd;
        }      

        public List<ErrorDescription> getErrors() {
            return errors;
        }

        @Override
        public List<? extends Error> getDiagnostics() {
            return Collections.emptyList();
        }
    }
    
}
