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

package coffeescript.nb.parser.tasks;

import coffeescript.nb.core.CoffeeScriptDataObject;
import coffeescript.nb.parser.ParsingResult;
import java.util.Collection;
import java.util.Collections;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.modules.parsing.spi.SchedulerTask;
import org.netbeans.modules.parsing.spi.TaskFactory;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class AddFileDefinitionToDataObjectTask extends ParserResultTask<ParsingResult> {

    public AddFileDefinitionToDataObjectTask() {
    }

    @Override
    public void run(ParsingResult result, SchedulerEvent event) {
        FileObject fo = result.getSnapshot().getSource().getFileObject();
        Lookup lookup = fo.getLookup();
        CoffeeScriptDataObject dataObject = lookup.lookup(CoffeeScriptDataObject.class);
        if(dataObject != null) {
            dataObject.publishFileDefinition(result.getFileDefinition());
        }
        
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass() {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    @Override
    public void cancel() {
    }
    
    public static class Factory extends TaskFactory {

        @Override
        public Collection<? extends SchedulerTask> create (Snapshot snapshot) {
            return Collections.singleton (new AddFileDefinitionToDataObjectTask());
        }
    }
}
