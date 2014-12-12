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

import java.util.Collection;
import javax.swing.text.Document;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;

/**
 *
 * @author Miloš Pensimus
 */
public abstract class CompletionOperation implements Runnable {
    protected final CompletionResultSet resultSet; 
    protected final Document doc;
    protected final int caretOffset;

    public CompletionOperation(CompletionResultSet resultSet, Document doc, int caretOffset) {
        this.resultSet = resultSet;
        this.doc = doc;
        this.caretOffset = caretOffset;
    }
    protected abstract Collection<CompletionItem> createCompletionItems(int startOffset, int caretOffset, CompletionContext context);
}
