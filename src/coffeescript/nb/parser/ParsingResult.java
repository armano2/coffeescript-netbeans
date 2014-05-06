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

package coffeescript.nb.parser;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.core.CoffeeScriptCompiler;
import coffeescript.nb.core.CoffeeScriptRhinoCompiler;
import java.util.Collections;
import java.util.List;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;

/**
 * 
 * @author Miloš Pensimus
 */

public class ParsingResult extends ParserResult {

    private final CoffeeScriptFileDefinition fileDefinition;
    private List<ErrorDefinition> errors;
    private CoffeeScriptCompiler.CompilerResult compilerResult;
    private final boolean legacy;

    public ParsingResult(Snapshot snapshot, CoffeeScriptFileDefinition fileDefinition, List<ErrorDefinition> errors) {
        super(snapshot);
        this.fileDefinition = fileDefinition;
        this.errors = errors;
        fileDefinition.setFileName(snapshot.getSource().getFileObject().getNameExt());
        this.legacy = false;
    }

    public ParsingResult(Snapshot snapshot, CoffeeScriptFileDefinition fileDefinition, CoffeeScriptRhinoCompiler.CompilerResult result) {
        super(snapshot);
        this.fileDefinition = fileDefinition;
        fileDefinition.setFileName(snapshot.getSource().getFileObject().getNameExt());
        if(result != null) {
            this.errors = (result.hasError()) ? 
                    Collections.singletonList(result.getError().getErrorDefinition()) : 
                    Collections.<ErrorDefinition>emptyList();
            this.compilerResult = result;
        }
        this.legacy = true;
    }

    @Override
    protected void invalidate() {
    }

    public CoffeeScriptFileDefinition getFileDefinition() {
        return fileDefinition;
    }      

    public List<ErrorDefinition> getErrors() {
        return errors;
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean isLegacy() {
        return legacy;
    }
    
    public CoffeeScriptCompiler.CompilerResult getCompilerResult() {
        return compilerResult;
    }   

    @Override
    public List<? extends org.netbeans.modules.csl.api.Error> getDiagnostics() {
        return Collections.emptyList();
    }
}
