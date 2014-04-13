package coffeescript.nb.core;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Denis Stepanov
 */
@ServiceProvider(service = MIMEResolver.class)
public class CoffeeScriptMimeResolver extends MIMEResolver {

    public CoffeeScriptMimeResolver() {
        super(Constants.MIME_TYPE);
    }

    @Override
    public String findMIMEType(FileObject fo) {
        if (fo.getExt().equals("coffee") || fo.getNameExt().equals("Cakefile")) {
            return Constants.MIME_TYPE;
        }
        return null;
    }
}
