/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.completion;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import org.netbeans.modules.editor.NbEditorDocument;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;

/**
 *
 * @author Miloš Pensimus
 */
public abstract class AbstractCompletionQuery extends AsyncCompletionQuery {
    
    protected char[] getActiveLine(Document doc, int offset) throws BadLocationException {
        NbEditorDocument document = (NbEditorDocument) doc;
        Element lineElement = document.getParagraphElement(offset);
        int start = lineElement.getStartOffset();
        return doc.getText(start, (offset-start)).toCharArray();        
    }
    
    protected int getFilterLength(char[] line) {
        int i = line.length;
        while(--i > -1){
            final char c = line[i];
            if(Character.isWhitespace(c) ||
                    c == ',' ||
                    c == '.'){
                return line.length - i - 1;
            }
        }
        return line.length;
    }
    
    
}
