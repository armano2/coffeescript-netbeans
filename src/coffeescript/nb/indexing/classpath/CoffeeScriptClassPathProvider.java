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


package coffeescript.nb.indexing.classpath;

import coffeescript.nb.project.CoffeeScriptProject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.spi.java.classpath.ClassPathImplementation;
import org.netbeans.spi.java.classpath.ClassPathProvider;
import org.netbeans.spi.java.classpath.FilteringPathResourceImplementation;
import org.netbeans.spi.java.classpath.PathResourceImplementation;
import org.netbeans.spi.java.classpath.support.ClassPathSupport;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptClassPathProvider implements ClassPathProvider {
    
    public static final String SOURCE_CS = "classpath/coffeescript"; //NOI18N
            
    private final CoffeeScriptProject project;

    public CoffeeScriptClassPathProvider(CoffeeScriptProject project) {
        this.project = project;
    }

    @Override
    public ClassPath findClassPath(FileObject file, String type) {
        if (SOURCE_CS.equals(type)) {
            FileObject siteRootFolder = project.getSiteRootFolder();
            if (siteRootFolder == null) {
                // broken project
                return null;
            }
            if (FileUtil.isParentOf(siteRootFolder, file)) {
                return project.getSourceClassPath();
            }
        }
        return null;
    }

    public static ClassPath createProjectClasspath(CoffeeScriptProject project) {
        return ClassPathSupport.createClassPath(Collections.<PathResourceImplementation>singletonList(new PathImpl(project)));
    }
    
    private static class PathImpl implements FilteringPathResourceImplementation {

        private final CoffeeScriptProject project;
        private final PropertyChangeSupport support = new PropertyChangeSupport(this);

        public PathImpl(CoffeeScriptProject project) {
            this.project = project;
        }
        
        @Override
        public boolean includes(URL root, String resource) {
            return !resource.startsWith("nbproject"); //NOI18N
        }

        @Override
        public URL[] getRoots() {
            List<URL> l = new ArrayList<URL>(2);
            FileObject sourcesFolder = project.getSiteRootFolder();
            if (sourcesFolder != null) {
                l.add(sourcesFolder.toURL());
            }
            return l.toArray(new URL[l.size()]);
        }

        @Override
        public ClassPathImplementation getContent() {
            return null;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            support.removePropertyChangeListener(listener);
        }
        
    }
    
}
