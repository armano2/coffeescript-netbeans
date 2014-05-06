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
package coffeescript.nb.hyperlink;

import coffeescript.nb.lexer.LexerTestBase;
import coffeescript.nb.lexer.TokenEnumLexer;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public class HyperlinkTest extends LexerTestBase {

    private FileObject hyperlinkTestFile;

    public HyperlinkTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        hyperlinkTestFile = getFileObjectInDataDir(testProjectSourcePath + "hyperlink.coffee");
    }

    //CoffeeScriptHyperlinkProvider uses Utilities.getFocusedComponent method, 
    //but I didnt find a way to get other value than null form this method in the test cases
    public void testIdentifierRecognition() throws Exception {
        performTest("$ide_3_nrst", 0, 11, TokenEnumLexer.IDENTIFIER);
        performTest("as_512", 12, 6, TokenEnumLexer.IDENTIFIER);
        performTest("$ide_3_nrst", 19, 11, TokenEnumLexer.IDENTIFIER);
        performTest("a", 32, 1, TokenEnumLexer.IDENTIFIER);
        performTest("as_512", 54, 6, TokenEnumLexer.IDENTIFIER);
        performTest("ide3_nrst", 61, 9, TokenEnumLexer.IDENTIFIER);
        performTest("as_512", 72, 6, TokenEnumLexer.IDENTIFIER);
        performTest("ident_$dsld_dsd", 95, 15, TokenEnumLexer.IDENTIFIER);
    }

    public void testFieldRecognition() throws Exception {
        performTest("@s_512", 33, 6, TokenEnumLexer.FIELD);
        performTest("@ide_3_nrst", 40, 11, TokenEnumLexer.FIELD);
        performTest("@fields_123_sdf", 79, 15, TokenEnumLexer.FIELD);
    }

    @Override
    protected FileObject getTestFile() {
        return hyperlinkTestFile;
    }

}
