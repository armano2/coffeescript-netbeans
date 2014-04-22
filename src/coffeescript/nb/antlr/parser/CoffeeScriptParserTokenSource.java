/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.antlr.parser;

import coffeescript.lexer.CoffeeScriptNativeToken;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptParserTokenSource implements TokenSource {
    private TokenFactory<?> factory;
    private List<CoffeeScriptNativeToken> tokens;
    private Iterator<CoffeeScriptNativeToken> tokenIterator;
    private Map<TokenEnumParser, Integer> tokenTypeMap;
    private Token actualToken;
    
    

    public CoffeeScriptParserTokenSource(List<CoffeeScriptNativeToken> tokens, Map<TokenEnumParser, Integer> tokenTypeMap) {
        this.factory = new CommonTokenFactory();
        this.tokens = tokens;
        this.tokenIterator = tokens.iterator();
        this.tokenTypeMap = tokenTypeMap;
    }
    
    
    @Override
    public Token nextToken() {
        if(tokenIterator.hasNext()) {        
        CoffeeScriptNativeToken nativeToken = tokenIterator.next();
        TokenEnumParser tag = TokenEnumParser.tokenValue(String.valueOf(nativeToken.getTag()));
        String text = String.valueOf(nativeToken.getValue());
        if (!tokenTypeMap.containsKey(tag)) tag = TokenEnumParser.BAD_TOKEN;
        actualToken = factory.create(new Pair<TokenSource, CharStream>(this, null), tokenTypeMap.get(tag) , text, 0, 0 ,0, nativeToken.getFirstLine(), nativeToken.getFirstColumn());
        return actualToken;
        } else {
            return factory.create(new Pair<TokenSource, CharStream>(this, null),-1 , "", 0, 0 ,0, 0, 0);
        }
    }

    @Override
    public int getLine() {
        return (actualToken != null) ? actualToken.getLine() : 0;
    }

    @Override
    public int getCharPositionInLine() {
        return (actualToken != null) ? actualToken.getCharPositionInLine(): 0;
    }

    @Override
    public CharStream getInputStream() {
        return null;
    }

    @Override
    public String getSourceName() {
        return "CoffeeScript";
    }

    @Override
    public void setTokenFactory(TokenFactory<?> tf) {
        this.factory = tf;
    }

    @Override
    public TokenFactory<?> getTokenFactory() {
        return factory;
    }    
}
