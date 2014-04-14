/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion;

import coffeescript.nb.completion.items.KeywordCompletionItem;
import coffeescript.nb.antlr.lexer.TokenEnumLexer;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.core.CoffeeScriptTokenId;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.openide.ErrorManager;
import org.openide.util.Exceptions;

/**
 *
 * @author Miloš Pensimus
 */
public class CompletionQueryLegacy extends AbstractCompletionQuery {       

    @Override
    protected void query(final CompletionResultSet resultSet, final Document doc, final int caretOffset) {            
        Operation op = new Operation(resultSet, doc, caretOffset);
        doc.render(op);
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
            if (filter != null) {
                for (CompletionItem rule : getPossibleCompletionItems(startOffset, caretOffset)) {                                
                    if (rule.getInsertPrefix().toString().startsWith(filter)) {
                        resultSet.addItem(rule);
                    }
                }
            } else {
                resultSet.addAllItems(getPossibleCompletionItems(startOffset, caretOffset));
            }
            // Position of dialog
            resultSet.setAnchorOffset(caretOffset);
            resultSet.finish();
        }
        
        private Collection<CompletionItem> getPossibleCompletionItems(int startOffset, int caretOffset) { 
            Set<CompletionItem> items = new HashSet<CompletionItem>();

            Collection<CoffeeScriptTokenId> keywordTokens = CoffeeScriptLanguage.getLanguage().tokenCategoryMembers(TokenEnumLexer.Category.KEYWORD_CAT.getName());
            
            for (CoffeeScriptTokenId keyword : keywordTokens) {
                if(!keyword.isLegacy()) items.add(new KeywordCompletionItem(keyword.getTokenString(), startOffset, caretOffset));
            }

            return items;
        }

    }    
    
}
