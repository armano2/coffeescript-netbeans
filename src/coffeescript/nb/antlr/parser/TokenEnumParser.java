/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser;

import java.util.logging.Logger;

/**
 *
 * @author milos
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
    TERMINATOR(null),
    STATEMENT(null),
    POST_IF(null),
    ELSE(null),
    INDENT(null),
    OUTDENT(null),
    IDENTIFIER(null),
    NUMBER(null),
    STRING(null),
    JS(null),
    REGEX(null),
    DEBUGGER(null),
    UNDEFINED(null),
    NULL(null),
    BOOL(null),
    RETURN(null),
    HERECOMMENT(null),
    PARAM_START(null),
    PARAM_END(null),
    SUPER(null),
    INDEX_START(null),
    INDEX_END(null),
    INDEX_SOAK(null),
    CLASS(null),
    EXTENDS(null),
    FUNC_EXIST(null),
    CALL_START(null),
    CALL_END(null),
    THIS(null),
    TRY(null),
    FINALLY(null),
    CATCH(null),
    THROW(null),
    WHILE(null),
    WHEN(null),
    UNTIL(null),
    LOOP(null),
    FOR(null),
    OWN(null),
    FORIN(null),
    FOROF(null),
    BY(null),
    SWITCH(null),
    LEADING_WHEN(null),
    IF(null),
    UNARY(null),
    UNARY_MATH(null),
    COMPOUND_ASSIGN(null),
    MATH(null),
    SHIFT(null),
    COMPARE(null),
    LOGIC(null),
    RELATION(null),
    BAD_TOKEN(null);
        
    private TokenEnumParser(String text) {
        this.text = text;
    }
    private Category category;
    private String text;
    private static final Logger logger = Logger.getLogger(TokenEnumParser.class.getName());

    public Category getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }    
    
    public static TokenEnumParser tokenValue(String name) {
        try {
            return TokenEnumParser.valueOf(name);
        } catch (Exception e) {
            for (TokenEnumParser en : TokenEnumParser.values()) {
                if(name.equals(en.getText())) 
                    return en;
            }
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
