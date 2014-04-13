/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.folding;

import coffeescript.nb.core.Constants;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldManagerFactory;

    
@MimeRegistration(mimeType= Constants.MIME_TYPE , service=FoldManagerFactory.class)
public class CoffeeScriptFoldingManagerFactory implements FoldManagerFactory {
    @Override
    public FoldManager createFoldManager() {
        return new CoffeeScriptFoldingManager();
    }
    
}
