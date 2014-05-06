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
package coffeescript.nb.indexing;

import coffeescript.nb.core.CoffeeScriptUtils;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author Miloš Pensimus
 */
public class IndexingTest extends IndexingTestBase {

    public IndexingTest(String name) {
        super(name);
    }

    public void testIndexClassFields() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : R|f, B|a
        assertEquals(2, index.getAllClassFields(thisTestFile).size());
    }

    public void testIndexClassMethods() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : R|d
        assertEquals(1, index.getAllClassMethods(thisTestFile).size());
    }

    public void testIndexClassesOtherFiles() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : R, A, B
        assertEquals(3, index.getAllClassesFromOtherFiles(thisTestFile).size());
    }

    public void testIndexClassesInFile() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : W, Q
        assertEquals(2, index.getAllClassesInFile(thisTestFile).size());
    }

    public void testIndexFieldsInFile() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : a, t, t, f
        assertEquals(4, index.getAllFieldsInFile(thisTestFile).size());
    }

    public void testIndexMethodsInFile() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : b, f, s, anonymous0
        assertEquals(4, index.getAllMethodsInFile(thisTestFile).size());
    }

    public void testIndexFieldsFromOtherFiles() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : B|a, R|f, a, b, c
        assertEquals(5, index.getAllRootFieldsFromOtherFiles(thisTestFile).size());
    }

    public void testIndexMethodsFromOtherFiles() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : a, b, R|d
        assertEquals(3, index.getAllRootMethodsFromOtherFiles(thisTestFile).size());
    }

    public void testIndexClassFieldsInFile() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : empty
        assertEquals(0, index.getClassFieldsInFile(thisTestFile).size());
    }

    public void testIndexClassMethodsInFile() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        //result : empty
        assertEquals(0, index.getClassFieldsInFile(thisTestFile).size());
    }

    public void testIndexClassExists() throws Exception {
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        assertTrue(index.classExists("A"));
        assertTrue(index.classExists("B"));
        assertTrue(index.classExists("R"));
        assertTrue(index.classExists("W"));
        assertTrue(index.classExists("Q"));

    }

}
