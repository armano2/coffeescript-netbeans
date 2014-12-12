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


package coffeescript.nb.completion;

import coffeescript.nb.completion.items.KeywordCompletionItem;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.indexing.CoffeeScriptIndex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.Document;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.openide.filesystems.FileObject;
import org.openide.util.Utilities;
import static coffeescript.nb.completion.CompletionContext.ContextType.*;
import coffeescript.nb.core.CoffeeScriptUtils;
import org.netbeans.modules.editor.NbEditorUtilities;

/**
 *
 * @author Miloš Pensimus
 */
public class CompletionQuery extends AbstractCompletionQuery {
    private boolean all;

    public CompletionQuery(boolean all) {
        this.all = all;
    }

    @Override
    public void query(final CompletionResultSet resultSet, final Document doc, final int caretOffset) { 
        CompletionOperationImpl op = new CompletionOperationImpl(resultSet, doc, caretOffset);
        doc.render(op);
    }

    private class CompletionOperationImpl extends CompletionOperation {
        
        private final CoffeeScriptIndex index;

        public CompletionOperationImpl(CompletionResultSet resultSet, Document doc, int caretOffset) {
            super(resultSet, doc, caretOffset);
            this.index = CoffeeScriptUtils.getIndex();
        }

        @Override
        public void run() {  
            FileObject thisFile = NbEditorUtilities.getFileObject(doc);
            CompletionContext context = getCompletionContext(doc, caretOffset, thisFile, index);
            int startOffset = caretOffset - context.getFilter().length();
            resultSet.setHasAdditionalItems(true);
            resultSet.setHasAdditionalItemsText("For completion proposals from other files:"); //NOI18N
            resultSet.setAnchorOffset(caretOffset);

            if (!context.getFilter().isEmpty()) {
                for (CompletionItem item : createCompletionItems(startOffset, caretOffset, context)) {                                
                    if (item.getInsertPrefix().toString().toLowerCase().startsWith(context.getFilter())) {
                        resultSet.addItem(item);
                    }
                }
            } else {
                resultSet.addAllItems(createCompletionItems(startOffset, caretOffset, context));
            }
            resultSet.finish();
        }
        
        @Override
        protected Collection<CompletionItem> createCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
            
            switch (context.getType()) {
                case NEW_INSTANCE:
                case EXTENDS:
                    return createAllClassCompletionItems(startOffset, caretOffset, context);
                case NORMAL_ACCESSOR:
                    return createNormalAccessorCompletionItems(startOffset, caretOffset, context);
                case CLASS_ONLY_ACCESSOR:
                    return createClassOnlyAccessorCompletionItems(startOffset, caretOffset, context);
                case DEFAULT:
                    return createDefaultCompletionItems(startOffset, caretOffset, context);
            }            
            return Collections.emptyList();
        }
        
        //--------------
        
        private Collection<CompletionItem> createAllClassCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
            List<CompletionItem> items = new ArrayList<CompletionItem>();
            items.addAll(getAllCompletionClasses(context.getFile(), startOffset, caretOffset, context));
            return items;
        }
       
        private Collection<CompletionItem> createNormalAccessorCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
            List<CompletionItem> items = new ArrayList<CompletionItem>();
            items.addAll(getAllCompletionFields(context.getFile(), startOffset, caretOffset, context));
            items.addAll(getAllCompletionMethods(context.getFile(), startOffset, caretOffset, context));

            return items;
        }
        
        private Collection<CompletionItem> createClassOnlyAccessorCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
            List<CompletionItem> items = new ArrayList<CompletionItem>();
            items.addAll(getAllClassCompletionMethods(context.getFile(), startOffset, caretOffset, context));
            items.addAll(getAllClassCompletionFields(context.getFile(), startOffset, caretOffset, context));
            return items;
        } 
        
        private Collection<CompletionItem> createDefaultCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
            List<CompletionItem> items = new ArrayList<CompletionItem>();
            items.addAll(getKeywords(startOffset, caretOffset));
            if(all) {
                 items.addAll(getAllCompletionFields(context.getFile(), startOffset, caretOffset, context));
                 items.addAll(getAllCompletionClasses(context.getFile(), startOffset, caretOffset, context));
                 items.addAll(getAllCompletionMethods(context.getFile(), startOffset, caretOffset, context));
            } else {
                items.addAll(getLocalCompletionClasses(context.getFile(), startOffset, caretOffset, context));
                items.addAll(getLocalCompletionFields(context.getFile(), startOffset, caretOffset, context));
                items.addAll(getLocalCompletionMethods(context.getFile(), startOffset, caretOffset, context));
            }
            return items;
        } 
        
        //---------------------
        
        private Collection<CompletionItem> getLocalCompletionFields(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection<CompletionItem> results = getVisibleCompletionItems(index.getAllFieldsInFile(thisFile), startOffset, caretOffset, context);
            return results;
        }
        
        private Collection<CompletionItem> getAllCompletionFields(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection results = getLocalCompletionFields(thisFile, startOffset, caretOffset, context);
            results.addAll(getAllCompletionItems(index.getAllRootFieldsFromOtherFiles(thisFile), startOffset, caretOffset, context));
            return results;
        }
        
        private Collection<CompletionItem> getLocalCompletionClasses(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection<CompletionItem> results = getAllCompletionItems(index.getAllClassesInFile(thisFile), startOffset, caretOffset, context);
            return results;
        }
        
        private Collection<CompletionItem> getAllCompletionClasses(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection<CompletionItem> results = getLocalCompletionClasses(thisFile, startOffset, caretOffset, context);
            results.addAll(getAllCompletionItems(index.getAllClassesFromOtherFiles(thisFile), startOffset, caretOffset, context));
            return results;
        }
        
        private Collection<CompletionItem> getLocalCompletionMethods(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection results = getVisibleCompletionItems(index.getAllMethodsInFile(thisFile), startOffset, caretOffset, context);
            return results;
        }
        
        private Collection<CompletionItem> getAllCompletionMethods(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection results = getLocalCompletionMethods(thisFile, startOffset, caretOffset, context);
            results.addAll(getAllCompletionItems(index.getAllRootMethodsFromOtherFiles(thisFile), startOffset, caretOffset, context));
            return results;
        }
        
        private Collection<CompletionItem> getKeywords(int startOffset, int caretOffset) {
            Set<CompletionItem> items = new HashSet<CompletionItem>();
            for (String keyword : CoffeeScriptLanguage.getKeywords()) {
                items.add(new KeywordCompletionItem(keyword, startOffset, caretOffset));
            }        
            return items;            
        }
        
        private Collection<CompletionItem> getAllClassCompletionMethods(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection results = getAllCompletionItems(index.getAllClassMethods(thisFile), startOffset, caretOffset, context);
            return results;
        }
        
        private Collection<CompletionItem> getAllClassCompletionFields(FileObject thisFile, int startOffset, int caretOffset, CompletionContext context) {
            Collection results = getAllCompletionItems(index.getAllClassFields(thisFile), startOffset, caretOffset, context);
            return results;
        }
        
        //----------------------
        
        private Collection<CompletionItem> getVisibleCompletionItems(Collection<Definition> definitions, int startOffset, int caretOffset, CompletionContext context) { 
            Set<CompletionItem> items = new HashSet<CompletionItem>();
            for (Definition def : definitions) {
                Collection<CompletionItem> itemsToAdd = def.getCompletionItems(startOffset, caretOffset, context);
                if(def.visible(caretOffset)) items.addAll(itemsToAdd);
            }
            return items;
        }
        
        private Collection<CompletionItem> getAllCompletionItems(Collection<Definition> definitions, int startOffset, int caretOffset, CompletionContext context) { 
            Set<CompletionItem> items = new HashSet<CompletionItem>();
            for (Definition def : definitions) {
                Collection<CompletionItem> itemsToAdd = def.getCompletionItems(startOffset, caretOffset, context);
                items.addAll(itemsToAdd);
            }
            return items;
        }        
        
    }    
    
}
