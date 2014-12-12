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

import coffeescript.nb.completion.items.KeywordCompletionItem;
import coffeescript.nb.core.CoffeeScriptLanguage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.swing.text.Document;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionResultSet;

/**
 *
 * @author Miloš Pensimus
 */
public class CompletionQueryLegacy extends AbstractCompletionQuery {

    @Override
    protected void query(final CompletionResultSet resultSet, final Document doc, final int caretOffset) {
        CompletionOperation op = new CompletionOperationImplLegacy(resultSet, doc, caretOffset);
        doc.render(op);
    }

    private class CompletionOperationImplLegacy extends CompletionOperation {

        public CompletionOperationImplLegacy(CompletionResultSet resultSet, Document doc, int caretOffset) {
            super(resultSet, doc, caretOffset);
        }

        @Override
        public void run() {
            CompletionContext context = getFilterOnly(doc, caretOffset);
            int startOffset = caretOffset - context.getFilter().length();
            resultSet.setAnchorOffset(caretOffset);

            if (!context.getFilter().isEmpty()) {
                for (CompletionItem item : createCompletionItems(startOffset, caretOffset, context)) {
                    if (item.getInsertPrefix().toString().toLowerCase().startsWith(context.getFilter())) {
                        resultSet.addItem(item);
                    }
                }
            } else {
                resultSet.addAllItems(createCompletionItems(startOffset, caretOffset, context));
            }
            resultSet.finish();
        }

        @Override
        protected Collection<CompletionItem> createCompletionItems(int startOffset, int caretOffset, CompletionContext context) {
            Set<CompletionItem> items = new HashSet<CompletionItem>();
            for (String keyword : CoffeeScriptLanguage.getKeywords()) {
                items.add(new KeywordCompletionItem(keyword, startOffset, caretOffset));
            }
            return items;
        }

    }

}
