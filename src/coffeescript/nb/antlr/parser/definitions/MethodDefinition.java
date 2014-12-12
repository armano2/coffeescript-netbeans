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
import coffeescript.nb.navigator.nodes.MethodNode;
import java.util.Collection;
import java.util.List;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class MethodDefinition extends Definition {
    
    private final List<Definition> params;
    private final boolean anonymous;
    private String className;

    public MethodDefinition(String text, int startOffset, int endOffset, int blockStartOffset, int blockEndOffset, boolean classMember, List<Definition> params, boolean anonymous) {
        super(text, startOffset, endOffset, blockStartOffset, blockEndOffset, classMember);
        this.params = params;
        this.anonymous = anonymous;    
    }

    public List<Definition> getParams() {
        return params;
    }
    
    public String getParamString() {
        if(params.isEmpty()) return "()";
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Definition param : params) {
            VariableDefinition def = (VariableDefinition) param;
            if(def.isClassMember()) sb.append("@");
            sb.append(def.getText());
            if(def.isSplat()) sb.append("...");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }    

    public boolean isAnonymous() {
        return anonymous;
    }    

    @Override
    public Node getNode(Lookup lookup) {
        return new MethodNode(this, lookup);
    }

    @Override
    public Collection<CompletionItem> getCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
        return CompletionItemsFactory.create(this, startOffset, caretOffset, context);
    }    

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(IndexedDefinitionFactory.METHOD_PREFIX);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(text);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getStartOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getEndOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getBlockStartOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getBlockEndOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(classMember);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(anonymous);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(className);   
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        for(Definition d : params) {
            sb.append(d.serialize());
            sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        }        
         
        return sb.toString();
    }

}
