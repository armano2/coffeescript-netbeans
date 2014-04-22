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
public class FieldCompletionItem extends IdentifierCompletionItem {
    
    private final boolean methodParam;
    private final boolean protectedField;

    public FieldCompletionItem(String text, String fileName, int startOffset, int carretOffset, boolean decreasePriority, boolean methodParam, boolean protectedField) {
        super(text, fileName, startOffset, carretOffset, decreasePriority);
        this.methodParam = methodParam;
        this.protectedField = protectedField;
    }
    
    @Override
    protected ImageIcon getIcon() {
        if(methodParam)
            return ImageUtilities.loadImageIcon(Constants.CONSTANT_ICON, true);
        if(protectedField)
            return ImageUtilities.loadImageIcon(Constants.FIELD_PRIVATE_ICON, true);
        return ImageUtilities.loadImageIcon(Constants.FIELD_ICON, true);
    }
    
}
