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

import coffeescript.nb.core.CoffeeScriptCompiler;
import coffeescript.nb.core.CoffeeScriptUtils;
import coffeescript.nb.options.CoffeeScriptSettings;
import coffeescript.nb.parser.CoffeeScriptAutocompileContext;
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

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptAutocompileTask extends ParserResultTask<ParsingResult> {

    @Override
    public void run(ParsingResult t, SchedulerEvent se) {
        Snapshot snapshot = t.getSnapshot();
        if(!CoffeeScriptAutocompileContext.get().isEnabled(snapshot.getSource().getFileObject())) return;        
        CoffeeScriptCompiler.CompilerResult compilerResult;
        if(t.isLegacy()) {
            compilerResult = t.getCompilerResult();
        } else {
            CharSequence text = snapshot.getText();
            compilerResult = CoffeeScriptSettings.getCompiler().compile(text.toString(), CoffeeScriptSettings.get().isBare());
        }
        if ((compilerResult != null) && (compilerResult.getJs() != null)) {
            FileObject coffeeFile = snapshot.getSource().getFileObject();
            String js = compilerResult.getJs();
            CoffeeScriptUtils.writeJSForCoffeeScriptFile(js, coffeeFile);
        }
    }

    @Override
    public int getPriority() {
        return 10000;
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
            return Collections.singleton (new CoffeeScriptAutocompileTask());
        }
    }
}
