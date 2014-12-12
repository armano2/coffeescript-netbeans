package coffeescript.nb.antlr.parser;
import coffeescript.nb.parser.ErrorDefinition;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author Miloš Pensimus
 */

public class ParserErrorListener extends BaseErrorListener {
    private final List<ErrorDefinition> ERRORS = new ArrayList<ErrorDefinition>();

    @Override
    public <T extends Token> void syntaxError(Recognizer<T, ?> recognizer, T offendingSymbol, 
            int line, int charPositionInLine, String msg, RecognitionException e) {
        ERRORS.add(new ErrorDefinition(line,charPositionInLine,msg));
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }

    
    public void addSingleError(int line, int charPositionInLine,
                            String msg) {
        ERRORS.add(new ErrorDefinition(line,charPositionInLine,msg));
    }
    
    public void addErrors(List<ErrorDefinition> errors) {
        ERRORS.addAll(errors);
    }

    public List<ErrorDefinition> getErrors() {
        return ERRORS;
    }
}