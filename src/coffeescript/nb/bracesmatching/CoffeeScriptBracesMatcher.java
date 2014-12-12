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

package coffeescript.nb.bracesmatching;

import coffeescript.nb.core.CoffeeScriptTokenId;
import coffeescript.nb.lexer.LexUtilities;
import coffeescript.nb.lexer.TokenEnumLexer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.editor.BaseDocument;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.netbeans.spi.editor.bracesmatching.MatcherContext;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptBracesMatcher implements BracesMatcher {
    
    MatcherContext context;

    public CoffeeScriptBracesMatcher (MatcherContext context) {
        this.context = context;
    }
    
    @Override
    public int[] findOrigin() throws InterruptedException, BadLocationException {
        ((AbstractDocument) context.getDocument()).readLock();
        try {
            BaseDocument doc = (BaseDocument) context.getDocument();

            int offset = context.getSearchOffset();

            TokenSequence<CoffeeScriptTokenId> ts = LexUtilities.getTokenSequence(doc, offset);

            if (ts != null) {
                ts.move(offset);

                if (!ts.moveNext()) {
                    return null;
                }

                Token<CoffeeScriptTokenId> token = ts.token();

                if (token == null) {
                    return null;
                }
                
                CoffeeScriptTokenId id = token.id();
                
                if (id.getTokenEnum() == TokenEnumLexer.LPAREN) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RPAREN) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACE) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACE) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACKET) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACKET) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                }
            }
            return null;
        } finally {
            ((AbstractDocument) context.getDocument()).readUnlock();
        }
    }

    @Override
    public int[] findMatches() throws InterruptedException, BadLocationException {
        ((AbstractDocument) context.getDocument()).readLock();
        try {
            BaseDocument doc = (BaseDocument) context.getDocument();
            
            int offset = context.getSearchOffset();

            TokenSequence<CoffeeScriptTokenId> ts = LexUtilities.getTokenSequence(doc, offset);

            if (ts != null) {
                ts.move(offset);

                if (!ts.moveNext()) {
                    return null;
                }

                Token<CoffeeScriptTokenId> token = ts.token();

                if (token == null) {
                    return null;
                }
                
                CoffeeScriptTokenId id = token.id();
                
                OffsetRange r;
                if (id.getTokenEnum() == TokenEnumLexer.LPAREN) {
                    r = LexUtilities.findFwd(doc, ts, TokenEnumLexer.LPAREN, TokenEnumLexer.RPAREN);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RPAREN) {
                    r = LexUtilities.findBwd(doc, ts, TokenEnumLexer.LPAREN, TokenEnumLexer.RPAREN);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACKET) {
                    r = LexUtilities.findFwd(doc, ts, TokenEnumLexer.LBRACKET, TokenEnumLexer.RBRACKET);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACKET) {
                    r = LexUtilities.findBwd(doc, ts, TokenEnumLexer.LBRACKET, TokenEnumLexer.RBRACKET);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACE) {
                    r = LexUtilities.findFwd(doc, ts, TokenEnumLexer.LBRACE, TokenEnumLexer.RBRACE);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACE) {
                    r = LexUtilities.findBwd(doc, ts, TokenEnumLexer.LBRACE, TokenEnumLexer.RBRACE);
                    return new int [] {r.getStart(), r.getEnd() };
                }
            }
            return null;
        } finally {
            ((AbstractDocument) context.getDocument()).readUnlock();
        }
    }
    
    public static class Factory implements BracesMatcherFactory {

        @Override
        public CoffeeScriptBracesMatcher createMatcher(MatcherContext context) {
            return new CoffeeScriptBracesMatcher(context);
        }
    }
    
}
