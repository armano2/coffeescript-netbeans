/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.navigator.nodes;

import coffeescript.nb.core.Constants;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public class RootNode extends AbstractNode {

    public RootNode(String name,Children children, Lookup lookup) {
        super(children, lookup);
        this.setIconBaseWithExtension(Constants.COFFEE_SCRIPT_ICON);
        this.setDisplayName(name);
    }    
}
