/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion.items;

import coffeescript.nb.core.Constants;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Miloš Pensimus
 */
public class MethodCompletionItem extends IdentifierCompletionItem {

    public MethodCompletionItem(String text, String fileName, int startOffset, int carretOffset) {
        super(text, fileName, startOffset, carretOffset);
    }    

    @Override
    public int getSortPriority() {
        return 10;
    }

    @Override
    protected ImageIcon getIcon() {
        return ImageUtilities.loadImageIcon(Constants.METHOD_ICON, true);
    }
    
}
