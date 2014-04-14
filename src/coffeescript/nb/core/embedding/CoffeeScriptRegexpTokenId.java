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

package coffeescript.nb.core.embedding;

import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author Denis Stepanov
 */
public enum CoffeeScriptRegexpTokenId implements TokenId {

    REGEXP("regexp"),
    EMBEDDED("embedded"),
    COMMENT("comment");
    //
    private String category;

    private CoffeeScriptRegexpTokenId(String category) {
        this.category = category;
    }

    public String primaryCategory() {
        return category;
    }
}