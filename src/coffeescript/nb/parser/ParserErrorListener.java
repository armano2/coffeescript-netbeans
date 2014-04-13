package coffeescript.nb.parser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class ParserErrorListener extends BaseErrorListener {
    private final List<ErrorDescription> ERRORS = new ArrayList<ErrorDescription>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        ERRORS.add(new ErrorDescription(line,charPositionInLine,msg));
    }
    
    public void addSingleError(int line, int charPositionInLine,
                            String msg)
    {
        ERRORS.add(new ErrorDescription(line,charPositionInLine,msg));
    }

    public List<ErrorDescription> getErrors() {
        return ERRORS;
    }
}