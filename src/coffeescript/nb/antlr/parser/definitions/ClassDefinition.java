/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import coffeescript.nb.navigator.nodes.ClassNode;
import coffeescript.nb.navigator.nodes.DefinitionChildren;
import java.util.Collection;
import java.util.Map;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public class ClassDefinition extends Definition {
    private String parent;
//    private Map<String, Definition> varMap;
    private Collection<Definition> vars;

    public ClassDefinition(String text, int line, int charPositionInLine, String parent) {
        super(text, line, charPositionInLine);
        this.parent = parent;
//        this.varMap = varMap;
    }

    //    public Map<String, Definition> getVarMap() {
    //        return varMap;
    //    }
    //
    //    public void setVarMap(Map<String, Definition> varMap) {
    //        this.varMap = varMap;
    //    }
    public Collection<Definition> getVars() {
        return vars;
    }

    public void setVars(Collection<Definition> vars) {
        this.vars = vars;
    }
    
    

    public String getParent() {
        return parent;
    }

    @Override
    public Node getNode(Lookup lookup) {
        return new ClassNode(this, new DefinitionChildren(vars, lookup), lookup);
    }
    
    
}
