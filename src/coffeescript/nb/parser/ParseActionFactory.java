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

package coffeescript.nb.parser;

import coffeescript.nb.options.CoffeeScriptSettings;
import java.util.concurrent.Callable;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;

/**
 *
 * @author Miloš Pensimus
 */
public class ParseActionFactory {
    
    public static Callable<ParserResult> createParseAction(Snapshot snapshot) {
        return CoffeeScriptSettings.get().isLegacy() ? new ParseActionLegacy(snapshot) : new ParseAction(snapshot);
    }
    
}
