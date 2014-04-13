/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.parser;

import coffeescript.nb.antlr.parser.definitions.BlockDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import org.netbeans.modules.csl.api.OffsetRange;

/**
 *
 * @author milos
 */
public class BlockUtils {
    public static Definition findForDefinition(BlockDefinition rootBlock, int caretOffset, String name) {
        Definition pontentialDefinition = rootBlock.getMember(name);
        BlockDefinition activeBlock = rootBlock;
        boolean changedBlock = false;
        while (activeBlock.hasChildren()) {
            for (OffsetRange range : activeBlock.getChildren().keySet()) {
                if(range.containsInclusive(caretOffset)) {
                    activeBlock = activeBlock.getChildren().get(range);
                    changedBlock = true;
                }                
            }
            if(changedBlock) {
                if (activeBlock.hasMember(name)) pontentialDefinition = activeBlock.getMember(name);
                changedBlock = false;
            } else {
                break;
            }
        }
        return pontentialDefinition;
    }
}
