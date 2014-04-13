/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.lexer;

import coffeescript.nb.core.CoffeeScriptTokenId;
import java.util.Map;
import org.netbeans.api.lexer.Token;
import org.netbeans.spi.lexer.TokenPropertyProvider;

/**
 *
 * @author milos
 */
public class PropertyProvider implements TokenPropertyProvider<CoffeeScriptTokenId> {
    
    Map<Object,Object> properties;

    public PropertyProvider(Map<Object, Object> properties) {
        this.properties = properties;
    }

    public Object getValue(Token<CoffeeScriptTokenId> token, Object o) {
        if(properties.containsKey(o))
            return properties.get(o);
        return null;
    }    
}
