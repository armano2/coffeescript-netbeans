// Copyright 2011 Denis Stepanov
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
import coffeescript.nb.antlr.parser.AntlrTokenReaderParser;
import coffeescript.nb.lexer.CoffeeScriptLexer;
import coffeescript.nb.core.embedding.CoffeeScriptRegexpLanguage;
import coffeescript.nb.core.embedding.CoffeeScriptStringLanguage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.netbeans.api.lexer.InputAttributes;
import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.LanguagePath;
import org.netbeans.api.lexer.Token;
import org.netbeans.modules.parsing.spi.indexing.PathRecognizerRegistration;
import org.netbeans.spi.lexer.EmbeddingPresence;
import org.netbeans.spi.lexer.LanguageEmbedding;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 * 
 * @author Denis Stepanov
 */
@PathRecognizerRegistration(mimeTypes = Constants.MIME_TYPE)
public class CoffeeScriptLanguage extends LanguageHierarchy<CoffeeScriptTokenId> {

    private static final Language<CoffeeScriptTokenId> LANGUAGE = new CoffeeScriptLanguage().language();
    private static Map<coffeescript.nb.antlr.parser.TokenEnumParser,Integer> typeMap;
    private static Map<TokenEnumLexer, CoffeeScriptTokenId> enumToToken;
    private static List<String> keywords;

    public static final Language<CoffeeScriptTokenId> getLanguage() {
        return LANGUAGE;
    }

    private CoffeeScriptLanguage() {
    }

    protected Collection<CoffeeScriptTokenId> createTokenIds() {
        if (enumToToken == null) {
            init();
        }
        return enumToToken.values();
    }
    
    private static void init() {        
        KeywordsReader keywordsReader = new KeywordsReader();
        AntlrTokenReaderParser tokenReader = new AntlrTokenReaderParser();
        keywords = keywordsReader.getKeywords();
        typeMap = tokenReader.getTypeMap();
        enumToToken = new EnumMap<TokenEnumLexer, CoffeeScriptTokenId>(TokenEnumLexer.class);
        int i = 0;
        for (TokenEnumLexer en : TokenEnumLexer.values()) {
            if(!enumToToken.containsKey(en)) {
                CoffeeScriptTokenId id = new CoffeeScriptTokenId(en, i);                
                enumToToken.put(en, id);
                i++;
            }
        }
    }

    @Override
    protected Lexer<CoffeeScriptTokenId> createLexer(LexerRestartInfo<CoffeeScriptTokenId> lri) {
        return new CoffeeScriptLexer(lri);
    }

    @Override
    protected Map<String, Collection<CoffeeScriptTokenId>> createTokenCategories() {
        if (enumToToken == null) {
            init();
        }
        Map<String, Collection<CoffeeScriptTokenId>> map = new HashMap<String, Collection<CoffeeScriptTokenId>>();
        for (CoffeeScriptTokenId token : enumToToken.values()) {
            Collection<CoffeeScriptTokenId> categoryCollection = map.get(token.primaryCategory());
            if (categoryCollection == null) {
                categoryCollection = new HashSet<CoffeeScriptTokenId>();
                map.put(token.primaryCategory(), categoryCollection);
            }
            categoryCollection.add(token);
        }
        return map;
    }

    @Override
    protected EmbeddingPresence embeddingPresence(CoffeeScriptTokenId id) {
        switch (id.getTokenEnum()) {
            case STRING_LEG:
            case HEREGEX_LEG:
            case JSTOKEN_LEG:
                return EmbeddingPresence.ALWAYS_QUERY;
        }
        return null;
    }

    @Override
    protected LanguageEmbedding<?> embedding(Token<CoffeeScriptTokenId> token, LanguagePath languagePath, InputAttributes inputAttributes) {
        switch (token.id().getTokenEnum()) {
            case STRING_LEG:
                return LanguageEmbedding.create(CoffeeScriptStringLanguage.getLanguage(), 0, 0);
            case HEREGEX_LEG:
                return LanguageEmbedding.create(CoffeeScriptRegexpLanguage.getLanguage(), 0, 0);
            case JSTOKEN_LEG:
                Language<?> javasSriptLanguage = Language.find("text/javascript");
                if (javasSriptLanguage != null && token.length() > 2) {
                    return LanguageEmbedding.create(javasSriptLanguage, 1, 1);
                }
                break;                    
        }
        return null;
    }

    @Override
    protected String mimeType() {
        return Constants.MIME_TYPE;
    }
    
    public static Map<coffeescript.nb.antlr.parser.TokenEnumParser, Integer> getTypeMap() {
        return typeMap;
    }    
    
    public static synchronized CoffeeScriptTokenId getToken(TokenEnumLexer id) {
        if (enumToToken == null) {
            init();
        }
        return enumToToken.get(id);
    }
    
    public static synchronized List<String> getKeywords() {
        if (keywords == null) {
            init();
        }
        return keywords;
    }
}