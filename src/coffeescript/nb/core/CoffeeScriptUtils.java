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

import coffeescript.nb.antlr.parser.definitions.BlockDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.api.queries.FileEncodingQuery;
import org.netbeans.modules.csl.api.OffsetRange;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author Denis Stepanov
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
    
    public static Definition findForDefinition(BlockDefinition rootBlock, int caretOffset, String name) {
        Definition pontentialDefinition = rootBlock.getMember(name);
        BlockDefinition activeBlock = rootBlock;
        boolean changedBlock = false;
        while (activeBlock.hasChildren()) {
            for (OffsetRange range : activeBlock.getChildren().keySet()) {
                if(range.containsInclusive(caretOffset)) {
                    activeBlock = activeBlock.getChildren().get(range);
                    changedBlock = true;
                }                
            }
            if(changedBlock) {
                if (activeBlock.hasMember(name)) pontentialDefinition = activeBlock.getMember(name);
                changedBlock = false;
            } else {
                break;
            }
        }
        return pontentialDefinition;
    }
}
