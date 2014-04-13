/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2010 Sun Microsystems, Inc.
 */
package coffeescript.nb.completion;

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
 * @author jeanmarc
 */
public class IdentifierCompletionItem implements CompletionItem  {

    private static final ImageIcon RULE_ICON = ImageUtilities.loadImageIcon("coffeescript/nb/resources/fields.png", true);
    private final String ruleName;
    private int startOffset;
    private int carretOffset;
    private String fileName;

    public IdentifierCompletionItem(String name, String fileName, int startOffset, int carretOffset) {
        this.ruleName = name;
        this.fileName = fileName;
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
        return CompletionUtilities.getPreferredWidth(this.ruleName, fileName, g, defaultFont);
    }

    @Override
    public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        CompletionUtilities.renderHtml(RULE_ICON, this.ruleName, fileName, g, defaultFont, (selected ? Color.WHITE : defaultColor), width, height, selected);
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
        return 10;
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
                String value = IdentifierCompletionItem.this.getRuleName();

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
