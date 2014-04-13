/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.netbeans.modules.csl.api.OffsetRange;

/**
 *
 * @author milos
 */
public class BlockDefinition {
    private int start;
    private int stop;
    private BlockDefinition parent;
    private Map<OffsetRange,BlockDefinition> children = new HashMap<OffsetRange, BlockDefinition>();
    
    private Map<String, Definition> members = new HashMap<String, Definition>();

    public BlockDefinition() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }
    
    

    public Map<OffsetRange, BlockDefinition> getChildren() {
        return children;
    }
    
    public void addMember(String name, Definition def) {
        if(!members.containsKey(name)) members.put(name, def);        
    }
    
    public Definition getMember(String name) {
        return members.get(name);
    }
    
    public boolean hasMember(String name) {
        return members.containsKey(name);
    }
    
    private void setParent(BlockDefinition parent){
        this.parent = parent;
    }
    
    public BlockDefinition getParent() {
        return parent;
    }
    
    public Collection<Definition> getVars() {
        return members.values();
        
    }
    
    public void addBlock(OffsetRange range, BlockDefinition block) {
        block.setParent(this);
        children.put(range, block);
    }

    public boolean hasChildren() {
        return !members.isEmpty();
    }
    
}
