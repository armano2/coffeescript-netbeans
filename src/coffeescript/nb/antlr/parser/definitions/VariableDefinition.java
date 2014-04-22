/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser.definitions;

import coffeescript.nb.completion.items.FieldCompletionItem;
import coffeescript.nb.indexing.CoffeeScriptIndex;
import coffeescript.nb.indexing.IndexedDefinitionFactory;
import coffeescript.nb.navigator.nodes.VariableNode;
import java.util.Collection;
import java.util.Collections;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public class VariableDefinition extends Definition {
    
    private boolean splat;
    private boolean methodParam;
    private boolean protectedField;
    
    public VariableDefinition(String text, int startOffset, int endOffset, int blockStartOffset,
            int blockEndOffset, boolean classMember, boolean splat, boolean methodParam, boolean protectedField) {
        super(text, startOffset, endOffset, blockStartOffset, blockEndOffset, classMember);
        this.splat = splat;
        this.methodParam = methodParam;
        this.protectedField = protectedField;
    }    

    public boolean isSplat() {
        return splat;
    }

    public void setSplat(boolean splat) {
        this.splat = splat;
    }

    @Override
    public Node getNode(Lookup lookup) {
        return new VariableNode(this,lookup);
    }

    @Override
    public Collection<CompletionItem> getCompletionItems(String fileName, int startOffset, int caretOffset, boolean decreasePriority) {
        return Collections.<CompletionItem>singletonList(new FieldCompletionItem(text, fileName, startOffset, caretOffset, decreasePriority, methodParam, protectedField));
    }   

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(IndexedDefinitionFactory.FIELD_PREFIX);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(text);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getStartOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getEndOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getBlockStartOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(getBlockEndOffset());
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(classMember);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(splat);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(methodParam);
        sb.append(IndexedDefinitionFactory.FIELD_DELIM);
        sb.append(protectedField);
        return sb.toString();
    }

   
}
