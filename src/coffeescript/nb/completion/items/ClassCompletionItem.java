/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion.items;

import coffeescript.nb.core.Constants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Miloš Pensimus
 */
public class ClassCompletionItem extends IdentifierCompletionItem {

    public ClassCompletionItem(String text, String fileName, int startOffset, int carretOffset) {
        super(text, fileName, startOffset, carretOffset);
    }
    
    

    @Override
    public int getSortPriority() {
        return 10;
    }

    @Override
    protected ImageIcon getIcon() {
        return ImageUtilities.loadImageIcon(Constants.CLASS_ICON, true);
    }
    
    

    
}
