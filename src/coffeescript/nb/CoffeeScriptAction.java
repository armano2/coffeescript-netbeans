package coffeescript.nb;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.execution.ExecutionEngine;
import org.openide.execution.ExecutorTask;
import org.openide.filesystems.FileObject;
import org.openide.util.Cancellable;
import org.openide.util.ContextAwareAction;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.Presenter.Popup;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Denis Stepanov
 */
@ActionID(category = "Build", id = "CoffeeScriptAction")
@ActionRegistration(displayName = "coffeescript.nb.resources.Bundle#CoffeeScriptAction")
@ActionReferences({
    @ActionReference(path = "Loaders/text/coffeescript/Actions", position = 200, separatorAfter = 201, separatorBefore = 199),
    @ActionReference(path = "Editors/text/coffeescript/Popup", position = 10001, separatorBefore = 10000)
})
public class CoffeeScriptAction extends AbstractAction implements ContextAwareAction {

    public Action createContextAwareInstance(Lookup actionContext) {
        final CoffeeScriptDataObject data = actionContext.lookup(CoffeeScriptDataObject.class);
        if (data == null) {
            return null;
        }
        return new PopupAction(data);
    }

    public void actionPerformed(ActionEvent ae) {
    }

    static class PopupAction extends AbstractAction implements Popup {

        private CoffeeScriptDataObject data;

        public PopupAction(CoffeeScriptDataObject data) {
            super(NbBundle.getBundle("coffeescript.nb.resources.Bundle").getString("CoffeeScriptAction"));
            this.data = data;
        }

        public JMenuItem getPopupPresenter() {
            final JMenu menu = new JMenu(this);
            menu.add(new AutocompileSwitchAction(data));
            menu.addSeparator();
            menu.add(new CompileAction(data));
            return menu;
        }

        public void actionPerformed(ActionEvent ae) {
        }
    }

    static class AutocompileSwitchAction extends AbstractAction {

        CoffeeScriptDataObject data;

        public AutocompileSwitchAction(CoffeeScriptDataObject data) {
            this.data = data;
            boolean autobuildStatus = CoffeeScriptAutocompileContext.get().isEnabled(data.getPrimaryFile());
            String color = autobuildStatus ? "green" : "black";
            String label = autobuildStatus ? "on" : "off";
            putValue(NAME, String.format("<html>Autocompile: <b style=\"color: %s\">%s</b>", color, label));
        }

        public void actionPerformed(ActionEvent ae) {
            if (CoffeeScriptAutocompileContext.get().isEnabled(data.getPrimaryFile())) {
                CoffeeScriptAutocompileContext.get().disableAutocompile(data.getPrimaryFile());
            } else {
                CoffeeScriptAutocompileContext.get().enableAutocompile(data.getPrimaryFile());
            }
        }
    }

    static class CompileAction extends AbstractAction {

        CoffeeScriptDataObject data;

        public CompileAction(CoffeeScriptDataObject data) {
            super("Compile");
            this.data = data;
        }

        public void actionPerformed(ActionEvent ae) {
            final CancelAction cancelAction = new CancelAction();
            final InputOutput io = IOProvider.getDefault().getIO(data.getPrimaryFile().getNameExt(), new Action[]{
                        cancelAction});

            final ExecutorTask[] taskHolder = new ExecutorTask[1];
            Cancellable cancellable = new Cancellable() {

                public boolean cancel() {
                    taskHolder[0].stop();
                    cancelAction.setEnabled(false);
                    return true;
                }
            };
            cancelAction.setCancellable(cancellable);
            final ProgressHandle handle = ProgressHandleFactory.createHandle("Compiling " + data.getPrimaryFile().getNameExt(), cancellable);
            taskHolder[0] = ExecutionEngine.getDefault().execute("CoffeeScriptExecutor", new Runnable() {

                public void run() {
                    handle.start();
                    try {
                        cancelAction.setEnabled(true);
                        LifecycleManager.getDefault().saveAll();
                        CoffeeScriptRhinoCompiler.CompilerResult result = CoffeeScriptRhinoCompiler.get().compile(data.getPrimaryFile().asText());
                        if (result == null) {
                            return; // Canceled
                        }
                        if (result.getJs() != null) {
                            FileObject folder = data.getFolder().getPrimaryFile();
                            FileObject file = folder.getFileObject(data.getName(), "js");
                            if (file != null) {
                                file.delete();
                            }
                            file = folder.createData(data.getName(), "js");
                            file.getOutputStream().write(result.getJs().getBytes());
                            io.getOut().append(result.getJs());
                        } else {
                            io.getErr().append(result.getError().getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace(io.getErr());
                    } finally {
                        cancelAction.setEnabled(false);
                        handle.finish();
                    }
                }
            }, io);
        }
    }

    static class CancelAction extends AbstractAction {

        private Cancellable cancellable;

        CancelAction() {
            putValue(Action.SMALL_ICON, ImageUtilities.loadImageIcon("coffeescript/nb/resources/stop.png", false));
            putValue(Action.NAME, "Stop");
            setEnabled(false);
        }

        public void setCancellable(Cancellable cancellable) {
            this.cancellable = cancellable;
        }

        public void actionPerformed(ActionEvent e) {
            setEnabled(false);
            RequestProcessor.getDefault().post(new Runnable() {

                public void run() {
                    cancellable.cancel();
                }
            });
        }
    }
}