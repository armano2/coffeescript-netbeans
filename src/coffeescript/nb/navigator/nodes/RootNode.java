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
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class RootNode extends AbstractNode {

    public RootNode(String name,Children children, Lookup lookup) {
        super(children, lookup);
        this.setIconBaseWithExtension(Constants.COFFEE_SCRIPT_ICON);
        this.setDisplayName(name);
    }    
}
