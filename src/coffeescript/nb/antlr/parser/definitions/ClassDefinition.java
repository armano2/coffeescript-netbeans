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

import coffeescript.nb.completion.CompletionContext;
import coffeescript.nb.completion.items.CompletionItemsFactory;
import coffeescript.nb.indexing.IndexedDefinitionFactory;
import coffeescript.nb.navigator.nodes.ClassNode;
import coffeescript.nb.navigator.nodes.DefinitionChildren;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class ClassDefinition extends Definition {
    private final String parent;
    private final Collection<VariableDefinition> fields = new ArrayList<VariableDefinition>();
    private final Collection<MethodDefinition> methods = new ArrayList<MethodDefinition>();
    private final List<Definition> vars = new ArrayList<Definition>();
    private final Collection<MethodDefinition> constructors = new ArrayList<MethodDefinition>();

    public ClassDefinition(String text, int startOffset, int endOffset, int blockStartOffset, int blockEndOffset, String parent) {
        super(text, startOffset, endOffset, blockStartOffset, blockEndOffset, false);
        this.parent = parent;
    }

    public Collection<VariableDefinition> getFields() {
        return fields;
    }

    public Collection<MethodDefinition> getConstructors() {
        return constructors;
    }    

    public Collection<MethodDefinition> getMethods() {
        return methods;
    }
 
    public void addField(VariableDefinition def) {
        def.setClassName(text);
        fields.add(def);
        vars.add(def);
    }
    
    public void addMethod(MethodDefinition def) {
        def.setClassName(text);
        methods.add(def);
        if(!def.isAnonymous()) vars.add(def);
    }
    
    public void addConstructor(MethodDefinition def) {
        constructors.add(def);
        vars.add(def);
    }
    
    public void setConstructors(List<MethodDefinition> constructors) {
        this.constructors.addAll(constructors);
    }

    public String getParent() {
        return parent;
    }

    @Override
    public Node getNode(Lookup lookup) {
        return new ClassNode(this, new DefinitionChildren(vars, lookup), lookup);
    }

    @Override
    public Collection<CompletionItem> getCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
        return CompletionItemsFactory.create(this, startOffset, caretOffset, context);
    }   

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(IndexedDefinitionFactory.CLASS_PREFIX);
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(text);
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getStartOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getEndOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getBlockStartOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getBlockEndOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(parent);
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        for(Definition constructor : constructors) {
            sb.append(constructor.serialize());
            sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        }        
        return sb.toString();
    }
    
}
