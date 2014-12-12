// Copyright 2014 Miloš Pensimus
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package coffeescript.nb.completion.items;

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

/**
 *
 * @author Miloš Pensimus
 */
public abstract class AbstractCompletionItem implements CompletionItem {
    protected final String visibleText;
    protected final String simpleText;
    protected int startOffset;
    protected int carretOffset;

    public AbstractCompletionItem(String visibleText, String simpleText, int startOffset, int carretOffset) {
        this.visibleText = visibleText;
        this.simpleText = simpleText;
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
        return simpleText != null ? simpleText : "";
    }

    @Override
    public CharSequence getInsertPrefix() {
        return simpleText != null ? simpleText : "";
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
                String value = visibleText;

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
                "</html>"; //NOI18N
    }
    
    protected String getRightDescription(String desc) {
        return "<html>" + (getRightBold() ? "<b>" : "") + 
                "<font color=#" + getRightColor() + ">" + desc + 
                "</font>" + (getRightBold() ? "</b>" : "") + 
                "</html>"; //NOI18N
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
    
    protected boolean getLeftBold() {
        return false;
    };
    
    protected boolean getRightBold() {
        return false;
    };
    
    protected String getLeftColor() {
        return "000000"; //NOI18N
    };
    
    protected String getRightColor() {
        return "000000"; //NOI18N
    };
    
    protected abstract ImageIcon getIcon();
    protected abstract String getLeftText();
    protected abstract String getRightText();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.visibleText != null ? this.visibleText.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractCompletionItem other = (AbstractCompletionItem) obj;
        if ((this.visibleText == null) ? (other.visibleText != null) : !this.visibleText.equals(other.visibleText)) {
            return false;
        }
        return true;
    }
    
    
    
}
