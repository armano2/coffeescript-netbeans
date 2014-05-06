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
package coffeescript.nb.bracesmatching;

import coffeescript.nb.core.CoffeeScriptTestBase;
import org.netbeans.modules.editor.bracesmatching.SpiAccessor;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;

/**
 *
 * @author Miloš Pensimus
 */
public class BracesMatchingTest extends CoffeeScriptTestBase {

    public BracesMatchingTest(String name) {
        super(name);
    }

    public void testBraces() throws Exception {
        performTestOpening(0, new int[]{0, 1}, new int[]{15, 16});
        performTestClosing(15, new int[]{0, 1}, new int[]{15, 16});
        performTestOpening(2, new int[]{2, 3}, new int[]{5, 6});
        performTestClosing(5, new int[]{2, 3}, new int[]{5, 6});
    }

    public void testBrackets() throws Exception {
        performTestOpening(4, new int[]{4, 5}, new int[]{11, 12});
        performTestClosing(11, new int[]{4, 5}, new int[]{11, 12});
        performTestOpening(8, new int[]{8, 9}, new int[]{10, 11});
        performTestClosing(10, new int[]{8, 9}, new int[]{10, 11});
        performTestOpening(14, new int[]{14, 15}, new int[]{18, 19});
        performTestClosing(18, new int[]{14, 15}, new int[]{18, 19});
    }

    public void testParens() throws Exception {
        performTestOpening(1, new int[]{1, 2}, new int[]{3, 4});
        performTestClosing(3, new int[]{1, 2}, new int[]{3, 4});
        performTestOpening(6, new int[]{6, 7}, new int[]{20, 21});
        performTestClosing(20, new int[]{6, 7}, new int[]{20, 21});
        performTestOpening(7, new int[]{7, 8}, new int[]{9, 10});
        performTestClosing(9, new int[]{7, 8}, new int[]{9, 10});
    }

    private MatcherContext getMatcherContext(String file, int caretOffset) {
        return SpiAccessor.get().createCaretContext(getDocument(getFileObjectInDataDir(testProjectSourcePath + file)), caretOffset, true, -1);
    }

    private void performTestOpening(int caretOffset, int[] origin, int[] matchingResult) throws Exception {
        MatcherContext matcherContext = getMatcherContext("bracesMatching.coffee", caretOffset);
        CoffeeScriptBracesMatcher bracesMatcher = new CoffeeScriptBracesMatcher(matcherContext);
        compareArrays(origin, bracesMatcher.findOrigin());
        compareArrays(matchingResult, bracesMatcher.findMatches());
    }

    private void performTestClosing(int caretOffset, int[] origin, int[] matchingResult) throws Exception {
        MatcherContext matcherContext = getMatcherContext("bracesMatching.coffee", caretOffset);
        CoffeeScriptBracesMatcher bracesMatcher = new CoffeeScriptBracesMatcher(matcherContext);
        compareArrays(origin, bracesMatcher.findMatches());
        compareArrays(matchingResult, bracesMatcher.findOrigin());
    }

    private void compareArrays(int[] a, int[] b) {
        for (int i = 0; i < b.length; i++) {
            assertEquals(a[i], b[i]);
        }
    }

}
