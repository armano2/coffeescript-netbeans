/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion.items;

import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import coffeescript.nb.core.Constants;
import java.util.Collection;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Miloš Pensimus
 */
public class ClassCompletionItem extends IdentifierCompletionItem {
    
    Collection<Definition> params;

    public ClassCompletionItem(String text, String fileName, int startOffset, int carretOffset, boolean decreasePriority, Collection<Definition> params) {
        super(text, fileName, startOffset, carretOffset, decreasePriority);
        this.params = params;
    }   

    @Override
    protected ImageIcon getIcon() {
        return ImageUtilities.loadImageIcon(Constants.CLASS_ICON, true);
    }
    
}
