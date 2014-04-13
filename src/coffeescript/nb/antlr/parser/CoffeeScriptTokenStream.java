/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser;

import coffeescript.lexer.CoffeeScriptNativeToken;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;


/**
 *
 * @author milos
 */
public class CoffeeScriptTokenStream implements TokenStream {
    
    private TokenFactory<CommonToken> factory;
    private Map<TokenEnumParser, Integer> tokenTypeMap;
    private int p=-1;
    private Lexer lex;
    private Map<Integer,Token> m;
    private List<CoffeeScriptNativeToken> tokens;

    public CoffeeScriptTokenStream(List<CoffeeScriptNativeToken> tokens, Map<TokenEnumParser, Integer> tokenTypeMap) {
        
        this.tokens = tokens;
        this.tokenTypeMap = tokenTypeMap;
        this.factory = new CommonTokenFactory();
        this.lex = new EmptyLexer();
        this.m = new HashMap<Integer, Token>();
        
    }
    
    protected int adjustSeekIndex(int i) {
        return i;
    }

    protected final void lazyInit() {
        if (p == -1) {
        setup();
        }
    }

    protected void setup() {
        p = adjustSeekIndex(0);
    }
    
    

    public Token LT(int k) {
        lazyInit();
        if ( k==0 ) return null;
        if ( k < 0 ) return LB(-k);

        int i = p + k - 1;
        if ( i >= size() ) { // return EOF token
            // EOF must be last token
            return factory.create(-1, "");
        }
        return getToken(i);

    }
    
    protected Token LB(int k) {
        if ( (p-k)<0 ) return null;
        return getToken(p-k);
    }

    public Token get(int i) {
        if ( i < 0 || i >= size() ) {
            throw new IndexOutOfBoundsException("token index "+i+" out of range 0.."+(size()-1));
        }
        return getToken(i);
    }

    public TokenSource getTokenSource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getText(Interval intrvl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getText(RuleContext rc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getText(Token token, Token token1) {
        lazyInit();
        return token.getText();
    }

    public void consume() {
        p = adjustSeekIndex(p + 1);
    }

    public int LA(int i) {
        return LT(i).getType();
    }

    public int mark() {
        return 0;
    }

    public void release(int i) {        
    }

    public int index() {
        return p;
    }

    public void seek(int i) {
        lazyInit();
        p = adjustSeekIndex(i);
    }

    public int size() {
        return tokens.size()+1;
    }

    public String getSourceName() {
        return null;
    }
    
    protected Token getToken(int pointer) {
        if(p>=size()-1) return factory.create(new Pair<TokenSource, CharStream>(lex, null),-1 , "", 0, 0 ,0, 1, 1);
        CoffeeScriptNativeToken nativeToken = tokens.get(pointer);
        TokenEnumParser tag = TokenEnumParser.tokenValue(String.valueOf(nativeToken.getTag()));
        String text = String.valueOf(nativeToken.getValue());
        lex.setLine(nativeToken.getFirstLine());
        lex.setCharPositionInLine(nativeToken.getFirstColumn());
        if (m.containsKey(pointer)) return m.get(pointer);
        if (!tokenTypeMap.containsKey(tag)) tag = TokenEnumParser.BAD_TOKEN;
        Token t = factory.create(new Pair<TokenSource, CharStream>(lex, null),tokenTypeMap.get(tag) , text, 0, 0 ,0, nativeToken.getFirstLine()+1, nativeToken.getFirstColumn()+1);
        m.put(pointer, t);
        return t;  
               
    }
    
    private class EmptyLexer extends Lexer {

        int positionInLine;
        int line;

        @Override
        public String[] getRuleNames() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getGrammarFileName() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ATN getATN() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setLine(int line) {
            this.line = line; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getLine() {
            return line; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getCharPositionInLine() {
            return positionInLine;
        }

        @Override
        public void setCharPositionInLine(int charPositionInLine) {
            this.positionInLine = charPositionInLine;
        }  
    }
    
}
