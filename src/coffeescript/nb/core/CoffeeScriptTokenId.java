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

package coffeescript.nb.core;

import coffeescript.nb.lexer.TokenEnumLexer;
import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptTokenId implements TokenId {

    private static final Language<CoffeeScriptTokenId> LANGUAGE = CoffeeScriptLanguage.getLanguage();
    private TokenEnumLexer token;
    private final Integer id;
    private String tokenString;

    public CoffeeScriptTokenId(
            TokenEnumLexer token,
            Integer id) {
        this.token = token;
        this.id = id;
    }

    @Override
    public String name() {
        return token.name();
    }

    @Override
    public int ordinal() {
        return id;
    }

    @Override
    public String primaryCategory() {
        return token.getCategory().getName();
    }  
    
    public String fixedText() {
        return token.fixedText();
    }    

    public static Language<CoffeeScriptTokenId> getLanguage() {
        return LANGUAGE;
    }
    
    public TokenEnumLexer getTokenEnum() {
        return token;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TokenEnumLexer)
            return token.equals(obj);
        return super.equals(obj);
    }    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}
