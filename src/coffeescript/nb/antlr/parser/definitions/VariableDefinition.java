/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import coffeescript.nb.navigator.nodes.VariableNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public class VariableDefinition extends Definition {
    
    private boolean thisVariable;

    public VariableDefinition(String text, int line, int charPositionInLine, boolean thisVariable) {
        super(text, line, charPositionInLine);
        this.thisVariable = thisVariable;
    }
    

    @Override
    public Node getNode(Lookup lookup) {
        return new VariableNode(this,lookup);
    }
    
   
}
