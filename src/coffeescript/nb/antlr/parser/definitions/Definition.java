/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public abstract class Definition {
    protected String text;
    protected int line;
    protected int charPositionInLine;
    protected Definition(String text, int line, int charPositionInLine) {
            this.text = text;
            this.line = line;
            this.charPositionInLine = charPositionInLine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLine() {
        return line-1;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCharPositionInLine() {
        return charPositionInLine-1;
    }

    public void setCharPositionInLine(int charPositionInLine) {
        this.charPositionInLine = charPositionInLine;
    } 
    public abstract Node getNode(Lookup lookup);
    
}
