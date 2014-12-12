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
import coffeescript.nb.core.CoffeeScriptUtils;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public class JumpToDefinitionAction extends AbstractAction {
        private Lookup lookup;
        private Definition definition;
        
        public JumpToDefinitionAction(Lookup lookup, Definition definition) {
            this.lookup = lookup;
            this.definition = definition;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CoffeeScriptUtils.jumpToDefinition(lookup, definition);
        }
    }
