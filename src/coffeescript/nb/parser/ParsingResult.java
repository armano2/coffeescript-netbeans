/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.core.CoffeeScriptCompiler;
import coffeescript.nb.core.CoffeeScriptRhinoCompiler;
import java.util.Collections;
import java.util.List;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;


public class ParsingResult extends ParserResult {

    private CoffeeScriptFileDefinition gd;
    private List<ErrorDescription> errors;

    //Legacy
    private CoffeeScriptCompiler.CompilerResult compilerResult;
    private boolean legacy;

    public ParsingResult(Snapshot snapshot, CoffeeScriptFileDefinition gd, List<ErrorDescription> errors) {
        super(snapshot);
        this.gd = gd;
        this.errors = errors;
        this.legacy = false;
        gd.setFileName(snapshot.getSource().getFileObject().getNameExt());
    }

    public ParsingResult(Snapshot snapshot, CoffeeScriptFileDefinition gd, CoffeeScriptRhinoCompiler.CompilerResult result) {
        super(snapshot);
        this.gd = gd;
        if(result != null) {
            this.errors = (result.hasError()) ? 
                    Collections.singletonList(result.getError().getErrorDesription()) : 
                    Collections.<ErrorDescription>emptyList();
            this.compilerResult = result;
        }
        this.legacy = true;
    }

    @Override
    protected void invalidate() {
    }

    public CoffeeScriptFileDefinition getGd() {
        return gd;
    }      

    public List<ErrorDescription> getErrors() {
        return errors;
    }

    public CoffeeScriptCompiler.CompilerResult getCompilerResult() {
        return compilerResult;
    }

    public boolean isLegacy() {
        return legacy;
    }
    

    @Override
    public List<? extends org.netbeans.modules.csl.api.Error> getDiagnostics() {
        return Collections.emptyList();
    }
}
