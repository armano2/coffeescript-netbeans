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

import coffeescript.nb.core.CoffeeScriptTestBase;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public abstract class LexerTestBase extends CoffeeScriptTestBase {

    public LexerTestBase(String name) {
        super(name);

    }

    protected void performTest(String text, int caretOffset, int length, TokenEnumLexer tokenEnum) {
        LexUtilities.TokenInfo info = LexUtilities.getTokenAtOffset(getDocument(getTestFile()), caretOffset, tokenEnum);
        assertNotNull(info);
        assertEquals(text, info.getText());
        assertEquals(caretOffset, info.getStart());
        assertEquals(length, info.getLength());
        assertEquals(tokenEnum, info.getTokenEnum());
    }

    protected abstract FileObject getTestFile();

}
