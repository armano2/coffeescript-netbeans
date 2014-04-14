/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.lexer;

import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public enum TokenEnumLexer {
    
    NULL(null,Category.KEYWORD_CAT),
    THIS_TOK(null,Category.KEYWORD_CAT),
    NEW(null,Category.KEYWORD_CAT),
    DELETE(null,Category.KEYWORD_CAT),
    TYPEOF(null,Category.KEYWORD_CAT),
    IN(null,Category.KEYWORD_CAT),
    INSTANCEOF(null,Category.KEYWORD_CAT),
    RETURN_TOK(null,Category.KEYWORD_CAT),
    THROW_TOK(null,Category.KEYWORD_CAT),
    BREAK(null,Category.KEYWORD_CAT),
    CONTINUE(null,Category.KEYWORD_CAT),
    DEBUGGER(null,Category.KEYWORD_CAT),
    IF_TOK(null,Category.KEYWORD_CAT),
    ELSE(null,Category.KEYWORD_CAT),
    SWITCH_TOK(null,Category.KEYWORD_CAT),
    FOR_TOK(null,Category.KEYWORD_CAT),
    WHILE_TOK(null,Category.KEYWORD_CAT),
    DO(null,Category.KEYWORD_CAT),
    TRY_TOK(null,Category.KEYWORD_CAT),
    CATCH(null,Category.KEYWORD_CAT),
    FINALLY(null,Category.KEYWORD_CAT),
    CLASS_TOK(null,Category.KEYWORD_CAT),
    EXTENDS(null,Category.KEYWORD_CAT),
    SUPER(null,Category.KEYWORD_CAT),
    UNDEFINED(null,Category.KEYWORD_CAT),
    THEN(null,Category.KEYWORD_CAT),
    UNLESS(null,Category.KEYWORD_CAT),
    UNTIL(null,Category.KEYWORD_CAT),
    LOOP(null,Category.KEYWORD_CAT),
    OF(null,Category.KEYWORD_CAT),
    BY(null,Category.KEYWORD_CAT),
    WHEN(null,Category.KEYWORD_CAT),
    AND(null,Category.KEYWORD_CAT),
    OR(null,Category.KEYWORD_CAT),
    IS(null,Category.KEYWORD_CAT),
    ISNT(null,Category.KEYWORD_CAT),
    NOT(null,Category.KEYWORD_CAT),
    YES(null,Category.KEYWORD_CAT),
    NO(null,Category.KEYWORD_CAT),
    ON(null,Category.KEYWORD_CAT),
    OFF(null,Category.KEYWORD_CAT),
    CASE(null,Category.KEYWORD_CAT),
    DEFAULT(null,Category.KEYWORD_CAT),
    FUNCTION(null,Category.KEYWORD_CAT),
    VAR(null,Category.KEYWORD_CAT),
    VOID(null,Category.KEYWORD_CAT),
    WITH(null,Category.KEYWORD_CAT),
    CONST(null,Category.KEYWORD_CAT),
    LET(null,Category.KEYWORD_CAT),
    ENUM(null,Category.KEYWORD_CAT),
    EXPORT(null,Category.KEYWORD_CAT),
    IMPORT(null,Category.KEYWORD_CAT),
    NATIVE(null,Category.KEYWORD_CAT),
    U_HASPROP(null,Category.KEYWORD_CAT),
    U_EXTENDS(null,Category.KEYWORD_CAT),
    U_SLICE(null,Category.KEYWORD_CAT),
    U_BIND(null,Category.KEYWORD_CAT),
    U_INDEXOF(null,Category.KEYWORD_CAT),
    IMPLEMENTS(null,Category.KEYWORD_CAT),
    INTERFACE(null,Category.KEYWORD_CAT),
    PUBLIC(null,Category.KEYWORD_CAT),
    STATIC(null,Category.KEYWORD_CAT),
    YIELD(null,Category.KEYWORD_CAT),
    OWN(null,Category.KEYWORD_CAT),
    TRUE(null,Category.KEYWORD_CAT),
    FALSE(null,Category.KEYWORD_CAT),
    MINUS_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    PLUS_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    DIV_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    MULT_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    MOD_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    OR_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    AND_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    QUEST_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    LSHIFT_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    RSHIFT_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    RSHIFT3_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    AMP_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    XOR_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    PIPE_ASSIGN_TOK(null,Category.OPERATOR_CAT),
    AMP_TOK(null,Category.OPERATOR_CAT),
    DOUBLE_AMP_TOK(null,Category.OPERATOR_CAT),
    PIPE_TOK(null,Category.OPERATOR_CAT),
    DOUBLE_PIPE_TOK(null,Category.OPERATOR_CAT),
    XOR(null,Category.OPERATOR_CAT),
    EQUALS_TOK(null,Category.OPERATOR_CAT),
    NOT_EQUALS_TOK(null,Category.OPERATOR_CAT),
    LT_TOK(null,Category.OPERATOR_CAT),
    GT_TOK(null,Category.OPERATOR_CAT),
    LE_TOK(null,Category.OPERATOR_CAT),
    GE_TOK(null,Category.OPERATOR_CAT),
    LSHIFT_TOK(null,Category.OPERATOR_CAT),
    RSHIFT_TOK(null,Category.OPERATOR_CAT),
    RSHIFT3_TOK(null,Category.OPERATOR_CAT),
    DIV_TOK(null,Category.OPERATOR_CAT),
    MULT_TOK(null,Category.OPERATOR_CAT),
    MOD_TOK(null,Category.OPERATOR_CAT),
    EQUATING_TOK(null,Category.OPERATOR_CAT),
    COLON_TOK(null,Category.OPERATOR_CAT),
    FUNC_DOUBLE_ARROW(null,Category.OPERATOR_CAT),
    FUNC_SINGLE_ARROW(null,Category.OPERATOR_CAT),
    TRIPLE_DOT_TOK(null,Category.OPERATOR_CAT),
    DOT_TOK(null,Category.OPERATOR_CAT),
    QUEST_DOT_TOK(null,Category.OPERATOR_CAT),
    DOUBLE_COLON_TOK(null,Category.OPERATOR_CAT),
    QUEST_DOUBLE_COLON_TOK(null,Category.OPERATOR_CAT),
    AT_TOK(null,Category.OPERATOR_CAT),
    DOUBLE_DOT_TOK(null,Category.OPERATOR_CAT),
    PLUS_TOK(null,Category.OPERATOR_CAT),
    MINUS_TOK(null,Category.OPERATOR_CAT),
    DOUBLE_PLUS_TOK(null,Category.OPERATOR_CAT),
    DOUBLE_MINUS_TOK(null,Category.OPERATOR_CAT),
    QUEST_TOK(null,Category.OPERATOR_CAT),
    TILDE_TOK(null,Category.OPERATOR_CAT),
    EXCLAM_TOK(null,Category.OPERATOR_CAT),
    BACKTICK(null,Category.SEPARATOR_CAT),
    COMMA_TOK(null,Category.SEPARATOR_CAT),
    LBRACE(null,Category.SEPARATOR_CAT),
    RBRACE(null,Category.SEPARATOR_CAT),
    LBRACKET(null,Category.SEPARATOR_CAT),
    RBRACKET(null,Category.SEPARATOR_CAT),
    LPAR(null,Category.SEPARATOR_CAT),
    RPAR(null,Category.SEPARATOR_CAT),
    NUMBER(null,Category.NUMBER_CAT),
    STRING(null,Category.STRING_CAT),
    JSTOKEN(null,Category.STRING_CAT),
    REGEX(null,Category.REGEXP_CAT),
    REGEXP(null,Category.REGEXP_CAT),
    COMMENT(null,Category.COMMENT_CAT),
    IDENTIFIER(null,Category.IDENTIFIER_CAT),
    CLASS_FIELD(null,Category.FIELD_CAT),
    TERMINATOR(null,Category.WHITESPACE_CAT),
    NEW_LINE(null,Category.WHITESPACE_CAT),
    SKIP(null,Category.WHITESPACE_CAT),
    SPACES(null,Category.WHITESPACE_CAT),
    INDENT(null,Category.WHITESPACE_CAT),
    OUTDENT(null,Category.WHITESPACE_CAT),
    
    //legacy tokens
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
    private Category category;
    private String fixedText;
    private static final Logger logger = Logger.getLogger(TokenEnumLexer.class.getName());
    
    public static EnumSet<TokenEnumLexer> getLegacyTokens() {
        return EnumSet.of(ERROR_LEG, NEW_LEG, IDENTIFIER_LEG, REGEX_LEG, HEREGEX_LEG,
                NUMBER_LEG, STRING_LEG, SIMPLE_STRING_LEG, JSTOKEN_LEG, BOOL_LEG,
                WHITESPACE_LEG, INDENT_LEG, OUTDENT_LEG, EOL_LEG, COMMENT_LEG, LPAREN_LEG,
                RPAREN_LEG, LBRACE_LEG, RBRACE_LEG, LBRACKET_LEG, RBRACKET_LEG, ANY_KEYWORD_LEG,
                ANY_OPERATOR_LEG, QM_LEG, DOT_LEG, QDOT_LEG, THIS_LEG, FOR_LEG, IF_LEG, 
                ELSE_LEG, WHILE_LEG, CASE_LEG, DEFAULT_LEG, BREAK_LEG, SWITCH_LEG, COLON_LEG,
                DOUBLE_COLON_LEG, SEMI_LEG, FIELD_LEG, DIV_LEG, INC_LEG, DEC_LEG, AT_LEG);
    }

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
