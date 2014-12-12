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

import coffeescript.nb.core.Constants;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Miloš Pensimus
 */
public class KeywordCompletionItem extends AbstractCompletionItem  {

    public KeywordCompletionItem(String keywordName, int startOffset, int carretOffset) {
        super(keywordName,keywordName, startOffset, carretOffset);
    }

    @Override
    public int getSortPriority() {
        return 100;
    }

    @Override
    protected ImageIcon getIcon() {
        return ImageUtilities.loadImageIcon(Constants.COFFEE_SCRIPT_ICON, true);
    }

    @Override
    protected String getLeftText() {
        return visibleText;
    }

    @Override
    protected String getRightText() {
        return "Keyword"; //NOI18N
    }    
    
}