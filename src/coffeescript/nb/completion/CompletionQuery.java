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
import coffeescript.nb.parser.ParsingResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.parsing.api.ParserManager;
import org.netbeans.modules.parsing.api.ResultIterator;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.api.UserTask;
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
       

    @Override
    protected void query(final CompletionResultSet resultSet, final Document doc, final int caretOffset) {            
        Operation op = new Operation(resultSet, doc, caretOffset);
        doc.render(op);
    }
    
    private Collection<FileObject> getRNGFiles() {
        Project project = Utilities.actionsGlobalContext().lookup(Project.class);
        if (project == null) {
            FileObject fileObject = Utilities.actionsGlobalContext().lookup(FileObject.class);
            if (fileObject != null) {
                project = FileOwnerQuery.getOwner(fileObject);
            }
        }
        if (project != null) {
            Collection<FileObject> rngFiles = new TreeSet<FileObject>(new Comparator<FileObject>() {

                public int compare(FileObject o1, FileObject o2) {
                    return o2.lastModified().compareTo(o1.lastModified());
                }
            });
            rngFiles.addAll(CoffeeScriptIndex.create(project).getAll());
            return rngFiles;
        } else {
            return Collections.emptyList();
        }
    }
    private class Operation implements Runnable {
        
        private CompletionResultSet resultSet; 
        private Document doc;
        private int caretOffset;

        public Operation(CompletionResultSet resultSet, Document doc, int caretOffset) {
            this.resultSet = resultSet;
            this.doc = doc;
            this.caretOffset = caretOffset;
        }

        @Override
        public void run() {                   
            String filter = null;
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
            Collection<CoffeeScriptFileDefinition> descriptors = getDescriptors();
            if (filter != null) {
                for (CompletionItem rule : getPossibleCompletionItems(descriptors, startOffset, caretOffset)) {                                
                    if (rule.getInsertPrefix().toString().startsWith(filter)) {
                        resultSet.addItem(rule);
                    }
                }
            } else {
                resultSet.addAllItems(getPossibleCompletionItems(descriptors, startOffset, caretOffset));
            }
            // Position of dialog
            resultSet.setAnchorOffset(caretOffset);
            resultSet.finish();
        }
        
        private Collection<CoffeeScriptFileDefinition> getDescriptors() {
            
            final ICoffeeScriptMainContext completionContext = Lookup.getDefault().lookup(ICoffeeScriptMainContext.class);
            Collection<Source> s = new HashSet<Source>();
            final List<CoffeeScriptFileDefinition> descriptors = new ArrayList<CoffeeScriptFileDefinition>();
            
            for (FileObject fo : getRNGFiles()) {
                if(completionContext.hasDefinition(fo)) {
                    descriptors.add(completionContext.getDefinition(fo));
                } else {
                    s.add(Source.create(fo));
                }
                
            }
            try {
                ParserManager.parse(s, new UserTask() {
                    @Override
                    public void run(ResultIterator resultIterator) throws Exception {
                        ParsingResult r = (ParsingResult) resultIterator.getParserResult();                        
                        if(r != null) {
                            FileObject fo = r.getSnapshot().getSource().getFileObject();
                            completionContext.addDefinition(fo, r.getGd());
                            descriptors.add(r.getGd());
                        }
                    }
                });
            } catch (Exception e) {
                Exceptions.printStackTrace(e);            
            }
            return descriptors;            
        }
        
        private Collection<CompletionItem> getPossibleCompletionItems(Collection<CoffeeScriptFileDefinition> descriptors, int startOffset, int caretOffset) { 
            Set<CompletionItem> items = new HashSet<CompletionItem>();

            Collection<CoffeeScriptTokenId> keywordTokens = CoffeeScriptLanguage.getLanguage().tokenCategoryMembers(TokenEnumLexer.Category.KEYWORD_CAT.getName());
            
            for (CoffeeScriptTokenId keyword : keywordTokens) {
                if(!keyword.isLegacy()) items.add(new KeywordCompletionItem(keyword.getTokenString(), startOffset, caretOffset));
            }
            for (CoffeeScriptFileDefinition desc : descriptors) {
                if(desc != null)
                for (Definition def : desc.getVariables()) {
                    items.add(def.getCompletionItem(desc.getFileName(), startOffset, caretOffset));
                }
            }

            return items;
        }

    }    
    
}
