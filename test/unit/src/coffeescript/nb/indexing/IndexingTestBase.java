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

import coffeescript.nb.core.CoffeeScriptTestBase;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public abstract class IndexingTestBase extends CoffeeScriptTestBase {

    protected final FileObject thisTestFile;

    public IndexingTestBase(String name) {
        super(name);
        thisTestFile = getFileObjectInDataDir(testProjectSourcePath + "indexing.coffee");
    }

    protected void indexFiles() throws Exception {
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "class1.coffee"));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "class2.coffee"));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "class3.coffee"));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "methods1.coffee"));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "fields1.coffee"));
        indexFile(thisTestFile);
    }

    protected void indexErrorneousFile() throws Exception {
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "indexingError.coffee"));
    }

}
