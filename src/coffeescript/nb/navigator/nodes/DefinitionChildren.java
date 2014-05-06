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

import coffeescript.nb.antlr.parser.definitions.Definition;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class DefinitionChildren extends Children.Keys<Definition> {

    Collection<Definition> definitions;
    Lookup lookup;

    public DefinitionChildren(List<Definition> definition, Lookup lookup) {
        Collections.sort(definition, new StartOffsetComparator());
        this.definitions = definition;
        this.lookup = lookup;
    }

    @Override
    protected void addNotify() {
        setKeys(definitions);
    }

    @Override
    protected Node[] createNodes(Definition key) {
        return new Node[]{key.getNode(lookup)};
    }
    
    private class StartOffsetComparator implements Comparator<Definition> {

        public int compare(Definition o1, Definition o2) {
            return Integer.compare(o1.getStartOffset(), o2.getStartOffset());
        }        
        
    }
}