/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.options.CoffeeScriptSettings;
import java.util.concurrent.Callable;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;

/**
 *
 * @author milos
 */
public class ParseActionFactory {
    
    public static Callable<ParserResult> createParseAction(Snapshot snapshot) {
        return CoffeeScriptSettings.get().isLegacy() ? new ParseActionLegacy(snapshot) : new ParseAction(snapshot);
    }
    
}
