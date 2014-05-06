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

import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.generated.CoffeeScriptParserGrammar;
import coffeescript.nb.indexing.CoffeeScriptIndex;
import coffeescript.nb.indexing.CoffeeScriptIndexer;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.dfa.DFA;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.queries.FileEncodingQuery;
import org.netbeans.modules.parsing.api.indexing.IndexingManager;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.text.Line;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Utilities;

/**
 *
 * @author Denis Stepanov & Miloš Pensimus
 */
public class CoffeeScriptUtils {

    public static void writeJS(final String js, String name, FileObject folder, Charset encoding) {
        String outputFolder = CoffeeScriptSettings.get().getOutputFolder();
        try {
            Project project = FileOwnerQuery.getOwner(folder);
            if (outputFolder != null && outputFolder.trim().length() > 0) {
                if (project != null) {
                    FileObject projectDirectory = project.getProjectDirectory();
                    String projectPath = projectDirectory.getPath();
                    String path = folder.getPath().replace(projectPath, outputFolder);
                    File pathFolder = new File(path);
                    if(!pathFolder.exists()) {
                        pathFolder.mkdirs();
                    }
                    folder = FileUtil.toFileObject(pathFolder);
                } else {
                    folder = FileUtil.toFileObject(new File(outputFolder));
                }
            }
            FileObject file = folder.getFileObject(name, "js");
            if (file == null) {
                file = folder.createData(name, "js");
            }
            if (!file.asText().equals(js)) {
                OutputStream out = file.getOutputStream();
                try {
                    out.write(js.getBytes(encoding));
                    out.flush();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
        }
    }

    public static void writeJSForCoffeeScriptFile(final String js, FileObject coffeeFile) {
        
        writeJS(js, coffeeFile.getName(), coffeeFile.getParent(), FileEncodingQuery.getEncoding(coffeeFile));
    }
    
    public static Definition findForDefinition(int caretOffset, String name) {
        CoffeeScriptIndex index = getIndex();
        FileObject fileObject = Utilities.actionsGlobalContext().lookup(FileObject.class);
        List<Definition> potentialDefinitions = new ArrayList<Definition>();
        for(Definition def : index.getAllFieldsInFile(fileObject)) {
            if(name.equals(def.getText())) potentialDefinitions.add(def);
        }
        for(Definition def : index.getAllMethodsInFile(fileObject)) {
            if(name.equals(def.getText())) potentialDefinitions.add(def);
        }
        for(Definition def : index.getAllClassesInFile(fileObject)) {
            if(name.equals(def.getText())) potentialDefinitions.add(def);
        }
        Definition acceptedDefinition = acceptDefinition(potentialDefinitions, caretOffset);
        if(acceptedDefinition == null) {
            potentialDefinitions.clear();
            for(Definition def : index.getAllRootFieldsFromOtherFiles(fileObject)) {
                if(name.equals(def.getText())) potentialDefinitions.add(def);
            }
            for(Definition def : index.getAllRootMethodsFromOtherFiles(fileObject)) {
                if(name.equals(def.getText())) potentialDefinitions.add(def);
            }
            for(Definition def : index.getAllClassesFromOtherFiles(fileObject)) {
                if(name.equals(def.getText())) potentialDefinitions.add(def);
            }

            acceptedDefinition = acceptFirstDefinition(potentialDefinitions, caretOffset);
        }
        return acceptedDefinition;
    }
    
    public static Definition findForClassMember(int caretOffset, String name) {
        CoffeeScriptIndex index = getIndex();
        FileObject fileObject = Utilities.actionsGlobalContext().lookup(FileObject.class);
        List<Definition> potentialDefinitions = new ArrayList<Definition>();
        for(Definition def : index.getClassFieldsInFile(fileObject)) {
            if(name.equals(def.getText())) potentialDefinitions.add(def);
        }
        for(Definition def : index.getClassMethodsInFile(fileObject)) {
            if(name.equals(def.getText())) potentialDefinitions.add(def);
        }
        Definition acceptedDefinition = acceptDefinition(potentialDefinitions, caretOffset);

        return acceptedDefinition;
    }
    
    public static CoffeeScriptIndex getIndex() {
        Project project = Utilities.actionsGlobalContext().lookup(Project.class);
        FileObject fileObject = Utilities.actionsGlobalContext().lookup(FileObject.class);
        if (project == null) {            
            if (fileObject != null) {
                project = FileOwnerQuery.getOwner(fileObject);
            }
        }     
        return CoffeeScriptIndex.create(project);
    }

    public static void reindex() {
        IndexingManager.getDefault().refreshAllIndices(CoffeeScriptIndexer.Factory.NAME);
    }

    public static void reindexFile(FileObject fileObject) {
        IndexingManager.getDefault().refreshAllIndices(fileObject);
    }
    
    public static void jumpToDefinition(Lookup lookup, Definition definition) {
        LineCookie cookie = lookup.lookup(LineCookie.class);
        EditorCookie editor = lookup.lookup(EditorCookie.class);
        try {
            StyledDocument doc = editor.openDocument();
            if (cookie == null) {
                return;
            }

            int line = NbDocument.findLineNumber(doc, definition.getStartOffset());
            int column = NbDocument.findLineColumn(doc, definition.getStartOffset());

            Line l = cookie.getLineSet().getCurrent(line);
            l.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
        } catch (IndexOutOfBoundsException ex) {
            Toolkit.getDefaultToolkit().beep();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    public static void cleanupAntlrATNConfig() {
        CoffeeScriptParserGrammar parser = new CoffeeScriptParserGrammar(null);
        ATN atn = parser.getATN();
        for (int i = 0; i < atn.decisionToDFA.length; i++) {
            atn.decisionToDFA[i] = new DFA(atn.getDecisionState(i), i);
        }
    }
    
    
    // private part
        
    private static Definition acceptDefinition(List<Definition> potentialDefinitions, int caretOffset) {
        if(!potentialDefinitions.isEmpty()) {
            Collections.sort(potentialDefinitions, new Comparator<Definition>() {

                public int compare(Definition o1, Definition o2) {
                    return Integer.compare(o1.blockSize(), o2.blockSize());
                }

            });

            for(Definition def : potentialDefinitions) {
                if(def.visible(caretOffset)) return def;
            }
        }
        return null;
    }
    
    private static Definition acceptFirstDefinition(List<Definition> potentialDefinitions, int caretOffset) {
        if(!potentialDefinitions.isEmpty()) {
            return potentialDefinitions.get(0);
        }
        return null;
    }
}
