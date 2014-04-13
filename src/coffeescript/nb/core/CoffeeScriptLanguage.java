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

import coffeescript.nb.lexer.CoffeeScriptLexer;
import coffeescript.nb.antlr.lexer.AntlrTokenReaderLexer;
import coffeescript.nb.antlr.lexer.TokenEnumLexer;
import coffeescript.nb.antlr.parser.AntlrTokenReaderParser;
import coffeescript.nb.lexer.CoffeeScriptLexerLegacy;
import coffeescript.nb.core.embedding.CoffeeScriptRegexpLanguage;
import coffeescript.nb.core.embedding.CoffeeScriptStringLanguage;
import coffeescript.nb.options.CoffeeScriptSettings;
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
    private static List<CoffeeScriptTokenId> tokens;
    private static Map<coffeescript.nb.antlr.parser.TokenEnumParser,Integer> typeMap;
    private static Map<Integer, CoffeeScriptTokenId> idToToken;
    private static Map<TokenEnumLexer, CoffeeScriptTokenId> enumToToken;

    public static final Language<CoffeeScriptTokenId> getLanguage() {
        return LANGUAGE;
    }

    private CoffeeScriptLanguage() {
    }

    protected Collection<CoffeeScriptTokenId> createTokenIds() {
        if (tokens == null) {
            init();
        }
        return tokens;
    }
    
    private static void init() {
        AntlrTokenReaderLexer reader = new AntlrTokenReaderLexer();
        AntlrTokenReaderParser reader2 = new AntlrTokenReaderParser();
        tokens = new ArrayList<CoffeeScriptTokenId>(reader.readTokenFile());
        reader2.readTokenFile();
        typeMap = reader2.getTokens();
        idToToken = new HashMap<Integer, CoffeeScriptTokenId>();
        enumToToken = new EnumMap<TokenEnumLexer, CoffeeScriptTokenId>(TokenEnumLexer.class);
        int i = tokens.size()+1;
        for (TokenEnumLexer en : TokenEnumLexer.getLegacyTokens()) {
            if(!enumToToken.containsKey(en)) {
                CoffeeScriptTokenId id = new CoffeeScriptTokenId(en, i);
                i++;
                tokens.add(id);
                enumToToken.put(en, id);
            }
        }
        for (CoffeeScriptTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
            enumToToken.put(token.getTokenEnum(), token);
        }
    }

    @Override
    protected Lexer<CoffeeScriptTokenId> createLexer(LexerRestartInfo<CoffeeScriptTokenId> lri) {
        return (CoffeeScriptSettings.get().isLegacy()) ? new CoffeeScriptLexer(lri) : new CoffeeScriptLexer(lri);
    }

    @Override
    protected Map<String, Collection<CoffeeScriptTokenId>> createTokenCategories() {
        if (tokens == null) {
            init();
        }
        Map<String, Collection<CoffeeScriptTokenId>> map = new HashMap<String, Collection<CoffeeScriptTokenId>>();
        for (CoffeeScriptTokenId token : tokens) {
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
            case STRING:
            case REGEX:
            case JSTOKEN:
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
            case STRING:
                return LanguageEmbedding.create(CoffeeScriptStringLanguage.getLanguage(), 0, 0);
            case REGEX:
            case HEREGEX_LEG:
                return LanguageEmbedding.create(CoffeeScriptRegexpLanguage.getLanguage(), 0, 0);
            case JSTOKEN:
            case JSTOKEN_LEG:
                Language<?> javasSriptLanguage = Language.find("text/javascript");
                if (javasSriptLanguage != null && token.length() > 2) {
                    return LanguageEmbedding.create(javasSriptLanguage, 1, 1);
                }
                break;                    
        }
        return null;
    }

    public static Map<coffeescript.nb.antlr.parser.TokenEnumParser, Integer> getTypeMap() {
        return typeMap;
    }    

    @Override
    protected String mimeType() {
        return Constants.MIME_TYPE;
    }
    
    public static synchronized CoffeeScriptTokenId getToken(int id) {
        if (idToToken == null) {
            init();
        }
        return idToToken.get(id);
    }
    
    public static synchronized CoffeeScriptTokenId getToken(TokenEnumLexer id) {
        if (enumToToken == null) {
            init();
        }
        return enumToToken.get(id);
    }
}