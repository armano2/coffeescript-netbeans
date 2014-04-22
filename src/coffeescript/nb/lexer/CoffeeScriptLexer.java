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
package coffeescript.nb.lexer;

import coffeescript.nb.core.CoffeeScriptLexerBase;
import coffeescript.nb.core.CoffeeScriptTokenId;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.netbeans.spi.lexer.LexerInput;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.netbeans.spi.lexer.TokenPropertyProvider;
import static coffeescript.nb.lexer.TokenEnumLexer.*;
import coffeescript.nb.core.CoffeeScriptLanguage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Denis Stepanov
 */
public class CoffeeScriptLexer extends CoffeeScriptLexerBase<CoffeeScriptTokenId> {

    private final static Set<String> COFFEE_ALIASES = new HashSet<String>(Arrays.asList("and", "or", "is", "isnt", "not", "yes", "no", "on", "off"));
    private final static Set<TokenEnumLexer> NOT_REGEX = EnumSet.of(NUMBER_LEG, REGEX_LEG, BOOL_LEG, INC_LEG, DEC_LEG, RBRACKET_LEG);
    private final static Set<TokenEnumLexer> NOT_SPACED_REGEX = EnumSet.of(RPAREN_LEG, RBRACE_LEG, THIS_LEG, IDENTIFIER_LEG, STRING_LEG);
    private final static Map<String, CoffeeScriptTokenId> TEXTID_TO_TOKEN = new HashMap<String, CoffeeScriptTokenId>();

    static {
        for (TokenEnumLexer token : TokenEnumLexer.values()) {
            if (token.getCategory() == Category.KEYWORD_CAT && token.fixedText() != null) {
                TEXTID_TO_TOKEN.put(token.fixedText(), getToken(token));
            }
        }
        for (String jsKeywork : Arrays.asList("true", "false", "null", "this", "new", "delete", "typeof", "in", "instanceof",
                "return", "throw", "break", "continue", "debugger", "if", "else", "switch", "for", "while", "do", "try", "catch", "finally",
                "class", "extends", "super")) {
            TEXTID_TO_TOKEN.put(jsKeywork, getToken(ANY_KEYWORD_LEG));
        }
        for (String coffeeKeyword : Arrays.asList("undefined", "then", "unless", "until", "loop", "of", "by", "when")) {
            TEXTID_TO_TOKEN.put(coffeeKeyword, getToken(ANY_KEYWORD_LEG));
        }
        for (String coffeeAlias : COFFEE_ALIASES) {
            TEXTID_TO_TOKEN.put(coffeeAlias, getToken(ANY_KEYWORD_LEG));
        }
        TEXTID_TO_TOKEN.put("true", getToken(BOOL_LEG));
        TEXTID_TO_TOKEN.put("false", getToken(BOOL_LEG));
    }
    
    private final static Pattern REGEX_MATCH = Pattern.compile("^\\/(?![\\s=])[^\\/\\n\\\\]*(?:(?:\\\\[\\s\\S]|\\[[^\\]\\n\\\\]*(?:\\\\[\\s\\S][^\\]\\n\\\\]*)*])[^\\/\\n\\\\]*)*\\/[imgy]{0,4}(?!\\w)");

    static {
        NOT_SPACED_REGEX.addAll(NOT_REGEX);
    }
    // 
    private CoffeeScriptTokenId prevToken;
    private boolean prevSpaced;
    private int indent;

    public CoffeeScriptLexer(LexerRestartInfo<CoffeeScriptTokenId> info) {
        super(info.input(), info.tokenFactory());
        if (info.state() != null) {
            State state = (State) info.state();
            prevToken = state.getPrevToken();
            prevSpaced = state.isPrevSpaced();
            indent = state.getIndent();
        }
    }

    public void release() {
    }

    public Object state() {
        return new State(prevToken, prevSpaced, indent);
    }
    
    private static CoffeeScriptTokenId getToken(TokenEnumLexer token) {
        return CoffeeScriptLanguage.getToken(token);
    }

    @Override
    protected org.netbeans.api.lexer.Token<CoffeeScriptTokenId> token(CoffeeScriptTokenId id) {
        if (id.equals(WHITESPACE_LEG)) {
            prevSpaced = true;
        } else {
            prevToken = id;
            prevSpaced = false;
        }

        switch (id.getTokenEnum()) {
            case INDENT_LEG:
                return tokenFactory.createPropertyToken(id, input.readLength(), new IndentTokenProperty(indent));
            case OUTDENT_LEG:
                return tokenFactory.createPropertyToken(id, input.readLength(), new IndentTokenProperty(indent));
        }

        String fixedText = id.fixedText();
        return (fixedText != null) ? tokenFactory.getFlyweightToken(id, fixedText) : super.token(id);
    }

    public org.netbeans.api.lexer.Token<CoffeeScriptTokenId> nextToken() {
        int c;
        int lineAt = -1;
        while (true) {
            c = input.read();
            if (c == -1) {
                if (input.readLength() > 0) {
                    input.backup(1);
                    return indentToken(0);
                }
                return null;
            } else if (c == '\n') {
                lineAt = input.readLength();
            } else if (!isSpaceCharacter(c)) {
                if (input.readLength() > 1) {
                    input.backup(1);
                    if (lineAt == -1) {
                        return token(getToken(WHITESPACE_LEG));
                    }
                    return indentToken(input.readLength() - lineAt);
                }
                break;
            }
        }

        if (isDigit(c) || (c == '.' && isDigit(peek()))) {

            StringBuilder buffer = new StringBuilder();

            int base = 10;

            if (c == '0') {
                c = input.read();
                if (c == 'x' || c == 'X') {
                    base = 16;
                    c = input.read();
                } else if (isDigit(c)) {
                    base = 8;
                } else {
                    buffer.append('0');
                }
            }

            if (base == 16) {
                while (0 <= xDigitToInt(c, 0)) {
                    buffer.append((char) c);
                    c = input.read();
                }
            } else {
                while ('0' <= c && c <= '9') {
                    /*
                     * We permit 08 and 09 as decimal numbers, which
                     * makes our behavior a superset of the ECMA
                     * numeric grammar.  We might not always be so
                     * permissive, so we warn about it.
                     */
                    if (base == 8 && c >= '8') {
                        base = 10;
                    }
                    buffer.append((char) c);
                    c = input.read();
                }
            }

            boolean isInteger = true;

            if (base == 10 && (c == '.' || c == 'e' || c == 'E')) {
                isInteger = false;
                if (c == '.') {
                    do {
                        buffer.append((char) c);
                        c = input.read();
                    } while (isDigit(c));
                }
                if (c == 'e' || c == 'E') {
                    buffer.append((char) c);
                    c = input.read();
                    if (c == '+' || c == '-') {
                        buffer.append((char) c);
                        c = input.read();
                    }
                    if (!isDigit(c)) {
                        return token(getToken(ERROR_LEG));
                    }
                    do {
                        buffer.append((char) c);
                        c = input.read();
                    } while (isDigit(c));
                }
            }

            input.backup(1);

            String numString = buffer.toString();
            if (base == 10 && !isInteger) {
                try {
                    Double.valueOf(numString).doubleValue();
                } catch (NumberFormatException ex) {
                    return token(getToken(ERROR_LEG));
                }
            }
            return token(getToken(NUMBER_LEG));
        }

        boolean startsWithAt = false;
        boolean identifierStart;
        boolean isUnicodeEscapeStart = false;
        if (c == '\\') {
            c = input.read();
            if (c == 'u') {
                identifierStart = true;
                isUnicodeEscapeStart = true;
            } else {
                identifierStart = false;
                input.backup(1);
                c = '\\';
            }
        } else {
            if (c == '@') {
                c = input.read();
                startsWithAt = true;
            }
            identifierStart = Character.isJavaIdentifierStart((char) c);
        }
        if (startsWithAt && !identifierStart) {
            return token(getToken(AT_LEG));
        }
        if (identifierStart) {
            StringBuilder buffer = new StringBuilder();
            if (!isUnicodeEscapeStart) {
                buffer.append((char) c);
            }
            boolean containsEscape = isUnicodeEscapeStart;
            while (true) {
                if (isUnicodeEscapeStart) {
                    // strictly speaking we should probably push-back
                    // all the bad characters if the <backslash>uXXXX
                    // sequence is malformed. But since there isn't a
                    // correct context(is there?) for a bad Unicode
                    // escape sequence in an identifier, we can report
                    // an error here.
                    int escapeVal = 0;
                    for (int i = 0; i != 4; ++i) {
                        c = input.read();
                        escapeVal = xDigitToInt(c, escapeVal);
                        // Next check takes care about c < 0 and bad escape
                        if (escapeVal < 0) {
                            break;
                        }
                    }
                    if (escapeVal < 0) {
                        return token(getToken(ERROR_LEG));
                    }
                    buffer.append((char) escapeVal);

                    isUnicodeEscapeStart = false;
                } else {
                    c = input.read();
                    if (c == '\\') {
                        c = input.read();
                        if (c == 'u') {
                            isUnicodeEscapeStart = true;
                            containsEscape = true;
                        } else {
                            return token(getToken(ERROR_LEG));
                        }
                    } else {
                        if (c == LexerInput.EOF || !Character.isJavaIdentifierPart((char) c)) {
                            break;
                        }
                        buffer.append((char) c);
                    }
                }
            }

            input.backup(1);

            if (startsWithAt) {
                return token(getToken(FIELD_LEG));
            }

            if (EnumSet.of(DOT_LEG, QDOT_LEG, DOUBLE_COLON_LEG).contains(prevToken)) {
                return token(getToken(IDENTIFIER_LEG));
            }

            String text = buffer.toString();
            if (!containsEscape) {
                CoffeeScriptTokenId token = TEXTID_TO_TOKEN.get(text);
                if (token != null) {
                    return token(token);
                }
                if ("own".equals(text) && prevToken.equals(TokenEnumLexer.FOR_LEG)) {
                    return token(getToken(ANY_KEYWORD_LEG));
                }
            }
            return token(getToken(IDENTIFIER_LEG));
        }

        switch (c) {
            case ';':
                return token(getToken(SEMI_LEG));
            case '(':
                return token(getToken(LPAREN_LEG));
            case ')':
                return token(getToken(RPAREN_LEG));
            case '{':
                return token(getToken(LBRACE_LEG));
            case '}':
                return token(getToken(RBRACE_LEG));
            case '[':
                return token(getToken(LBRACKET_LEG));
            case ']':
                return token(getToken(RBRACKET_LEG));
            case '\\':
                return token(getToken(ANY_OPERATOR_LEG));
            case '"': {
                if (inputMatch("\"\"")) {
                    return balancedInterpolatedString("\"\"\"") ? token(getToken(STRING_LEG)) : token(getToken(ERROR_LEG));
                } else {
                    return balancedInterpolatedString("\"") ? token(getToken(STRING_LEG)) : token(getToken(ERROR_LEG));
                }
            }
            case '\'': {
                if (inputMatch("''")) {
                    return balancedString("'''") ? token(getToken(SIMPLE_STRING_LEG)) : token(getToken(ERROR_LEG));
                } else {
                    return balancedString("'") ? token(getToken(SIMPLE_STRING_LEG)) : token(getToken(ERROR_LEG));
                }
            }
            case '/': {
                if (inputMatch("//")) {
                    if (balancedInterpolatedString("///")) {
                        while (true) {
                            c = input.read();
                            if (c == 'i' || c == 'm' || c == 'g' || c == 'y') {
                                continue;
                            } else {
                                input.backup(1);
                                break;
                            }
                        }
                        return token(getToken(HEREGEX_LEG));
                    } else {
                        return token(getToken(ERROR_LEG));
                    }
                } else if (prevToken != null) {
                    Set<TokenEnumLexer> notRegex = prevSpaced ? NOT_REGEX : NOT_SPACED_REGEX;
                    if (!notRegex.contains(prevToken.getTokenEnum())) {
                        if (balancedRegex()) {
                            while (true) {
                                c = input.read();
                                if (c == 'i' || c == 'm' || c == 'g' || c == 'y') {
                                    continue;
                                } else {
                                    input.backup(1);
                                    break;
                                }
                            }
                            if (REGEX_MATCH.matcher(input.readText()).matches()) {
                                return token(getToken(REGEX_LEG));
                            }
                        }
                        input.backup(input.readLength() - 1);
                    }

                }
                if (inputMatch('=')) {
                    return token(getToken(ANY_OPERATOR_LEG));
                }
                return token(getToken(DIV_LEG));
            }
            case '#': {
                if (inputNotMatch("###") && inputMatch("##")) {
                    return balancedString("###") ? token(getToken(COMMENT_LEG)) : token(getToken(ERROR_LEG));
                } else {
                    while (true) {
                        c = input.read();
                        if (c == '\n') {
                            input.backup(1);
                            return token(getToken(COMMENT_LEG));
                        }
                        if (c == LexerInput.EOF) {
                            return token(getToken(COMMENT_LEG));
                        }
                    }
                }
            }
            case '`': {
                return balancedJSToken() ? token(getToken(JSTOKEN_LEG)) : token(getToken(ERROR_LEG));
            }
            case '.': {
                return token(getToken(DOT_LEG));
            }
            case '?': {
                return inputMatch('.') ? token(getToken(QDOT_LEG)) : token(getToken(QM_LEG));
            }
            case ':': {
                return inputMatch(':') ? token(getToken(DOUBLE_COLON_LEG)): token(getToken(COLON_LEG));
            }
            case '+': {
                return inputMatch('+') ? token(getToken(INC_LEG)) : token(getToken(ANY_OPERATOR_LEG));
            }
            case '-': {
                return inputMatch('-') ? token(getToken(DEC_LEG)) : token(getToken(ANY_OPERATOR_LEG));
            }
        }
        return token(getToken(ANY_OPERATOR_LEG));
    }

    private org.netbeans.api.lexer.Token<CoffeeScriptTokenId> indentToken(int lineIndent) {
        if (lineIndent < indent) {
            indent = lineIndent;
            return token(getToken(OUTDENT_LEG));
        } else if (lineIndent > indent) {
            indent = lineIndent;
            return token(getToken(INDENT_LEG));
        }
        return token(getToken(WHITESPACE_LEG));
    }

    /**
     * If character <tt>c</tt> is a hexadecimal digit, return
     * <tt>accumulator</tt> * 16 plus corresponding number. Otherise return -1.
     */
    public static int xDigitToInt(int c, int accumulator) {
        check:
        {
            // Use 0..9 < A..Z < a..z
            if (c <= '9') {
                c -= '0';
                if (0 <= c) {
                    break check;
                }
            } else if (c <= 'F') {
                if ('A' <= c) {
                    c -= ('A' - 10);
                    break check;
                }
            } else if (c <= 'f') {
                if ('a' <= c) {
                    c -= ('a' - 10);
                    break check;
                }
            }
            return -1;
        }
        return (accumulator << 4) | c;
    }

    static boolean isDigit(int c) {
        return '0' <= c && c <= '9';
    }

    private boolean isSpaceCharacter(int c) {
        if (c <= 127) {
            return c == 0x20 || c == 0x9 || c == 0xC || c == 0xB;
        } else {
            return c == 0xA0 || Character.getType((char) c) == Character.SPACE_SEPARATOR;
        }
    }

    private static class State {

        final CoffeeScriptTokenId prevToken;
        final boolean prevSpaced;
        int indent;

        public State(CoffeeScriptTokenId prevToken, boolean prevSpaced, int indent) {
            this.prevToken = prevToken;
            this.prevSpaced = prevSpaced;
            this.indent = indent;
        }

        public CoffeeScriptTokenId getPrevToken() {
            return prevToken;
        }

        public boolean isPrevSpaced() {
            return prevSpaced;
        }

        public int getIndent() {
            return indent;
        }
    }

    private static class IndentTokenProperty implements TokenPropertyProvider<CoffeeScriptTokenId> {

        private final int indent;

        public IndentTokenProperty(int indent) {
            this.indent = indent;
        }

        public Object getValue(org.netbeans.api.lexer.Token<CoffeeScriptTokenId> token, Object key) {
            if ("indent".equals(key)) {
                return indent;
            }
            return null;
        }
    }
}
