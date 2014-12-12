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

import coffeescript.nb.parser.ErrorDefinition;
import coffeescript.nb.parser.ParsingResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.text.Document;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.modules.parsing.spi.SchedulerTask;
import org.netbeans.modules.parsing.spi.TaskFactory;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.util.Exceptions;

public class SyntaxErrorsHighlightingTask extends ParserResultTask<ParsingResult> {

    public SyntaxErrorsHighlightingTask() {
    }

    @Override
    public void run(ParsingResult result, SchedulerEvent event) {
        try {
            Document document = result.getSnapshot ().getSource ().getDocument (false);
            List<ErrorDescription> errors = new ArrayList<ErrorDescription> ();
            for (ErrorDefinition d : result.getErrors()) {
                ErrorDescription errorDescription = ErrorDescriptionFactory
                        .createErrorDescription(
                        Severity.ERROR,
                        d.getMsg(),
                        document,
                        d.getLine());
                errors.add(errorDescription);
            }
            HintsController.setErrors(document, "coffeescript", errors);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
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
            return Collections.singleton (new SyntaxErrorsHighlightingTask ());
        }
    }
}