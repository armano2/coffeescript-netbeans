/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.core.context;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import java.util.HashMap;
import java.util.Map;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileRenameEvent;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Miloš Pensimus
 */
@ServiceProvider(service = ICoffeeScriptMainContext.class)
public class CoffeeScriptMainContext implements ICoffeeScriptMainContext {
    
    private static final Map<FileObject, CoffeeScriptFileDefinition> DEFINITIONS_MAP = new HashMap<FileObject, CoffeeScriptFileDefinition>();
    private static final Map<FileObject, FileObjectChangeListener> LISTENERS = new HashMap<FileObject, FileObjectChangeListener>();
    
    public CoffeeScriptFileDefinition getDefinition(FileObject fo) {
        return DEFINITIONS_MAP.get(fo);
    }

    public void addDefinition(FileObject fo, CoffeeScriptFileDefinition def) {
        if(!DEFINITIONS_MAP.containsKey(fo)) {
            FileObjectChangeListener listener = new FileObjectChangeListener();
            fo.addFileChangeListener(listener);
            LISTENERS.put(fo, listener);
        }
        DEFINITIONS_MAP.put(fo, def);
    }

    public boolean hasDefinition(FileObject fo) {
        return DEFINITIONS_MAP.containsKey(fo);
    }
    
    public void cleanup() {
        for(FileObject fo : DEFINITIONS_MAP.keySet()) {
            DEFINITIONS_MAP.remove(fo);
            removeListener(fo);
        }
    }
    
    private class FileObjectChangeListener implements FileChangeListener {

        public void fileFolderCreated(FileEvent fe) {
        }

        public void fileDataCreated(FileEvent fe) {
        }

        public void fileChanged(FileEvent fe) {
        }

        public void fileDeleted(FileEvent fe) {
            FileObject fo = fe.getFile();
            DEFINITIONS_MAP.remove(fo);
            removeListener(fo);
        }

        public void fileRenamed(FileRenameEvent fre) {
        }

        public void fileAttributeChanged(FileAttributeEvent fae) {
        }
        
    }
    
    private void removeListener(FileObject fo) {
        FileObjectChangeListener listener = LISTENERS.get(fo);
        fo.removeFileChangeListener(listener);
        LISTENERS.remove(fo);
    }
    
}
