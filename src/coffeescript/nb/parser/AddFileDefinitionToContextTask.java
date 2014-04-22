/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.core.CoffeeScriptDataObject;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class AddFileDefinitionToContextTask extends ParserResultTask<ParsingResult> {

    public AddFileDefinitionToContextTask() {
    }

    @Override
    public void run(ParsingResult result, SchedulerEvent event) {
        FileObject fo = result.getSnapshot().getSource().getFileObject();
        Lookup lookup = fo.getLookup();
        CoffeeScriptDataObject dataObject = lookup.lookup(CoffeeScriptDataObject.class);
        if(dataObject != null) {
            dataObject.publishGrammarDescriptor(result.getGd());
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
}
