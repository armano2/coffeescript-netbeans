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

import coffeescript.nb.core.CoffeeScriptDataObject;
import java.util.Collections;
import org.netbeans.modules.parsing.api.ParserManager;
import org.netbeans.modules.parsing.api.ResultIterator;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.api.UserTask;
import org.netbeans.modules.parsing.spi.ParseException;
import org.openide.util.Exceptions;

/**
 *
 * @author Miloš Pensimus
 */
public class ParseTask implements Runnable {
    CoffeeScriptDataObject dataObject;

    public ParseTask(CoffeeScriptDataObject dataObject) {
        this.dataObject = dataObject;
    }

    @Override
    public void run() {
        Source source = Source.create(dataObject.getPrimaryFile());
        try {
            ParserManager.parse(Collections.singleton(source), new UserTask() {

                @Override
                public void run(ResultIterator resultIterator) throws Exception {
                    ParsingResult parseResult = (ParsingResult) resultIterator.getParserResult();
                    if(parseResult != null) {
                        dataObject.publishFileDefinition(parseResult.getFileDefinition());
                    }
                        
                }
            });
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
