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

package coffeescript.nb.completion.items;

import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import coffeescript.nb.completion.CompletionContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.netbeans.spi.editor.completion.CompletionItem;

/**
 *
 * @author Miloš Pensimus
 */
public class CompletionItemsFactory {
    private static final String thisPrefix = "@"; //NOI18N
    
    public static Collection<CompletionItem> create(ClassDefinition def,int  startOffset, int caretOffset, CompletionContext context) {
        if(def.getText() == null)  return Collections.emptyList();
        int priorityDecrementor = 0;
        if(def.getFileObject().equals(context.getFile())) priorityDecrementor++;
        switch (context.getType()) {
            case NEW_INSTANCE:
                Collection<CompletionItem> items = new ArrayList<CompletionItem>();
                for(MethodDefinition constructor : def.getConstructors()) {
                    if(!constructor.getParams().isEmpty())
                        items.add(new ClassCompletionItem(def.getText() + constructor.getParamString(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor, constructor.getParams()));
                }
                items.add(new ClassCompletionItem(def.getText(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor, Collections.<Definition>emptyList()));
                return items;
            case EXTENDS:
            case DEFAULT:
                return Collections.<CompletionItem>singletonList(new ClassCompletionItem(def.getText(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor, Collections.<Definition>emptyList()));
        }
        return Collections.emptyList();
    }
    
    public static Collection<CompletionItem> create(MethodDefinition def, int  startOffset, int caretOffset, CompletionContext context) {
        if(def.isAnonymous()) return Collections.emptyList();
        int priorityDecrementor = 0;
        boolean optionalAt = false;
        StringBuilder visibleText = new StringBuilder();
        if(def.getFileObject().equals(context.getFile())) {
            priorityDecrementor++;
            if(def.isClassMember() && def.visible(caretOffset)) {
                optionalAt = true;
            }
        }
        visibleText.append(def.getText()).append(def.getParamString());
        switch (context.getType()) {
            case DEFAULT:
                if(optionalAt) visibleText.insert(0, thisPrefix);
                return Collections.<CompletionItem>singletonList(new MethodCompletionItem(visibleText.toString(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor));
            case CLASS_ONLY_ACCESSOR:
                if(def.isClassMember() && def.getClassName().equals(context.getClassName()))
                    return Collections.<CompletionItem>singletonList(new MethodCompletionItem(visibleText.toString(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor));
                break;
            case NORMAL_ACCESSOR:
                return Collections.<CompletionItem>singletonList(new MethodCompletionItem(visibleText.toString(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor));
        }
        return Collections.emptyList();
    }
    
    public static Collection<CompletionItem> create(VariableDefinition def, int  startOffset, int caretOffset, CompletionContext context) {
        int priorityDecrementor = 0;
        StringBuilder visibleText = new StringBuilder();
        if(def.getFileObject().equals(context.getFile())) priorityDecrementor++;
        if(def.isMethodParam()) priorityDecrementor++;
        visibleText.append(def.getText());
        switch (context.getType()) {
            case DEFAULT:
                if(def.isClassMember()) visibleText.insert(0, thisPrefix);
                return Collections.<CompletionItem>singletonList(new FieldCompletionItem(visibleText.toString(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor, def.isMethodParam(), def.isProtectedField(), def.isClassMember()));
            case CLASS_ONLY_ACCESSOR:
                if(def.isClassMember() && def.getClassName().equals(context.getClassName()))
                    return Collections.<CompletionItem>singletonList(new FieldCompletionItem(visibleText.toString(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor, def.isMethodParam(), def.isProtectedField(), def.isClassMember()));
                break;
            case NORMAL_ACCESSOR:            
                return Collections.<CompletionItem>singletonList(new FieldCompletionItem(visibleText.toString(), def.getText(), def.getFileObject().getNameExt(), startOffset, caretOffset, priorityDecrementor, def.isMethodParam(), def.isProtectedField(), def.isClassMember()));
        }
        return Collections.emptyList();
    }
    
    
    

}
