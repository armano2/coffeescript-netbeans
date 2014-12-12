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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import org.netbeans.api.lexer.TokenSequence;
import org.openide.filesystems.FileObject;
import static coffeescript.nb.lexer.TokenEnumLexer.*;

/**
 *
 * @author Miloš Pensimus
 */
public class LexerTest extends LexerTestBase {

    protected FileObject lexerTestFile;

    public LexerTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lexerTestFile = getFileObjectInDataDir(testProjectSourcePath + "lexer.coffee");
    }

    public void testGetSequence() throws Exception {
        TokenSequence s = LexUtilities.getTokenSequence(getDocument(lexerTestFile), 0);
        assertNotNull(s);
        assertFalse(s.isEmpty());
    }

    public void testComment() throws Exception {
        performTest("#Numbers", 0, 8, COMMENT);
    }

    public void testNumbers() throws Exception {
        performTest("3e4", 9, 3, NUMBER);
        performTest("4", 13, 1, NUMBER);
        performTest("0x4", 15, 3, NUMBER);
//        performTest("0o4", 19, 3, NUMBER);
    }

    public void testString() throws Exception {
        performTest("\"#{@code}#{if @locationData then \": \" + locationDataToString(@locationData) else ''}\"", 19, 85, STRING);
        performTest("'simplestring'", 105, 14, SIMPLE_STRING);
        performTest("\"\"\"\n"
                + "a\n"
                + "\"\"\"", 120, 9, STRING);
        performTest("'''\n"
                + "a\n"
                + "'''", 130, 9, SIMPLE_STRING);
    }

    public void testJsToken() throws Exception {
        performTest("`function a() {\n"
                + "  s = 3\n"
                + "}`", 140, 26, JSTOKEN);
    }

    public void testBraces() throws Exception {
        performTest("(", 167, 1, LPAREN);
        performTest("{", 168, 1, LBRACE);
        performTest("[", 169, 1, LBRACKET);
        performTest("]", 170, 1, RBRACKET);
        performTest("}", 171, 1, RBRACE);
        performTest(")", 172, 1, RPAREN);
    }

    public void testIdent() throws Exception {
        //idents are lowercase
        performTest("$ident", 174, 6, IDENTIFIER);
        performTest("@field", 181, 6, FIELD);
    }

    public void testOperator() throws Exception {
        performTest("?", 188, 1, QM);
        performTest(".", 190, 1, DOT);
        performTest("?.", 192, 2, QDOT);
        performTest(":", 195, 1, COLON);
        performTest("::", 197, 2, DOUBLE_COLON);
        performTest("?::", 200, 3, QDOUBLE_COLON);
        performTest(";", 204, 1, SEMI);
        performTest("/", 206, 1, DIV);
        performTest("++", 208, 2, INC);
        performTest("--", 211, 2, DEC);
        performTest("@", 214, 1, AT);
    }

    public void testKeywords() throws Exception {
        // TODO - remove unused keywords
        // keyword for and this shouldnt be ANY_KEYWORD
        performTest("this", 216, 4, ANY_KEYWORD);
        performTest("for", 221, 3, ANY_KEYWORD);
        performTest("if", 225, 2, ANY_KEYWORD);
        performTest("else", 228, 4, ANY_KEYWORD);
        performTest("while", 233, 5, ANY_KEYWORD);
        performTest("break", 252, 5, ANY_KEYWORD);
        performTest("switch", 258, 6, ANY_KEYWORD);
        performTest("case", 239, 4, CASE);
        performTest("default", 244, 7, DEFAULT);
        performTest("new", 265, 3, NEW);
        performTest("extends", 269, 7, EXTENDS);
    }

    @Override
    protected FileObject getTestFile() {
        return lexerTestFile;
    }

}
