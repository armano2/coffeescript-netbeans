/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion.items;

import coffeescript.nb.core.Constants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.openide.ErrorManager;
import org.openide.text.NbDocument;
import org.openide.util.ImageUtilities;

/**
 *
 * @author milos
 */
public class KeywordCompletionItem extends AbstractCompletionItem  {

    public KeywordCompletionItem(String keywordName, int startOffset, int carretOffset) {
        super(keywordName, startOffset, carretOffset);
    }

    @Override
    public int getSortPriority() {
        return 100;
    }

    @Override
    protected ImageIcon getIcon() {
        return ImageUtilities.loadImageIcon(Constants.COFFEE_SCRIPT_ICON, true);
    }

    @Override
    protected String getLeftText() {
        return text;
    }

    @Override
    protected String getRightText() {
        return "Keyword";
    }    
    
}