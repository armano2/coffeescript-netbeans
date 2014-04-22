/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import coffeescript.nb.completion.items.ClassCompletionItem;
import coffeescript.nb.indexing.IndexedDefinitionFactory;
import coffeescript.nb.navigator.nodes.ClassNode;
import coffeescript.nb.navigator.nodes.DefinitionChildren;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public class ClassDefinition extends Definition {
    private final String parent;
    private final Collection<VariableDefinition> fields = new ArrayList<VariableDefinition>();
    private final Collection<MethodDefinition> methods = new ArrayList<MethodDefinition>();
    private final List<Definition> vars = new ArrayList<Definition>();
    private final Collection<MethodDefinition> constructors = new ArrayList<MethodDefinition>();

    public ClassDefinition(String text, int startOffset, int endOffset, int blockStartOffset, int blockEndOffset, String parent) {
        super(text, startOffset, endOffset, blockStartOffset, blockEndOffset, false);
        this.parent = parent;
    }

    public Collection<VariableDefinition> getFields() {
        return fields;
    }

    public Collection<MethodDefinition> getMethods() {
        return methods;
    }
 
    public void addField(VariableDefinition def) {
        fields.add(def);
        vars.add(def);
    }
    
    public void addMethod(MethodDefinition def) {
        methods.add(def);
        vars.add(def);
    }
    
    public void addConstructor(MethodDefinition def) {
        constructors.add(def);
        vars.add(def);
    }
    
    public void setConstructors(List<MethodDefinition> constructors) {
        this.constructors.addAll(constructors);
    }

    public String getParent() {
        return parent;
    }

    @Override
    public Node getNode(Lookup lookup) {
        return new ClassNode(this, new DefinitionChildren(vars, lookup), lookup);
    }

    @Override
    public Collection<CompletionItem> getCompletionItems(String fileName, int startOffset, int caretOffset, boolean decreasePriority) {
        Collection<CompletionItem> items = new ArrayList<CompletionItem>();
        boolean emptyConstructorHit = false;
        for(MethodDefinition constructor : constructors) {
            if(constructor.getParams().isEmpty()) emptyConstructorHit = true;
            items.add(new ClassCompletionItem(text + constructor.getParamString(), fileName, startOffset, caretOffset, decreasePriority, constructor.getParams()));
        }
        if(!emptyConstructorHit) items.add(new ClassCompletionItem(text + "()", fileName, startOffset, caretOffset, decreasePriority, Collections.<Definition>emptyList()));
        return items;
    }   

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(IndexedDefinitionFactory.CLASS_PREFIX);
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(text);
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getStartOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getEndOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getBlockStartOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(getBlockEndOffset());
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        sb.append(parent);
        sb.append(IndexedDefinitionFactory.CLASS_DELIM);
        for(Definition constructor : constructors) {
            sb.append(constructor.serialize());
        }        
        return sb.toString();
    }
    
}
