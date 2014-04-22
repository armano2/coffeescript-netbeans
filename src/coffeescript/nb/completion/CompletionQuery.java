/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion;

import coffeescript.nb.completion.items.KeywordCompletionItem;
import coffeescript.nb.antlr.lexer.TokenEnumLexer;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.core.CoffeeScriptTokenId;
import coffeescript.nb.indexing.CoffeeScriptIndex;
import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.core.context.ICoffeeScriptMainContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Utilities;

/**
 *
 * @author Miloš Pensimus
 */
public class CompletionQuery extends AbstractCompletionQuery {
    private ICoffeeScriptMainContext completionContext;
    private boolean all;

    public CompletionQuery(boolean all) {
        this.all = all;
    }

    @Override
    protected void query(final CompletionResultSet resultSet, final Document doc, final int caretOffset) { 
        completionContext = Lookup.getDefault().lookup(ICoffeeScriptMainContext.class);
        Operation op = new Operation(resultSet, doc, caretOffset, getIndex());
        doc.render(op);
    }
    
    private CoffeeScriptIndex getIndex() {
        Project project = Utilities.actionsGlobalContext().lookup(Project.class);
        FileObject fileObject = Utilities.actionsGlobalContext().lookup(FileObject.class);
        if (project == null) {            
            if (fileObject != null) {
                project = FileOwnerQuery.getOwner(fileObject);
            }
        }     
        return CoffeeScriptIndex.create(project);
    }
    
    private class Operation implements Runnable {
        
        private CompletionResultSet resultSet; 
        private Document doc;
        private int caretOffset;
        private CoffeeScriptIndex index;

        public Operation(CompletionResultSet resultSet, Document doc, int caretOffset, CoffeeScriptIndex index) {
            this.resultSet = resultSet;
            this.doc = doc;
            this.caretOffset = caretOffset;
            this.index = index;
        }

        @Override
        public void run() {                   
            String filter = null;
            resultSet.setHasAdditionalItems(true);
            resultSet.setHasAdditionalItemsText("Wait for searching...");
            resultSet.setAnchorOffset(caretOffset);
            int filterLength = 0;
            try {
                filterLength = getFilterLength(getActiveLine(doc, caretOffset));
            } catch (BadLocationException ex) {
                Exceptions.printStackTrace(ex);
            }
            int startOffset = caretOffset - filterLength;
            if (filterLength > 0) {
                try {
                    filter = doc.getText(startOffset, caretOffset - startOffset);
                } catch (BadLocationException ex) {
                    ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
                }
            }
            if (filter != null) {
                for (CompletionItem rule : createCompletionItems(resultSet, startOffset, caretOffset)) {                                
                    if (rule.getInsertPrefix().toString().startsWith(filter)) {
                        resultSet.addItem(rule);
                    }
                }
            } else {
                resultSet.addAllItems(createCompletionItems(resultSet, startOffset, caretOffset));
            }
            resultSet.setAnchorOffset(caretOffset);
            resultSet.finish();
        }
        
        private Collection<CompletionItem> createCompletionItems(CompletionResultSet resultSet, int startOffset, int caretOffset) {
            FileObject thisFile = Utilities.actionsGlobalContext().lookup(FileObject.class);
            List<CompletionItem> items = new ArrayList<CompletionItem>();
            CoffeeScriptFileDefinition def = completionContext.getDefinition(thisFile);
            items.addAll(getKeywords(startOffset, caretOffset));
            if(all) {
                 items.addAll(getAllCompletionFields(thisFile, "", startOffset, caretOffset, all));
                 items.addAll(getAllCompletionClasses(thisFile, "", startOffset, caretOffset, all));
                 items.addAll(getAllCompletionMethods(thisFile, "", startOffset, caretOffset, all));
            } else {
                items.addAll(getLocalCompletionClasses(thisFile, "", startOffset, caretOffset, all));
                items.addAll(getLocalCompletionFields(thisFile, "", startOffset, caretOffset, all));
                items.addAll(getLocalCompletionMethods(thisFile, "", startOffset, caretOffset, all));
            }
            return items;
        }
        
        private Collection<CompletionItem> getLocalCompletionFields(FileObject thisFile, String fileName, int startOffset, int caretOffset,boolean decreasePriority) {
            Collection<CompletionItem> results = getVisibleCompletionItems(index.getAllFieldsInFile(thisFile), fileName, startOffset, caretOffset, decreasePriority);
            return results;
        }
        
        private Collection<CompletionItem> getAllCompletionFields(FileObject thisFile, String fileName, int startOffset, int caretOffset,boolean decreasePriority) {
            Collection results = getLocalCompletionFields(thisFile, fileName, startOffset, caretOffset, decreasePriority);
            results.addAll(getAllCompletionItems(index.getAllRootFieldsFromOtherFiles(thisFile), fileName, startOffset, caretOffset, decreasePriority));
            return results;
        }
        
        private Collection<CompletionItem> getLocalCompletionClasses(FileObject thisFile, String fileName, int startOffset, int caretOffset,boolean decreasePriority) {
            Collection<CompletionItem> results = getAllCompletionItems(index.getAllClassesInFile(thisFile), fileName, startOffset, caretOffset, decreasePriority);
            return results;
        }
        
        private Collection<CompletionItem> getAllCompletionClasses(FileObject thisFile, String fileName, int startOffset, int caretOffset,boolean decreasePriority) {
            Collection<CompletionItem> results = getLocalCompletionClasses(thisFile, fileName, startOffset, caretOffset, decreasePriority);
            results.addAll(getAllCompletionItems(index.getAllClassesFromOtherFiles(thisFile), fileName, startOffset, caretOffset, decreasePriority));
            return results;
        }
        
        private Collection<CompletionItem> getLocalCompletionMethods(FileObject thisFile, String fileName, int startOffset, int caretOffset,boolean decreasePriority) {
            Collection results = getVisibleCompletionItems(index.getAllMethodsInFile(thisFile), fileName, startOffset, caretOffset, decreasePriority);
            return results;
        }
        
        private Collection<CompletionItem> getAllCompletionMethods(FileObject thisFile, String fileName, int startOffset, int caretOffset,boolean decreasePriority) {
            Collection results = getLocalCompletionMethods(thisFile, fileName, startOffset, caretOffset, decreasePriority);
            results.addAll(getAllCompletionItems(index.getAllRootMethodsFromOtherFiles(thisFile), fileName, startOffset, caretOffset, decreasePriority));
            return results;
        }
        
        private Collection<CompletionItem> getVisibleCompletionItems(Collection<Definition> definitions, String fileName, int startOffset, int caretOffset,boolean decreasePriority) { 
            Set<CompletionItem> items = new HashSet<CompletionItem>();
            for (Definition def : definitions) {
                Collection<CompletionItem> itemsToAdd = def.getCompletionItems(fileName, startOffset, caretOffset, decreasePriority);
                if(def.visible(caretOffset)) items.addAll(itemsToAdd);
            }
            return items;
        }
        
        private Collection<CompletionItem> getAllCompletionItems(Collection<Definition> definitions, String fileName, int startOffset, int caretOffset,boolean decreasePriority) { 
            Set<CompletionItem> items = new HashSet<CompletionItem>();
            for (Definition def : definitions) {
                Collection<CompletionItem> itemsToAdd = def.getCompletionItems(fileName, startOffset, caretOffset, decreasePriority);
                items.addAll(itemsToAdd);
            }
            return items;
        }        
        
        private Collection<CompletionItem> getKeywords(int startOffset, int caretOffset) {
            Set<CompletionItem> items = new HashSet<CompletionItem>();
            Collection<CoffeeScriptTokenId> keywordTokens = CoffeeScriptLanguage.getLanguage().tokenCategoryMembers(TokenEnumLexer.Category.KEYWORD_CAT.getName());            
            for (CoffeeScriptTokenId keyword : keywordTokens) {
                if(!keyword.isLegacy()) items.add(new KeywordCompletionItem(keyword.getTokenString(), startOffset, caretOffset));
            }            
            return items;            
        }

    }    
    
}
