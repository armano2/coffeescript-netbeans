/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.lexer;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author milos
 */
public class PropertyTokenInfo extends TokenInfo {
    
    private Map<Object,Object> properties;

    public PropertyTokenInfo(Token token) {
        super(token);
        this.properties = new HashMap<Object, Object>();
    }    
    
    @Override
    boolean hasProperties() {
        return true;
    }

    @Override
    Map<Object, Object> getProperties() {
        return properties;
    }
    
    void addProperty(Object key,Object value) {
        properties.put(key, value);
    }
    
    Object getProperty(Object key) {
        if(properties.containsKey(key))
            return properties.get(key);
        return null;
    }
    
}
