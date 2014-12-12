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
public class FieldCompletionItem extends IdentifierCompletionItem {
    
    private final boolean methodParam;
    private final boolean protectedField;
    private final boolean classMember;

    public FieldCompletionItem(String text, String simpleText, String fileName, int startOffset, int carretOffset, 
            int priorityDecrementor, boolean methodParam, boolean protectedField, boolean classMember) {
        super(text, simpleText, fileName, startOffset, carretOffset, priorityDecrementor);
        this.methodParam = methodParam;
        this.protectedField = protectedField;
        this.classMember = classMember;
    }
    
    @Override
    protected ImageIcon getIcon() {
        if(methodParam)
            return ImageUtilities.loadImageIcon(Constants.CONSTANT_ICON, true);
        if(!classMember && protectedField)
            return ImageUtilities.loadImageIcon(Constants.FIELD_PRIVATE_ICON, true);
        return ImageUtilities.loadImageIcon(Constants.FIELD_ICON, true);
    }
    
}
