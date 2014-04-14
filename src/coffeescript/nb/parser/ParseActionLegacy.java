/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.core.CoffeeScriptCompiler;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.core.CoffeeScriptRhinoCompiler;
import coffeescript.nb.core.Constants;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.csl.api.Severity;
import org.netbeans.modules.csl.spi.DefaultError;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.openide.text.NbDocument;

/**
 *
 * @author milos
 */
public class ParseActionLegacy implements Callable<ParserResult> {
    private final Snapshot snapshot;   

    public ParseActionLegacy(Snapshot snapshot) {
        this.snapshot = snapshot;
    }    
    
    public ParsingResult call() throws Exception {
        CharSequence text = snapshot.getText();
        CoffeeScriptFileDefinition def = new CoffeeScriptFileDefinition();
        CoffeeScriptCompiler.CompilerResult compilerResult = CoffeeScriptSettings.getCompiler().compile(text.toString(), CoffeeScriptSettings.get().isBare());
        SwingUtilities.invokeLater(new AutocompilationTask(snapshot));
        return new ParsingResult(snapshot, def, compilerResult);
    }    
}


