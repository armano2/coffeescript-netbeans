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

package coffeescript.nb.antlr.parser.definitions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptFileDefinition {
    
    private String fileName;
    private final Collection<ClassDefinition> rootClasses = new ArrayList<ClassDefinition>();
    private final Collection<VariableDefinition> rootFields = new ArrayList<VariableDefinition>();
    private final Collection<MethodDefinition> rootMethods = new ArrayList<MethodDefinition>();
    private final Collection<VariableDefinition> fields = new ArrayList<VariableDefinition>();
    private final Collection<MethodDefinition> methods = new ArrayList<MethodDefinition>();
    
    private final List<Definition> rootDefinitions = new ArrayList<Definition>();

    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Collection<VariableDefinition> getFields() {
        return fields;
    }

    public void setFields(Collection<VariableDefinition> fields) {
        this.fields.addAll(fields);
    }

    public Collection<MethodDefinition> getMethods() {
        return methods;
    }

    public void setMethods(Collection<MethodDefinition> methods) {
        this.methods.addAll(methods);
    } 

    public Collection<ClassDefinition> getRootClasses() {
        return rootClasses;
    }

    public void setRootClasses(Collection<ClassDefinition> rootClasses) {
        this.rootClasses.addAll(rootClasses);
        rootDefinitions.addAll(rootClasses);
    }

    public Collection<VariableDefinition> getRootFields() {
        return rootFields;
    }

    public void setRootFields(Collection<VariableDefinition> rootFields) {
        this.rootFields.addAll(rootFields);
        rootDefinitions.addAll(rootFields);
    }

    public Collection<MethodDefinition> getRootMethods() {
        return rootMethods;
    }

    public void setRootMethods(Collection<MethodDefinition> rootMethods) {
        this.rootMethods.addAll(rootMethods);
        for(MethodDefinition def : rootMethods) {
            if(!def.isAnonymous()) rootDefinitions.add(def);
        }
        
    }
    
    public List<Definition> getRootDefinitions() {
        return rootDefinitions;
    }
}
