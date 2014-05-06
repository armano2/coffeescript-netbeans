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

package coffeescript.nb.indexing.classpath;

import coffeescript.nb.core.Constants;
import java.util.Collections;
import java.util.Set;
import org.netbeans.modules.parsing.spi.indexing.PathRecognizer;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Miloš Pensimus
 */
@ServiceProvider(service = PathRecognizer.class)
public final class CoffeeScriptPathRecognizer extends PathRecognizer {

    @Override
    public Set<String> getSourcePathIds() {
        return Collections.singleton(CoffeeScriptClassPathProvider.SOURCE_CS);
    }

    @Override
    public Set<String> getLibraryPathIds() {
        return null;
    }

    @Override
    public Set<String> getBinaryLibraryPathIds() {
        return null;
    }

    @Override
    public Set<String> getMimeTypes() {
        return Collections.singleton(Constants.MIME_TYPE);
    }

}