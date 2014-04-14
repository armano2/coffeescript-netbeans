/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.lexer;

import coffeescript.nb.core.CoffeeScriptTokenId;
import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.antlr.lexer.AntlrCharStream;
import coffeescript.nb.antlr.lexer.CoffeeScriptLexerGrammar;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.modules.editor.NbEditorDocument;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.netbeans.spi.lexer.MutableTextInput;
import org.openide.cookies.EditorCookie;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.RequestProcessor;
import org.openide.util.Utilities;

/**
 *
 * @author milos
 */
public class CoffeeScriptLexer implements Lexer<CoffeeScriptTokenId>{

    private LexerRestartInfo<CoffeeScriptTokenId> info;
    private CoffeeScriptLexerGrammar lexer;
    private Queue<TokenInfo> queue;
    private boolean prevOutdent = false;
    private DocumentListener listener;
    
    private static final RequestProcessor RP = new RequestProcessor(CoffeeScriptLexer.class.getName(),
            1, false, false);

    public CoffeeScriptLexer(LexerRestartInfo<CoffeeScriptTokenId> info) {        
        this.info = info;   
        AntlrCharStream charStream = new AntlrCharStream(info.input(), "CoffeeScriptAntlr");
        lexer = new CoffeeScriptLexerGrammar(charStream);
        queue = new LinkedList<TokenInfo>();
        setupTokenHierarchy();
    }

    @Override
    public org.netbeans.api.lexer.Token<CoffeeScriptTokenId> nextToken() {
        if (queue.isEmpty()) mergeOutdentsAndGetNextToken(lexer);
        TokenInfo token = queue.poll();

        Token<CoffeeScriptTokenId> createdToken = null;
        
        if(info.input().readLength() == 0) {
            return createdToken;
        }
    
        if (token.getType() != -1){
            CoffeeScriptTokenId tokenId  = CoffeeScriptLanguage.getToken(token.getType());
            if(token.hasProperties()) {
                createdToken = info.tokenFactory().createPropertyToken(tokenId, (token.getStopIndex() - token.getStartIndex() + 1), new PropertyProvider(token.getProperties()));  
            } else {
                createdToken = info.tokenFactory().createToken(tokenId, (token.getStopIndex() - token.getStartIndex() + 1));  
            }
            
        } else if(info.input().readLength() > 0){
            CoffeeScriptTokenId tokenId  = CoffeeScriptLanguage.getToken(CoffeeScriptLexerGrammar.TERMINATOR);
            createdToken = info.tokenFactory().createToken(tokenId);
        }

        return createdToken;
    }
    
    private void mergeOutdentsAndGetNextToken(org.antlr.v4.runtime.Lexer lexer) {
        org.antlr.v4.runtime.Token token = lexer.nextToken();
        boolean terminator = false;
        
        
        while((token.getType() == CoffeeScriptLexerGrammar.TERMINATOR && terminator) 
                || (token.getType() == CoffeeScriptLexerGrammar.OUTDENT && !terminator)) 
        {
            if(token.getType() == CoffeeScriptLexerGrammar.OUTDENT) {
                TokenInfo peek = null;
                if(queue.isEmpty() || (!queue.isEmpty() && ((peek = queue.peek()).getType() != CoffeeScriptLexerGrammar.OUTDENT))) {
                    PropertyTokenInfo info  = new PropertyTokenInfo(token);
                    info.addProperty("multiplicity", 1);
                    queue.add(info);
                } else if (peek != null && peek.getType() == CoffeeScriptLexerGrammar.OUTDENT) {
                    PropertyTokenInfo info = (PropertyTokenInfo) peek;
                    Integer multiplicity = (Integer)info.getProperty("multiplicity") + 1;
                    info.addProperty("multiplicity", multiplicity);
                }
            }
            
            token = lexer.nextToken();
            terminator = !terminator;
        }
        queue.add(new DefaultTokenInfo(token));
    }

    @Override
    public Object state() {
        return null;
    }
    @Override
    public void release() {
        Lookup l = Utilities.actionsGlobalContext();        
        DataObject dataObject = l.lookup(DataObject.class);
        if(dataObject == null) return;
        EditorCookie.Observable ec = dataObject.getLookup().lookup(EditorCookie.Observable.class);
        if(ec == null) return;
        final NbEditorDocument doc = (NbEditorDocument) ec.getDocument();
//        doc.removeUpdateDocumentListener(listener);
    }
   
    private void setupTokenHierarchy() {
        Lookup l = Utilities.actionsGlobalContext();        
        DataObject dataObject = l.lookup(DataObject.class);
        if(dataObject == null) return;
        EditorCookie.Observable ec = dataObject.getLookup().lookup(EditorCookie.Observable.class);
        if(ec == null) return;
        StyledDocument doc = (StyledDocument) ec.getDocument();
        listener = new ThisDocumentListener();
        try {
            doc = ec.openDocument();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
//        doc.addUpdateDocumentListener(listener);      
    }
    private class ThisDocumentListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            final NbEditorDocument doc = (NbEditorDocument) e.getDocument();
//            LexUtilities.TokenInfo t = LexUtilities.getTokenAtOffset(doc, e.getOffset(), CoffeeScriptLexerGrammar.OUTDENT);
//            if(t != null) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        doc.runAtomic(new Runnable() {
                            @Override
                            public void run() {
                                MutableTextInput mti = (MutableTextInput) doc.getProperty(MutableTextInput.class);
                                if (mti != null) {
                                    mti.tokenHierarchyControl().rebuild();
                                }
                            }
                        });
                    }
                });
//            }
        }

        public void removeUpdate(DocumentEvent e) {
            final NbEditorDocument doc = (NbEditorDocument) e.getDocument();
//            LexUtilities.TokenInfo t = LexUtilities.getTokenAtOffset(doc, e.getOffset(), CoffeeScriptLexerGrammar.OUTDENT);
//            if(t != null) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        doc.runAtomic(new Runnable() {
                            @Override
                            public void run() {
                                MutableTextInput mti = (MutableTextInput) doc.getProperty(MutableTextInput.class);
                                if (mti != null) {
                                    mti.tokenHierarchyControl().rebuild();
                                }
                            }
                        });
                    }
                });
        }

        public void changedUpdate(DocumentEvent e) {
        }
        
    }   

            
}