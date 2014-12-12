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
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
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
public class MethodNode extends AbstractNode {

    private final Action defaultAction;
    private final MethodDefinition definition;

    public MethodNode(MethodDefinition definition, Lookup lookup) {
        super(Children.LEAF,new ProxyLookup(Lookups.singleton(definition), lookup));
        this.defaultAction = new JumpToDefinitionAction(lookup, definition);
        this.definition = definition;
        this.setIconBaseWithExtension(Constants.METHOD_ICON);
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
            return "<font color='000000'>" + definition.getText() + definition.getParamString() +  "</font>" ; //NOI18N
    }    
}
