package coffeescript.nb.parser;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Document;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;

public class SyntaxErrorsHighlightingTask extends ParserResultTask<ParsingResult> {

    public SyntaxErrorsHighlightingTask() {
    }

    @Override
    public void run(ParsingResult result, SchedulerEvent event) {
        try {
            Document document = result.getSnapshot ().getSource ().getDocument (false);
            List<ErrorDescription> errors = new ArrayList<ErrorDescription> ();
            for (coffeescript.nb.parser.ErrorDescription d : result.getErrors()) {
                ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription(
                        Severity.ERROR,
                        d.getMsg(),
                        document,
                        d.getLine());
                errors.add(errorDescription);
            }
            HintsController.setErrors(document, "coffeescript", errors);
        } catch (Exception ex) {
            ex.printStackTrace();
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