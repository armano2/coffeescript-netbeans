/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.completion;

import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.core.FileDescriptorMap;
import coffeescript.nb.indexing.CoffeeScriptIndex;
import coffeescript.nb.antlr.lexer.TokenEnumLexer;
import coffeescript.nb.core.CoffeeScriptTokenId;
import coffeescript.nb.core.Constants;
import coffeescript.nb.navigator.GrammarDescriptor;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Future;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.editor.NbEditorDocument;
import org.netbeans.modules.parsing.api.ParserManager;
import org.netbeans.modules.parsing.api.ResultIterator;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.api.UserTask;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.RequestProcessor;
import org.openide.util.Utilities;

/**
 *
 * @author milos
 */
@MimeRegistration(mimeType = Constants.MIME_TYPE, service = CompletionProvider.class)
public class CoffeeScriptCompletionProvider implements CompletionProvider {
    
    private final RequestProcessor parseRequestProcessor = new RequestProcessor("parse");
    private Future<List<GrammarDescriptor>> future;

    @Override
    public CompletionTask createTask(int queryType, JTextComponent jtc) {
        if(CoffeeScriptSettings.get().isLegacy()) return null;
        if (queryType != CompletionProvider.COMPLETION_QUERY_TYPE)
            return null;
        
        return new AsyncCompletionTask(new RuleNamesAsyncCompletionQuery(), jtc);
    }

    @Override
    public int getAutoQueryTypes(JTextComponent jtc, String string) {
        return 0;
    }
    
    private char[] getActiveLine(Document doc, int offset) throws BadLocationException {
        NbEditorDocument document = (NbEditorDocument) doc;
        Element lineElement = document.getParagraphElement(offset);
        int start = lineElement.getStartOffset();
        return doc.getText(start, (offset-start)).toCharArray();        
    }
    
    private int getFilterLength(char[] line) {
        int i = line.length;
        while(--i > -1){
            final char c = line[i];
            if(Character.isWhitespace(c) ||
                    c == ',' ||
                    c == '.'){
                return line.length - i - 1;
            }
        }
        return line.length;
    }
    
    private class RuleNamesAsyncCompletionQuery extends AsyncCompletionQuery {

        @Override
        protected void query(final CompletionResultSet resultSet, final Document doc, final int caretOffset) {            
            Operation op = new Operation(resultSet, doc, caretOffset);
            // Execute run making sure underlying doc model does change during execution
            doc.render(op);
        }
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
    class Operation implements Runnable {
        
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
            Collection<GrammarDescriptor> descriptors = getDescriptors();
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
        
        private Collection<GrammarDescriptor> getDescriptors() {
            Collection<Source> s = new HashSet<Source>();
            FileObject fileObject = Utilities.actionsGlobalContext().lookup(FileObject.class);
            Project p = FileOwnerQuery.getOwner(fileObject);
            FileDescriptorMap fileDescriptorMap = p.getLookup().lookup(FileDescriptorMap.class);
            
            
            for (FileObject fo : getRNGFiles()) {
                if(!fileDescriptorMap.isPresent(fo.getName()))
                    s.add(Source.create(fo));
            }
            try {
                ParserManager.parse(s, new UserTask() {
                    @Override
                    public void run(ResultIterator resultIterator) throws Exception {}
                });
            } catch (Exception e) {}
            Collection<GrammarDescriptor> descriptors = fileDescriptorMap.getGrammarDescriptors();
            return descriptors;            
        }
        
        private Collection<CompletionItem> getPossibleCompletionItems(Collection<GrammarDescriptor> descriptors, int startOffset, int caretOffset) { 
            Set<CompletionItem> items = new HashSet<CompletionItem>();
//            GrammarDescriptor grammarDescriptor = lookup.lookup(GrammarDescriptor.class);

            Collection<CoffeeScriptTokenId> keywordTokens = CoffeeScriptLanguage.getLanguage().tokenCategoryMembers(TokenEnumLexer.Category.KEYWORD_CAT.getName());
            
            for (CoffeeScriptTokenId keyword : keywordTokens) {
                items.add(new KeywordCompletionItem(keyword.getTokenString(), startOffset, caretOffset));
            }
//            for (GrammarDescriptor desc : descriptors) {
//                if(desc != null)
//                for (RuleDescriptor rule : desc.getRulesDescriptor().getUnifiedDescriptors()) {
//                    items.add(new IdentifierCompletionItem(rule.getRuleNameDescriptor(), desc.getFileName() , startOffset, caretOffset));
//                }
//            }

            return items;
        }

    }    
}
