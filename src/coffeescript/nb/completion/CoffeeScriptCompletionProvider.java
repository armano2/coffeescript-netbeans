/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.completion;

import coffeescript.nb.core.Constants;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;

/**
 *
 * @author milos
 */
@MimeRegistration(mimeType = Constants.MIME_TYPE, service = CompletionProvider.class)
public class CoffeeScriptCompletionProvider implements CompletionProvider {
    
    
       
    @Override
    public CompletionTask createTask(int queryType, JTextComponent jtc) {
        if (queryType != COMPLETION_QUERY_TYPE && queryType != COMPLETION_ALL_QUERY_TYPE)
            return null;
        
        boolean all = queryType == COMPLETION_ALL_QUERY_TYPE;
        return new AsyncCompletionTask(CompletionQueryFactory.createCompletionQuery(all), jtc);
    }

    @Override
    public int getAutoQueryTypes(JTextComponent jtc, String string) {
        return 0;
    }    
    
}
