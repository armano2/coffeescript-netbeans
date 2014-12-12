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

package coffeescript.nb.hyperlink;

import coffeescript.nb.lexer.TokenEnumLexer;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.core.CoffeeScriptUtils;
import coffeescript.nb.core.Constants;
import coffeescript.nb.lexer.LexUtilities;
import coffeescript.nb.options.CoffeeScriptSettings;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.lib.editor.hyperlink.spi.HyperlinkProvider;
import org.netbeans.modules.editor.NbEditorDocument;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;

/**
 *
 * @author Miloš Pensimus
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
        this.tokenInfo = LexUtilities.getTokenAtOffset(doc, caretOffset, TokenEnumLexer.IDENTIFIER);
        if(tokenInfo == null)
            this.tokenInfo = LexUtilities.getTokenAtOffset(doc, caretOffset, TokenEnumLexer.FIELD);
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
        
        Definition definition = null;
        if(tokenInfo.getTokenEnum().equals(TokenEnumLexer.IDENTIFIER)) {
            definition = CoffeeScriptUtils.findForDefinition(caretOffset, tokenInfo.getText());            
        } else if(tokenInfo.getTokenEnum().equals(TokenEnumLexer.FIELD)) {
            definition = CoffeeScriptUtils.findForClassMember(caretOffset, tokenInfo.getText().substring(1));
        }
        
        if (definition != null) {   
            DataObject dataObject = null;
            try {
                dataObject = DataObject.find(definition.getFileObject());
            } catch (DataObjectNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            }
            if(dataObject != null) {
                CoffeeScriptUtils.jumpToDefinition(dataObject.getLookup(), definition);
            }                
        }
        
    }
}
