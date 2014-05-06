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

package coffeescript.nb.completion;

import org.openide.filesystems.FileObject;


public class CompletionContext {
    
    private final FileObject file;
    private final ContextType  type;
    private final String className;
    private final String filter;

    public CompletionContext(FileObject file, ContextType type, String className, String filter) {
        this.file = file;
        this.type = type;
        this.className = className;        
        this.filter = filter;
    }
    
    public CompletionContext(String filter) { 
        this.file = null; this.type = null; this.className = null; 
        this.filter = filter;
    }  

    public FileObject getFile() {
        return file;
    }

    public ContextType getType() {
        return type;
    }   

    public String getClassName() {
        return className;
    }
    
    public boolean hasClass() {
        return className != null;
    }

    public String getFilter() {
        return filter.toLowerCase();
    }

    public enum ContextType {
        NEW_INSTANCE, NORMAL_ACCESSOR, CLASS_ONLY_ACCESSOR, DEFAULT, EXTENDS
    }
}


