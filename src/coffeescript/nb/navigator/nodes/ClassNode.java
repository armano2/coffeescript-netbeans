// Copyright 2014 Miloš Pensimus
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package coffeescript.nb.navigator.nodes;

import coffeescript.nb.core.Constants;
import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Miloš Pensimus
 */
public class ClassNode extends AbstractNode {
    
    private final ClassDefinition definition;
    private final Action defaultAction;

    public ClassNode(ClassDefinition definition, Children children, Lookup lookup) {
        super(children, new ProxyLookup(Lookups.singleton(definition), lookup));
        this.definition = definition;
        this.defaultAction = new JumpToDefinitionAction(lookup, definition);
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
        if(definition.getText()!= null) sb.append("<font color='000000'>").append(definition.getText()).append("</font>"); // NOI18N
        if(definition.getParent() != null) sb.append(" <font color='AAAAAA'>:: ").append(definition.getParent()).append("</font>"); //NOI18N
        return sb.toString();
    }    
    
}
