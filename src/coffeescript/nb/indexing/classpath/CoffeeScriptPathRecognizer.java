package coffeescript.nb.indexing.classpath;

import coffeescript.nb.core.CoffeeScriptLanguage;
import coffeescript.nb.core.Constants;
import java.util.Collections;
import java.util.Set;
import org.netbeans.modules.parsing.spi.indexing.PathRecognizer;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 */
@ServiceProvider(service = PathRecognizer.class)
public final class CoffeeScriptPathRecognizer extends PathRecognizer {

    @Override
    public Set<String> getSourcePathIds() {
        return Collections.singleton(CoffeeScriptClassPathProvider.SOURCE_CS);
    }

    @Override
    public Set<String> getLibraryPathIds() {
        return null;
    }

    @Override
    public Set<String> getBinaryLibraryPathIds() {
        return null;
    }

    @Override
    public Set<String> getMimeTypes() {
        return Collections.singleton(Constants.MIME_TYPE); //NOI18N
    }

}