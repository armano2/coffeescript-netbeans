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
package coffeescript.nb.antlr.parser.definitions;

import coffeescript.nb.completion.CompletionContext;
import java.util.Collection;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Miloš Pensimus
 */
public abstract class Definition {
    protected String text;
    protected OffsetRange offsetRange;
    protected OffsetRange blockOffsetRange;
    protected boolean classMember;
    protected FileObject fileObject;
    
    protected Definition(String text, int startOffset, int endOffset, int blockStartOffset, int blockEndOffset, boolean classMember) {
        this.text = text;
        this.offsetRange = new OffsetRange(startOffset, endOffset);  
        this.blockOffsetRange = new OffsetRange(blockStartOffset, blockEndOffset); 
        this.classMember = classMember;
    }    

    public String getText() {
        return (text != null) ? text : "";
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public int getStartOffset() {
        return offsetRange.getStart();
    }
    
    public int getEndOffset() {
        return offsetRange.getEnd();
    }

    public int getBlockStartOffset() {
        return blockOffsetRange.getStart();
    }

    public int getBlockEndOffset() {
        return blockOffsetRange.getEnd();
    }

    public boolean isClassMember() {
        return classMember;
    }
    
    public int blockSize() {
        return blockOffsetRange.getLength();
    }
    
    public boolean visible(int caretOffset) {
        return blockOffsetRange.containsInclusive(caretOffset);
    }

    public FileObject getFileObject() {
        return fileObject;
    }

    public void setFileObject(FileObject fileObject) {
        this.fileObject = fileObject;
    }

    public abstract Node getNode(Lookup lookup);
    public abstract Collection<CompletionItem> getCompletionItems(int startOffset, int caretOffset, CompletionContext context);
    public abstract String serialize();
    
}
