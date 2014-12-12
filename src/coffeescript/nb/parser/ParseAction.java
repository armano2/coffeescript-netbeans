// Copyright 2014 Miloš Pensimus
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package coffeescript.nb.parser;

import coffeescript.nb.antlr.parser.ParserErrorListener;
import coffeescript.lexer.CoffeeScriptNativeLexer;
import coffeescript.lexer.CoffeeScriptNativeLexerException;
import coffeescript.nb.antlr.parser.generated.CoffeeScriptParserGrammar;
import coffeescript.nb.antlr.parser.CoffeeScriptParseTreeListener;
import coffeescript.nb.antlr.parser.CoffeeScriptParserTokenSource;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import java.util.concurrent.Callable;
import javax.swing.LookAndFeel;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.openide.cookies.EditorCookie;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class ParseAction implements Callable<ParserResult> {
    private final Snapshot snapshot;

    public ParseAction(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public ParserResult call() throws Exception {
        CoffeeScriptFileDefinition fileDefinition = new CoffeeScriptFileDefinition();  
        ParserErrorListener listener = new ParserErrorListener();      
        CoffeeScriptParserGrammar parser = null;

        try {
            CoffeeScriptNativeLexer lexer = new CoffeeScriptNativeLexer(snapshot.getText().toString());
            TokenSource tokenSource = new CoffeeScriptParserTokenSource(lexer.tokenize(true), CoffeeScriptLanguage.getTypeMap());  
            TokenStream tokens = new CommonTokenStream(tokenSource);
            parser = new CoffeeScriptParserGrammar(tokens);
            parser.removeErrorListeners();  
            parser.addErrorListener(listener);
            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            ParseTree tree = parser.root();  
            ParseTreeWalker walker = new ParseTreeWalker(); 
            EditorCookie ec = Lookup.getDefault().lookup(EditorCookie.class);
            final StyledDocument doc = (StyledDocument) snapshot.getSource().getDocument(true);
            CoffeeScriptParseTreeListener parseTreeListener = new CoffeeScriptParseTreeListener(doc);
            walker.walk(parseTreeListener, tree);
            
            fileDefinition.setRootClasses(parseTreeListener.getRootClasses());
            fileDefinition.setRootFields(parseTreeListener.getRootVariables());
            fileDefinition.setRootMethods(parseTreeListener.getRootMethods());
            fileDefinition.setFields(parseTreeListener.getVariables());
            fileDefinition.setMethods(parseTreeListener.getMethods());
            listener.addErrors(parseTreeListener.getErrors());
            
            
        } catch (CoffeeScriptNativeLexerException e) {            
            listener.addSingleError(e.getLine(), e.getColumn(), e.getMessage());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            return new ParsingResult(snapshot,fileDefinition,listener.getErrors());
        }        
        
    } 

}
