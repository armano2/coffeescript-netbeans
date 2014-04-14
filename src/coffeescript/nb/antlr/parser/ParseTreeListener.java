/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeescript.nb.antlr.parser;

import coffeescript.nb.antlr.parser.definitions.BlockDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.Token;
import org.netbeans.modules.csl.api.OffsetRange;
import org.openide.text.NbDocument;

/**
 *
 * @author milos
 */
public class ParseTreeListener extends CoffeeScriptParserGrammarBaseListener {
    
    private Stack<BlockType> stack;
    private StyledDocument doc;
    private Stack <BlockDefinition> blockStack;
    private final Stack<ClassDefinition> classStack;
    private BlockDefinition activeBlock;
    private final List<ClassDefinition> definitions;
    private List<VariableDefinition> params;

    public ParseTreeListener(List<ClassDefinition> definitions, BlockDefinition rootBlock, StyledDocument doc) {
        stack = new Stack<BlockType>();
        this.doc = doc;
        classStack = new Stack<ClassDefinition>();
        blockStack = new Stack<BlockDefinition>();
        activeBlock = rootBlock;
        this.definitions = definitions;
    }

    @Override
    public void enterRoot(CoffeeScriptParserGrammar.RootContext ctx) {        
        activeBlock.setStart(getAbsoluteOffset(ctx.getStart()));
        super.enterRoot(ctx);
    }

    @Override
    public void exitRoot(CoffeeScriptParserGrammar.RootContext ctx) {
        activeBlock.setStop(getAbsoluteOffset(ctx.getStop()));
        super.exitRoot(ctx); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void enterBlock(CoffeeScriptParserGrammar.BlockContext ctx) {
        if(ctx.getParent() instanceof CoffeeScriptParserGrammar.ClassRuleContext) {
            stack.push(BlockType.CLASS);
        } else {
            stack.push(BlockType.OTHER);
        }
        
        blockStack.push(activeBlock);
        
        activeBlock = new BlockDefinition();
        if(ctx.getParent() instanceof  CoffeeScriptParserGrammar.CodeContext) {
            if(params!= null)
            for(Definition d : params) {
                activeBlock.addMember(d.getText(), d);
            }
        }
        activeBlock.setStart(getAbsoluteOffset(ctx.getStart()));        
        super.enterBlock(ctx);
    }

    @Override
    public void exitBlock(CoffeeScriptParserGrammar.BlockContext ctx) {
        if(ctx.getParent() instanceof CoffeeScriptParserGrammar.ClassRuleContext) {
            ClassDefinition classDefinition = classStack.pop();
            classDefinition.setVars(activeBlock.getVars());
            definitions.add(classDefinition);
        } 
        stack.pop();
        BlockDefinition parentBlock = blockStack.pop();
        OffsetRange range = new OffsetRange(activeBlock.getStart(), getAbsoluteOffset(ctx.getStop()));
        parentBlock.addBlock(range, activeBlock);
        activeBlock = parentBlock;
        super.exitBlock(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enterAssign(CoffeeScriptParserGrammar.AssignContext ctx) {
        if (ctx.expression().code() != null) {
            CoffeeScriptParserGrammar.IdentifierContext ident;
            if ((ident = ctx.assignable().identifier()) != null) {
                Token t = null;
                CoffeeScriptParserGrammar.ParamListContext paramListCtx = ctx.expression().code().paramList();
                params = new ArrayList<VariableDefinition>();
//                while (paramListCtx != null) {                    
//                    if (paramListCtx.param() != null) {
//                        if(paramListCtx.param().paramVar().identifier() != null)  {
//                            t = paramListCtx.param().paramVar().identifier().IDENTIFIER().getSymbol();
//                            params.add(new VariableDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(), false));
//                        }
//                        if(paramListCtx.param().paramVar().thisProperty() != null)  {
//                            t = paramListCtx.param().paramVar().thisProperty().identifier().IDENTIFIER().getSymbol();
//                            params.add(new VariableDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(), true));
//                        }
//                    }
////                    paramListCtx = paramListCtx.paramList(0);
//                }
                t = ident.IDENTIFIER().getSymbol();
                Definition def = new MethodDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(),params);
                activeBlock.addMember(t.getText(), def);                
            }
        }
        if (ctx.expression().value() != null) {
            CoffeeScriptParserGrammar.IdentifierContext ident;
            if ((ident = ctx.assignable().identifier()) != null) {
                Token t = ident.IDENTIFIER().getSymbol();
                Definition def = new VariableDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(),false);
                activeBlock.addMember(t.getText(), def);            
            }
        }
        super.enterAssign(ctx);
    }

    @Override
    public void enterAssignObj(CoffeeScriptParserGrammar.AssignObjContext ctx) {
        if(!stack.isEmpty() && stack.peek().equals(BlockType.CLASS)) {
            Token t;
            CoffeeScriptParserGrammar.ObjAssignableContext objAssCtx = ctx.objAssignable();
            if (objAssCtx.alphaNumeric() != null) {
                CoffeeScriptParserGrammar.AlphaNumericContext anCtx = objAssCtx.alphaNumeric();
                if ((anCtx.NUMBER()) != null) {
                    t = anCtx.NUMBER().getSymbol();
                    activeBlock.addMember(anCtx.NUMBER().getText(), new VariableDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(),false));
                }
                if ((anCtx.STRING()) != null) {
                    t = anCtx.STRING().getSymbol();
                    activeBlock.addMember(anCtx.NUMBER().getText(), new VariableDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(),false));
                }                
            }
            
            if (objAssCtx.identifier() != null) {
                t= objAssCtx.identifier().IDENTIFIER().getSymbol();
                addVariable(ctx, t);
            }
            if (objAssCtx.thisProperty() != null) {
                t= objAssCtx.thisProperty().identifier().IDENTIFIER().getSymbol();
                addVariable(ctx, t);
            }
        }
        super.enterAssignObj(ctx);
    }

    @Override
    public void enterClassRule(CoffeeScriptParserGrammar.ClassRuleContext ctx) {
        Token classToken = ctx.CLASS().getSymbol();
        String className = null;
        String parentName = null;      
        if(ctx.simpleAssignable() != null) {
            if(ctx.simpleAssignable().identifier() != null) {
                className = ctx.simpleAssignable().identifier().IDENTIFIER().getText();
            }
        }
        
        if(ctx.expression()!= null) {
            if(ctx.expression().value() != null) {
                if(ctx.expression().value().identifier() != null) {
                    parentName = ctx.expression().value().identifier().IDENTIFIER().getText();
                }
            }
        }
        ClassDefinition classDefinition = new ClassDefinition(className, classToken.getLine(), classToken.getCharPositionInLine(), parentName);
        activeBlock.addMember(classDefinition.getText(), classDefinition);
        classStack.push(classDefinition);
        super.enterClassRule(ctx); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void exitClassRule(CoffeeScriptParserGrammar.ClassRuleContext ctx) {
//        ClassDefinition classDefinition = classStack.pop();
//        classDefinition.setVars(activeBlock.getVars());
//        
//        super.exitClassRule(ctx);
//    }    
    
    
    private void addVariable(CoffeeScriptParserGrammar.AssignObjContext ctx, Token t) {
        if (ctx.expression() == null) return;
        if (ctx.expression().code() != null) {
            if ((ctx.objAssignable().identifier()) != null) {
                Definition def = new MethodDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(),new ArrayList<VariableDefinition>());
                activeBlock.addMember(t.getText(), def);                
            }
        }
        if (ctx.expression().value() != null) {

            if ((ctx.objAssignable().identifier()) != null) {
                Definition def = new VariableDefinition(t.getText(), t.getLine(), t.getCharPositionInLine(),false);
                activeBlock.addMember(t.getText(), def);            
            }
        }
    }
    
    private enum BlockType {
        CLASS,OTHER
    }
    
    private int getAbsoluteOffset(Token token) {
        if(token== null) return 0;
        return NbDocument.findLineOffset(doc, token.getLine()-1) + token.getCharPositionInLine()-1;
    }
    
    
    
}
