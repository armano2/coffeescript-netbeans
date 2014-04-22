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
package coffeescript.nb.hyperlink;

import coffeescript.nb.lexer.TokenEnumLexer;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.core.CoffeeScriptUtils;
import coffeescript.nb.core.Constants;
import coffeescript.nb.lexer.LexUtilities;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.lib.editor.hyperlink.spi.HyperlinkProvider;
import org.netbeans.modules.editor.NbEditorDocument;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.LineCookie;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author INCH Team
 */
@MimeRegistration(mimeType = Constants.MIME_TYPE, service = HyperlinkProvider.class)
public class CoffeeScriptHyperlinkProvider implements HyperlinkProvider {

    private Document lastDocument;
    private LexUtilities.TokenInfo tokenInfo = null;

    @Override
    public boolean isHyperlinkPoint(Document doc, int caretOffset) {

        if (!(doc instanceof NbEditorDocument) || CoffeeScriptSettings.get().isLegacy()) {
            return false;
        }

        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        JTextComponent target = org.netbeans.editor.Utilities.getFocusedComponent();

        // We want to work only with the open editor and
        // the editor has to be the active component:
        if ((target == null) || (target.getDocument() != editorDocument)) {
            return false;
        }
        
        lastDocument = editorDocument;
        this.tokenInfo = LexUtilities.getTokenAtOffset(doc, caretOffset, TokenEnumLexer.IDENTIFIER_LEG);
        if(tokenInfo == null)
            this.tokenInfo = LexUtilities.getTokenAtOffset(doc, caretOffset, TokenEnumLexer.FIELD_LEG);
        return ((tokenInfo != null) && (tokenInfo.getStart() > -1) && (tokenInfo.getEnd() >= tokenInfo.getStart()));
    }

    @Override
    public int[] getHyperlinkSpan(Document doc, int caretOffset) {
        if (!(doc instanceof NbEditorDocument)) {
            return null;
        }

        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        JTextComponent target = org.netbeans.editor.Utilities.getFocusedComponent();

        // We want to work only with the open editor and
        // the editor has to be the active component:
        if ((target == null) || (lastDocument != editorDocument) || (tokenInfo == null)) {
            return null;
        }

        return new int[]{tokenInfo.getStart(), tokenInfo.getEnd()};
    }

    @Override
    public void performClickAction(Document doc, int caretOffset) {
        if (!(doc instanceof NbEditorDocument)) {
            return;
        }

        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        JTextComponent target = org.netbeans.editor.Utilities.getFocusedComponent();

        // We want to work only with the open editor and
        // the editor has to be the active component:
        if ((target == null) || (lastDocument != editorDocument)) {
            return;
        }
        if(tokenInfo.getTokenEnum().equals(TokenEnumLexer.IDENTIFIER_LEG)) {
            Definition definition = CoffeeScriptUtils.findForDefinition(caretOffset, tokenInfo.getText());
            DataObject dataObject = null;
            if (definition != null) {   
                try {
                    dataObject = DataObject.find(definition.getFileObject());
                } catch (DataObjectNotFoundException ex) {
                    Exceptions.printStackTrace(ex);
                }
                goToDefinition(dataObject, definition);
            }
        }
        
        if(tokenInfo.getTokenEnum().equals(TokenEnumLexer.FIELD_LEG)) {
            Definition definition = CoffeeScriptUtils.findForClassMember(caretOffset, tokenInfo.getText().substring(1));
            DataObject dataObject = null;
            if (definition != null) {   
                try {
                    dataObject = DataObject.find(definition.getFileObject());
                } catch (DataObjectNotFoundException ex) {
                    Exceptions.printStackTrace(ex);
                }
                goToDefinition(dataObject, definition);
            }
        }
        
    }
    
    private void goToDefinition(DataObject dataObject, Definition definition) {
        if(dataObject == null) return;
        Lookup lookup = dataObject.getLookup();
        LineCookie cookie = lookup.lookup(LineCookie.class);
        EditorCookie editor = lookup.lookup(EditorCookie.class);
        try {
            StyledDocument doc = editor.openDocument();
            if (cookie == null) {
                return;
            }

            int line = NbDocument.findLineNumber(doc, definition.getStartOffset());
            int column = NbDocument.findLineColumn(doc, definition.getStartOffset());

            Line l = cookie.getLineSet().getCurrent(line);
            l.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
        } catch (IndexOutOfBoundsException ex) {
            Toolkit.getDefaultToolkit().beep();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
