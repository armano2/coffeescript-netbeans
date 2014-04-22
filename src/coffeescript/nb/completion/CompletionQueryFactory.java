/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion;

import coffeescript.nb.options.CoffeeScriptSettings;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;

/**
 *
 * @author Miloš Pensimus
 */
public class CompletionQueryFactory {
    public static AsyncCompletionQuery createCompletionQuery(boolean all) {
        return CoffeeScriptSettings.get().isLegacy() ? new CompletionQueryLegacy() : new CompletionQuery(all);        
    }
}
