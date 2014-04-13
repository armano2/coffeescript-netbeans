// Copyright 2011 Denis Stepanov
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

import java.util.Collection;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.swing.event.ChangeListener;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.ParseException;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.ParserFactory;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.openide.util.RequestProcessor;

/**
 * 
 * @author Denis Stepanov
 */
public class CoffeeScriptParser extends Parser {

    private static final RequestProcessor PARSER_TASK = new RequestProcessor(CoffeeScriptParser.class.getName(), 1);
    private Future<ParserResult> future;

    public void parse(final Snapshot snapshot, Task task, SourceModificationEvent event) throws ParseException {
        future = PARSER_TASK.submit(ParseActionFactory.createParseAction(snapshot));
    }

    public Result getResult(Task task) throws ParseException {
        try {
            return future.get(60, TimeUnit.SECONDS);
        } catch (Exception ex) {
            ex.printStackTrace();
        } // Ignore
        future.cancel(true);
        return null;
    }

    public void addChangeListener(ChangeListener changeListener) {
    }

    public void removeChangeListener(ChangeListener changeListener) {
    }

    public static class Factory extends ParserFactory {

        public Parser createParser(Collection<Snapshot> snapshots) {
            return new CoffeeScriptParser();
        }
    }


}
