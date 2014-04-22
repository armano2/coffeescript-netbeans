/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.lexer;

import coffeescript.nb.core.CoffeeScriptTokenId;
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
 * @author milos
 */
public class CoffeeScriptBracesMatcher implements BracesMatcher {
    
    MatcherContext context;

    public CoffeeScriptBracesMatcher (MatcherContext context) {
        this.context = context;
    }

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
                
                if (id.getTokenEnum() == TokenEnumLexer.LPAREN_LEG) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RPAREN_LEG) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACE_LEG) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACE_LEG) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACKET_LEG) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACKET_LEG) {
                    return new int [] { ts.offset(), ts.offset() + token.length() };
                }
            }
            return null;
        } finally {
            ((AbstractDocument) context.getDocument()).readUnlock();
        }
    }

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
                if (id.getTokenEnum() == TokenEnumLexer.LPAREN_LEG) {
                    r = LexUtilities.findFwd(doc, ts, TokenEnumLexer.LPAREN_LEG, TokenEnumLexer.RPAREN_LEG);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RPAREN_LEG) {
                    r = LexUtilities.findBwd(doc, ts, TokenEnumLexer.LPAREN_LEG, TokenEnumLexer.RPAREN_LEG);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACKET_LEG) {
                    r = LexUtilities.findFwd(doc, ts, TokenEnumLexer.LBRACKET_LEG, TokenEnumLexer.RBRACKET_LEG);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACKET_LEG) {
                    r = LexUtilities.findBwd(doc, ts, TokenEnumLexer.LBRACKET_LEG, TokenEnumLexer.RBRACKET_LEG);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.LBRACE_LEG) {
                    r = LexUtilities.findFwd(doc, ts, TokenEnumLexer.LBRACE_LEG, TokenEnumLexer.RBRACE_LEG);
                    return new int [] {r.getStart(), r.getEnd() };
                } else if (id.getTokenEnum() == TokenEnumLexer.RBRACE_LEG) {
                    r = LexUtilities.findBwd(doc, ts, TokenEnumLexer.LBRACE_LEG, TokenEnumLexer.RBRACE_LEG);
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
