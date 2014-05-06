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
import coffeescript.nb.navigator.nodes.VariableNode;
import java.util.Collection;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class VariableDefinition extends Definition {
    
    private boolean splat;
    private final boolean methodParam;
    private final boolean protectedField;
    private String className;
    
    public VariableDefinition(String text, int startOffset, int endOffset, int blockStartOffset,
            int blockEndOffset, boolean classMember, boolean splat, boolean methodParam, boolean protectedField) {
        super(text, startOffset, endOffset, blockStartOffset, blockEndOffset, classMember);
        this.splat = splat;
        this.methodParam = methodParam;
        this.protectedField = protectedField;
    }    

    public boolean isSplat() {
        return splat;
    }

    public boolean isMethodParam() {
        return methodParam;
    }

    public boolean isProtectedField() {
        return protectedField;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }    

    public void setSplat(boolean splat) {
        this.splat = splat;
    }

    @Override
    public Node getNode(Lookup lookup) {
        return new VariableNode(this,lookup);
    }

    @Override
    public Collection<CompletionItem> getCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
        return CompletionItemsFactory.create(this, startOffset, caretOffset, context);
    }   

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(IndexedDefinitionFactory.FIELD_PREFIX);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(text);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getStartOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getEndOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getBlockStartOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getBlockEndOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(classMember);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(splat);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(methodParam);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(protectedField);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);        
        sb.append(className); 
        return sb.toString();
    }

   
}
