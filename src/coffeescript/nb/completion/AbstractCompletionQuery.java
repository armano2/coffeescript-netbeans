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

package coffeescript.nb.completion;

import static coffeescript.nb.completion.CompletionContext.ContextType.CLASS_ONLY_ACCESSOR;
import static coffeescript.nb.completion.CompletionContext.ContextType.DEFAULT;
import static coffeescript.nb.completion.CompletionContext.ContextType.EXTENDS;
import static coffeescript.nb.completion.CompletionContext.ContextType.NEW_INSTANCE;
import static coffeescript.nb.completion.CompletionContext.ContextType.NORMAL_ACCESSOR;
import coffeescript.nb.core.CoffeeScriptTokenId;
import coffeescript.nb.indexing.CoffeeScriptIndex;
import coffeescript.nb.lexer.LexUtilities;
import coffeescript.nb.lexer.TokenEnumLexer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.text.Document;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.modules.editor.NbEditorDocument;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import static coffeescript.nb.lexer.TokenEnumLexer.Category.*;
import java.util.EnumSet;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public abstract class AbstractCompletionQuery extends AsyncCompletionQuery {
    
    private static final Set<TokenEnumLexer.Category> delimiters = EnumSet.of(COMMENT_CAT, KEYWORD_CAT, REGEXP_CAT, STRING_CAT, WHITESPACE_CAT, OPERATOR_CAT,
            SEPARATOR_CAT, ERROR_CAT);
    private static final Set<TokenEnumLexer> accessors = EnumSet.of(TokenEnumLexer.DOUBLE_COLON, TokenEnumLexer.QDOUBLE_COLON);

    protected CompletionContext getCompletionContext(Document doc, int offset, FileObject thisFile, CoffeeScriptIndex csIndex) {
        return getCompletionContext(doc, offset, thisFile, csIndex, false);
    }

    protected CompletionContext getFilterOnly(Document doc, int offset) {
        return getCompletionContext(doc, offset, null, null, true);
    }
    
    private CompletionContext getCompletionContext(Document doc, int offset, FileObject thisFile, CoffeeScriptIndex csIndex, boolean filterOnly) {
        List<Token> tokens = new ArrayList<Token>();
        String className = null;
        StringBuilder sb = new StringBuilder();
        NbEditorDocument editorDocument = (NbEditorDocument) doc;
        
        editorDocument.readLock();
        TokenSequence<CoffeeScriptTokenId> ts = LexUtilities.getTokenSequence(editorDocument, offset);
        int index = ts.move(offset);
        if (index > 0) {
            ts.moveNext();
        } else if (offset == 0) {
            ts.moveStart();
            ts.moveNext();
        } else {
            ts.movePrevious();
        }
        
        Token<CoffeeScriptTokenId> token = ts.token();
        while(!delimiters.contains(token.id().getTokenEnum().getCategory())) {
            tokens.add(token);
            if(!ts.movePrevious()) break;
            token = ts.token();
        }
        
        for(int i = 0; i < tokens.size(); i++) {
            Token<CoffeeScriptTokenId> t = tokens.get(i);
            sb.append(t.text());
        }
        
        // for legacy completion query is only filter string needed
        if(filterOnly) {
            editorDocument.readUnlock();
            return new CompletionContext(sb.toString());
        }
            
        
        Token<CoffeeScriptTokenId> accessor = ts.token();

        if(ts.movePrevious()) {
            Token<CoffeeScriptTokenId> beforeDelimToken = ts.token();
            switch(beforeDelimToken.id().getTokenEnum()) {
                case IDENTIFIER:
                    if(accessors.contains(accessor.id().getTokenEnum())) {
                        className = (csIndex.classExists(ts.token().text().toString())) ? ts.token().text().toString() : null;
                    }
                    break;
                case NEW:
                case EXTENDS:
                    accessor = beforeDelimToken;
                    break;
            }
        }
        
        editorDocument.readUnlock();
        CompletionContext.ContextType type = getCompletionType(accessor.id().getTokenEnum());
        return new CompletionContext(thisFile, type, className, sb.toString());
    }
    
    private CompletionContext.ContextType getCompletionType(TokenEnumLexer delimiter) {
        switch(delimiter) {
            case NEW:
                return NEW_INSTANCE;
            case DOT:
            case QDOT:
                return NORMAL_ACCESSOR;
            case DOUBLE_COLON:
            case QDOUBLE_COLON:
                return CLASS_ONLY_ACCESSOR;
            case EXTENDS:
                return EXTENDS;
            default: 
                return DEFAULT;
        }
    }
    
}
