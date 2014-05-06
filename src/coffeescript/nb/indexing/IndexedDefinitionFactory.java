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

package coffeescript.nb.indexing;

import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import static coffeescript.nb.indexing.IndexedDefinitionFactory.CLASS_DELIM;
import static coffeescript.nb.indexing.IndexedDefinitionFactory.create;
import java.util.ArrayList;
import java.util.List;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public class IndexedDefinitionFactory {
    public static String FIELD_PREFIX = "F"; //NOI18N
    public static String CLASS_PREFIX = "C"; //NOI18N
    public static String METHOD_PREFIX = "M"; //NOI18N
    public static String FIELD_DELIM = "#"; //NOI18N
    public static String CLASS_DELIM = "|"; //NOI18N
    public static String METHOD_DELIM = ":"; //NOI18N
    
    public static Definition create(String value, FileObject fo) {
        if(value.startsWith(FIELD_PREFIX)) return getVariableDefinition(value, fo);
        if(value.startsWith(CLASS_PREFIX)) return getClassDefinition(value, fo);
        if(value.startsWith(METHOD_PREFIX)) return getMethodDefinition(value, fo);
        return null;
    }

    private static VariableDefinition getVariableDefinition(String value, FileObject fo) {
        String[] varDef = value.split(FIELD_DELIM);
        int i = 1;
        VariableDefinition var = new VariableDefinition(varDef[i++], Integer.valueOf(varDef[i++]), Integer.valueOf(varDef[i++]), Integer.valueOf(varDef[i++]),
                Integer.valueOf(varDef[i++]), Boolean.valueOf(varDef[i++]), Boolean.valueOf(varDef[i++]), Boolean.valueOf(varDef[i++]), Boolean.valueOf(varDef[i++]));
        var.setFileObject(fo);
        var.setClassName(varDef[i++]);
        return var;
    }
    
    private static ClassDefinition getClassDefinition(String value, FileObject fo) {
        String[] varDef = value.split("\\"+CLASS_DELIM);
        List<MethodDefinition> constructors = new ArrayList<MethodDefinition>();
        for(int i = 7; i< varDef.length ; i++) {
            constructors.add(getMethodDefinition(varDef[i], fo));
        }
        
        ClassDefinition var = new ClassDefinition(varDef[1], Integer.valueOf(varDef[2]), Integer.valueOf(varDef[3]), Integer.valueOf(varDef[4]), Integer.valueOf(varDef[5]), varDef[6]);
        var.setConstructors(constructors);
        var.setFileObject(fo);
        return var;
    }
        
    private static MethodDefinition getMethodDefinition(String value, FileObject fo) {
        String[] varDef = value.split(METHOD_DELIM);
        
        List<Definition> params = new ArrayList<Definition>();
        int i = 1;
        MethodDefinition var = new MethodDefinition(varDef[i++], Integer.valueOf(varDef[i++]), Integer.valueOf(varDef[i++]), Integer.valueOf(varDef[i++]), 
                Integer.valueOf(varDef[i++]), Boolean.valueOf(varDef[i++]), params, Boolean.valueOf(varDef[i++]));
        var.setClassName(varDef[i++]);
        for(; i < varDef.length ; i++) {
            params.add(create(varDef[i], fo));
        }       
        var.setFileObject(fo);
        
        return var;
    }
}


