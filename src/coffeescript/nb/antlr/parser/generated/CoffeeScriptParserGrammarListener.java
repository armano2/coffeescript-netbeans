// Generated from CoffeeScriptParserGrammar.g4 by ANTLR 4.x
package coffeescript.nb.antlr.parser.generated;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoffeeScriptParserGrammar}.
 */
public interface CoffeeScriptParserGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull CoffeeScriptParserGrammar.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull CoffeeScriptParserGrammar.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull CoffeeScriptParserGrammar.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull CoffeeScriptParserGrammar.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(@NotNull CoffeeScriptParserGrammar.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(@NotNull CoffeeScriptParserGrammar.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull CoffeeScriptParserGrammar.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull CoffeeScriptParserGrammar.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(@NotNull CoffeeScriptParserGrammar.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(@NotNull CoffeeScriptParserGrammar.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#catchRule}.
	 * @param ctx the parse tree
	 */
	void enterCatchRule(@NotNull CoffeeScriptParserGrammar.CatchRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#catchRule}.
	 * @param ctx the parse tree
	 */
	void exitCatchRule(@NotNull CoffeeScriptParserGrammar.CatchRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#simpleAssignableOperation}.
	 * @param ctx the parse tree
	 */
	void enterSimpleAssignableOperation(@NotNull CoffeeScriptParserGrammar.SimpleAssignableOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#simpleAssignableOperation}.
	 * @param ctx the parse tree
	 */
	void exitSimpleAssignableOperation(@NotNull CoffeeScriptParserGrammar.SimpleAssignableOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#optComma}.
	 * @param ctx the parse tree
	 */
	void enterOptComma(@NotNull CoffeeScriptParserGrammar.OptCommaContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#optComma}.
	 * @param ctx the parse tree
	 */
	void exitOptComma(@NotNull CoffeeScriptParserGrammar.OptCommaContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(@NotNull CoffeeScriptParserGrammar.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(@NotNull CoffeeScriptParserGrammar.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#invocation}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(@NotNull CoffeeScriptParserGrammar.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#invocation}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(@NotNull CoffeeScriptParserGrammar.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#optFuncExist}.
	 * @param ctx the parse tree
	 */
	void enterOptFuncExist(@NotNull CoffeeScriptParserGrammar.OptFuncExistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#optFuncExist}.
	 * @param ctx the parse tree
	 */
	void exitOptFuncExist(@NotNull CoffeeScriptParserGrammar.OptFuncExistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#simpleAssignable}.
	 * @param ctx the parse tree
	 */
	void enterSimpleAssignable(@NotNull CoffeeScriptParserGrammar.SimpleAssignableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#simpleAssignable}.
	 * @param ctx the parse tree
	 */
	void exitSimpleAssignable(@NotNull CoffeeScriptParserGrammar.SimpleAssignableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#thisProperty}.
	 * @param ctx the parse tree
	 */
	void enterThisProperty(@NotNull CoffeeScriptParserGrammar.ThisPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#thisProperty}.
	 * @param ctx the parse tree
	 */
	void exitThisProperty(@NotNull CoffeeScriptParserGrammar.ThisPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull CoffeeScriptParserGrammar.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull CoffeeScriptParserGrammar.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#splat}.
	 * @param ctx the parse tree
	 */
	void enterSplat(@NotNull CoffeeScriptParserGrammar.SplatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#splat}.
	 * @param ctx the parse tree
	 */
	void exitSplat(@NotNull CoffeeScriptParserGrammar.SplatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#throwRule}.
	 * @param ctx the parse tree
	 */
	void enterThrowRule(@NotNull CoffeeScriptParserGrammar.ThrowRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#throwRule}.
	 * @param ctx the parse tree
	 */
	void exitThrowRule(@NotNull CoffeeScriptParserGrammar.ThrowRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#when}.
	 * @param ctx the parse tree
	 */
	void enterWhen(@NotNull CoffeeScriptParserGrammar.WhenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#when}.
	 * @param ctx the parse tree
	 */
	void exitWhen(@NotNull CoffeeScriptParserGrammar.WhenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull CoffeeScriptParserGrammar.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull CoffeeScriptParserGrammar.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#whens}.
	 * @param ctx the parse tree
	 */
	void enterWhens(@NotNull CoffeeScriptParserGrammar.WhensContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#whens}.
	 * @param ctx the parse tree
	 */
	void exitWhens(@NotNull CoffeeScriptParserGrammar.WhensContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#parenthetical}.
	 * @param ctx the parse tree
	 */
	void enterParenthetical(@NotNull CoffeeScriptParserGrammar.ParentheticalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#parenthetical}.
	 * @param ctx the parse tree
	 */
	void exitParenthetical(@NotNull CoffeeScriptParserGrammar.ParentheticalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#assignable}.
	 * @param ctx the parse tree
	 */
	void enterAssignable(@NotNull CoffeeScriptParserGrammar.AssignableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#assignable}.
	 * @param ctx the parse tree
	 */
	void exitAssignable(@NotNull CoffeeScriptParserGrammar.AssignableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(@NotNull CoffeeScriptParserGrammar.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(@NotNull CoffeeScriptParserGrammar.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#unaryOperation}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperation(@NotNull CoffeeScriptParserGrammar.UnaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#unaryOperation}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperation(@NotNull CoffeeScriptParserGrammar.UnaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#tryRule}.
	 * @param ctx the parse tree
	 */
	void enterTryRule(@NotNull CoffeeScriptParserGrammar.TryRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#tryRule}.
	 * @param ctx the parse tree
	 */
	void exitTryRule(@NotNull CoffeeScriptParserGrammar.TryRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#literalTokens}.
	 * @param ctx the parse tree
	 */
	void enterLiteralTokens(@NotNull CoffeeScriptParserGrammar.LiteralTokensContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#literalTokens}.
	 * @param ctx the parse tree
	 */
	void exitLiteralTokens(@NotNull CoffeeScriptParserGrammar.LiteralTokensContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#doubleSignOperation}.
	 * @param ctx the parse tree
	 */
	void enterDoubleSignOperation(@NotNull CoffeeScriptParserGrammar.DoubleSignOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#doubleSignOperation}.
	 * @param ctx the parse tree
	 */
	void exitDoubleSignOperation(@NotNull CoffeeScriptParserGrammar.DoubleSignOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#indexValue}.
	 * @param ctx the parse tree
	 */
	void enterIndexValue(@NotNull CoffeeScriptParserGrammar.IndexValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#indexValue}.
	 * @param ctx the parse tree
	 */
	void exitIndexValue(@NotNull CoffeeScriptParserGrammar.IndexValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(@NotNull CoffeeScriptParserGrammar.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(@NotNull CoffeeScriptParserGrammar.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#forVariables}.
	 * @param ctx the parse tree
	 */
	void enterForVariables(@NotNull CoffeeScriptParserGrammar.ForVariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#forVariables}.
	 * @param ctx the parse tree
	 */
	void exitForVariables(@NotNull CoffeeScriptParserGrammar.ForVariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#objAssignable}.
	 * @param ctx the parse tree
	 */
	void enterObjAssignable(@NotNull CoffeeScriptParserGrammar.ObjAssignableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#objAssignable}.
	 * @param ctx the parse tree
	 */
	void exitObjAssignable(@NotNull CoffeeScriptParserGrammar.ObjAssignableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull CoffeeScriptParserGrammar.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull CoffeeScriptParserGrammar.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#simpleArgs}.
	 * @param ctx the parse tree
	 */
	void enterSimpleArgs(@NotNull CoffeeScriptParserGrammar.SimpleArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#simpleArgs}.
	 * @param ctx the parse tree
	 */
	void exitSimpleArgs(@NotNull CoffeeScriptParserGrammar.SimpleArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(@NotNull CoffeeScriptParserGrammar.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(@NotNull CoffeeScriptParserGrammar.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(@NotNull CoffeeScriptParserGrammar.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(@NotNull CoffeeScriptParserGrammar.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(@NotNull CoffeeScriptParserGrammar.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(@NotNull CoffeeScriptParserGrammar.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#forBody}.
	 * @param ctx the parse tree
	 */
	void enterForBody(@NotNull CoffeeScriptParserGrammar.ForBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#forBody}.
	 * @param ctx the parse tree
	 */
	void exitForBody(@NotNull CoffeeScriptParserGrammar.ForBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#alphaNumeric}.
	 * @param ctx the parse tree
	 */
	void enterAlphaNumeric(@NotNull CoffeeScriptParserGrammar.AlphaNumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#alphaNumeric}.
	 * @param ctx the parse tree
	 */
	void exitAlphaNumeric(@NotNull CoffeeScriptParserGrammar.AlphaNumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(@NotNull CoffeeScriptParserGrammar.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(@NotNull CoffeeScriptParserGrammar.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(@NotNull CoffeeScriptParserGrammar.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(@NotNull CoffeeScriptParserGrammar.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#funcGlyph}.
	 * @param ctx the parse tree
	 */
	void enterFuncGlyph(@NotNull CoffeeScriptParserGrammar.FuncGlyphContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#funcGlyph}.
	 * @param ctx the parse tree
	 */
	void exitFuncGlyph(@NotNull CoffeeScriptParserGrammar.FuncGlyphContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#rangeDots}.
	 * @param ctx the parse tree
	 */
	void enterRangeDots(@NotNull CoffeeScriptParserGrammar.RangeDotsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#rangeDots}.
	 * @param ctx the parse tree
	 */
	void exitRangeDots(@NotNull CoffeeScriptParserGrammar.RangeDotsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(@NotNull CoffeeScriptParserGrammar.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(@NotNull CoffeeScriptParserGrammar.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull CoffeeScriptParserGrammar.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull CoffeeScriptParserGrammar.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(@NotNull CoffeeScriptParserGrammar.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(@NotNull CoffeeScriptParserGrammar.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#classRule}.
	 * @param ctx the parse tree
	 */
	void enterClassRule(@NotNull CoffeeScriptParserGrammar.ClassRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#classRule}.
	 * @param ctx the parse tree
	 */
	void exitClassRule(@NotNull CoffeeScriptParserGrammar.ClassRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#whileSource}.
	 * @param ctx the parse tree
	 */
	void enterWhileSource(@NotNull CoffeeScriptParserGrammar.WhileSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#whileSource}.
	 * @param ctx the parse tree
	 */
	void exitWhileSource(@NotNull CoffeeScriptParserGrammar.WhileSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(@NotNull CoffeeScriptParserGrammar.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(@NotNull CoffeeScriptParserGrammar.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#operationRest}.
	 * @param ctx the parse tree
	 */
	void enterOperationRest(@NotNull CoffeeScriptParserGrammar.OperationRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#operationRest}.
	 * @param ctx the parse tree
	 */
	void exitOperationRest(@NotNull CoffeeScriptParserGrammar.OperationRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#switchRule}.
	 * @param ctx the parse tree
	 */
	void enterSwitchRule(@NotNull CoffeeScriptParserGrammar.SwitchRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#switchRule}.
	 * @param ctx the parse tree
	 */
	void exitSwitchRule(@NotNull CoffeeScriptParserGrammar.SwitchRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#returnRule}.
	 * @param ctx the parse tree
	 */
	void enterReturnRule(@NotNull CoffeeScriptParserGrammar.ReturnRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#returnRule}.
	 * @param ctx the parse tree
	 */
	void exitReturnRule(@NotNull CoffeeScriptParserGrammar.ReturnRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#ifBlock}.
	 * @param ctx the parse tree
	 */
	void enterIfBlock(@NotNull CoffeeScriptParserGrammar.IfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#ifBlock}.
	 * @param ctx the parse tree
	 */
	void exitIfBlock(@NotNull CoffeeScriptParserGrammar.IfBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#thisRule}.
	 * @param ctx the parse tree
	 */
	void enterThisRule(@NotNull CoffeeScriptParserGrammar.ThisRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#thisRule}.
	 * @param ctx the parse tree
	 */
	void exitThisRule(@NotNull CoffeeScriptParserGrammar.ThisRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#forSource}.
	 * @param ctx the parse tree
	 */
	void enterForSource(@NotNull CoffeeScriptParserGrammar.ForSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#forSource}.
	 * @param ctx the parse tree
	 */
	void exitForSource(@NotNull CoffeeScriptParserGrammar.ForSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull CoffeeScriptParserGrammar.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull CoffeeScriptParserGrammar.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#accessor}.
	 * @param ctx the parse tree
	 */
	void enterAccessor(@NotNull CoffeeScriptParserGrammar.AccessorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#accessor}.
	 * @param ctx the parse tree
	 */
	void exitAccessor(@NotNull CoffeeScriptParserGrammar.AccessorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#commonAssignable}.
	 * @param ctx the parse tree
	 */
	void enterCommonAssignable(@NotNull CoffeeScriptParserGrammar.CommonAssignableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#commonAssignable}.
	 * @param ctx the parse tree
	 */
	void exitCommonAssignable(@NotNull CoffeeScriptParserGrammar.CommonAssignableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#slice}.
	 * @param ctx the parse tree
	 */
	void enterSlice(@NotNull CoffeeScriptParserGrammar.SliceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#slice}.
	 * @param ctx the parse tree
	 */
	void exitSlice(@NotNull CoffeeScriptParserGrammar.SliceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#forStart}.
	 * @param ctx the parse tree
	 */
	void enterForStart(@NotNull CoffeeScriptParserGrammar.ForStartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#forStart}.
	 * @param ctx the parse tree
	 */
	void exitForStart(@NotNull CoffeeScriptParserGrammar.ForStartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#assignObj}.
	 * @param ctx the parse tree
	 */
	void enterAssignObj(@NotNull CoffeeScriptParserGrammar.AssignObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#assignObj}.
	 * @param ctx the parse tree
	 */
	void exitAssignObj(@NotNull CoffeeScriptParserGrammar.AssignObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#assignList}.
	 * @param ctx the parse tree
	 */
	void enterAssignList(@NotNull CoffeeScriptParserGrammar.AssignListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#assignList}.
	 * @param ctx the parse tree
	 */
	void exitAssignList(@NotNull CoffeeScriptParserGrammar.AssignListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoffeeScriptParserGrammar#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull CoffeeScriptParserGrammar.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoffeeScriptParserGrammar#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull CoffeeScriptParserGrammar.LiteralContext ctx);
}