/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.core.CoffeeScriptCompiler;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.util.concurrent.Callable;
import javax.swing.SwingUtilities;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;

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
        SwingUtilities.invokeLater(new CoffeeScriptAutocompileTask(snapshot));
        return new ParsingResult(snapshot, def, compilerResult);
    }    
}


