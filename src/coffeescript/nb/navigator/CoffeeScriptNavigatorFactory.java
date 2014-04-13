/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.navigator;

import coffeescript.nb.options.CoffeeScriptSettings;
import org.netbeans.spi.navigator.NavigatorPanel;

/**
 *
 * @author milos
 */
public class CoffeeScriptNavigatorFactory {
    public static NavigatorPanel getNavigator() {
        return /*CoffeeScriptSettings.get().isLegacy() ? null :*/ new CoffeeScriptNavigator();
    }
}
