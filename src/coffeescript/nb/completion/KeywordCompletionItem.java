/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion;

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
public class KeywordCompletionItem implements CompletionItem  {

    private static final ImageIcon RULE_ICON = ImageUtilities.loadImageIcon(Constants.COFFEE_SCRIPT_ICON, true);
    private final String ruleName;
    private int startOffset;
    private int carretOffset;

    public KeywordCompletionItem(String keywordName, int startOffset, int carretOffset) {
        this.ruleName = keywordName;
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
    public int getPreferredWidth(Graphics g, Font defaultFont) {
        return CompletionUtilities.getPreferredWidth(this.ruleName, "Keyword", g, defaultFont);
    }

    @Override
    public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        CompletionUtilities.renderHtml(RULE_ICON, this.ruleName, "Keyword", g, defaultFont, (selected ? Color.WHITE : defaultColor), width, height, selected);
    }

    @Override
    public CompletionTask createDocumentationTask() {
        return null;
    }

    @Override
    public CompletionTask createToolTipTask() {
        return null;
    }

    @Override
    public boolean instantSubstitution(JTextComponent component) {
        substitue(component, null);
        Completion.get().hideAll();
        return true;
    }

    @Override
    public int getSortPriority() {
        return 100;
    }

    @Override
    public CharSequence getSortText() {
        return getRuleName();
    }

    @Override
    public CharSequence getInsertPrefix() {
        return getRuleName();
    }

    public String getRuleName() {
        return this.ruleName;
    }

    private void substitue(final JTextComponent component, final String toAdd) {
        final StyledDocument doc = (StyledDocument) component.getDocument();

        class AtomicChange implements Runnable {

            @Override
            public void run() {
                String value = ruleName;

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
}