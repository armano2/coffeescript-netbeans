/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2010 Sun Microsystems, Inc.
 */
package coffeescript.nb.navigator;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.navigator.nodes.DefinitionChildren;
import coffeescript.nb.core.CoffeeScriptDataObject;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.navigator.nodes.RootNode;
import coffeescript.nb.parser.ParseTask;
import java.io.IOException;
import javax.swing.event.DocumentEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.Collection;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.cookies.EditorCookie;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.util.WeakListeners;
import org.openide.util.lookup.InstanceContent;

/**
 * Basic dummy implementation of NavigatorPanel interface.
 */
public class CoffeeScriptNavigator implements NavigatorPanel {

    /* Dynamic Lookup content */
    private final InstanceContent ic;
    /** Lookup instance */
//    private final Lookup navigatorLookup;
    /** holds UI of this panel */
    private NavPanel panel;
    /** template for finding data in given context */
    private Result<CoffeeScriptFileDefinition> grammarLookupResult;
    private Result<CoffeeScriptDataObject> coffeeScriptDataObjectLookupResult;
    private Lookup contextLookup;
    /** listener to context changes */
    private CoffeeScriptDataObjectLookupListener abnfDataObjectLookupListener = null;
    private LookupListener grammarDescriptorLookupListener;
    private DocumentListener documentListener = null;
    private CaretPositionListener caretListener;
    private int caretPosition = -1;
    private EditorPropertyChangeLister editorPropertyChangeLister;
    /** UI */
    private final ExplorerManager manager = new ExplorerManager();
    private BeanTreeView view = new BeanTreeView();
    private RootNode rootNode;
    /** Parsing stuff */
    private static final RequestProcessor parseRequestProcessor = new RequestProcessor("parse");
    private RequestProcessor.Task parsingTask;
    private boolean parsingValid = false;

    public CoffeeScriptNavigator() {
        this.ic = new InstanceContent();
        // Publish selected node in NavigatorPanel lookup so that the title of the window updates with the display name of the node
        manager.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName())) {
                    Node[] oldNodes = (Node[]) evt.getOldValue();
                    for (Node node : oldNodes) {
                        ic.remove(node);
                    }
                    Node[] newNodes = (Node[]) evt.getNewValue();
                    for (Node node : newNodes) {
                        ic.add(node);
                    }
                }
            }
        });
    }

    @Override
    public String getDisplayHint() {
        return "coffeescript";
    }

    @Override
    public String getDisplayName() {
        return "Navigator";
    }

    @Override
    public JComponent getComponent() {
        if (panel == null) {
            panel = new NavPanel();
        }
        return panel;
    }

    /** Called when this panel's component is about to being displayed.
     * Right place to attach listeners to current navigation data context,
     * as clients are responsible for listening to context changes when active
     * (in the time between panelActivated - panelDeactivated calls).
     *
     * This method is always called in event dispatch thread.
     *
     * @param context Lookup instance representing current context to take
     * data from
     */
    @Override
    public void panelActivated(Lookup context) {
        this.contextLookup = context;

        //notified when the the data objects change
        this.coffeeScriptDataObjectLookupResult = context.lookupResult(CoffeeScriptDataObject.class);
        this.coffeeScriptDataObjectLookupResult.addLookupListener(getCoffeeScriptDataObjectLookupListener());

        // notified when new grammar descriptor available
        this.grammarLookupResult = context.lookupResult(CoffeeScriptFileDefinition.class);
        this.grammarLookupResult.addLookupListener(getGrammarDescriptorLookupListener());
        this.grammarLookupResult.allInstances(); // Required for triggering events!!!

        // Force initialization since it waCoffeeScriptSourceEncodingQuerys not triggered yet
//        newDataObject(this.coffeeScriptDataObjectLookupResult.allInstances());
    }

    private void registerEditorListeners(Lookup context) {
        
        if(!EventQueue.isDispatchThread()) return;
        EditorCookie ec = context.lookup(EditorCookie.class);
        FileObject fo = context.lookup(FileObject.class);
        if(fo == null || !fo.isValid()) return;
        if (ec != null) {
            StyledDocument styledDocument = ec.getDocument();
            // force open document. Might never need this section of code
            if (styledDocument == null) {
                try {
                    styledDocument = ec.openDocument();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

            if (styledDocument != null) {
                // TODO: check if create each time a new document listener...
                if (this.documentListener == null) {
                    this.documentListener = new InvalidationDocumentLister();
                    styledDocument.addDocumentListener(WeakListeners.create(DocumentListener.class, this.documentListener, styledDocument));
                }

                // Register caret listeners
                JEditorPane[] panes = ec.getOpenedPanes();
                if (panes != null) {
                    JEditorPane pane = panes[0];
                    if (pane != null) {
                        this.caretListener = new CaretPositionListener();
                        pane.addCaretListener(WeakListeners.create(CaretListener.class, this.caretListener, pane));
                        // trigger caret position
                        this.caretPosition = pane.getCaretPosition();
                        updateSelectedRule();
                    }
                }
            }
        }
    }

    private void unregisterListeners() {
        this.editorPropertyChangeLister = null;
        this.documentListener = null;
        this.caretListener = null;
        this.caretPosition = -1;
        this.parsingValid = false;
    }

    /** Called when this panel's component is about to being hidden.
     * Right place to detach, remove listeners from data context, that
     * were added in panelActivated impl.
     *
     * This method is always called in event dispatch thread.
     */
    @Override
    public void panelDeactivated() {
        this.grammarLookupResult.removeLookupListener(getGrammarDescriptorLookupListener());
        this.grammarLookupResult = null;
        this.grammarDescriptorLookupListener = null;

        this.coffeeScriptDataObjectLookupResult.removeLookupListener(getCoffeeScriptDataObjectLookupListener());
        this.coffeeScriptDataObjectLookupResult = null;
        this.abnfDataObjectLookupListener = null;

        this.contextLookup = null;
        unregisterListeners();
        for (Object object : manager.getSelectedNodes()) {
            ic.remove(object);
        }
    }

    @Override
    public Lookup getLookup() {
        return null;
    }

    /************* non - public part ************/
    private static Node createWaitNode() {
        AbstractNode an = new AbstractNode(Children.LEAF);
        an.setIconBaseWithExtension("coffeescript/nb/resources/wait.gif"); //NOI18N
        an.setDisplayName(NbBundle.getMessage(CoffeeScriptNavigator.class, "LBL_Wait"));
        return an;
    }

    private void newGrammar(Collection<? extends CoffeeScriptFileDefinition> newData) {
        if (newData.size() > 0) {
            CoffeeScriptFileDefinition grammarDescriptor = newData.toArray(new CoffeeScriptFileDefinition[0])[0];            
            List<Definition> ruleNameDescriptor = grammarDescriptor.getRootDefinitions();
            if (ruleNameDescriptor != null) {                
                rootNode = new RootNode (
                        grammarDescriptor.getFileName(), 
                        new DefinitionChildren(ruleNameDescriptor, contextLookup), 
                        contextLookup
                        );
                Runnable updateTree = new Runnable() {

                    @Override
                    public void run() {
                        view.setRootVisible(true);
                        manager.setRootContext(rootNode);
                        if (caretListener == null) {
                            try {
                                manager.setSelectedNodes(new Node[]{rootNode});
                            } catch (PropertyVetoException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        } else {
                            // selected node should change after new data arrived..
                            parsingValid = true;
                            updateSelectedRule();
                        }
                    }
                };
                if (EventQueue.isDispatchThread()) {
                    updateTree.run();
                } else {
                    EventQueue.invokeLater(updateTree);
                }
            }
        }
    }

    private void newDataObject(Collection<? extends CoffeeScriptDataObject> newDataObjects) {
        // unregister previous listeners
        unregisterListeners();

        if (newDataObjects.size() > 0) {
            CoffeeScriptDataObject dataObject = newDataObjects.toArray(new CoffeeScriptDataObject[0])[0];
            Lookup dataObjectLookup = dataObject.getLookup();

            // Very first time, panel is not yet available
            if (this.panel != null) {
                this.panel.showWaitNode();
            }

            // update nodes with existing grammar if available
            EditorCookie ec = dataObjectLookup.lookup(EditorCookie.class);
            if (ec == null) {
                return;
            }

            // Detects when editor actually opens -> register document listener
            EditorCookie.Observable obs = dataObjectLookup.lookup(EditorCookie.Observable.class);
            this.editorPropertyChangeLister = new EditorPropertyChangeLister(dataObjectLookup);
            obs.addPropertyChangeListener(WeakListeners.create(PropertyChangeListener.class, this.editorPropertyChangeLister, obs));

            Collection<? extends CoffeeScriptFileDefinition> grammarCollection = dataObjectLookup.lookupAll(CoffeeScriptFileDefinition.class);
            if (grammarCollection.size() > 0) {
                parsingValid = true;
                newGrammar(grammarCollection);
            }

            StyledDocument doc = ec.getDocument();
            if (doc == null) {
                // editor not opened yet. Probably selecting an ABNF file in the project view
                // try to parse document
                if (grammarCollection.isEmpty()) {
                    // Don't need to reparse when already parsed but editor still not open
                    parsingTask = parseRequestProcessor.create(new ParseTask(dataObject));
                    parsingTask.schedule(1000);
                }
            } else {
                registerEditorListeners(dataObjectLookup);
            }
        }
    }

    /** Accessor for listener to context */
    private LookupListener getGrammarDescriptorLookupListener() {
        if (grammarDescriptorLookupListener == null) {
            grammarDescriptorLookupListener = new GrammarDescriptorContextListener();
        }
        return grammarDescriptorLookupListener;
    }

    private LookupListener getCoffeeScriptDataObjectLookupListener() {
        if (abnfDataObjectLookupListener == null) {
            abnfDataObjectLookupListener = new CoffeeScriptDataObjectLookupListener();
        }
        return abnfDataObjectLookupListener;
    }

    private void updateSelectedRule() {
        if(!EventQueue.isDispatchThread()) return;
        int position = this.caretPosition;
        if (position < 0) {
            return;
        }

        if (rootNode != null && parsingValid) {
            try {
                DefinitionChildren children = (DefinitionChildren) rootNode.getChildren();
                EditorCookie ec = rootNode.getLookup().lookup(EditorCookie.class);
                StyledDocument doc = ec.openDocument();
                for (Node node : children.snapshot()) {
                    Definition ruleDescriptor = node.getLookup().lookup(Definition.class);
                    if (ruleDescriptor == null) return;
                    int start = ruleDescriptor.getStartOffset();
                    int end = ruleDescriptor.getEndOffset();
                    if ((position >= start) && (position <= end)) {
                        try {
                            manager.setSelectedNodes(new Node[]{node});
                        } catch (PropertyVetoException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                        break;
                    }
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    /** Listens to changes of context and triggers proper action */
    private class GrammarDescriptorContextListener implements LookupListener {

        @Override
        public void resultChanged(LookupEvent ev) {
            Collection<? extends CoffeeScriptFileDefinition> data = grammarLookupResult.allInstances();
            newGrammar(data);
        }
    }

    private class CoffeeScriptDataObjectLookupListener implements LookupListener {

        @Override
        public void resultChanged(LookupEvent ev) {
            Collection<? extends CoffeeScriptDataObject> dataObjects = coffeeScriptDataObjectLookupResult.allInstances();
            newDataObject(dataObjects);
        }
    }

    /** Listens to caret position in document and select proper rule in navigator */
    private class CaretPositionListener implements CaretListener {

        @Override
        public void caretUpdate(CaretEvent e) {
            CoffeeScriptNavigator.this.caretPosition = e.getDot();
            updateSelectedRule();
        }
    }

    private class InvalidationDocumentLister implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            parsingValid = false;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            parsingValid = false;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            parsingValid = false;
        }
    }

    private class EditorPropertyChangeLister implements PropertyChangeListener {

        final Lookup lookup;

        public EditorPropertyChangeLister(Lookup lookup) {
            this.lookup = lookup;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(EditorCookie.Observable.PROP_OPENED_PANES)) {
                registerEditorListeners(lookup);
            }
        }
    }

    class NavPanel extends JPanel implements ExplorerManager.Provider, Lookup.Provider {

        public NavPanel() {
            view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setLayout(new BorderLayout());
            add(view, BorderLayout.CENTER);
            showWaitNode();
        }

        @Override
        public boolean requestFocusInWindow() {
            return view.requestFocusInWindow();
        }

        @Override
        public ExplorerManager getExplorerManager() {
            return manager;
        }

        @Override
        public Lookup getLookup() {
            return null;
        }

        private void showWaitNode() {
            view.setRootVisible(true);
            manager.setRootContext(createWaitNode());
        }
    }
}
