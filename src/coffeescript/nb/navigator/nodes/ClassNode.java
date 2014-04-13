/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.navigator.nodes;

import coffeescript.nb.core.Constants;
import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;

public class ClassNode extends AbstractNode {
    
    private ClassDefinition definition;
    private Action defaultAction;

    public ClassNode(ClassDefinition definition, Children children, Lookup lookup) {
        super(children, lookup);
        this.definition = definition;
        this.defaultAction = new GoToRuleAction(lookup, definition);
        this.setDisplayName(definition.getText());
        this.setIconBaseWithExtension(Constants.CLASS_ICON);
    }
    
    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{};
    }

    @Override
    public Action getPreferredAction() {
        return defaultAction;
    }       

    @Override
    public String getHtmlDisplayName() {
        StringBuilder sb = new StringBuilder();
        if(definition.getText()!= null) sb.append("<font color='000000'>").append(definition.getText()).append("</font>");
        if(definition.getParent() != null) sb.append(" <font color='AAAAAA'>:: ").append(definition.getParent()).append("</font>");
        return sb.toString();
    }    
    
}
