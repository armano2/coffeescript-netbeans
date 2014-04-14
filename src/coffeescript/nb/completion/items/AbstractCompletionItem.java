/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion.items;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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

/**
 *
 * @author Miloš Pensimus
 */
public abstract class AbstractCompletionItem implements CompletionItem {
    protected final String text;
    protected int startOffset;
    protected int carretOffset;

    public AbstractCompletionItem(String text, int startOffset, int carretOffset) {
        this.text = text;
        this.startOffset = startOffset;
        this.carretOffset = carretOffset;
    }    
    
    @Override
    public void defaultAction(JTextComponent component) {
        substitue(component, null);
        Completion.get().hideAll();
    }

    @Override
    public void processKeyEvent(KeyEvent evt) {
    }

    @Override
    public boolean instantSubstitution(JTextComponent component) {
        substitue(component, null);
        Completion.get().hideAll();
        return true;
    }

    @Override
    public CharSequence getSortText() {
        return text != null ? text : "";
    }

    @Override
    public CharSequence getInsertPrefix() {
        return text != null ? text : "";
    }

    public CompletionTask createDocumentationTask() {
        return null;
    }

    public CompletionTask createToolTipTask() {
        return null;
    }
    
    protected void substitue(final JTextComponent component, final String toAdd) {
        final StyledDocument doc = (StyledDocument) component.getDocument();

        class AtomicChange implements Runnable {

            @Override
            public void run() {
                String value = text;

                if (toAdd != null) {
                    value += toAdd;
                }
                try {
                    doc.remove(startOffset, carretOffset - startOffset);
                    doc.insertString(startOffset, value, null);
                    component.setCaretPosition(startOffset + value.length());
                } catch (BadLocationException ex) {
                    ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
                }
            }
        }

        try {
            NbDocument.runAtomicAsUser(doc, new AtomicChange());
        } catch (BadLocationException ex) {
            ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, ex);
        }
    }
    
    protected String getLeftDescription(String desc) {
        return "<html>" + (getLeftBold() ? "<b>" : "") + 
                "<font color=#" + getLeftColor() + ">" + desc + 
                "</font>" + (getLeftBold() ? "</b>" : "") + 
                "</html>";
    }
    
    protected String getRightDescription(String desc) {
        return "<html>" + (getRightBold() ? "<b>" : "") + 
                "<font color=#" + getRightColor() + ">" + desc + 
                "</font>" + (getRightBold() ? "</b>" : "") + 
                "</html>";
    }
    
    @Override public abstract int getSortPriority();
    
    @Override 
    public int getPreferredWidth(Graphics g, Font defaultFont) {
        return CompletionUtilities.getPreferredWidth(getLeftDescription(getLeftText()), getRightDescription(getRightText()), g, defaultFont);
    }
    
    @Override 
    public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        CompletionUtilities.renderHtml(getIcon(), getLeftDescription(getLeftText()), getRightDescription(getRightText()), g, defaultFont, (selected ? Color.WHITE : defaultColor), width, height, selected);
    }
    protected abstract ImageIcon getIcon();
    protected boolean getLeftBold() {
        return false;
    };
    
    protected boolean getRightBold() {
        return false;
    };
    
    protected String getLeftColor() {
        return "000000";
    };
    
    protected String getRightColor() {
        return "000000";
    };
    
    protected abstract String getLeftText();
    protected abstract String getRightText();
    
}
