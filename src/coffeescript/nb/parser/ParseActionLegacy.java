/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.core.CoffeeScriptCompiler;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.core.CoffeeScriptRhinoCompiler;
import coffeescript.nb.core.Constants;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.csl.api.Severity;
import org.netbeans.modules.csl.spi.DefaultError;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.openide.text.NbDocument;

/**
 *
 * @author milos
 */
public class ParseActionLegacy implements Callable<ParserResult> {
    private final Snapshot snapshot;   
    private Result res;

    public ParseActionLegacy(Snapshot snapshot) {
        this.snapshot = snapshot;
        this.res = res;
    }    
    
    public ParsingResult call() throws Exception {
        CharSequence text = snapshot.getText();
        CoffeeScriptCompiler.CompilerResult compilerResult = CoffeeScriptSettings.getCompiler().compile(text.toString(), CoffeeScriptSettings.get().isBare());
        return new ParsingResult(snapshot, compilerResult);
    }
    
    
    public static class ParsingResult extends ParserResult {
        private CoffeeScriptRhinoCompiler.CompilerResult compilerResult;

        public ParsingResult(Snapshot snapshot, CoffeeScriptRhinoCompiler.CompilerResult compilerResult) {
            super(snapshot);
            this.compilerResult = compilerResult;
        }

        public CoffeeScriptCompiler.CompilerResult getCompilerResult() {
            return compilerResult;
        }

        @Override
        public List<? extends org.netbeans.modules.csl.api.Error> getDiagnostics() {
            if ((compilerResult != null) && (compilerResult.getError() != null)) {
                CoffeeScriptCompiler.Error error = compilerResult.getError();
                int line = error.getLine() == -1 ? 0 : error.getLine();
                String msg = error.getLine() == -1 ? error.getMessage() : error.getErrorName();
                StyledDocument doc = (StyledDocument) getSnapshot().getSource().getDocument(true);
                if (!getSnapshot().getMimePath().getMimeType(0).equals(Constants.MIME_TYPE)) {
                    int originalOffset = getSnapshot().getOriginalOffset(0);
                    line += NbDocument.findLineNumber(doc, originalOffset);
                }
                int lineOffset = NbDocument.findLineOffset(doc, Math.max(0, line - 1));
                int offsetError = getSnapshot().getEmbeddedOffset(lineOffset + error.getColumn());
                if (offsetError >= getSnapshot().getText().length()) {
                    offsetError = lineOffset;
                }
                return Collections.singletonList(DefaultError.createDefaultError(
                        "cs.key", msg, "", getSnapshot().getSource().getFileObject(),
                        offsetError, -1, true, Severity.ERROR));
            }
            return Collections.emptyList();
        }

        protected void invalidate() {
    //            compilerResult = null;
        }
    }
    
}


