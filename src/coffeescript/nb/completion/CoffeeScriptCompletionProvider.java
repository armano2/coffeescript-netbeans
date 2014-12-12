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

import coffeescript.nb.core.Constants;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;

/**
 *
 * @author Miloš Pensimus
 */
@MimeRegistration(mimeType = Constants.MIME_TYPE, service = CompletionProvider.class)
public class CoffeeScriptCompletionProvider implements CompletionProvider {

    @Override
    public CompletionTask createTask(int queryType, JTextComponent jtc) {
        if (queryType != COMPLETION_QUERY_TYPE && queryType != COMPLETION_ALL_QUERY_TYPE) {
            return null;
        }

        return new AsyncCompletionTask(CompletionQueryFactory.createCompletionQuery(queryType == COMPLETION_ALL_QUERY_TYPE), jtc);
    }

    @Override
    public int getAutoQueryTypes(JTextComponent jtc, String string) {
        return 0;
    }

}
