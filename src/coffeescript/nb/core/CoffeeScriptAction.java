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
package coffeescript.nb.core;

import coffeescript.nb.parser.CoffeeScriptAutocompileContext;
import coffeescript.nb.core.CoffeeScriptCompiler.CompilerResult;
import coffeescript.nb.navigator.CoffeeScriptNavigator;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.concurrent.Future;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.execution.ExecutionEngine;
import org.openide.execution.ExecutorTask;
import org.openide.util.Cancellable;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.actions.Presenter.Popup;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 *
 * @author Denis Stepanov & Miloš Pensimus
 */
@ActionID(category = "Build", id = "CoffeeScriptAction")
@ActionRegistration(displayName = "coffeescript.nb.resources.Bundle#CoffeeScriptAction")
@ActionReferences({
    @ActionReference(path = "Loaders/text/coffeescript/Actions", position = 200, separatorAfter = 201, separatorBefore = 199),
    @ActionReference(path = "Editors/text/coffeescript/Popup", position = 10001, separatorBefore = 10000)
})
public class CoffeeScriptAction extends AbstractAction implements ContextAwareAction {

    public Action createContextAwareInstance(Lookup actionContext) {
        Collection<? extends CoffeeScriptDataObject> data = actionContext.lookupAll(CoffeeScriptDataObject.class);
        if (data.isEmpty()) {
            return null;
        }
        return new PopupAction(data);
    }

    public void actionPerformed(ActionEvent ae) {
    }

    private static class PopupAction extends AbstractAction implements Popup {

        private Collection<? extends CoffeeScriptDataObject> data;
        boolean switchOn = true, switchOff = true, switchMultipleValues;

        public PopupAction(Collection<? extends CoffeeScriptDataObject> data) {
            super(NbBundle.getBundle("coffeescript.nb.resources.Bundle").getString("CoffeeScriptAction"));
            this.data = data;

            for (CoffeeScriptDataObject dataObject : data) {
                boolean status = CoffeeScriptAutocompileContext.get().isEnabled(dataObject.getPrimaryFile());
                switchOn = switchOn && status;
                switchOff = switchOff && !status;
            }
            switchMultipleValues = !switchOn && !switchOff;
        }

        public JMenuItem getPopupPresenter() {
            String color = switchMultipleValues ? "gray" : (switchOn ? "green" : "black");
            String label = switchMultipleValues ? "-" : (switchOn ? "on" : "off");
            String switchActionName = String.format("<html>Autocompile: <b style=\"color: %s\">%s</b>", color, label);

            final JMenu menu = new JMenu(this);
            JMenu switchmenu = new JMenu(switchActionName);
            if (switchMultipleValues || switchOff) {
                switchmenu.add(new AbstractAction("Turn on") {

                    public void actionPerformed(ActionEvent ae) {
                        if (CoffeeScriptSettings.get().isShowCompilerWarning() && CoffeeScriptSettings.CompilerType.RHINO.equals(CoffeeScriptSettings.get().getCompilerType())) {
                            String defaultM = NbBundle.getMessage(CoffeeScriptAction.class, "LBL_never_show_again");
                            String remind = NbBundle.getMessage(CoffeeScriptAction.class, "LBL_remind_later");
                            NotifyDescriptor nd = new NotifyDescriptor(
                                    NbBundle.getMessage(CoffeeScriptAction.class, "LBL_autocompile_warning_message"),
                                    NbBundle.getMessage(CoffeeScriptAction.class, "LBL_rhino_detected"),
                                    NotifyDescriptor.DEFAULT_OPTION,
                                    NotifyDescriptor.WARNING_MESSAGE,
                                    new String[]{defaultM, remind},
                                    defaultM
                            );

                            String value = (String) DialogDisplayer.getDefault().notify(nd);

                            if (value.equals(defaultM)) {
                                CoffeeScriptSettings.get().setShowCompilerWarning(false);
                            }
                        }

                        for (CoffeeScriptDataObject dataObject : data) {
                            CoffeeScriptAutocompileContext.get().enableAutocompile(dataObject.getPrimaryFile());
                        }

                        RequestProcessor processor = RequestProcessor.getDefault();
                        final Future[] futureHolder = new Future[1];
                        futureHolder[0] = processor.submit(new CompilerTask(data, CoffeeScriptSettings.get().isBare()) {

                            public void run() {
                                try {
                                    compile();
                                } catch (Exception e) {
                                    Exceptions.printStackTrace(e);
                                }
                            }

                            public boolean cancel() {
                                futureHolder[0].cancel(true);
                                return true;
                            }
                        });
                    }
                });
            }
            if (switchMultipleValues || switchOn) {
                switchmenu.add(new AbstractAction("Turn off") {

                    public void actionPerformed(ActionEvent ae) {
                        for (CoffeeScriptDataObject dataObject : data) {
                            CoffeeScriptAutocompileContext.get().disableAutocompile(dataObject.getPrimaryFile());
                        }
                    }
                });
            }
            menu.add(switchmenu);
            menu.addSeparator();
            menu.add(new CompileAction(data, false));
            menu.add(new CompileAction(data, true));
            return menu;
        }

        public void actionPerformed(ActionEvent ae) {
        }
    }

    private static class CompileAction extends AbstractAction {

        Collection<? extends CoffeeScriptDataObject> data;
        boolean bare;

        public CompileAction(Collection<? extends CoffeeScriptDataObject> data, boolean bare) {
            super(bare ? "Compile without the top-level function" : "Compile");
            this.data = data;
            this.bare = bare;
        }

        public void actionPerformed(ActionEvent ae) {
            new ConsoleOutputCompileTask(data, bare).execute();
        }
    }

    private static class CancelAction extends AbstractAction {

        private Cancellable cancellable;

        private CancelAction(Cancellable cancellable) {
            putValue(Action.SMALL_ICON, ImageUtilities.loadImageIcon(Constants.STOP_ICON, false));
            putValue(Action.NAME, "Stop");
            setEnabled(false);
            this.cancellable = cancellable;
        }

        public void actionPerformed(ActionEvent e) {
            cancellable.cancel();
            setEnabled(false);
        }
    }

    private static class ConsoleOutputCompileTask extends CompilerTask {

        private final CancelAction cancelAction;
        private ExecutorTask executorTask;
        private InputOutput io;

        public ConsoleOutputCompileTask(Collection<? extends CoffeeScriptDataObject> data, boolean bare) {
            super(data, bare);
            this.cancelAction = new CancelAction(this);
            this.io = IOProvider.getDefault().getIO(getName(), new Action[]{cancelAction});
        }

        public void execute() {
            executorTask = ExecutionEngine.getDefault().execute("CoffeeScriptExecutor", this, io);
        }

        @Override
        public void run() {
            try {
                cancelAction.setEnabled(true);
                super.compile();
            } catch (Exception e) {
                e.printStackTrace(io.getErr());
            } finally {
                cancelAction.setEnabled(false);
            }
        }

        @Override
        protected void handleResult(CompilerResult result) {
            if (result.getJs() != null) {
                io.getOut().append(result.getJs());
            } else {
                io.getErr().append(result.getError().getMessage());
            }
        }

        @Override
        public boolean cancel() {
            executorTask.stop();
            cancelAction.setEnabled(false);
            return true;
        }
    }

    private static abstract class CompilerTask implements Cancellable, Runnable {

        private Collection<? extends CoffeeScriptDataObject> data;
        private String taskName;
        private boolean bare;

        public CompilerTask(Collection<? extends CoffeeScriptDataObject> data, boolean bare) {
            this.data = data;
            this.bare = bare;
            taskName = data.size() > 1 ? "Compiling CoffeeScript files" : "Compiling " + data.iterator().next().getPrimaryFile().getNameExt();
        }

        public void compile() throws Exception {
            LifecycleManager.getDefault().saveAll();
            for (CoffeeScriptDataObject coffeeFile : data) {
                ProgressHandle handle = ProgressHandleFactory.createHandle("Compiling " + coffeeFile.getPrimaryFile().getNameExt(), this);
                try {
                    handle.start();
                    CoffeeScriptCompiler.CompilerResult result = CoffeeScriptSettings.getCompiler().compile(coffeeFile.getPrimaryFile().asText(), bare);
                    if (result == null) {
                        return; // Canceled
                    }
                    if (result.getJs() != null) {
                        CoffeeScriptUtils.writeJSForCoffeeScriptFile(result.getJs(), coffeeFile.getPrimaryFile());
                    }
                    handleResult(result);
                } finally {
                    handle.finish();
                }
            }
        }

        protected void handleResult(CoffeeScriptRhinoCompiler.CompilerResult result) {
        }

        public String getName() {
            return taskName;
        }
    }
}
