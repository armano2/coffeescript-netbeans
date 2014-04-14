/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.navigator.nodes;

import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.AbstractAction;
import org.openide.cookies.LineCookie;
import org.openide.text.Line;
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
            if (cookie == null) {
                return;
            }
            try {
                CoffeeScriptFileDefinition grammarDescriptor = lookup.lookup(CoffeeScriptFileDefinition.class);
                Collection<Definition> ruleNameDescriptor = grammarDescriptor.getVariables();
                if ((ruleNameDescriptor != null) && (ruleNameDescriptor.size() > 0)) {
                    Line l = cookie.getLineSet().getCurrent(definition.getLine());
                    l.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS, definition.getCharPositionInLine());
                }
            } catch (IndexOutOfBoundsException ex) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
