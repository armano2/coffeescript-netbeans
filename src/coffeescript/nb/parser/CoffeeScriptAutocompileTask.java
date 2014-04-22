/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.parser;

import coffeescript.nb.core.CoffeeScriptCompiler;
import coffeescript.nb.core.CoffeeScriptUtils;
import coffeescript.nb.options.CoffeeScriptSettings;
import org.netbeans.modules.parsing.api.Snapshot;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptAutocompileTask implements Runnable {
    private final Snapshot snapshot;

    public CoffeeScriptAutocompileTask(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public void run() {
        if(!CoffeeScriptAutocompileContext.get().isEnabled(snapshot.getSource().getFileObject())) return;
        CharSequence text = snapshot.getText();
        CoffeeScriptCompiler.CompilerResult compilerResult = CoffeeScriptSettings.getCompiler().compile(text.toString(), CoffeeScriptSettings.get().isBare());
        if ((compilerResult != null) && (compilerResult.getJs() != null)) {
            FileObject coffeeFile = snapshot.getSource().getFileObject();
            String js = compilerResult.getJs();
            CoffeeScriptUtils.writeJSForCoffeeScriptFile(js, coffeeFile);
        }
    }
    
}
