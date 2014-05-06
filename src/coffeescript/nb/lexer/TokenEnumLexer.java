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

package coffeescript.nb.lexer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miloš Pensimus
 */
public enum TokenEnumLexer {
    
    ERROR(null,Category.ERROR_CAT),
    FIELD(null,Category.FIELD_CAT),
    IDENTIFIER(null,Category.IDENTIFIER_CAT),
    REGEX(null,Category.REGEXP_CAT),
    HEREGEX(null,Category.REGEXP_CAT),
    NUMBER(null,Category.NUMBER_CAT),
    STRING(null,Category.STRING_CAT),
    SIMPLE_STRING(null,Category.STRING_CAT),
    JSTOKEN(null,Category.STRING_CAT),
    WHITESPACE(null,Category.WHITESPACE_CAT),
    INDENT(null,Category.WHITESPACE_CAT),
    OUTDENT(null,Category.WHITESPACE_CAT),
    EOL(null,Category.WHITESPACE_CAT),
    COMMENT(null,Category.COMMENT_CAT),
    LPAREN("(",Category.SEPARATOR_CAT),
    RPAREN(")",Category.SEPARATOR_CAT),
    LBRACE("{",Category.SEPARATOR_CAT),
    RBRACE("}",Category.SEPARATOR_CAT),
    LBRACKET("[",Category.SEPARATOR_CAT),
    RBRACKET("]",Category.SEPARATOR_CAT),
    ANY_OPERATOR(null,Category.OPERATOR_CAT),
    QM("?",Category.OPERATOR_CAT),
    DOT(".",Category.OPERATOR_CAT),
    QDOT("?.",Category.OPERATOR_CAT),
    COLON(":",Category.OPERATOR_CAT),
    DOUBLE_COLON("::",Category.OPERATOR_CAT),
    QDOUBLE_COLON("?::",Category.OPERATOR_CAT),
    SEMI(";",Category.OPERATOR_CAT),
    DIV("/",Category.OPERATOR_CAT),
    INC("++",Category.OPERATOR_CAT),
    DEC("--",Category.OPERATOR_CAT),
    AT("@",Category.KEYWORD_CAT),
    THIS("this",Category.KEYWORD_CAT),
    FOR("for",Category.KEYWORD_CAT),
//    IF("if",Category.KEYWORD_CAT),
//    ELSE("else",Category.KEYWORD_CAT),
//    WHILE("while",Category.KEYWORD_CAT),
    CASE("case",Category.KEYWORD_CAT),
    DEFAULT("default",Category.KEYWORD_CAT),
//    BREAK("break",Category.KEYWORD_CAT),
//    SWITCH("switch",Category.KEYWORD_CAT),
    NEW("new",Category.KEYWORD_CAT),    
    EXTENDS("extends",Category.KEYWORD_CAT),
    ANY_KEYWORD(null,Category.KEYWORD_CAT),
    BOOL(null,Category.KEYWORD_CAT);
    
        
    private TokenEnumLexer(String fixedText, Category category) {
        this.category = category;
        this.fixedText = fixedText;
    }
    private final Category category;
    private final String fixedText;
    private static final Logger logger = Logger.getLogger(TokenEnumLexer.class.getName());
    

    public Category getCategory() {
        return category;
    }
    
    public String fixedText() {
        return fixedText;
    }
    
    public static TokenEnumLexer tokenValue(String name) {
        try {
            return TokenEnumLexer.valueOf(name);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Token not found for String: {0}", name);
            return null;
        }
    }
    
    public static enum Category {
        COMMENT_CAT("comment"),
        KEYWORD_CAT("keyword"),
        REGEXP_CAT("regexp"),
        STRING_CAT("string"),
        WHITESPACE_CAT("whitespace"),
        OPERATOR_CAT("operator"),
        SEPARATOR_CAT("separator"),
        ERROR_CAT("error"),
        NUMBER_CAT("number"),
        IDENTIFIER_CAT("identifier"),
        FIELD_CAT("field");
        private String name;

        private Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }    
}
