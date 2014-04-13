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
public abstract class TokenInfo {
    private Token token;

    public TokenInfo(Token token) {
        this.token = token;
    }
    
    abstract boolean hasProperties();
    abstract Map<Object,Object> getProperties();
    public Token getToken() {
        return token;
    }
    public int getType() {
        return token.getType();
    }
    
    public int getStopIndex() {
        return token.getStopIndex();
    }
    
    public int getStartIndex() {
        return token.getStartIndex();
    }
}
