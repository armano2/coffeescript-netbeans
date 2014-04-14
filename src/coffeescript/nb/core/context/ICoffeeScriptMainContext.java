/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.core.context;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public interface ICoffeeScriptMainContext {
    
    boolean hasDefinition(FileObject fo);
    CoffeeScriptFileDefinition getDefinition(FileObject fo);
    void addDefinition(FileObject fo, CoffeeScriptFileDefinition def);
    
}
