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

/**
 *
 * @author Miloš Pensimus
 */
public enum TokenEnumParser {

    MINUS_TOK("-"),
    DOUBLE_MINUS_TOK("--"),
    FUNC_SINGLE_ARROW("->"),
    COMMA_TOK(","),
    COLON_TOK(":"),
    DOUBLE_COLON_TOK("::"),
    QUEST_DOUBLE_COLON_TOK("?::"),
    QUEST_DOT_TOK("?."),
    DOT_TOK("."),
    DOUBLE_DOT_TOK(".."),
    TRIPLE_DOT_TOK("..."),
    LPAR("("),
    RPAR(")"),
    LBRACKET("["),
    RBRACKET("]"),
    LBRACE("{"),
    RBRACE("}"),
    AT_TOK("@"),
    DOUBLE_STAR("**"),
    PLUS_TOK("+"),
    DOUBLE_PLUS_TOK("++"),
    EQUATING_TOK("="),
    FUNC_DOUBLE_ARROW("=>"),
    QUEST_TOK("?"),
    TERMINATOR,
    STATEMENT,
    POST_IF,
    ELSE,
    INDENT,
    OUTDENT,
    IDENTIFIER(null),
    NUMBER(null),
    STRING(null),
    JS,
    REGEX,
    DEBUGGER,
    UNDEFINED,
    NULL,
    BOOL,
    RETURN,
    HERECOMMENT,
    PARAM_START,
    PARAM_END,
    SUPER,
    INDEX_START,
    INDEX_END,
    INDEX_SOAK,
    CLASS,
    EXTENDS,
    FUNC_EXIST,
    CALL_START,
    CALL_END,
    THIS,
    TRY,
    FINALLY,
    CATCH,
    THROW,
    WHILE,
    WHEN,
    UNTIL,
    LOOP,
    FOR,
    OWN,
    FORIN,
    FOROF,
    BY,
    SWITCH,
    LEADING_WHEN,
    IF,
    UNARY,
    UNARY_MATH,
    COMPOUND_ASSIGN,
    MATH,
    SHIFT,
    COMPARE,
    LOGIC,
    RELATION,
    BAD_TOKEN;
    
        
    private TokenEnumParser(String text) {
        this.text = text;
        this.showTokenValue = true;
    }
    
    private TokenEnumParser() {
        this.showTokenValue = false;
    }
    private String text;
    private final boolean showTokenValue;
    
    public String getText() {
        return text;
    }

    public boolean isShowTokenValue() {
        return showTokenValue;
    }
    
    public static TokenEnumParser tokenValue(String name) {
        try {
            return TokenEnumParser.valueOf(name);
        } catch (Exception e) {
            for (TokenEnumParser en : TokenEnumParser.values()) {
                if(name.equals(en.getText())) 
                    return en;
            }
            return BAD_TOKEN;
        }
    }  
}
