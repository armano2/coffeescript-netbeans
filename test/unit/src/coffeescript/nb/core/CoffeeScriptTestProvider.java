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
package coffeescript.nb.core;

import coffeescript.nb.parser.CoffeeScriptParser;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.api.lexer.InputAttributes;
import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.LanguagePath;
import org.netbeans.api.lexer.Token;
import org.netbeans.modules.editor.NbEditorKit;
import org.netbeans.spi.editor.mimelookup.MimeDataProvider;
import org.netbeans.spi.lexer.LanguageEmbedding;
import org.netbeans.spi.lexer.LanguageProvider;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Denis Stepanov
 */
@ServiceProviders({
    @ServiceProvider(service = MimeDataProvider.class),
    @ServiceProvider(service = MimeDataProvider.class)
})
public class CoffeeScriptTestProvider extends LanguageProvider implements MimeDataProvider {

    public Lookup getLookup(MimePath mimePath) {
        if (MimePath.get(Constants.MIME_TYPE).equals(mimePath)) {
            return Lookups.fixed(CoffeeScriptLanguage.getLanguage(), new CoffeeScriptParser.Factory(), new NbEditorKit());
        }
        return Lookup.EMPTY;
    }

    @Override
    public Language<?> findLanguage(String mimeType) {
        if (Constants.MIME_TYPE.equals(mimeType)) {
            return CoffeeScriptLanguage.getLanguage();
        }
        return null;
    }

    @Override
    public LanguageEmbedding<?> findLanguageEmbedding(Token<?> token, LanguagePath languagePath, InputAttributes inputAttributes) {
        return null;
    }

}
