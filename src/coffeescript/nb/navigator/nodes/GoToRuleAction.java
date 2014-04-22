/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.navigator.nodes;

import coffeescript.nb.antlr.parser.definitions.Definition;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.text.Line;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author milos
 */
public class GoToRuleAction extends AbstractAction {
        private Lookup lookup;
        private Definition definition;
        
        public GoToRuleAction(Lookup lookup, Definition definition) {
            this.lookup = lookup;
            this.definition = definition;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            LineCookie cookie = lookup.lookup(LineCookie.class);
            EditorCookie editor = lookup.lookup(EditorCookie.class);
            try {
                StyledDocument doc = editor.openDocument();
                if (cookie == null) {
                    return;
                }
            
                int line = NbDocument.findLineNumber(doc, definition.getStartOffset());
                int column = NbDocument.findLineColumn(doc, definition.getStartOffset());
                
                Line l = cookie.getLineSet().getCurrent(line);
                l.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, column);
            } catch (IndexOutOfBoundsException ex) {
                Toolkit.getDefaultToolkit().beep();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
