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

package coffeescript.nb.lexer;

import coffeescript.nb.core.CoffeeScriptTokenId;
import coffeescript.nb.core.CoffeeScriptLanguage;
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
 * @author Miloš Pensimus
 */
public class LexUtilities {

    @SuppressWarnings("unchecked")
    public static TokenSequence<CoffeeScriptTokenId> getTokenSequence(Document doc, int offset) {
        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        
        editorDocument.readLock();
        TokenHierarchy<Document> th = TokenHierarchy.get(doc);
        TokenSequence<CoffeeScriptTokenId> ts = (th == null ? null : th.tokenSequence(CoffeeScriptTokenId.getLanguage()));
        if (ts == null) {
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
        editorDocument.readUnlock();
        return ts;
    }

    public static TokenInfo getTokenAtOffset(Document doc, int caretOffset, TokenEnumLexer tokenEnum) {
        int tokenOrdinal = CoffeeScriptLanguage.getToken(tokenEnum).ordinal();
        TokenInfo tokenInfo = null;
        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        
        editorDocument.readLock();
        TokenSequence<CoffeeScriptTokenId> ts = LexUtilities.getTokenSequence(editorDocument, caretOffset);
        if (ts != null) {
            
            Token<CoffeeScriptTokenId> token = getTokenAtCaret(ts, caretOffset);
            if(token == null) return null;
            
            CoffeeScriptTokenId id = token.id();
            if (id.ordinal() == tokenOrdinal) {
                CharSequence text = token.text();
                tokenInfo = new TokenInfo(ts.offset(), ts.offset() + text.length(), text.toString(), tokenEnum);
            }
        }
        editorDocument.readUnlock();

        return tokenInfo;
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
        private final TokenEnumLexer tokenEnum;

        public TokenInfo(int start, int end, String text, TokenEnumLexer tokenEnum) {
            this.start = start;
            this.end = end;
            this.text = text;
            this.tokenEnum = tokenEnum;
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
        
        public int getLength() {
            return end - start;
        }

        public TokenEnumLexer getTokenEnum() {
            return tokenEnum;
        }
        
        
    }
}





