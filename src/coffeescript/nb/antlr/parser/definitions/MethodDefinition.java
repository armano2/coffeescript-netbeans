/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import coffeescript.nb.completion.items.MethodCompletionItem;
import coffeescript.nb.indexing.CoffeeScriptIndex;
import coffeescript.nb.indexing.IndexedDefinitionFactory;
import coffeescript.nb.navigator.nodes.MethodNode;
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
public class MethodDefinition extends Definition {
    
    private List<Definition> params;
    private boolean anonymous;
    private boolean thisMethod;

    public MethodDefinition(String text, int startOffset, int endOffset, int blockStartOffset, int blockEndOffset, boolean classMember, List<Definition> params, boolean anonymous) {
        super(text, startOffset, endOffset, blockStartOffset, blockEndOffset, classMember);
        this.params = params;
        this.anonymous = anonymous;        
    }

    public List<Definition> getParams() {
        return params;
    }
    
    public String getParamString() {
        if(params.isEmpty()) return "()";
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Definition param : params) {
            VariableDefinition def = (VariableDefinition) param;
            if(def.isClassMember()) sb.append("@");
            sb.append(def.getText());
            if(def.isSplat()) sb.append("...");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }

    public void setThisMethod(boolean thisMethod) {
        this.thisMethod = thisMethod;
    }

    @Override
    public Node getNode(Lookup lookup) {
        return new MethodNode(this, lookup);
    }

    @Override
    public Collection<CompletionItem> getCompletionItems(String fileName, int startOffset, int caretOffset, boolean decreasePriority) {
        String optionalAt = (thisMethod) ? "@" : "";
        return Collections.<CompletionItem>singletonList(new MethodCompletionItem(optionalAt + text + getParamString(), fileName, startOffset, caretOffset, decreasePriority));
    }    

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(IndexedDefinitionFactory.METHOD_PREFIX);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(text);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getStartOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getEndOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getBlockStartOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(getBlockEndOffset());
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(classMember);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        sb.append(anonymous);
        sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        for(Definition d : params) {
            sb.append(d.serialize());
            sb.append(IndexedDefinitionFactory.METHOD_DELIM);
        }        
        return sb.toString();
    }

}
