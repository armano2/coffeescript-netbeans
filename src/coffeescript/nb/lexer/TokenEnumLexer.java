/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.lexer;

import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public enum TokenEnumLexer {
    
    ERROR_LEG(null,Category.ERROR_CAT),
    NEW_LEG("new",Category.KEYWORD_CAT),
    IDENTIFIER_LEG(null,Category.IDENTIFIER_CAT),
    REGEX_LEG(null,Category.REGEXP_CAT),
    HEREGEX_LEG(null,Category.REGEXP_CAT),
    NUMBER_LEG(null,Category.NUMBER_CAT),
    STRING_LEG(null,Category.STRING_CAT),
    SIMPLE_STRING_LEG(null,Category.STRING_CAT),
    JSTOKEN_LEG(null,Category.STRING_CAT),
    BOOL_LEG(null,Category.KEYWORD_CAT),
    WHITESPACE_LEG(null,Category.WHITESPACE_CAT),
    INDENT_LEG(null,Category.WHITESPACE_CAT),
    OUTDENT_LEG(null,Category.WHITESPACE_CAT),
    EOL_LEG(null,Category.WHITESPACE_CAT),
    COMMENT_LEG(null,Category.COMMENT_CAT),
    LPAREN_LEG("(",Category.SEPARATOR_CAT),
    RPAREN_LEG(")",Category.SEPARATOR_CAT),
    LBRACE_LEG("{",Category.SEPARATOR_CAT),
    RBRACE_LEG("}",Category.SEPARATOR_CAT),
    LBRACKET_LEG("[",Category.SEPARATOR_CAT),
    RBRACKET_LEG("]",Category.SEPARATOR_CAT),
    ANY_KEYWORD_LEG(null,Category.KEYWORD_CAT),
    ANY_OPERATOR_LEG(null,Category.OPERATOR_CAT),
    QM_LEG("?",Category.OPERATOR_CAT),
    DOT_LEG(".",Category.OPERATOR_CAT),
    QDOT_LEG("?.",Category.OPERATOR_CAT),
    THIS_LEG("this",Category.KEYWORD_CAT),
    FOR_LEG("for",Category.KEYWORD_CAT),
    IF_LEG("if",Category.KEYWORD_CAT),
    ELSE_LEG("else",Category.KEYWORD_CAT),
    WHILE_LEG("while",Category.KEYWORD_CAT),
    CASE_LEG("case",Category.KEYWORD_CAT),
    DEFAULT_LEG("default",Category.KEYWORD_CAT),
    BREAK_LEG("break",Category.KEYWORD_CAT),
    SWITCH_LEG("switch",Category.KEYWORD_CAT),
    COLON_LEG(":",Category.OPERATOR_CAT),
    DOUBLE_COLON_LEG("::",Category.OPERATOR_CAT),
    SEMI_LEG(";",Category.OPERATOR_CAT),
    FIELD_LEG(null,Category.FIELD_CAT),
    DIV_LEG("/",Category.OPERATOR_CAT),
    INC_LEG("++",Category.OPERATOR_CAT),
    DEC_LEG("--",Category.OPERATOR_CAT),
    AT_LEG("@",Category.KEYWORD_CAT);
    
        
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
