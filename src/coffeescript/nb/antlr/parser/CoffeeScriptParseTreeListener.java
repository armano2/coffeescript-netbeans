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

package coffeescript.nb.antlr.parser;

import coffeescript.nb.antlr.parser.generated.CoffeeScriptParserGrammarBaseListener;
import coffeescript.nb.antlr.parser.generated.CoffeeScriptParserGrammar;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import coffeescript.nb.parser.ErrorDefinition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.antlr.v4.runtime.Token;
import org.netbeans.editor.BaseDocument;
import org.netbeans.editor.Utilities;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptParseTreeListener extends CoffeeScriptParserGrammarBaseListener {
    
    private final Stack<Block> stack;
    private final StyledDocument doc;
    private final List<VariableDefinition> variables;
    private final List<MethodDefinition> methods;
    private final List<ClassDefinition> rootClasses;
    private final List<MethodDefinition> rootMethods;
    private final List<VariableDefinition> rootVariables;
    private final Stack<ClassDefinition> classStack; 
    private final List<ErrorDefinition> errors;
    private boolean newDefinitionsInArray;
    private boolean objectAssign;
    private int anonymousCounter;
    
    public CoffeeScriptParseTreeListener(StyledDocument doc) {
        this.doc = doc;
        this.stack = new Stack<Block>();        
        this.variables = new ArrayList<VariableDefinition>();
        this.methods = new ArrayList<MethodDefinition>();
        this.classStack = new Stack<ClassDefinition>();
        this.rootClasses = new ArrayList<ClassDefinition>();        
        this.rootMethods = new ArrayList<MethodDefinition>(); 
        this.rootVariables = new ArrayList<VariableDefinition>(); 
        this.errors = new ArrayList<ErrorDefinition>();
    }

    @Override
    public void enterRoot(CoffeeScriptParserGrammar.RootContext ctx) {              
        stack.push(new Block(getAbsoluteOffset(ctx.start), getBlockEnd(ctx.stop), BlockType.ROOT));
        super.enterRoot(ctx);
    }
    
    @Override
    public void exitRoot(CoffeeScriptParserGrammar.RootContext ctx) {              
        addRootDefinitions(stack.pop());
    }

    @Override
    public void enterBlock(CoffeeScriptParserGrammar.BlockContext ctx) {
        if(ctx.getParent() instanceof CoffeeScriptParserGrammar.ClassRuleContext) {            
            stack.push(new Block(getAbsoluteOffset(ctx.start), getBlockEnd(ctx.stop), BlockType.CLASS));
        } else if(ctx.getParent() instanceof CoffeeScriptParserGrammar.CodeContext) {
            stack.push(new Block(getAbsoluteOffset(ctx.start), getBlockEnd(ctx.stop), BlockType.METHOD));
        }       
        super.enterBlock(ctx);
    }

    @Override
    public void enterCode(CoffeeScriptParserGrammar.CodeContext ctx) {
        List<Definition> params = new ArrayList<Definition>();
        int startOffset=0;
        int endOffset=0;
        Token t = null;
        boolean classMember = false;
        getMethodParams(ctx, params);
        Collections.reverse(params);
        
        if(ctx.getParent().getParent() instanceof CoffeeScriptParserGrammar.AssignContext) {
            CoffeeScriptParserGrammar.AssignContext assign = (CoffeeScriptParserGrammar.AssignContext) ctx.getParent().getParent();
            if (assign.assignable() != null) {  
                if(assign.assignable().identifier() != null) {
                    t = assign.assignable().identifier().IDENTIFIER().getSymbol();
                    startOffset = getAbsoluteOffset(t);
                    endOffset = startOffset + t.getText().length();
                }     
                if(assign.assignable().thisProperty() != null) {
                    t = assign.assignable().thisProperty().identifier().IDENTIFIER().getSymbol();
                    startOffset = getAbsoluteOffset(t);
                    endOffset = startOffset + t.getText().length();
                    classMember = true;
                }  
            }    
        } else if(ctx.getParent().getParent() instanceof CoffeeScriptParserGrammar.AssignObjContext) {
            classMember = true;
            CoffeeScriptParserGrammar.AssignObjContext assign = (CoffeeScriptParserGrammar.AssignObjContext) ctx.getParent().getParent();
            if (assign.objAssignable().identifier() != null) {                
                t = assign.objAssignable().identifier().IDENTIFIER().getSymbol();
                startOffset = getAbsoluteOffset(t);
                endOffset = startOffset + t.getText().length();
            }
            if (assign.objAssignable().thisProperty() != null) {                
                t = assign.objAssignable().thisProperty().identifier().IDENTIFIER().getSymbol();
                startOffset = getAbsoluteOffset(t);
                endOffset = startOffset + t.getText().length();
            }
        } else {
            startOffset = getAbsoluteOffset(ctx.start);
            endOffset = getAbsoluteOffset(ctx.stop);
        }
        MethodDefinition method = (classMember) ? getClassMethod(t, startOffset, endOffset, params) 
                : getMethod(t, startOffset, endOffset, params);
        addMethod(method);
        super.enterCode(ctx);
    }
    
    

    @Override
    public void exitBlock(CoffeeScriptParserGrammar.BlockContext ctx) {
        if(ctx.getParent() instanceof CoffeeScriptParserGrammar.ClassRuleContext) {            
            classStack.pop(); 
            stack.pop();
        } else if(ctx.getParent() instanceof CoffeeScriptParserGrammar.CodeContext) {
            addDefinitions(stack.pop());
        }
        super.exitBlock(ctx);
    }

    @Override
    public void enterAssign(CoffeeScriptParserGrammar.AssignContext ctx) {
        if(ctx.expression() == null) {
            super.enterAssign(ctx);
            return;
        }
        Token t = null;
        if(ctx.assignable().identifier() != null) {
            t = ctx.assignable().identifier().IDENTIFIER().getSymbol();
        }
        
        if(ctx.assignable().thisProperty()!= null) {
            t = ctx.assignable().thisProperty().identifier().IDENTIFIER().getSymbol();
        }
        
        handleExpression(ctx.expression(), t, ctx.assignable().thisProperty()!= null);

        super.enterAssign(ctx);
    }

    @Override
    public void exitCommonAssignable(CoffeeScriptParserGrammar.CommonAssignableContext ctx) {
        if(ctx.array() != null) newDefinitionsInArray = false;
        if(ctx.object() != null) objectAssign = false;
    }

    @Override
    public void enterCommonAssignable(CoffeeScriptParserGrammar.CommonAssignableContext ctx) {
        if(ctx.array() != null) newDefinitionsInArray = true;
        if(ctx.object() != null) objectAssign = true;
    }

    @Override
    public void exitAssignable(CoffeeScriptParserGrammar.AssignableContext ctx) {
        if(ctx.array() != null) newDefinitionsInArray = false;
        if(ctx.object() != null) objectAssign = false;
    }

    @Override
    public void enterAssignable(CoffeeScriptParserGrammar.AssignableContext ctx) {
        if(ctx.array() != null) newDefinitionsInArray = true;
        if(ctx.object() != null) objectAssign = true;
    }
    
    private void getMethodParams(CoffeeScriptParserGrammar.CodeContext ctx, List<Definition> params) {
        CoffeeScriptParserGrammar.ParamListContext paramListCtx = ctx.paramList(); 
        Token t = null;
        stack.push(new Block(getAbsoluteOffset(ctx.start), getBlockEnd(ctx.stop), BlockType.METHOD));
        while (paramListCtx != null) {              
            if (paramListCtx.param() != null) {
                if(paramListCtx.param().commonAssignable() != null) {
                    VariableDefinition def = null;
                    if(paramListCtx.param().commonAssignable().identifier() != null)  {
                        t = paramListCtx.param().commonAssignable().identifier().IDENTIFIER().getSymbol();
                        def = getVariable(t, paramListCtx.param().TRIPLE_DOT_TOK() != null, true, false);
                    }
                    if(paramListCtx.param().commonAssignable().thisProperty() != null)  {
                        t = paramListCtx.param().commonAssignable().thisProperty().identifier().IDENTIFIER().getSymbol();
                        def = getClassVariable(t, paramListCtx.param().TRIPLE_DOT_TOK() != null, false, false);
                        addVariable(def);
                    }
                    if(def != null) {
                        params.add(def);                        
                    }
                }
            }
            paramListCtx = paramListCtx.paramList(0);
        }
        stack.pop();
    }

    @Override
    public void enterAssignObj(CoffeeScriptParserGrammar.AssignObjContext ctx) {
        if(inClassBlock()) {
            if(ctx.comment() != null) {
                super.enterAssignObj(ctx);
                return;
            }
                
            Token t = null;
            CoffeeScriptParserGrammar.ObjAssignableContext objAssCtx = ctx.objAssignable();            
            if (objAssCtx.alphaNumeric() != null) {
                CoffeeScriptParserGrammar.AlphaNumericContext anCtx = objAssCtx.alphaNumeric();
                if ((anCtx.NUMBER()) != null) {
                    t = anCtx.NUMBER().getSymbol();
                }
                if ((anCtx.STRING()) != null) {
                    t = anCtx.STRING().getSymbol();
                }                
            } else if (objAssCtx.identifier() != null) {
                t = objAssCtx.identifier().IDENTIFIER().getSymbol();
            } else if (objAssCtx.thisProperty() != null) {
                t = objAssCtx.thisProperty().identifier().IDENTIFIER().getSymbol();
            }
            if(ctx.expression() != null)
                handleExpression(ctx.expression(), t, true);
            else {
                VariableDefinition def = getClassVariable(t, false, false, inMethodBlock());
                addVariable(def);
            }
                
        }
        
        if(objectAssign) {
            CoffeeScriptParserGrammar.ObjAssignableContext objAssCtx = ctx.objAssignable();
            Token t = null;
            boolean classMember = false;
            if(ctx.expression() == null) {
                if (objAssCtx.identifier() != null) {
                    t = objAssCtx.identifier().IDENTIFIER().getSymbol();
                }
                if (objAssCtx.thisProperty() != null) {
                    t = objAssCtx.thisProperty().identifier().IDENTIFIER().getSymbol();
                    classMember = true;
                }
            } else {
                t = findIdentifierInExpression(ctx.expression());
                classMember = thisIdentifierInExpression(ctx.expression());
            }
            VariableDefinition def = (classMember) ? getClassVariable(t, false, false, inMethodBlock()):
                    getVariable(t, false, false, inMethodBlock());
            addVariable(def);
         
        }
        super.enterAssignObj(ctx);
    }
    
    private boolean thisIdentifierInExpression(CoffeeScriptParserGrammar.ExpressionContext ctx) {
        return ((ctx.value() != null) && (ctx.value().thisProperty() != null));
    }
    
    private Token findIdentifierInExpression(CoffeeScriptParserGrammar.ExpressionContext ctx) {
        if(ctx.value()!= null) {
            if(ctx.value().identifier() != null) {
                return ctx.value().identifier().IDENTIFIER().getSymbol();
            }
            if(ctx.value().thisProperty()!= null) {
                return ctx.value().thisProperty().identifier().IDENTIFIER().getSymbol();
            }
        }
        return null;
    }

    @Override
    public void enterClassRule(CoffeeScriptParserGrammar.ClassRuleContext ctx) {
        String className = null;
        String parentName = null;      
        if(ctx.simpleAssignable() != null) {
            if(ctx.simpleAssignable().identifier() != null) {
                className = ctx.simpleAssignable().identifier().IDENTIFIER().getText();
            }
            if(ctx.simpleAssignable().accessor() != null) {
                if(ctx.simpleAssignable().accessor().identifier() != null)
                    className = ctx.simpleAssignable().accessor().identifier().IDENTIFIER().getText();
            }
        }
        
        if(ctx.expression()!= null) {
            if(ctx.expression().value() != null) {
                if(ctx.expression().value().identifier() != null) {
                    parentName = ctx.expression().value().identifier().IDENTIFIER().getText();
                }
            }
        }
        if(className != null) {
            ClassDefinition classDefinition = getClass(className, ctx.start, ctx.stop, parentName);
            classStack.push(classDefinition);
            addClass(classDefinition);
        }
        super.enterClassRule(ctx);
    }

    @Override
    public void enterArg(CoffeeScriptParserGrammar.ArgContext ctx) {
        if(newDefinitionsInArray) {
            if(ctx.expression() != null) {
                if(ctx.expression().value() != null) {
                    VariableDefinition def = null;
                    if(ctx.expression().value().identifier() != null) {
                        Token t = ctx.expression().value().identifier().IDENTIFIER().getSymbol();
                        def = getVariable(t, false, false, inMethodBlock());
                    }
                    if(ctx.expression().value().thisProperty()!= null) {
                        Token t = ctx.expression().value().thisProperty().identifier().IDENTIFIER().getSymbol();
                        def = getClassVariable(t, false, false, inMethodBlock());
                    }
                    addVariable(def);
                }
            }
        }
        super.enterArg(ctx);
    }
    
    private void handleExpression(CoffeeScriptParserGrammar.ExpressionContext ctx, Token t, boolean thisVariable) {
        if(t == null) return;
        if (ctx.classRule() == null && ctx.code() == null) {
            VariableDefinition def = (thisVariable) ? getClassVariable(t, false, false, inMethodBlock()) :
                    getVariable(t, false, false, inMethodBlock());
            addVariable(def);
        }
    }    
    
    private void addVariable(VariableDefinition def) {
        if(def == null) return ;
        if(def.isClassMember()) {
            addClassVariable(def);
        } else if(inRootBlock() || inMethodBlock()) {
            if(!isFieldDefined(def)) this.stack.peek().addField(def);
        }
    }
    
    private void addMethod(MethodDefinition def) {
        if(def == null) return ;
        if(def.isClassMember()) {
            addClassMethod(def);
        } else if(inRootBlock() || inMethodBlock()) {
            if(!isMethodDefined(def)) this.stack.peek().addMethod(def);
        }
    }

    private void addClass(ClassDefinition def) {
        if(def == null) return ;
        if(inRootBlock()) {
            this.stack.peek().addClass(def);
        } 
    }
    
    private boolean isFieldDefined(VariableDefinition def) {
        boolean contains = false;
        for (Block block : this.stack) {
            contains |= block.containsField(def);
        }
        return contains;
    }
    
    private boolean isMethodDefined(MethodDefinition def) {
        boolean contains = false;
        for (Block block : this.stack) {
            contains |= block.containsMethod(def);
        }
        return !def.isAnonymous() && contains;
    }
    
    private boolean inRootBlock() {
        return this.stack.peek().type.equals(BlockType.ROOT);
    }
    
    private boolean inClassBlock() {
        return this.stack.peek().type.equals(BlockType.CLASS);
    }
    
    private boolean inMethodBlock() {
        return this.stack.peek().type.equals(BlockType.METHOD);
    }
    
    private int getAbsoluteOffset(Token token) {
        if(token == null) return 0;
        int line = token.getLine();
        int lineCount = Utilities.getRowCount((BaseDocument)doc);
        if(lineCount < line + 1) {
            errors.add(new ErrorDefinition(lineCount, 0, "Line index out of bounds."));
            line = lineCount;
        }
        return NbDocument.findLineOffset(doc, line) + token.getCharPositionInLine();
    }  
    
    private int getBlockEnd(Token token) {
        BaseDocument document = (BaseDocument) doc;
        int offset = getAbsoluteOffset(token);
        try {       
            int end = Utilities.getFirstNonWhiteFwd(document, offset);
            return (end > -1) ? end : getLastCharacterOffset();
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IndexOutOfBoundsException ex) {   
            Exceptions.printStackTrace(ex);
        }
        // ignore exceptions - if bad location exception - accept absolute token offset
        return offset;
    }    
    
    private int getLastCharacterOffset() throws BadLocationException {
        BaseDocument document = (BaseDocument) doc;
        int lastLineOffset = NbDocument.findLineOffset(doc,Utilities.getRowCount(document)-1);
        return Utilities.getRowEnd(document, lastLineOffset);
    }
    
    private VariableDefinition getVariable(Token t, boolean splat, boolean methodParam, boolean protectedField) {
        if(t == null) return null;
        int start = getAbsoluteOffset(t);
        int end = start + t.getText().length();
        int blockStart = this.stack.peek().getStartOffset();
        int blockEnd = this.stack.peek().getEndOffset();
        return new VariableDefinition(t.getText(), start, end, blockStart, blockEnd, false ,splat, methodParam, protectedField);        
    }
    
    private VariableDefinition getClassVariable(Token t, boolean splat, boolean methodParam, boolean protectedField) {
        if(t == null || classStack.isEmpty()) return null;
        
        int start = getAbsoluteOffset(t);
        int end = start + t.getText().length();
        int blockStart = this.classStack.peek().getStartOffset();
        int blockEnd = this.classStack.peek().getEndOffset();
        return new VariableDefinition(t.getText(), start, end, blockStart, blockEnd, true ,splat, methodParam, protectedField);        
    }
    
    private ClassDefinition getClass(String className, Token startToken, Token stopToken, String parentName) {
        int start = getAbsoluteOffset(startToken);
        int end = getBlockEnd(stopToken);
        int blockStart = this.stack.peek().getStartOffset();
        int blockEnd = this.stack.peek().getEndOffset();
        return new ClassDefinition(className, start, end, blockStart, blockEnd, parentName);      
    }
    
    private MethodDefinition getMethod(Token t, int startOffset, int endOffset, List<Definition> params) {
        int blockStart = this.stack.peek().getStartOffset();
        int blockEnd = this.stack.peek().getEndOffset();
        boolean anonymous = false;  
        String text;
        if(t == null) {
            anonymous = true;
            text = "anonymous" + anonymousCounter++;
        } else {
            text = t.getText();
        }
        return new MethodDefinition(text, startOffset, endOffset, blockStart, blockEnd, false, params, anonymous); 
    }  
    
    private MethodDefinition getClassMethod(Token t, int startOffset, int endOffset, List<Definition> params) {
        if(classStack.isEmpty()) return null;
        int blockStart = this.classStack.peek().getStartOffset();
        int blockEnd = this.classStack.peek().getEndOffset();
        String text = (t != null) ? t.getText() : null;
        return new MethodDefinition(text, startOffset, endOffset, blockStart, blockEnd, true, params, t == null); 
    } 


    
    private void addRootDefinitions(Block block) {
        rootClasses.addAll(block.getClasses());
        rootMethods.addAll(block.getMethods());
        rootVariables.addAll(block.getFields());
    }
    
    private void addDefinitions(Block block) {
        variables.addAll(block.getFields());
        methods.addAll(block.getMethods());
    }

    public Collection<VariableDefinition> getVariables() {
        return variables;
    }

    public Collection<MethodDefinition> getMethods() {
        return methods;
    }
    
    public Collection<MethodDefinition> getRootMethods() {
        return rootMethods;
    }

    public Collection<VariableDefinition> getRootVariables() {
        return rootVariables;
    }
    
    public Collection<ClassDefinition> getRootClasses() {
        return rootClasses;
    }

    public List<ErrorDefinition> getErrors() {
        return errors;
    }
    
    private void addClassVariable(VariableDefinition def) {
        if(!classStack.isEmpty()) classStack.peek().addField(def);
    }
    
    private void addClassMethod(MethodDefinition def) {
        if(!classStack.isEmpty()) {
            if(def.getText().equals("constructor")) {
                classStack.peek().addConstructor(def);
            } else {
                classStack.peek().addMethod(def);
            }
        }
    }
    
    private enum BlockType {
        ROOT, METHOD, CLASS
    }
    
    private class Block {
        private int startOffset;
        private int endOffset;
        private BlockType type;
        private final Map<String, VariableDefinition> fields = new HashMap<String, VariableDefinition>();
        private final Map<String, MethodDefinition> methods = new HashMap<String, MethodDefinition>();
        private final Map<String, ClassDefinition> classes = new HashMap<String, ClassDefinition>();

        public Block(int startOffset, int endOffset, BlockType type) {
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.type = type;
        }

        public int getStartOffset() {
            return startOffset;
        }

        public void setStartOffset(int startOffset) {
            this.startOffset = startOffset;
        }

        public int getEndOffset() {
            return endOffset;
        }

        public void setEndOffset(int endOffset) {
            this.endOffset = endOffset;
        }

        public BlockType getType() {
            return type;
        }

        public void setType(BlockType type) {
            this.type = type;
        }
        
        private void addField(VariableDefinition def) {
            if(!fields.containsKey(def.getText())) fields.put(def.getText(), def);
        }
               
        private void addMethod(MethodDefinition def) {
            if(!methods.containsKey(def.getText())) methods.put(def.getText(), def);
        }
        
        private void addClass(ClassDefinition def) {
            if(!classes.containsKey(def.getText())) classes.put(def.getText(), def);
        }

        public Collection<VariableDefinition> getFields() {
            return fields.values();
        }

        public Collection<MethodDefinition> getMethods() {
            return methods.values();
        }

        public Collection<ClassDefinition> getClasses() {
            return classes.values();
        }   
        
        public boolean containsField(VariableDefinition def) {
            return fields.containsKey(def.getText());
        }
        
        public boolean containsMethod(MethodDefinition def) {
            return methods.containsKey(def.getText());
        }
        
        public boolean containsClass(ClassDefinition def) {
            return classes.containsKey(def.getText());
        }
    }    
}
