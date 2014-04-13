/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.lexer;

import java.util.Map;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author milos
 */
public class DefaultTokenInfo extends TokenInfo {

    public DefaultTokenInfo(Token token) {
        super(token);
    }    
    
    @Override
    boolean hasProperties() {
        return false;
    }

    @Override
    Map<Object, Object> getProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
