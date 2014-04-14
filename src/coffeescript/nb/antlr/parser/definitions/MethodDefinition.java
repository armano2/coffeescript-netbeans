/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import coffeescript.nb.completion.items.MethodCompletionItem;
import coffeescript.nb.navigator.nodes.MethodNode;
import java.util.List;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public class MethodDefinition extends Definition {
    
    private List<VariableDefinition> params;

    public MethodDefinition(String text, int line, int charPositionInLine, List<VariableDefinition> params) {
        super(text, line, charPositionInLine);
        this.params = params;
    }

    public List<VariableDefinition> getParams() {
        return params;
    }
    
    public String getParamString() {
        if(params.isEmpty()) return "()";
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (VariableDefinition var : params) {
            sb.append(var.getText());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }
    

    @Override
    public Node getNode(Lookup lookup) {
        return new MethodNode(this, lookup);
    }

    @Override
    public CompletionItem getCompletionItem(String fileName, int startOffset, int caretOffset) {
        return new MethodCompletionItem(text, fileName, startOffset, caretOffset);
    }    
}
