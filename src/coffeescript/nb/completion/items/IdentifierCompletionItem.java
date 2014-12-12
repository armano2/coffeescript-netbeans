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

package coffeescript.nb.completion.items;

/**
 *
 * @author Miloš Pensimus
 */
public abstract class IdentifierCompletionItem extends AbstractCompletionItem  {

    protected final int DEFAULT_PRIORITY = 10;
    protected String fileName;
    private final int priorityDecrementor;

    public IdentifierCompletionItem(String text, String simpleText, String fileName, int startOffset, int carretOffset, int priorityDecrementor) {
        super(text, simpleText, startOffset, carretOffset);
        this.fileName = fileName;
        this.priorityDecrementor = priorityDecrementor;
    }

    @Override
    public CharSequence getSortText() {
        return fileName + visibleText;
    }

    @Override
    protected boolean getLeftBold() {
        return true;
    }

    @Override
    protected String getLeftText() {
        return visibleText != null ? visibleText : "";
    }

    @Override
    protected String getRightText() {
        return fileName;
    }

    @Override
    public int getSortPriority() {
        return DEFAULT_PRIORITY - priorityDecrementor;
    } 


    
    
}
