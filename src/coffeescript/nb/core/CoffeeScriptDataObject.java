// Copyright 2011 Denis Stepanov
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

package coffeescript.nb.core;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import java.io.IOException;
import java.util.Collection;
import org.netbeans.api.project.Project;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.text.DataEditorSupport;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 * 
 * @author Denis Stepanov
 */
public class CoffeeScriptDataObject extends MultiDataObject {
    
    private final InstanceContent content = new InstanceContent();
    private final Lookup lookup;

    public CoffeeScriptDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        CookieSet cookies = getCookieSet();
        Project project = Utilities.actionsGlobalContext().lookup(Project.class);
//        content.add(project);
        this.lookup = new ProxyLookup(new Lookup[] {
            getCookieSet().getLookup(),
            Lookups.fixed(new CoffeeScriptSourceEncodingQuery()),
            new AbstractLookup(content)
        });
        cookies.add((Node.Cookie) DataEditorSupport.create(this, getPrimaryEntry(), cookies));
    }

    @Override
    protected Node createNodeDelegate() {
        return new DataNode(this, Children.LEAF, getLookup());
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    public void publishGrammarDescriptor(CoffeeScriptFileDefinition descriptor)  {
        if(descriptor == null) return;
        Collection<? extends CoffeeScriptFileDefinition> allDescriptors = lookup.lookupAll(CoffeeScriptFileDefinition.class);
        for (CoffeeScriptFileDefinition desc : allDescriptors) {
            content.remove(desc);
        }
        content.add(descriptor);
    }
    
    public <T extends Object> void replaceAndPublish(T object, Class<T> type)  {
        Collection<? extends T> allDescriptors = lookup.lookupAll(type);
        for (T desc : allDescriptors) {
            content.remove(desc);
        }
        content.add(object);
    }
    
}
