/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.indexing;

import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import static coffeescript.nb.indexing.IndexedDefinitionFactory.CLASS_DELIM;
import static coffeescript.nb.indexing.IndexedDefinitionFactory.create;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public class IndexedDefinitionFactory {
    public static String FIELD_PREFIX = "F";
    public static String CLASS_PREFIX = "C";
    public static String METHOD_PREFIX = "M";
    public static String FIELD_DELIM = "#";
    public static String CLASS_DELIM = "|";
    public static String METHOD_DELIM = ":";
    
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
        MethodDefinition var = new MethodDefinition(varDef[i++], Integer.valueOf(varDef[i++]), Integer.valueOf(varDef[i++]), Integer.valueOf(varDef[i++]), Integer.valueOf(varDef[i++]), Boolean.valueOf(varDef[i++]), params, Boolean.valueOf(varDef[i++]));
        for(int j = i; j< varDef.length ; j++) {
            params.add(create(varDef[j], fo));
        }       
        var.setFileObject(fo);
        return var;
    }
}


