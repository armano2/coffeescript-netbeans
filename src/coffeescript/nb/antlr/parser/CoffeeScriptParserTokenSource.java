// Copyright 2014 Miloš Pensimus
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package coffeescript.nb.antlr.parser;

import coffeescript.lexer.CoffeeScriptNativeToken;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Tuple2;;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptParserTokenSource implements TokenSource {
    private TokenFactory factory;
    private final Queue<Token> tokenQueue;
    private final Iterator<CoffeeScriptNativeToken> tokenIterator;
    private final Map<TokenEnumParser, Integer> tokenTypeMap;
    private Token actualToken;
    
    

    public CoffeeScriptParserTokenSource(List<CoffeeScriptNativeToken> tokens, Map<TokenEnumParser, Integer> tokenTypeMap) {
        this.factory = new CommonTokenFactory();
        this.tokenIterator = tokens.iterator();
        this.tokenQueue = new LinkedList<Token>();
        this.tokenTypeMap = tokenTypeMap;
    }
    
    
    @Override
    public Token nextToken() {
        if(!tokenQueue.isEmpty()) return actualToken = tokenQueue.poll();
        if(tokenIterator.hasNext()) {            
            CoffeeScriptNativeToken nativeToken = tokenIterator.next();
            analyze(nativeToken);
            actualToken = tokenQueue.poll(); 
            return actualToken;
        } else {
            return factory.create(new Tuple2<TokenSource, CharStream>(this, null),-1 , "", 0, 0 ,0, 0, 0);
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
    public void setTokenFactory(TokenFactory tf) {
        this.factory = tf;
    }

    @Override
    public TokenFactory getTokenFactory() {
        return factory;
    }    
    
    public Token getToken(CoffeeScriptNativeToken nativeToken) {
        TokenEnumParser tag = TokenEnumParser.tokenValue(String.valueOf(nativeToken.getTag()));
        String text = (tag.isShowTokenValue()) ? nativeToken.getValue() : tag.name();
        if (!tokenTypeMap.containsKey(tag)) tag = TokenEnumParser.BAD_TOKEN;
        return factory.create(new Tuple2<TokenSource, CharStream>(this, null), tokenTypeMap.get(tag) , text, 0, 0 ,0, nativeToken.getFirstLine(), nativeToken.getFirstColumn());
    }
    
    private void analyze(CoffeeScriptNativeToken token) {
        TokenEnumParser tag = getTokenEnumParser(token.getTag());
        
        if(tag.equals(TokenEnumParser.DOT_TOK) || tag.equals(TokenEnumParser.UNARY) 
                || tag.equals(TokenEnumParser.DOUBLE_COLON_TOK) || tag.equals(TokenEnumParser.QUEST_DOT_TOK)
                || tag.equals(TokenEnumParser.QUEST_DOUBLE_COLON_TOK)) {    
            tokenQueue.add(getToken(token));
            if(tokenIterator.hasNext()) {
                CoffeeScriptNativeToken nextToken = tokenIterator.next();
                TokenEnumParser nextTag = getTokenEnumParser(nextToken.getTag());
                if(nextTag.equals(TokenEnumParser.OUTDENT)) {
                    tokenQueue.add(getToken(nextToken));
                    tokenQueue.add(getToken(new CoffeeScriptNativeToken(TokenEnumParser.TERMINATOR.name(), TokenEnumParser.TERMINATOR.name(), Boolean.TRUE)));
                } else {
                    tokenQueue.add(getToken(nextToken));
                }
            }
        } else if(tag.equals(TokenEnumParser.OUTDENT)) {            
            if(tokenIterator.hasNext()) {
                CoffeeScriptNativeToken nextToken = tokenIterator.next();
                tokenQueue.add(getToken(token));
                tokenQueue.add(getToken(nextToken));
            }
        } else {
            tokenQueue.add(getToken(token));
        }
    }
    
    private TokenEnumParser getTokenEnumParser(String tag) {
        return TokenEnumParser.tokenValue(String.valueOf(tag));
    }

}
