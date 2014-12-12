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

package coffeescript.nb.navigator;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.navigator.nodes.DefinitionChildren;
import coffeescript.nb.core.CoffeeScriptDataObject;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.core.Constants;
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
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptNavigator implements NavigatorPanel {

    /* Dynamic Lookup content */
    private final InstanceContent ic;
    private NavPanel panel;
    /**
     * template for finding data in given context
     */
    private Result<CoffeeScriptFileDefinition> coffeeScriptFileDefinitionLookupResult;
    private Result<CoffeeScriptDataObject> coffeeScriptDataObjectLookupResult;
    private Lookup contextLookup;
    /**
     * listener to context changes
     */
    private CoffeeScriptDataObjectLookupListener coffeeScriptDataObjectLookupListener = null;
    private LookupListener coffeeScriptFileDefinitionLookupListener;
    private DocumentListener documentListener = null;
    private CaretPositionListener caretListener;
    private int caretPosition = -1;
    private EditorPropertyChangeListener editorPropertyChangeListener;
    
    private final ExplorerManager manager = new ExplorerManager();
    private final BeanTreeView view = new BeanTreeView();
    private RootNode rootNode;

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
        return "coffeescript"; //NOI18N
    }

    @Override
    public String getDisplayName() {
        return "Navigator"; //NOI18N
    }

    @Override
    public JComponent getComponent() {
        if (panel == null) {
            panel = new NavPanel();
        }
        return panel;
    }

    /**
     * Called when this panel's component is about to being displayed. Right
     * place to attach listeners to current navigation data context, as clients
     * are responsible for listening to context changes when active (in the time
     * between panelActivated - panelDeactivated calls).
     *
     * This method is always called in event dispatch thread.
     *
     * @param context Lookup instance representing current context to take data
     * from
     */
    @Override
    public void panelActivated(Lookup context) {
        this.contextLookup = context;

        //notified when the the data objects change
        this.coffeeScriptDataObjectLookupResult = context.lookupResult(CoffeeScriptDataObject.class);
        this.coffeeScriptDataObjectLookupResult.addLookupListener(getCoffeeScriptDataObjectLookupListener());

        // notified when new file definition descriptor available
        this.coffeeScriptFileDefinitionLookupResult = context.lookupResult(CoffeeScriptFileDefinition.class);
        this.coffeeScriptFileDefinitionLookupResult.addLookupListener(getCoffeeScriptFileDefinitionLookupListener());
        this.coffeeScriptFileDefinitionLookupResult.allInstances(); // Required for triggering events!!!
        
        newDataObject(this.coffeeScriptDataObjectLookupResult.allInstances());
    }

    @Override
    public void panelDeactivated() {
        this.coffeeScriptFileDefinitionLookupResult.removeLookupListener(getCoffeeScriptFileDefinitionLookupListener());
        this.coffeeScriptFileDefinitionLookupResult = null;
        this.coffeeScriptFileDefinitionLookupListener = null;

        this.coffeeScriptDataObjectLookupResult.removeLookupListener(getCoffeeScriptDataObjectLookupListener());
        this.coffeeScriptDataObjectLookupResult = null;
        this.coffeeScriptDataObjectLookupListener = null;

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
    
    private void registerEditorListeners(Lookup context) {

        if (!EventQueue.isDispatchThread()) {
            return;
        }
        EditorCookie ec = context.lookup(EditorCookie.class);
        FileObject fo = context.lookup(FileObject.class);
        if (fo == null || !fo.isValid()) {
            return;
        }
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
                if (this.documentListener == null) {
                    this.documentListener = new InvalidationDocumentListener();
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
                        updateSelectedDefinition();
                    }
                }
            }
        }
    }

    private void unregisterListeners() {
        this.editorPropertyChangeListener = null;
        this.documentListener = null;
        this.caretListener = null;
        this.caretPosition = -1;
        this.parsingValid = false;
    }

    private Node createWaitNode() {
        AbstractNode an = new AbstractNode(Children.LEAF);
        an.setIconBaseWithExtension(Constants.WAIT_ICON);
        an.setDisplayName(NbBundle.getMessage(CoffeeScriptNavigator.class, "LBL_Wait"));
        return an;
    }

    private void newFileDefinition(Collection<? extends CoffeeScriptFileDefinition> newData) {
        if (newData.size() > 0) {
            CoffeeScriptFileDefinition fileDefinition = newData.toArray(new CoffeeScriptFileDefinition[0])[0];
            List<Definition> definitions = fileDefinition.getRootDefinitions();
            if (definitions != null) {
                rootNode = new RootNode(
                        fileDefinition.getFileName(),
                        new DefinitionChildren(definitions, contextLookup),
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
                            updateSelectedDefinition();
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

            // update nodes with existing file definition if available
            EditorCookie ec = dataObjectLookup.lookup(EditorCookie.class);
            if (ec == null) {
                return;
            }

            // Detects when editor actually opens -> register document listener
            EditorCookie.Observable obs = dataObjectLookup.lookup(EditorCookie.Observable.class);
            this.editorPropertyChangeListener = new EditorPropertyChangeListener(dataObjectLookup);
            obs.addPropertyChangeListener(WeakListeners.create(PropertyChangeListener.class, this.editorPropertyChangeListener, obs));

            Collection<? extends CoffeeScriptFileDefinition> fileDefinitionCollection = dataObjectLookup.lookupAll(CoffeeScriptFileDefinition.class);
            if (fileDefinitionCollection.size() > 0) {
                parsingValid = true;
                newFileDefinition(fileDefinitionCollection);
            }

            StyledDocument doc = ec.getDocument();
            if (doc == null) {
                // editor not opened yet. Probably selecting an ABNF file in the project view
                // try to parse document
                if (fileDefinitionCollection.isEmpty()) {
                    // Don't need to reparse when already parsed but editor still not open
                    parsingTask = parseRequestProcessor.create(new ParseTask(dataObject));
                    parsingTask.schedule(1000);
                }
            } else {
                registerEditorListeners(dataObjectLookup);
            }
        }
    }

    /**
     * Accessor for listener to context
     */
    private LookupListener getCoffeeScriptFileDefinitionLookupListener() {
        if (coffeeScriptFileDefinitionLookupListener == null) {
            coffeeScriptFileDefinitionLookupListener = new CoffeeScriptFileDefinitionContextListener();
        }
        return coffeeScriptFileDefinitionLookupListener;
    }

    private LookupListener getCoffeeScriptDataObjectLookupListener() {
        if (coffeeScriptDataObjectLookupListener == null) {
            coffeeScriptDataObjectLookupListener = new CoffeeScriptDataObjectLookupListener();
        }
        return coffeeScriptDataObjectLookupListener;
    }

    private void updateSelectedDefinition() {
        if (!EventQueue.isDispatchThread()) {
            return;
        }
        int position = this.caretPosition;
        if (position < 0) {
            return;
        }

        if (rootNode != null && parsingValid) {
            try {
                DefinitionChildren children = (DefinitionChildren) rootNode.getChildren();
                EditorCookie ec = rootNode.getLookup().lookup(EditorCookie.class);
                ec.openDocument();
                for (Node node : children.snapshot()) {
                    Definition definition = node.getLookup().lookup(Definition.class);
                    if (definition == null) {
                        return;
                    }
                    int start = definition.getStartOffset();
                    int end = definition.getEndOffset();
                    if ((position >= start) && (position < end)) {
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

    /**
     * Listens to changes of context and triggers proper action
     */
    private class CoffeeScriptFileDefinitionContextListener implements LookupListener {

        @Override
        public void resultChanged(LookupEvent ev) {
            Collection<? extends CoffeeScriptFileDefinition> data = coffeeScriptFileDefinitionLookupResult.allInstances();
            newFileDefinition(data);
        }
    }

    private class CoffeeScriptDataObjectLookupListener implements LookupListener {

        @Override
        public void resultChanged(LookupEvent ev) {
            Collection<? extends CoffeeScriptDataObject> dataObjects = coffeeScriptDataObjectLookupResult.allInstances();
            newDataObject(dataObjects);
        }
    }

    /**
     * Listens to caret position in document and select proper definition in navigator
     */
    private class CaretPositionListener implements CaretListener {

        @Override
        public void caretUpdate(CaretEvent e) {
            CoffeeScriptNavigator.this.caretPosition = e.getDot();
            updateSelectedDefinition();
        }
    }

    private class InvalidationDocumentListener implements DocumentListener {

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

    private class EditorPropertyChangeListener implements PropertyChangeListener {

        final Lookup lookup;

        public EditorPropertyChangeListener(Lookup lookup) {
            this.lookup = lookup;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals(EditorCookie.Observable.PROP_OPENED_PANES)) {
                registerEditorListeners(lookup);
            }
        }
    }

    private class NavPanel extends JPanel implements ExplorerManager.Provider {

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

        private void showWaitNode() {
            view.setRootVisible(true);
            manager.setRootContext(createWaitNode());
        }
    }
}
