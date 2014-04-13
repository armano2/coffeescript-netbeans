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
package coffeescript.nb.core;

import coffeescript.nb.parser.ParseActionLegacy;
import coffeescript.nb.parser.CoffeeScriptParser;
import java.util.Collection;
import java.util.Collections;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.modules.parsing.spi.SchedulerTask;
import org.netbeans.modules.parsing.spi.TaskFactory;
import org.openide.filesystems.FileObject;

/**
 * @author Denis Stepanov
 */
public class CoffeeScriptAutocompileTask extends ParserResultTask<ParseActionLegacy.ParsingResult> {

    public void run(final ParseActionLegacy.ParsingResult result, SchedulerEvent event) {
        if ((result != null) && !CoffeeScriptAutocompileContext.get().isEnabled(result.getSnapshot().getSource().getFileObject())) {
            return;
        }
        if ((result != null) && (result.getCompilerResult() != null) && (result.getCompilerResult().getJs() != null)) {
            FileObject coffeeFile = result.getSnapshot().getSource().getFileObject();
            String js = result.getCompilerResult().getJs();
            CoffeeScriptUtils.writeJSForCoffeeScriptFile(js, coffeeFile);
        }
    }

    public int getPriority() {
        return 10000;
    }

    public Class<? extends Scheduler> getSchedulerClass() {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    public void cancel() {
    }

    public static class Factory extends TaskFactory {

        public Collection<? extends SchedulerTask> create(Snapshot snapshot) {
            return Collections.singleton(new CoffeeScriptAutocompileTask());
        }
    }
}
