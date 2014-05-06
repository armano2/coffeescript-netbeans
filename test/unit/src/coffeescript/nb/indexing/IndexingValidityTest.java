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
import coffeescript.nb.options.CoffeeScriptSettings;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author Miloš Pensimus
 */
public class IndexingValidityTest extends IndexingTestBase {

    public IndexingValidityTest(String name) {
        super(name);
    }

    public void testIndexResultValidity1() throws Exception {
        CoffeeScriptSettings.get().setLegacy(true);
        indexFiles();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        assertEquals(0, index.getAllClassFields(thisTestFile).size());
        assertEquals(0, index.getAllClassMethods(thisTestFile).size());
        assertEquals(0, index.getAllClassesFromOtherFiles(thisTestFile).size());
        assertEquals(0, index.getAllClassesInFile(thisTestFile).size());
        assertEquals(0, index.getAllFieldsInFile(thisTestFile).size());
        assertEquals(0, index.getAllMethodsInFile(thisTestFile).size());
        assertEquals(0, index.getAllRootFieldsFromOtherFiles(thisTestFile).size());
        assertEquals(0, index.getAllRootMethodsFromOtherFiles(thisTestFile).size());
        assertEquals(0, index.getClassFieldsInFile(thisTestFile).size());
        assertEquals(0, index.getClassMethodsInFile(thisTestFile).size());
        CoffeeScriptSettings.get().setLegacy(false);
    }

    public void testIndexResultValidity2() throws Exception {
        indexErrorneousFile();
        CoffeeScriptIndex index = CoffeeScriptUtils.getIndex();
        assertEquals(0, index.getAllClassFields(thisTestFile).size());
        assertEquals(0, index.getAllClassMethods(thisTestFile).size());
        assertEquals(0, index.getAllClassesFromOtherFiles(thisTestFile).size());
        assertEquals(0, index.getAllClassesInFile(thisTestFile).size());
        assertEquals(0, index.getAllFieldsInFile(thisTestFile).size());
        assertEquals(0, index.getAllMethodsInFile(thisTestFile).size());
        assertEquals(0, index.getAllRootFieldsFromOtherFiles(thisTestFile).size());
        assertEquals(0, index.getAllRootMethodsFromOtherFiles(thisTestFile).size());
        assertEquals(0, index.getClassFieldsInFile(thisTestFile).size());
        assertEquals(0, index.getClassMethodsInFile(thisTestFile).size());
    }

}
