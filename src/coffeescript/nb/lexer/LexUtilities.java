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
package coffeescript.nb.lexer;

import coffeescript.nb.core.CoffeeScriptTokenId;
import coffeescript.nb.antlr.lexer.TokenEnumLexer;
import java.util.List;
import javax.swing.text.Document;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenId;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.editor.NbEditorDocument;



/**
 *
 * @author jeanmarc
 */
public class LexUtilities {

    @SuppressWarnings("unchecked")
    public static TokenSequence<CoffeeScriptTokenId> getTokenSequence(Document doc, int offset) {
        TokenHierarchy<Document> th = TokenHierarchy.get(doc);
        TokenSequence<CoffeeScriptTokenId> ts = (th == null ? null : th.tokenSequence(CoffeeScriptTokenId.getLanguage()));
        if (ts == null) {
            // Possibly an embedding scenario such as an RHTML file
            // First try with backward bias true
            List<TokenSequence<?>> list = th.embeddedTokenSequences(offset, true);
            for (TokenSequence<? extends TokenId> t : list) {
                if (t.language() == CoffeeScriptTokenId.getLanguage()) {
                    ts = (TokenSequence<CoffeeScriptTokenId>) t;
                    break;
                }
            }
            if (ts == null) {
                list = th.embeddedTokenSequences(offset, false);
                for (TokenSequence<? extends TokenId> t : list) {
                    if (t.language() == CoffeeScriptTokenId.getLanguage()) {
                        ts = (TokenSequence<CoffeeScriptTokenId>) t;
                        break;
                    }
                }
            }
        }
        return ts;
    }

    public static int getTokenStartOffset(Document doc, int caretOffset, int tokenOrdinal) {
        TokenInfo tokenInfo = getTokenAtOffset(doc, caretOffset, tokenOrdinal);
        return tokenInfo==null?-1:tokenInfo.getStart();
    }

    public static TokenInfo getTokenAtOffset(Document doc, int caretOffset, int tokenOrdinal) {
        TokenInfo tokenInfo = null;
        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        
        editorDocument.readLock();
        TokenSequence<CoffeeScriptTokenId> ts = LexUtilities.getTokenSequence(editorDocument, caretOffset);
        if (ts != null) {
            
            Token<CoffeeScriptTokenId> token = getTokenAtCaret(ts, caretOffset);
            CoffeeScriptTokenId id = token.id();
            if (id.ordinal() == tokenOrdinal) {
                CharSequence text = token.text();
                tokenInfo = new TokenInfo(ts.offset(), ts.offset() + text.length(), text.toString());
            }
        }
        editorDocument.readUnlock();

        return tokenInfo;
    }
    
    public static boolean isTokenPresentBackwards(Document doc, int caretOffset, int tokenOrdinal, int backwardsTokensCount) {
        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        
        editorDocument.readLock();
        TokenSequence<CoffeeScriptTokenId> ts = LexUtilities.getTokenSequence(editorDocument, caretOffset);
        if (ts != null) {
            
            getTokenAtCaret(ts, caretOffset);
            while (backwardsTokensCount-- != 0) {
                if(!ts.movePrevious()) return false;
                CoffeeScriptTokenId id = ts.token().id();
                if (id.ordinal() == tokenOrdinal) {
                    return true;
                }                
            }
            
        }
        editorDocument.readUnlock();
        return false;
    }
    
    public static OffsetRange findFwd(BaseDocument doc, TokenSequence<CoffeeScriptTokenId> ts, TokenEnumLexer up,
        TokenEnumLexer down) {
        int balance = 0;

        while (ts.moveNext()) {
            Token<CoffeeScriptTokenId> token = ts.token();
            CoffeeScriptTokenId id = token.id();

            if (id.getTokenEnum().equals(up)) {
                balance++;
            } else if (id.getTokenEnum().equals(down)) {
                if (balance == 0) {
                    return new OffsetRange(ts.offset(), ts.offset() + token.length());
                }

                balance--;
            }
        }

        return OffsetRange.NONE;
    }
    
    public static OffsetRange findBwd(BaseDocument doc, TokenSequence<CoffeeScriptTokenId> ts, TokenEnumLexer up,
        TokenEnumLexer down) {
        int balance = 0;

        while (ts.movePrevious()) {
            Token<CoffeeScriptTokenId> token = ts.token();
            CoffeeScriptTokenId id = token.id();

            if (id.getTokenEnum().equals(up)) {
                if (balance == 0) {
                    return new OffsetRange(ts.offset(), ts.offset() + token.length());
                }

                balance++;
            } else if (id.getTokenEnum().equals(down)) {
                balance--;
            }
        }

        return OffsetRange.NONE;
    }
    
    private static Token<CoffeeScriptTokenId> getTokenAtCaret(TokenSequence ts, int caretOffset) {
        int index = ts.move(caretOffset);
        if (index >= 0) {
            ts.moveNext();
        } else if (caretOffset == 0) {
            ts.moveStart();
            ts.moveNext();
        } else {
            ts.movePrevious();
        }
        return ts.token();
    }

    public static class TokenInfo {

        private final int start;
        private final int end;
        private final String text;

        public TokenInfo(int start, int end, String text) {
            this.start = start;
            this.end = end;
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}





