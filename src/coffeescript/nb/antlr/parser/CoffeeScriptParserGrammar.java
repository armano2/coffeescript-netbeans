// Generated from CoffeeScriptParserGrammar.g4 by ANTLR 4.2.2
package coffeescript.nb.antlr.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoffeeScriptParserGrammar extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MINUS_TOK=1, DOUBLE_MINUS_TOK=2, FUNC_SINGLE_ARROW=3, COMMA_TOK=4, COLON_TOK=5, 
		DOUBLE_COLON_TOK=6, QUEST_DOUBLE_COLON_TOK=7, QUEST_DOT_TOK=8, DOT_TOK=9, 
		DOUBLE_DOT_TOK=10, TRIPLE_DOT_TOK=11, LPAR=12, RPAR=13, LBRACKET=14, RBRACKET=15, 
		LBRACE=16, RBRACE=17, AT_TOK=18, DOUBLE_STAR=19, PLUS_TOK=20, DOUBLE_PLUS_TOK=21, 
		EQUATING_TOK=22, FUNC_DOUBLE_ARROW=23, QUEST_TOK=24, TERMINATOR=25, STATEMENT=26, 
		POST_IF=27, ELSE=28, INDENT=29, OUTDENT=30, IDENTIFIER=31, NUMBER=32, 
		STRING=33, JS=34, REGEX=35, DEBUGGER=36, UNDEFINED=37, NULL=38, BOOL=39, 
		RETURN=40, HERECOMMENT=41, PARAM_START=42, PARAM_END=43, SUPER=44, INDEX_START=45, 
		INDEX_END=46, INDEX_SOAK=47, CLASS=48, EXTENDS=49, FUNC_EXIST=50, CALL_START=51, 
		CALL_END=52, THIS=53, TRY=54, FINALLY=55, CATCH=56, THROW=57, WHILE=58, 
		WHEN=59, UNTIL=60, LOOP=61, FOR=62, OWN=63, FORIN=64, FOROF=65, BY=66, 
		SWITCH=67, LEADING_WHEN=68, IF=69, UNARY=70, UNARY_MATH=71, COMPOUND_ASSIGN=72, 
		MATH=73, SHIFT=74, COMPARE=75, LOGIC=76, RELATION=77, BAD_TOKEN=78;
	public static final String[] tokenNames = {
		"<INVALID>", "MINUS_TOK", "DOUBLE_MINUS_TOK", "FUNC_SINGLE_ARROW", "COMMA_TOK", 
		"COLON_TOK", "DOUBLE_COLON_TOK", "QUEST_DOUBLE_COLON_TOK", "QUEST_DOT_TOK", 
		"DOT_TOK", "DOUBLE_DOT_TOK", "TRIPLE_DOT_TOK", "LPAR", "RPAR", "LBRACKET", 
		"RBRACKET", "LBRACE", "RBRACE", "AT_TOK", "DOUBLE_STAR", "PLUS_TOK", "DOUBLE_PLUS_TOK", 
		"EQUATING_TOK", "FUNC_DOUBLE_ARROW", "QUEST_TOK", "TERMINATOR", "STATEMENT", 
		"POST_IF", "ELSE", "INDENT", "OUTDENT", "IDENTIFIER", "NUMBER", "STRING", 
		"JS", "REGEX", "DEBUGGER", "UNDEFINED", "NULL", "BOOL", "RETURN", "HERECOMMENT", 
		"PARAM_START", "PARAM_END", "SUPER", "INDEX_START", "INDEX_END", "INDEX_SOAK", 
		"CLASS", "EXTENDS", "FUNC_EXIST", "CALL_START", "CALL_END", "THIS", "TRY", 
		"FINALLY", "CATCH", "THROW", "WHILE", "WHEN", "UNTIL", "LOOP", "FOR", 
		"OWN", "FORIN", "FOROF", "BY", "SWITCH", "LEADING_WHEN", "IF", "UNARY", 
		"UNARY_MATH", "COMPOUND_ASSIGN", "MATH", "SHIFT", "COMPARE", "LOGIC", 
		"RELATION", "BAD_TOKEN"
	};
	public static final int
		RULE_root = 0, RULE_body = 1, RULE_line = 2, RULE_statement = 3, RULE_expression = 4, 
		RULE_block = 5, RULE_identifier = 6, RULE_alphaNumeric = 7, RULE_literal = 8, 
		RULE_assign = 9, RULE_assignObj = 10, RULE_objAssignable = 11, RULE_returnRule = 12, 
		RULE_comment = 13, RULE_code = 14, RULE_funcGlyph = 15, RULE_optComma = 16, 
		RULE_paramList = 17, RULE_param = 18, RULE_commonAssignable = 19, RULE_splat = 20, 
		RULE_simpleAssignable = 21, RULE_assignable = 22, RULE_value = 23, RULE_accessor = 24, 
		RULE_index = 25, RULE_indexValue = 26, RULE_object = 27, RULE_assignList = 28, 
		RULE_classRule = 29, RULE_invocation = 30, RULE_optFuncExist = 31, RULE_arguments = 32, 
		RULE_thisRule = 33, RULE_thisProperty = 34, RULE_array = 35, RULE_rangeDots = 36, 
		RULE_range = 37, RULE_slice = 38, RULE_argList = 39, RULE_arg = 40, RULE_simpleArgs = 41, 
		RULE_tryRule = 42, RULE_catchRule = 43, RULE_throwRule = 44, RULE_parenthetical = 45, 
		RULE_whileSource = 46, RULE_loop = 47, RULE_forBody = 48, RULE_forStart = 49, 
		RULE_forVariables = 50, RULE_forSource = 51, RULE_switchRule = 52, RULE_whens = 53, 
		RULE_when = 54, RULE_ifBlock = 55, RULE_unaryOperation = 56, RULE_doubleSignOperation = 57, 
		RULE_simpleAssignableOperation = 58, RULE_operationRest = 59, RULE_literalTokens = 60;
	public static final String[] ruleNames = {
		"root", "body", "line", "statement", "expression", "block", "identifier", 
		"alphaNumeric", "literal", "assign", "assignObj", "objAssignable", "returnRule", 
		"comment", "code", "funcGlyph", "optComma", "paramList", "param", "commonAssignable", 
		"splat", "simpleAssignable", "assignable", "value", "accessor", "index", 
		"indexValue", "object", "assignList", "classRule", "invocation", "optFuncExist", 
		"arguments", "thisRule", "thisProperty", "array", "rangeDots", "range", 
		"slice", "argList", "arg", "simpleArgs", "tryRule", "catchRule", "throwRule", 
		"parenthetical", "whileSource", "loop", "forBody", "forStart", "forVariables", 
		"forSource", "switchRule", "whens", "when", "ifBlock", "unaryOperation", 
		"doubleSignOperation", "simpleAssignableOperation", "operationRest", "literalTokens"
	};

	@Override
	public String getGrammarFileName() { return "CoffeeScriptParserGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoffeeScriptParserGrammar(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			setState(124);
			switch (_input.LA(1)) {
			case MINUS_TOK:
			case DOUBLE_MINUS_TOK:
			case FUNC_SINGLE_ARROW:
			case LPAR:
			case LBRACKET:
			case LBRACE:
			case AT_TOK:
			case PLUS_TOK:
			case DOUBLE_PLUS_TOK:
			case FUNC_DOUBLE_ARROW:
			case STATEMENT:
			case IDENTIFIER:
			case NUMBER:
			case STRING:
			case JS:
			case REGEX:
			case DEBUGGER:
			case UNDEFINED:
			case NULL:
			case BOOL:
			case RETURN:
			case HERECOMMENT:
			case PARAM_START:
			case SUPER:
			case CLASS:
			case THIS:
			case TRY:
			case THROW:
			case WHILE:
			case UNTIL:
			case LOOP:
			case FOR:
			case SWITCH:
			case IF:
			case UNARY:
			case UNARY_MATH:
				enterOuterAlt(_localctx, 1);
				{
				setState(122); body(0);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public LineContext line() {
			return getRuleContext(LineContext.class,0);
		}
		public TerminalNode TERMINATOR() { return getToken(CoffeeScriptParserGrammar.TERMINATOR, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		return body(0);
	}

	private BodyContext body(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BodyContext _localctx = new BodyContext(_ctx, _parentState);
		BodyContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_body, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(127); line();
			}
			_ctx.stop = _input.LT(-1);
			setState(136);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(134);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new BodyContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body);
						setState(129);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(130); match(TERMINATOR);
						setState(131); line();
						}
						break;

					case 2:
						{
						_localctx = new BodyContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body);
						setState(132);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(133); match(TERMINATOR);
						}
						break;
					}
					} 
				}
				setState(138);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_line);
		try {
			setState(141);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140); statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public ReturnRuleContext returnRule() {
			return getRuleContext(ReturnRuleContext.class,0);
		}
		public TerminalNode STATEMENT() { return getToken(CoffeeScriptParserGrammar.STATEMENT, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(146);
			switch (_input.LA(1)) {
			case RETURN:
				enterOuterAlt(_localctx, 1);
				{
				setState(143); returnRule();
				}
				break;
			case HERECOMMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(144); comment();
				}
				break;
			case STATEMENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(145); match(STATEMENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ThrowRuleContext throwRule() {
			return getRuleContext(ThrowRuleContext.class,0);
		}
		public SimpleAssignableOperationContext simpleAssignableOperation() {
			return getRuleContext(SimpleAssignableOperationContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public WhileSourceContext whileSource() {
			return getRuleContext(WhileSourceContext.class,0);
		}
		public DoubleSignOperationContext doubleSignOperation() {
			return getRuleContext(DoubleSignOperationContext.class,0);
		}
		public TryRuleContext tryRule() {
			return getRuleContext(TryRuleContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ClassRuleContext classRule() {
			return getRuleContext(ClassRuleContext.class,0);
		}
		public UnaryOperationContext unaryOperation() {
			return getRuleContext(UnaryOperationContext.class,0);
		}
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public SwitchRuleContext switchRule() {
			return getRuleContext(SwitchRuleContext.class,0);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class,0);
		}
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ForBodyContext forBody() {
			return getRuleContext(ForBodyContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(CoffeeScriptParserGrammar.ELSE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public OperationRestContext operationRest() {
			return getRuleContext(OperationRestContext.class,0);
		}
		public TerminalNode POST_IF() { return getToken(CoffeeScriptParserGrammar.POST_IF, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(149); statement();
				setState(150); match(POST_IF);
				setState(151); expression(13);
				}
				break;

			case 2:
				{
				setState(153); value(0);
				}
				break;

			case 3:
				{
				setState(154); invocation(0);
				}
				break;

			case 4:
				{
				setState(155); code();
				}
				break;

			case 5:
				{
				setState(156); unaryOperation();
				}
				break;

			case 6:
				{
				setState(157); doubleSignOperation();
				}
				break;

			case 7:
				{
				setState(158); simpleAssignableOperation();
				}
				break;

			case 8:
				{
				setState(159); assign();
				}
				break;

			case 9:
				{
				setState(160); ifBlock(0);
				}
				break;

			case 10:
				{
				setState(161); ifBlock(0);
				setState(162); match(ELSE);
				setState(163); block();
				}
				break;

			case 11:
				{
				setState(165); tryRule();
				}
				break;

			case 12:
				{
				setState(166); whileSource();
				setState(167); block();
				}
				break;

			case 13:
				{
				setState(169); statement();
				setState(170); whileSource();
				}
				break;

			case 14:
				{
				setState(172); loop();
				}
				break;

			case 15:
				{
				setState(173); statement();
				setState(174); forBody();
				}
				break;

			case 16:
				{
				setState(176); forBody();
				setState(177); block();
				}
				break;

			case 17:
				{
				setState(179); switchRule();
				}
				break;

			case 18:
				{
				setState(180); classRule();
				}
				break;

			case 19:
				{
				setState(181); throwRule();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(185); match(POST_IF);
						setState(186); expression(13);
						}
						break;

					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(188); operationRest();
						}
						break;

					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(190); whileSource();
						}
						break;

					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(192); forBody();
						}
						break;
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_block);
		try {
			setState(204);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198); match(INDENT);
				setState(199); match(OUTDENT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(200); match(INDENT);
				setState(201); body(0);
				setState(202); match(OUTDENT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CoffeeScriptParserGrammar.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlphaNumericContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(CoffeeScriptParserGrammar.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(CoffeeScriptParserGrammar.STRING, 0); }
		public AlphaNumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alphaNumeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterAlphaNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitAlphaNumeric(this);
		}
	}

	public final AlphaNumericContext alphaNumeric() throws RecognitionException {
		AlphaNumericContext _localctx = new AlphaNumericContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_alphaNumeric);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_la = _input.LA(1);
			if ( !(_la==NUMBER || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralTokensContext literalTokens() {
			return getRuleContext(LiteralTokensContext.class,0);
		}
		public AlphaNumericContext alphaNumeric() {
			return getRuleContext(AlphaNumericContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal);
		try {
			setState(212);
			switch (_input.LA(1)) {
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(210); alphaNumeric();
				}
				break;
			case JS:
			case REGEX:
			case DEBUGGER:
			case UNDEFINED:
			case NULL:
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(211); literalTokens();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TERMINATOR() { return getToken(CoffeeScriptParserGrammar.TERMINATOR, 0); }
		public TerminalNode EQUATING_TOK() { return getToken(CoffeeScriptParserGrammar.EQUATING_TOK, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitAssign(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assign);
		try {
			setState(229);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214); assignable();
				setState(215); match(EQUATING_TOK);
				setState(216); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(218); assignable();
				setState(219); match(EQUATING_TOK);
				setState(220); match(TERMINATOR);
				setState(221); expression(0);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(223); assignable();
				setState(224); match(EQUATING_TOK);
				setState(225); match(INDENT);
				setState(226); expression(0);
				setState(227); match(OUTDENT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignObjContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ObjAssignableContext objAssignable() {
			return getRuleContext(ObjAssignableContext.class,0);
		}
		public TerminalNode COLON_TOK() { return getToken(CoffeeScriptParserGrammar.COLON_TOK, 0); }
		public AssignObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignObj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterAssignObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitAssignObj(this);
		}
	}

	public final AssignObjContext assignObj() throws RecognitionException {
		AssignObjContext _localctx = new AssignObjContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignObj);
		try {
			setState(243);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(231); objAssignable();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232); objAssignable();
				setState(233); match(COLON_TOK);
				setState(234); expression(0);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(236); objAssignable();
				setState(237); match(COLON_TOK);
				setState(238); match(INDENT);
				setState(239); expression(0);
				setState(240); match(OUTDENT);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(242); comment();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjAssignableContext extends ParserRuleContext {
		public ThisPropertyContext thisProperty() {
			return getRuleContext(ThisPropertyContext.class,0);
		}
		public AlphaNumericContext alphaNumeric() {
			return getRuleContext(AlphaNumericContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ObjAssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objAssignable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterObjAssignable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitObjAssignable(this);
		}
	}

	public final ObjAssignableContext objAssignable() throws RecognitionException {
		ObjAssignableContext _localctx = new ObjAssignableContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_objAssignable);
		try {
			setState(248);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(245); identifier();
				}
				break;
			case NUMBER:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(246); alphaNumeric();
				}
				break;
			case AT_TOK:
				enterOuterAlt(_localctx, 3);
				{
				setState(247); thisProperty();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnRuleContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(CoffeeScriptParserGrammar.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterReturnRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitReturnRule(this);
		}
	}

	public final ReturnRuleContext returnRule() throws RecognitionException {
		ReturnRuleContext _localctx = new ReturnRuleContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_returnRule);
		try {
			setState(253);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(250); match(RETURN);
				setState(251); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(252); match(RETURN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode HERECOMMENT() { return getToken(CoffeeScriptParserGrammar.HERECOMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); match(HERECOMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeContext extends ParserRuleContext {
		public TerminalNode PARAM_END() { return getToken(CoffeeScriptParserGrammar.PARAM_END, 0); }
		public TerminalNode PARAM_START() { return getToken(CoffeeScriptParserGrammar.PARAM_START, 0); }
		public FuncGlyphContext funcGlyph() {
			return getRuleContext(FuncGlyphContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_code);
		try {
			setState(266);
			switch (_input.LA(1)) {
			case PARAM_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(257); match(PARAM_START);
				setState(258); paramList(0);
				setState(259); match(PARAM_END);
				setState(260); funcGlyph();
				setState(261); block();
				}
				break;
			case FUNC_SINGLE_ARROW:
			case FUNC_DOUBLE_ARROW:
				enterOuterAlt(_localctx, 2);
				{
				setState(263); funcGlyph();
				setState(264); block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncGlyphContext extends ParserRuleContext {
		public TerminalNode FUNC_DOUBLE_ARROW() { return getToken(CoffeeScriptParserGrammar.FUNC_DOUBLE_ARROW, 0); }
		public TerminalNode FUNC_SINGLE_ARROW() { return getToken(CoffeeScriptParserGrammar.FUNC_SINGLE_ARROW, 0); }
		public FuncGlyphContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcGlyph; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterFuncGlyph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitFuncGlyph(this);
		}
	}

	public final FuncGlyphContext funcGlyph() throws RecognitionException {
		FuncGlyphContext _localctx = new FuncGlyphContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_funcGlyph);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			_la = _input.LA(1);
			if ( !(_la==FUNC_SINGLE_ARROW || _la==FUNC_DOUBLE_ARROW) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptCommaContext extends ParserRuleContext {
		public TerminalNode COMMA_TOK() { return getToken(CoffeeScriptParserGrammar.COMMA_TOK, 0); }
		public OptCommaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optComma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterOptComma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitOptComma(this);
		}
	}

	public final OptCommaContext optComma() throws RecognitionException {
		OptCommaContext _localctx = new OptCommaContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_optComma);
		try {
			setState(272);
			switch (_input.LA(1)) {
			case COMMA_TOK:
				enterOuterAlt(_localctx, 1);
				{
				setState(270); match(COMMA_TOK);
				}
				break;
			case RBRACKET:
			case RBRACE:
			case TERMINATOR:
			case INDENT:
			case OUTDENT:
			case CALL_END:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public TerminalNode COMMA_TOK() { return getToken(CoffeeScriptParserGrammar.COMMA_TOK, 0); }
		public ParamListContext paramList(int i) {
			return getRuleContext(ParamListContext.class,i);
		}
		public List<OptCommaContext> optComma() {
			return getRuleContexts(OptCommaContext.class);
		}
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public OptCommaContext optComma(int i) {
			return getRuleContext(OptCommaContext.class,i);
		}
		public List<ParamListContext> paramList() {
			return getRuleContexts(ParamListContext.class);
		}
		public TerminalNode TERMINATOR() { return getToken(CoffeeScriptParserGrammar.TERMINATOR, 0); }
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitParamList(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		return paramList(0);
	}

	private ParamListContext paramList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ParamListContext _localctx = new ParamListContext(_ctx, _parentState);
		ParamListContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_paramList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(275); param();
				}
				break;

			case 2:
				{
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(296);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(294);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ParamListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_paramList);
						setState(279);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(280); match(COMMA_TOK);
						setState(281); param();
						}
						break;

					case 2:
						{
						_localctx = new ParamListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_paramList);
						setState(282);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(283); optComma();
						setState(284); match(TERMINATOR);
						setState(285); param();
						}
						break;

					case 3:
						{
						_localctx = new ParamListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_paramList);
						setState(287);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(288); optComma();
						setState(289); match(INDENT);
						setState(290); paramList(0);
						setState(291); optComma();
						setState(292); match(OUTDENT);
						}
						break;
					}
					} 
				}
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public CommonAssignableContext commonAssignable() {
			return getRuleContext(CommonAssignableContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TRIPLE_DOT_TOK() { return getToken(CoffeeScriptParserGrammar.TRIPLE_DOT_TOK, 0); }
		public TerminalNode EQUATING_TOK() { return getToken(CoffeeScriptParserGrammar.EQUATING_TOK, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_param);
		try {
			setState(308);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(299); commonAssignable();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(300); commonAssignable();
				setState(301); match(TRIPLE_DOT_TOK);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(303); commonAssignable();
				setState(304); match(EQUATING_TOK);
				setState(305); expression(0);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(307); match(TRIPLE_DOT_TOK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommonAssignableContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ThisPropertyContext thisProperty() {
			return getRuleContext(ThisPropertyContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public CommonAssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commonAssignable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterCommonAssignable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitCommonAssignable(this);
		}
	}

	public final CommonAssignableContext commonAssignable() throws RecognitionException {
		CommonAssignableContext _localctx = new CommonAssignableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_commonAssignable);
		try {
			setState(314);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(310); identifier();
				}
				break;
			case AT_TOK:
				enterOuterAlt(_localctx, 2);
				{
				setState(311); thisProperty();
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(312); array();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 4);
				{
				setState(313); object();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SplatContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TRIPLE_DOT_TOK() { return getToken(CoffeeScriptParserGrammar.TRIPLE_DOT_TOK, 0); }
		public SplatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_splat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterSplat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitSplat(this);
		}
	}

	public final SplatContext splat() throws RecognitionException {
		SplatContext _localctx = new SplatContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_splat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316); expression(0);
			setState(317); match(TRIPLE_DOT_TOK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleAssignableContext extends ParserRuleContext {
		public AccessorContext accessor() {
			return getRuleContext(AccessorContext.class,0);
		}
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ThisPropertyContext thisProperty() {
			return getRuleContext(ThisPropertyContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public SimpleAssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleAssignable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterSimpleAssignable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitSimpleAssignable(this);
		}
	}

	public final SimpleAssignableContext simpleAssignable() throws RecognitionException {
		SimpleAssignableContext _localctx = new SimpleAssignableContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_simpleAssignable);
		try {
			setState(327);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319); identifier();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(320); value(0);
				setState(321); accessor();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(323); invocation(0);
				setState(324); accessor();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(326); thisProperty();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignableContext extends ParserRuleContext {
		public AccessorContext accessor() {
			return getRuleContext(AccessorContext.class,0);
		}
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ThisPropertyContext thisProperty() {
			return getRuleContext(ThisPropertyContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterAssignable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitAssignable(this);
		}
	}

	public final AssignableContext assignable() throws RecognitionException {
		AssignableContext _localctx = new AssignableContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_assignable);
		try {
			setState(339);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(329); identifier();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(330); value(0);
				setState(331); accessor();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(333); invocation(0);
				setState(334); accessor();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(336); thisProperty();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(337); array();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(338); object();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public AccessorContext accessor() {
			return getRuleContext(AccessorContext.class,0);
		}
		public OptFuncExistContext optFuncExist() {
			return getRuleContext(OptFuncExistContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode SUPER() { return getToken(CoffeeScriptParserGrammar.SUPER, 0); }
		public ParentheticalContext parenthetical() {
			return getRuleContext(ParentheticalContext.class,0);
		}
		public ThisPropertyContext thisProperty() {
			return getRuleContext(ThisPropertyContext.class,0);
		}
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ThisRuleContext thisRule() {
			return getRuleContext(ThisRuleContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		return value(0);
	}

	private ValueContext value(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ValueContext _localctx = new ValueContext(_ctx, _parentState);
		ValueContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_value, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(342); identifier();
				}
				break;

			case 2:
				{
				setState(343); match(SUPER);
				setState(344); accessor();
				}
				break;

			case 3:
				{
				setState(345); match(SUPER);
				setState(346); arguments();
				setState(347); accessor();
				}
				break;

			case 4:
				{
				setState(349); thisProperty();
				}
				break;

			case 5:
				{
				setState(350); array();
				}
				break;

			case 6:
				{
				setState(351); object();
				}
				break;

			case 7:
				{
				setState(352); literal();
				}
				break;

			case 8:
				{
				setState(353); parenthetical();
				}
				break;

			case 9:
				{
				setState(354); range();
				}
				break;

			case 10:
				{
				setState(355); thisRule();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(367);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(365);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ValueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_value);
						setState(358);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(359); accessor();
						}
						break;

					case 2:
						{
						_localctx = new ValueContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_value);
						setState(360);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(361); optFuncExist();
						setState(362); arguments();
						setState(363); accessor();
						}
						break;
					}
					} 
				}
				setState(369);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AccessorContext extends ParserRuleContext {
		public TerminalNode QUEST_DOUBLE_COLON_TOK() { return getToken(CoffeeScriptParserGrammar.QUEST_DOUBLE_COLON_TOK, 0); }
		public TerminalNode DOT_TOK() { return getToken(CoffeeScriptParserGrammar.DOT_TOK, 0); }
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public TerminalNode DOUBLE_COLON_TOK() { return getToken(CoffeeScriptParserGrammar.DOUBLE_COLON_TOK, 0); }
		public TerminalNode QUEST_DOT_TOK() { return getToken(CoffeeScriptParserGrammar.QUEST_DOT_TOK, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AccessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterAccessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitAccessor(this);
		}
	}

	public final AccessorContext accessor() throws RecognitionException {
		AccessorContext _localctx = new AccessorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_accessor);
		try {
			setState(380);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(370); match(DOT_TOK);
				setState(371); identifier();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(372); match(QUEST_DOT_TOK);
				setState(373); identifier();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(374); match(DOUBLE_COLON_TOK);
				setState(375); identifier();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(376); match(QUEST_DOUBLE_COLON_TOK);
				setState(377); identifier();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(378); match(DOUBLE_COLON_TOK);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(379); index();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexContext extends ParserRuleContext {
		public TerminalNode INDEX_END() { return getToken(CoffeeScriptParserGrammar.INDEX_END, 0); }
		public TerminalNode INDEX_SOAK() { return getToken(CoffeeScriptParserGrammar.INDEX_SOAK, 0); }
		public TerminalNode INDEX_START() { return getToken(CoffeeScriptParserGrammar.INDEX_START, 0); }
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public IndexValueContext indexValue() {
			return getRuleContext(IndexValueContext.class,0);
		}
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitIndex(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_index);
		try {
			setState(388);
			switch (_input.LA(1)) {
			case INDEX_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(382); match(INDEX_START);
				setState(383); indexValue();
				setState(384); match(INDEX_END);
				}
				break;
			case INDEX_SOAK:
				enterOuterAlt(_localctx, 2);
				{
				setState(386); match(INDEX_SOAK);
				setState(387); index();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexValueContext extends ParserRuleContext {
		public SliceContext slice() {
			return getRuleContext(SliceContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IndexValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterIndexValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitIndexValue(this);
		}
	}

	public final IndexValueContext indexValue() throws RecognitionException {
		IndexValueContext _localctx = new IndexValueContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_indexValue);
		try {
			setState(392);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(390); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(391); slice();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(CoffeeScriptParserGrammar.LBRACE, 0); }
		public OptCommaContext optComma() {
			return getRuleContext(OptCommaContext.class,0);
		}
		public AssignListContext assignList() {
			return getRuleContext(AssignListContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CoffeeScriptParserGrammar.RBRACE, 0); }
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394); match(LBRACE);
			setState(395); assignList(0);
			setState(396); optComma();
			setState(397); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignListContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public TerminalNode COMMA_TOK() { return getToken(CoffeeScriptParserGrammar.COMMA_TOK, 0); }
		public List<OptCommaContext> optComma() {
			return getRuleContexts(OptCommaContext.class);
		}
		public List<AssignListContext> assignList() {
			return getRuleContexts(AssignListContext.class);
		}
		public OptCommaContext optComma(int i) {
			return getRuleContext(OptCommaContext.class,i);
		}
		public AssignListContext assignList(int i) {
			return getRuleContext(AssignListContext.class,i);
		}
		public AssignObjContext assignObj() {
			return getRuleContext(AssignObjContext.class,0);
		}
		public TerminalNode TERMINATOR() { return getToken(CoffeeScriptParserGrammar.TERMINATOR, 0); }
		public AssignListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterAssignList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitAssignList(this);
		}
	}

	public final AssignListContext assignList() throws RecognitionException {
		return assignList(0);
	}

	private AssignListContext assignList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AssignListContext _localctx = new AssignListContext(_ctx, _parentState);
		AssignListContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_assignList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(400); assignObj();
				}
				break;

			case 2:
				{
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(421);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(419);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new AssignListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_assignList);
						setState(404);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(405); match(COMMA_TOK);
						setState(406); assignObj();
						}
						break;

					case 2:
						{
						_localctx = new AssignListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_assignList);
						setState(407);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(408); optComma();
						setState(409); match(TERMINATOR);
						setState(410); assignObj();
						}
						break;

					case 3:
						{
						_localctx = new AssignListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_assignList);
						setState(412);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(413); optComma();
						setState(414); match(INDENT);
						setState(415); assignList(0);
						setState(416); optComma();
						setState(417); match(OUTDENT);
						}
						break;
					}
					} 
				}
				setState(423);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ClassRuleContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SimpleAssignableContext simpleAssignable() {
			return getRuleContext(SimpleAssignableContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(CoffeeScriptParserGrammar.EXTENDS, 0); }
		public TerminalNode CLASS() { return getToken(CoffeeScriptParserGrammar.CLASS, 0); }
		public ClassRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterClassRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitClassRule(this);
		}
	}

	public final ClassRuleContext classRule() throws RecognitionException {
		ClassRuleContext _localctx = new ClassRuleContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_classRule);
		try {
			setState(452);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(424); match(CLASS);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(425); match(CLASS);
				setState(426); block();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(427); match(CLASS);
				setState(428); match(EXTENDS);
				setState(429); expression(0);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(430); match(CLASS);
				setState(431); match(EXTENDS);
				setState(432); expression(0);
				setState(433); block();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(435); match(CLASS);
				setState(436); simpleAssignable();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(437); match(CLASS);
				setState(438); simpleAssignable();
				setState(439); block();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(441); match(CLASS);
				setState(442); simpleAssignable();
				setState(443); match(EXTENDS);
				setState(444); expression(0);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(446); match(CLASS);
				setState(447); simpleAssignable();
				setState(448); match(EXTENDS);
				setState(449); expression(0);
				setState(450); block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InvocationContext extends ParserRuleContext {
		public InvocationContext invocation() {
			return getRuleContext(InvocationContext.class,0);
		}
		public OptFuncExistContext optFuncExist() {
			return getRuleContext(OptFuncExistContext.class,0);
		}
		public TerminalNode SUPER() { return getToken(CoffeeScriptParserGrammar.SUPER, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public InvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitInvocation(this);
		}
	}

	public final InvocationContext invocation() throws RecognitionException {
		return invocation(0);
	}

	private InvocationContext invocation(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InvocationContext _localctx = new InvocationContext(_ctx, _parentState);
		InvocationContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_invocation, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(455); value(0);
				setState(456); optFuncExist();
				setState(457); arguments();
				}
				break;

			case 2:
				{
				setState(459); match(SUPER);
				}
				break;

			case 3:
				{
				setState(460); match(SUPER);
				setState(461); arguments();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(470);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InvocationContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_invocation);
					setState(464);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(465); optFuncExist();
					setState(466); arguments();
					}
					} 
				}
				setState(472);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OptFuncExistContext extends ParserRuleContext {
		public TerminalNode FUNC_EXIST() { return getToken(CoffeeScriptParserGrammar.FUNC_EXIST, 0); }
		public OptFuncExistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optFuncExist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterOptFuncExist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitOptFuncExist(this);
		}
	}

	public final OptFuncExistContext optFuncExist() throws RecognitionException {
		OptFuncExistContext _localctx = new OptFuncExistContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_optFuncExist);
		try {
			setState(475);
			switch (_input.LA(1)) {
			case FUNC_EXIST:
				enterOuterAlt(_localctx, 1);
				{
				setState(473); match(FUNC_EXIST);
				}
				break;
			case CALL_START:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public OptCommaContext optComma() {
			return getRuleContext(OptCommaContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode CALL_START() { return getToken(CoffeeScriptParserGrammar.CALL_START, 0); }
		public TerminalNode CALL_END() { return getToken(CoffeeScriptParserGrammar.CALL_END, 0); }
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_arguments);
		try {
			setState(484);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(477); match(CALL_START);
				setState(478); match(CALL_END);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(479); match(CALL_START);
				setState(480); argList(0);
				setState(481); optComma();
				setState(482); match(CALL_END);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThisRuleContext extends ParserRuleContext {
		public TerminalNode AT_TOK() { return getToken(CoffeeScriptParserGrammar.AT_TOK, 0); }
		public TerminalNode THIS() { return getToken(CoffeeScriptParserGrammar.THIS, 0); }
		public ThisRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thisRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterThisRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitThisRule(this);
		}
	}

	public final ThisRuleContext thisRule() throws RecognitionException {
		ThisRuleContext _localctx = new ThisRuleContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_thisRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			_la = _input.LA(1);
			if ( !(_la==AT_TOK || _la==THIS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThisPropertyContext extends ParserRuleContext {
		public TerminalNode AT_TOK() { return getToken(CoffeeScriptParserGrammar.AT_TOK, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ThisPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thisProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterThisProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitThisProperty(this);
		}
	}

	public final ThisPropertyContext thisProperty() throws RecognitionException {
		ThisPropertyContext _localctx = new ThisPropertyContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_thisProperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488); match(AT_TOK);
			setState(489); identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public OptCommaContext optComma() {
			return getRuleContext(OptCommaContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(CoffeeScriptParserGrammar.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(CoffeeScriptParserGrammar.RBRACKET, 0); }
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_array);
		try {
			setState(498);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(491); match(LBRACKET);
				setState(492); match(RBRACKET);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(493); match(LBRACKET);
				setState(494); argList(0);
				setState(495); optComma();
				setState(496); match(RBRACKET);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeDotsContext extends ParserRuleContext {
		public TerminalNode TRIPLE_DOT_TOK() { return getToken(CoffeeScriptParserGrammar.TRIPLE_DOT_TOK, 0); }
		public TerminalNode DOUBLE_DOT_TOK() { return getToken(CoffeeScriptParserGrammar.DOUBLE_DOT_TOK, 0); }
		public RangeDotsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeDots; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterRangeDots(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitRangeDots(this);
		}
	}

	public final RangeDotsContext rangeDots() throws RecognitionException {
		RangeDotsContext _localctx = new RangeDotsContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_rangeDots);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			_la = _input.LA(1);
			if ( !(_la==DOUBLE_DOT_TOK || _la==TRIPLE_DOT_TOK) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RangeDotsContext rangeDots() {
			return getRuleContext(RangeDotsContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode LBRACKET() { return getToken(CoffeeScriptParserGrammar.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(CoffeeScriptParserGrammar.RBRACKET, 0); }
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502); match(LBRACKET);
			setState(503); expression(0);
			setState(504); rangeDots();
			setState(505); expression(0);
			setState(506); match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SliceContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RangeDotsContext rangeDots() {
			return getRuleContext(RangeDotsContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public SliceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterSlice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitSlice(this);
		}
	}

	public final SliceContext slice() throws RecognitionException {
		SliceContext _localctx = new SliceContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_slice);
		try {
			setState(519);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(508); expression(0);
				setState(509); rangeDots();
				setState(510); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(512); expression(0);
				setState(513); rangeDots();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(515); rangeDots();
				setState(516); expression(0);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(518); rangeDots();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public TerminalNode COMMA_TOK() { return getToken(CoffeeScriptParserGrammar.COMMA_TOK, 0); }
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public List<OptCommaContext> optComma() {
			return getRuleContexts(OptCommaContext.class);
		}
		public OptCommaContext optComma(int i) {
			return getRuleContext(OptCommaContext.class,i);
		}
		public ArgListContext argList(int i) {
			return getRuleContext(ArgListContext.class,i);
		}
		public List<ArgListContext> argList() {
			return getRuleContexts(ArgListContext.class);
		}
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode TERMINATOR() { return getToken(CoffeeScriptParserGrammar.TERMINATOR, 0); }
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		return argList(0);
	}

	private ArgListContext argList(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArgListContext _localctx = new ArgListContext(_ctx, _parentState);
		ArgListContext _prevctx = _localctx;
		int _startState = 78;
		enterRecursionRule(_localctx, 78, RULE_argList, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			switch (_input.LA(1)) {
			case MINUS_TOK:
			case DOUBLE_MINUS_TOK:
			case FUNC_SINGLE_ARROW:
			case TRIPLE_DOT_TOK:
			case LPAR:
			case LBRACKET:
			case LBRACE:
			case AT_TOK:
			case PLUS_TOK:
			case DOUBLE_PLUS_TOK:
			case FUNC_DOUBLE_ARROW:
			case STATEMENT:
			case IDENTIFIER:
			case NUMBER:
			case STRING:
			case JS:
			case REGEX:
			case DEBUGGER:
			case UNDEFINED:
			case NULL:
			case BOOL:
			case RETURN:
			case HERECOMMENT:
			case PARAM_START:
			case SUPER:
			case CLASS:
			case THIS:
			case TRY:
			case THROW:
			case WHILE:
			case UNTIL:
			case LOOP:
			case FOR:
			case SWITCH:
			case IF:
			case UNARY:
			case UNARY_MATH:
				{
				setState(522); arg();
				}
				break;
			case INDENT:
				{
				setState(523); match(INDENT);
				setState(524); argList(0);
				setState(525); optComma();
				setState(526); match(OUTDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(547);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(545);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						_localctx = new ArgListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_argList);
						setState(530);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(531); match(COMMA_TOK);
						setState(532); arg();
						}
						break;

					case 2:
						{
						_localctx = new ArgListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_argList);
						setState(533);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(534); optComma();
						setState(535); match(TERMINATOR);
						setState(536); arg();
						}
						break;

					case 3:
						{
						_localctx = new ArgListContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_argList);
						setState(538);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(539); optComma();
						setState(540); match(INDENT);
						setState(541); argList(0);
						setState(542); optComma();
						setState(543); match(OUTDENT);
						}
						break;
					}
					} 
				}
				setState(549);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode TRIPLE_DOT_TOK() { return getToken(CoffeeScriptParserGrammar.TRIPLE_DOT_TOK, 0); }
		public SplatContext splat() {
			return getRuleContext(SplatContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_arg);
		try {
			setState(553);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(550); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(551); splat();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(552); match(TRIPLE_DOT_TOK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleArgsContext extends ParserRuleContext {
		public TerminalNode COMMA_TOK() { return getToken(CoffeeScriptParserGrammar.COMMA_TOK, 0); }
		public SimpleArgsContext simpleArgs() {
			return getRuleContext(SimpleArgsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SimpleArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterSimpleArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitSimpleArgs(this);
		}
	}

	public final SimpleArgsContext simpleArgs() throws RecognitionException {
		return simpleArgs(0);
	}

	private SimpleArgsContext simpleArgs(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SimpleArgsContext _localctx = new SimpleArgsContext(_ctx, _parentState);
		SimpleArgsContext _prevctx = _localctx;
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_simpleArgs, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(556); expression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(563);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SimpleArgsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_simpleArgs);
					setState(558);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(559); match(COMMA_TOK);
					setState(560); expression(0);
					}
					} 
				}
				setState(565);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TryRuleContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(CoffeeScriptParserGrammar.FINALLY, 0); }
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public CatchRuleContext catchRule() {
			return getRuleContext(CatchRuleContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public TerminalNode TRY() { return getToken(CoffeeScriptParserGrammar.TRY, 0); }
		public TryRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterTryRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitTryRule(this);
		}
	}

	public final TryRuleContext tryRule() throws RecognitionException {
		TryRuleContext _localctx = new TryRuleContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_tryRule);
		try {
			setState(583);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(566); match(TRY);
				setState(567); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(568); match(TRY);
				setState(569); block();
				setState(570); catchRule();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(572); match(TRY);
				setState(573); block();
				setState(574); match(FINALLY);
				setState(575); block();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(577); match(TRY);
				setState(578); block();
				setState(579); catchRule();
				setState(580); match(FINALLY);
				setState(581); block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchRuleContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public TerminalNode CATCH() { return getToken(CoffeeScriptParserGrammar.CATCH, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public CatchRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterCatchRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitCatchRule(this);
		}
	}

	public final CatchRuleContext catchRule() throws RecognitionException {
		CatchRuleContext _localctx = new CatchRuleContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_catchRule);
		try {
			setState(595);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(585); match(CATCH);
				setState(586); identifier();
				setState(587); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(589); match(CATCH);
				setState(590); object();
				setState(591); block();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(593); match(CATCH);
				setState(594); block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowRuleContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THROW() { return getToken(CoffeeScriptParserGrammar.THROW, 0); }
		public ThrowRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterThrowRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitThrowRule(this);
		}
	}

	public final ThrowRuleContext throwRule() throws RecognitionException {
		ThrowRuleContext _localctx = new ThrowRuleContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_throwRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(597); match(THROW);
			setState(598); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParentheticalContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public TerminalNode RPAR() { return getToken(CoffeeScriptParserGrammar.RPAR, 0); }
		public TerminalNode LPAR() { return getToken(CoffeeScriptParserGrammar.LPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ParentheticalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthetical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterParenthetical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitParenthetical(this);
		}
	}

	public final ParentheticalContext parenthetical() throws RecognitionException {
		ParentheticalContext _localctx = new ParentheticalContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_parenthetical);
		try {
			setState(610);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(600); match(LPAR);
				setState(601); body(0);
				setState(602); match(RPAR);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(604); match(LPAR);
				setState(605); match(INDENT);
				setState(606); body(0);
				setState(607); match(OUTDENT);
				setState(608); match(RPAR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileSourceContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(CoffeeScriptParserGrammar.WHILE, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode UNTIL() { return getToken(CoffeeScriptParserGrammar.UNTIL, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode WHEN() { return getToken(CoffeeScriptParserGrammar.WHEN, 0); }
		public WhileSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterWhileSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitWhileSource(this);
		}
	}

	public final WhileSourceContext whileSource() throws RecognitionException {
		WhileSourceContext _localctx = new WhileSourceContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_whileSource);
		try {
			setState(626);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(612); match(WHILE);
				setState(613); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(614); match(WHILE);
				setState(615); expression(0);
				setState(616); match(WHEN);
				setState(617); expression(0);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(619); match(UNTIL);
				setState(620); expression(0);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(621); match(UNTIL);
				setState(622); expression(0);
				setState(623); match(WHEN);
				setState(624); expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopContext extends ParserRuleContext {
		public TerminalNode LOOP() { return getToken(CoffeeScriptParserGrammar.LOOP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitLoop(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_loop);
		try {
			setState(632);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(628); match(LOOP);
				setState(629); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(630); match(LOOP);
				setState(631); expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForBodyContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(CoffeeScriptParserGrammar.FOR, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public ForSourceContext forSource() {
			return getRuleContext(ForSourceContext.class,0);
		}
		public ForStartContext forStart() {
			return getRuleContext(ForStartContext.class,0);
		}
		public ForBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterForBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitForBody(this);
		}
	}

	public final ForBodyContext forBody() throws RecognitionException {
		ForBodyContext _localctx = new ForBodyContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_forBody);
		try {
			setState(639);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(634); match(FOR);
				setState(635); range();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(636); forStart();
				setState(637); forSource();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStartContext extends ParserRuleContext {
		public TerminalNode OWN() { return getToken(CoffeeScriptParserGrammar.OWN, 0); }
		public TerminalNode FOR() { return getToken(CoffeeScriptParserGrammar.FOR, 0); }
		public ForVariablesContext forVariables() {
			return getRuleContext(ForVariablesContext.class,0);
		}
		public ForStartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterForStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitForStart(this);
		}
	}

	public final ForStartContext forStart() throws RecognitionException {
		ForStartContext _localctx = new ForStartContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_forStart);
		try {
			setState(646);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(641); match(FOR);
				setState(642); forVariables();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(643); match(FOR);
				setState(644); match(OWN);
				setState(645); forVariables();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForVariablesContext extends ParserRuleContext {
		public TerminalNode COMMA_TOK() { return getToken(CoffeeScriptParserGrammar.COMMA_TOK, 0); }
		public CommonAssignableContext commonAssignable(int i) {
			return getRuleContext(CommonAssignableContext.class,i);
		}
		public List<CommonAssignableContext> commonAssignable() {
			return getRuleContexts(CommonAssignableContext.class);
		}
		public ForVariablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forVariables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterForVariables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitForVariables(this);
		}
	}

	public final ForVariablesContext forVariables() throws RecognitionException {
		ForVariablesContext _localctx = new ForVariablesContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_forVariables);
		try {
			setState(653);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(648); commonAssignable();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(649); commonAssignable();
				setState(650); match(COMMA_TOK);
				setState(651); commonAssignable();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForSourceContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BY() { return getToken(CoffeeScriptParserGrammar.BY, 0); }
		public TerminalNode FOROF() { return getToken(CoffeeScriptParserGrammar.FOROF, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode FORIN() { return getToken(CoffeeScriptParserGrammar.FORIN, 0); }
		public TerminalNode WHEN() { return getToken(CoffeeScriptParserGrammar.WHEN, 0); }
		public ForSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterForSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitForSource(this);
		}
	}

	public final ForSourceContext forSource() throws RecognitionException {
		ForSourceContext _localctx = new ForSourceContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_forSource);
		try {
			setState(688);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(655); match(FORIN);
				setState(656); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(657); match(FOROF);
				setState(658); expression(0);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(659); match(FORIN);
				setState(660); expression(0);
				setState(661); match(WHEN);
				setState(662); expression(0);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(664); match(FOROF);
				setState(665); expression(0);
				setState(666); match(WHEN);
				setState(667); expression(0);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(669); match(FORIN);
				setState(670); expression(0);
				setState(671); match(BY);
				setState(672); expression(0);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(674); match(FORIN);
				setState(675); expression(0);
				setState(676); match(WHEN);
				setState(677); expression(0);
				setState(678); match(BY);
				setState(679); expression(0);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(681); match(FORIN);
				setState(682); expression(0);
				setState(683); match(BY);
				setState(684); expression(0);
				setState(685); match(WHEN);
				setState(686); expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchRuleContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public TerminalNode ELSE() { return getToken(CoffeeScriptParserGrammar.ELSE, 0); }
		public WhensContext whens() {
			return getRuleContext(WhensContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SWITCH() { return getToken(CoffeeScriptParserGrammar.SWITCH, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SwitchRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterSwitchRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitSwitchRule(this);
		}
	}

	public final SwitchRuleContext switchRule() throws RecognitionException {
		SwitchRuleContext _localctx = new SwitchRuleContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_switchRule);
		try {
			setState(716);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(690); match(SWITCH);
				setState(691); expression(0);
				setState(692); match(INDENT);
				setState(693); whens(0);
				setState(694); match(OUTDENT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(696); match(SWITCH);
				setState(697); expression(0);
				setState(698); match(INDENT);
				setState(699); whens(0);
				setState(700); match(ELSE);
				setState(701); block();
				setState(702); match(OUTDENT);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(704); match(SWITCH);
				setState(705); match(INDENT);
				setState(706); whens(0);
				setState(707); match(OUTDENT);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(709); match(SWITCH);
				setState(710); match(INDENT);
				setState(711); whens(0);
				setState(712); match(ELSE);
				setState(713); block();
				setState(714); match(OUTDENT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhensContext extends ParserRuleContext {
		public WhensContext whens() {
			return getRuleContext(WhensContext.class,0);
		}
		public WhenContext when() {
			return getRuleContext(WhenContext.class,0);
		}
		public WhensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterWhens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitWhens(this);
		}
	}

	public final WhensContext whens() throws RecognitionException {
		return whens(0);
	}

	private WhensContext whens(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		WhensContext _localctx = new WhensContext(_ctx, _parentState);
		WhensContext _prevctx = _localctx;
		int _startState = 106;
		enterRecursionRule(_localctx, 106, RULE_whens, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(719); when();
			}
			_ctx.stop = _input.LT(-1);
			setState(725);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new WhensContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_whens);
					setState(721);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(722); when();
					}
					} 
				}
				setState(727);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class WhenContext extends ParserRuleContext {
		public SimpleArgsContext simpleArgs() {
			return getRuleContext(SimpleArgsContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode LEADING_WHEN() { return getToken(CoffeeScriptParserGrammar.LEADING_WHEN, 0); }
		public TerminalNode TERMINATOR() { return getToken(CoffeeScriptParserGrammar.TERMINATOR, 0); }
		public WhenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_when; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterWhen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitWhen(this);
		}
	}

	public final WhenContext when() throws RecognitionException {
		WhenContext _localctx = new WhenContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_when);
		try {
			setState(737);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(728); match(LEADING_WHEN);
				setState(729); simpleArgs(0);
				setState(730); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(732); match(LEADING_WHEN);
				setState(733); simpleArgs(0);
				setState(734); block();
				setState(735); match(TERMINATOR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfBlockContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(CoffeeScriptParserGrammar.IF, 0); }
		public TerminalNode ELSE() { return getToken(CoffeeScriptParserGrammar.ELSE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterIfBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitIfBlock(this);
		}
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		return ifBlock(0);
	}

	private IfBlockContext ifBlock(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IfBlockContext _localctx = new IfBlockContext(_ctx, _parentState);
		IfBlockContext _prevctx = _localctx;
		int _startState = 110;
		enterRecursionRule(_localctx, 110, RULE_ifBlock, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(740); match(IF);
			setState(741); expression(0);
			setState(742); block();
			}
			_ctx.stop = _input.LT(-1);
			setState(752);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new IfBlockContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_ifBlock);
					setState(744);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(745); match(ELSE);
					setState(746); match(IF);
					setState(747); expression(0);
					setState(748); block();
					}
					} 
				}
				setState(754);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryOperationContext extends ParserRuleContext {
		public TerminalNode MINUS_TOK() { return getToken(CoffeeScriptParserGrammar.MINUS_TOK, 0); }
		public TerminalNode PLUS_TOK() { return getToken(CoffeeScriptParserGrammar.PLUS_TOK, 0); }
		public TerminalNode UNARY_MATH() { return getToken(CoffeeScriptParserGrammar.UNARY_MATH, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode UNARY() { return getToken(CoffeeScriptParserGrammar.UNARY, 0); }
		public UnaryOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterUnaryOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitUnaryOperation(this);
		}
	}

	public final UnaryOperationContext unaryOperation() throws RecognitionException {
		UnaryOperationContext _localctx = new UnaryOperationContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_unaryOperation);
		try {
			setState(763);
			switch (_input.LA(1)) {
			case UNARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(755); match(UNARY);
				setState(756); expression(0);
				}
				break;
			case UNARY_MATH:
				enterOuterAlt(_localctx, 2);
				{
				setState(757); match(UNARY_MATH);
				setState(758); expression(0);
				}
				break;
			case MINUS_TOK:
				enterOuterAlt(_localctx, 3);
				{
				setState(759); match(MINUS_TOK);
				setState(760); expression(0);
				}
				break;
			case PLUS_TOK:
				enterOuterAlt(_localctx, 4);
				{
				setState(761); match(PLUS_TOK);
				setState(762); expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleSignOperationContext extends ParserRuleContext {
		public TerminalNode DOUBLE_MINUS_TOK() { return getToken(CoffeeScriptParserGrammar.DOUBLE_MINUS_TOK, 0); }
		public TerminalNode DOUBLE_PLUS_TOK() { return getToken(CoffeeScriptParserGrammar.DOUBLE_PLUS_TOK, 0); }
		public SimpleAssignableContext simpleAssignable() {
			return getRuleContext(SimpleAssignableContext.class,0);
		}
		public DoubleSignOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleSignOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterDoubleSignOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitDoubleSignOperation(this);
		}
	}

	public final DoubleSignOperationContext doubleSignOperation() throws RecognitionException {
		DoubleSignOperationContext _localctx = new DoubleSignOperationContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_doubleSignOperation);
		try {
			setState(769);
			switch (_input.LA(1)) {
			case DOUBLE_MINUS_TOK:
				enterOuterAlt(_localctx, 1);
				{
				setState(765); match(DOUBLE_MINUS_TOK);
				setState(766); simpleAssignable();
				}
				break;
			case DOUBLE_PLUS_TOK:
				enterOuterAlt(_localctx, 2);
				{
				setState(767); match(DOUBLE_PLUS_TOK);
				setState(768); simpleAssignable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleAssignableOperationContext extends ParserRuleContext {
		public TerminalNode OUTDENT() { return getToken(CoffeeScriptParserGrammar.OUTDENT, 0); }
		public TerminalNode INDENT() { return getToken(CoffeeScriptParserGrammar.INDENT, 0); }
		public TerminalNode DOUBLE_MINUS_TOK() { return getToken(CoffeeScriptParserGrammar.DOUBLE_MINUS_TOK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOUBLE_PLUS_TOK() { return getToken(CoffeeScriptParserGrammar.DOUBLE_PLUS_TOK, 0); }
		public TerminalNode EXTENDS() { return getToken(CoffeeScriptParserGrammar.EXTENDS, 0); }
		public TerminalNode COMPOUND_ASSIGN() { return getToken(CoffeeScriptParserGrammar.COMPOUND_ASSIGN, 0); }
		public SimpleAssignableContext simpleAssignable() {
			return getRuleContext(SimpleAssignableContext.class,0);
		}
		public TerminalNode TERMINATOR() { return getToken(CoffeeScriptParserGrammar.TERMINATOR, 0); }
		public SimpleAssignableOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleAssignableOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterSimpleAssignableOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitSimpleAssignableOperation(this);
		}
	}

	public final SimpleAssignableOperationContext simpleAssignableOperation() throws RecognitionException {
		SimpleAssignableOperationContext _localctx = new SimpleAssignableOperationContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_simpleAssignableOperation);
		try {
			setState(796);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(771); simpleAssignable();
				setState(772); match(DOUBLE_MINUS_TOK);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(774); simpleAssignable();
				setState(775); match(DOUBLE_PLUS_TOK);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(777); simpleAssignable();
				setState(778); match(COMPOUND_ASSIGN);
				setState(779); expression(0);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(781); simpleAssignable();
				setState(782); match(COMPOUND_ASSIGN);
				setState(783); match(INDENT);
				setState(784); expression(0);
				setState(785); match(OUTDENT);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(787); simpleAssignable();
				setState(788); match(COMPOUND_ASSIGN);
				setState(789); match(TERMINATOR);
				setState(790); expression(0);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(792); simpleAssignable();
				setState(793); match(EXTENDS);
				setState(794); expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationRestContext extends ParserRuleContext {
		public TerminalNode LOGIC() { return getToken(CoffeeScriptParserGrammar.LOGIC, 0); }
		public TerminalNode COMPARE() { return getToken(CoffeeScriptParserGrammar.COMPARE, 0); }
		public TerminalNode MINUS_TOK() { return getToken(CoffeeScriptParserGrammar.MINUS_TOK, 0); }
		public TerminalNode PLUS_TOK() { return getToken(CoffeeScriptParserGrammar.PLUS_TOK, 0); }
		public TerminalNode DOUBLE_STAR() { return getToken(CoffeeScriptParserGrammar.DOUBLE_STAR, 0); }
		public TerminalNode RELATION() { return getToken(CoffeeScriptParserGrammar.RELATION, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MATH() { return getToken(CoffeeScriptParserGrammar.MATH, 0); }
		public TerminalNode SHIFT() { return getToken(CoffeeScriptParserGrammar.SHIFT, 0); }
		public TerminalNode QUEST_TOK() { return getToken(CoffeeScriptParserGrammar.QUEST_TOK, 0); }
		public OperationRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterOperationRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitOperationRest(this);
		}
	}

	public final OperationRestContext operationRest() throws RecognitionException {
		OperationRestContext _localctx = new OperationRestContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_operationRest);
		try {
			setState(815);
			switch (_input.LA(1)) {
			case QUEST_TOK:
				enterOuterAlt(_localctx, 1);
				{
				setState(798); match(QUEST_TOK);
				}
				break;
			case PLUS_TOK:
				enterOuterAlt(_localctx, 2);
				{
				setState(799); match(PLUS_TOK);
				setState(800); expression(0);
				}
				break;
			case MINUS_TOK:
				enterOuterAlt(_localctx, 3);
				{
				setState(801); match(MINUS_TOK);
				setState(802); expression(0);
				}
				break;
			case MATH:
				enterOuterAlt(_localctx, 4);
				{
				setState(803); match(MATH);
				setState(804); expression(0);
				}
				break;
			case DOUBLE_STAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(805); match(DOUBLE_STAR);
				setState(806); expression(0);
				}
				break;
			case SHIFT:
				enterOuterAlt(_localctx, 6);
				{
				setState(807); match(SHIFT);
				setState(808); expression(0);
				}
				break;
			case COMPARE:
				enterOuterAlt(_localctx, 7);
				{
				setState(809); match(COMPARE);
				setState(810); expression(0);
				}
				break;
			case LOGIC:
				enterOuterAlt(_localctx, 8);
				{
				setState(811); match(LOGIC);
				setState(812); expression(0);
				}
				break;
			case RELATION:
				enterOuterAlt(_localctx, 9);
				{
				setState(813); match(RELATION);
				setState(814); expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralTokensContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(CoffeeScriptParserGrammar.BOOL, 0); }
		public TerminalNode JS() { return getToken(CoffeeScriptParserGrammar.JS, 0); }
		public TerminalNode UNDEFINED() { return getToken(CoffeeScriptParserGrammar.UNDEFINED, 0); }
		public TerminalNode DEBUGGER() { return getToken(CoffeeScriptParserGrammar.DEBUGGER, 0); }
		public TerminalNode REGEX() { return getToken(CoffeeScriptParserGrammar.REGEX, 0); }
		public TerminalNode NULL() { return getToken(CoffeeScriptParserGrammar.NULL, 0); }
		public LiteralTokensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalTokens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).enterLiteralTokens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoffeeScriptParserGrammarListener ) ((CoffeeScriptParserGrammarListener)listener).exitLiteralTokens(this);
		}
	}

	public final LiteralTokensContext literalTokens() throws RecognitionException {
		LiteralTokensContext _localctx = new LiteralTokensContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_literalTokens);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(817);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << JS) | (1L << REGEX) | (1L << DEBUGGER) | (1L << UNDEFINED) | (1L << NULL) | (1L << BOOL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1: return body_sempred((BodyContext)_localctx, predIndex);

		case 4: return expression_sempred((ExpressionContext)_localctx, predIndex);

		case 17: return paramList_sempred((ParamListContext)_localctx, predIndex);

		case 23: return value_sempred((ValueContext)_localctx, predIndex);

		case 28: return assignList_sempred((AssignListContext)_localctx, predIndex);

		case 30: return invocation_sempred((InvocationContext)_localctx, predIndex);

		case 39: return argList_sempred((ArgListContext)_localctx, predIndex);

		case 41: return simpleArgs_sempred((SimpleArgsContext)_localctx, predIndex);

		case 53: return whens_sempred((WhensContext)_localctx, predIndex);

		case 55: return ifBlock_sempred((IfBlockContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 12);

		case 3: return precpred(_ctx, 18);

		case 4: return precpred(_ctx, 8);

		case 5: return precpred(_ctx, 5);
		}
		return true;
	}
	private boolean body_sempred(BodyContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 2);

		case 1: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean argList_sempred(ArgListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17: return precpred(_ctx, 1);

		case 16: return precpred(_ctx, 3);

		case 15: return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean ifBlock_sempred(IfBlockContext _localctx, int predIndex) {
		switch (predIndex) {
		case 20: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean simpleArgs_sempred(SimpleArgsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 18: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean invocation_sempred(InvocationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 14: return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean value_sempred(ValueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9: return precpred(_ctx, 11);

		case 10: return precpred(_ctx, 10);
		}
		return true;
	}
	private boolean paramList_sempred(ParamListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6: return precpred(_ctx, 4);

		case 7: return precpred(_ctx, 3);

		case 8: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean whens_sempred(WhensContext _localctx, int predIndex) {
		switch (predIndex) {
		case 19: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean assignList_sempred(AssignListContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11: return precpred(_ctx, 4);

		case 12: return precpred(_ctx, 3);

		case 13: return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3P\u0336\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\3\2\3\2\5\2\177\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\u0089\n"+
		"\3\f\3\16\3\u008c\13\3\3\4\3\4\5\4\u0090\n\4\3\5\3\5\3\5\5\5\u0095\n\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6"+
		"\u00b9\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00c4\n\6\f\6\16\6"+
		"\u00c7\13\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00cf\n\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\5\n\u00d7\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\5\13\u00e8\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u00f6\n\f\3\r\3\r\3\r\5\r\u00fb\n\r\3\16\3\16\3"+
		"\16\5\16\u0100\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u010d\n\20\3\21\3\21\3\22\3\22\5\22\u0113\n\22\3\23\3\23\3"+
		"\23\5\23\u0118\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\7\23\u0129\n\23\f\23\16\23\u012c\13\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0137\n\24\3\25\3\25\3\25"+
		"\3\25\5\25\u013d\n\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\5\27\u014a\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\5\30\u0156\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\5\31\u0167\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\7\31\u0170\n\31\f\31\16\31\u0173\13\31\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\5\32\u017f\n\32\3\33\3\33\3\33\3\33\3\33\3\33\5\33"+
		"\u0187\n\33\3\34\3\34\5\34\u018b\n\34\3\35\3\35\3\35\3\35\3\35\3\36\3"+
		"\36\3\36\5\36\u0195\n\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u01a6\n\36\f\36\16\36\u01a9\13\36"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\5\37\u01c7\n\37\3 \3 \3 \3 \3 \3 \3 \3 \5 \u01d1\n \3 \3 \3 \3 \7 \u01d7"+
		"\n \f \16 \u01da\13 \3!\3!\5!\u01de\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\""+
		"\u01e7\n\"\3#\3#\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\5%\u01f5\n%\3&\3&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u020a\n(\3)\3)"+
		"\3)\3)\3)\3)\3)\5)\u0213\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)"+
		"\3)\7)\u0224\n)\f)\16)\u0227\13)\3*\3*\3*\5*\u022c\n*\3+\3+\3+\3+\3+\3"+
		"+\7+\u0234\n+\f+\16+\u0237\13+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,"+
		"\3,\3,\3,\3,\5,\u024a\n,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u0256\n-\3."+
		"\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u0265\n/\3\60\3\60\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\5\60\u0275\n\60\3\61"+
		"\3\61\3\61\3\61\5\61\u027b\n\61\3\62\3\62\3\62\3\62\3\62\5\62\u0282\n"+
		"\62\3\63\3\63\3\63\3\63\3\63\5\63\u0289\n\63\3\64\3\64\3\64\3\64\3\64"+
		"\5\64\u0290\n\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\5\65\u02b3\n\65\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u02cf\n\66\3\67\3\67"+
		"\3\67\3\67\3\67\7\67\u02d6\n\67\f\67\16\67\u02d9\13\67\38\38\38\38\38"+
		"\38\38\38\38\58\u02e4\n8\39\39\39\39\39\39\39\39\39\39\39\79\u02f1\n9"+
		"\f9\169\u02f4\139\3:\3:\3:\3:\3:\3:\3:\3:\5:\u02fe\n:\3;\3;\3;\3;\5;\u0304"+
		"\n;\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<"+
		"\3<\3<\3<\5<\u031f\n<\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3=\3="+
		"\3=\5=\u0332\n=\3>\3>\3>\2\f\4\n$\60:>PTlp?\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz\2"+
		"\7\3\2\"#\4\2\5\5\31\31\4\2\24\24\67\67\3\2\f\r\3\2$)\u0389\2~\3\2\2\2"+
		"\4\u0080\3\2\2\2\6\u008f\3\2\2\2\b\u0094\3\2\2\2\n\u00b8\3\2\2\2\f\u00ce"+
		"\3\2\2\2\16\u00d0\3\2\2\2\20\u00d2\3\2\2\2\22\u00d6\3\2\2\2\24\u00e7\3"+
		"\2\2\2\26\u00f5\3\2\2\2\30\u00fa\3\2\2\2\32\u00ff\3\2\2\2\34\u0101\3\2"+
		"\2\2\36\u010c\3\2\2\2 \u010e\3\2\2\2\"\u0112\3\2\2\2$\u0117\3\2\2\2&\u0136"+
		"\3\2\2\2(\u013c\3\2\2\2*\u013e\3\2\2\2,\u0149\3\2\2\2.\u0155\3\2\2\2\60"+
		"\u0166\3\2\2\2\62\u017e\3\2\2\2\64\u0186\3\2\2\2\66\u018a\3\2\2\28\u018c"+
		"\3\2\2\2:\u0194\3\2\2\2<\u01c6\3\2\2\2>\u01d0\3\2\2\2@\u01dd\3\2\2\2B"+
		"\u01e6\3\2\2\2D\u01e8\3\2\2\2F\u01ea\3\2\2\2H\u01f4\3\2\2\2J\u01f6\3\2"+
		"\2\2L\u01f8\3\2\2\2N\u0209\3\2\2\2P\u0212\3\2\2\2R\u022b\3\2\2\2T\u022d"+
		"\3\2\2\2V\u0249\3\2\2\2X\u0255\3\2\2\2Z\u0257\3\2\2\2\\\u0264\3\2\2\2"+
		"^\u0274\3\2\2\2`\u027a\3\2\2\2b\u0281\3\2\2\2d\u0288\3\2\2\2f\u028f\3"+
		"\2\2\2h\u02b2\3\2\2\2j\u02ce\3\2\2\2l\u02d0\3\2\2\2n\u02e3\3\2\2\2p\u02e5"+
		"\3\2\2\2r\u02fd\3\2\2\2t\u0303\3\2\2\2v\u031e\3\2\2\2x\u0331\3\2\2\2z"+
		"\u0333\3\2\2\2|\177\5\4\3\2}\177\3\2\2\2~|\3\2\2\2~}\3\2\2\2\177\3\3\2"+
		"\2\2\u0080\u0081\b\3\1\2\u0081\u0082\5\6\4\2\u0082\u008a\3\2\2\2\u0083"+
		"\u0084\f\4\2\2\u0084\u0085\7\33\2\2\u0085\u0089\5\6\4\2\u0086\u0087\f"+
		"\3\2\2\u0087\u0089\7\33\2\2\u0088\u0083\3\2\2\2\u0088\u0086\3\2\2\2\u0089"+
		"\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\5\3\2\2\2"+
		"\u008c\u008a\3\2\2\2\u008d\u0090\5\n\6\2\u008e\u0090\5\b\5\2\u008f\u008d"+
		"\3\2\2\2\u008f\u008e\3\2\2\2\u0090\7\3\2\2\2\u0091\u0095\5\32\16\2\u0092"+
		"\u0095\5\34\17\2\u0093\u0095\7\34\2\2\u0094\u0091\3\2\2\2\u0094\u0092"+
		"\3\2\2\2\u0094\u0093\3\2\2\2\u0095\t\3\2\2\2\u0096\u0097\b\6\1\2\u0097"+
		"\u0098\5\b\5\2\u0098\u0099\7\35\2\2\u0099\u009a\5\n\6\17\u009a\u00b9\3"+
		"\2\2\2\u009b\u00b9\5\60\31\2\u009c\u00b9\5> \2\u009d\u00b9\5\36\20\2\u009e"+
		"\u00b9\5r:\2\u009f\u00b9\5t;\2\u00a0\u00b9\5v<\2\u00a1\u00b9\5\24\13\2"+
		"\u00a2\u00b9\5p9\2\u00a3\u00a4\5p9\2\u00a4\u00a5\7\36\2\2\u00a5\u00a6"+
		"\5\f\7\2\u00a6\u00b9\3\2\2\2\u00a7\u00b9\5V,\2\u00a8\u00a9\5^\60\2\u00a9"+
		"\u00aa\5\f\7\2\u00aa\u00b9\3\2\2\2\u00ab\u00ac\5\b\5\2\u00ac\u00ad\5^"+
		"\60\2\u00ad\u00b9\3\2\2\2\u00ae\u00b9\5`\61\2\u00af\u00b0\5\b\5\2\u00b0"+
		"\u00b1\5b\62\2\u00b1\u00b9\3\2\2\2\u00b2\u00b3\5b\62\2\u00b3\u00b4\5\f"+
		"\7\2\u00b4\u00b9\3\2\2\2\u00b5\u00b9\5j\66\2\u00b6\u00b9\5<\37\2\u00b7"+
		"\u00b9\5Z.\2\u00b8\u0096\3\2\2\2\u00b8\u009b\3\2\2\2\u00b8\u009c\3\2\2"+
		"\2\u00b8\u009d\3\2\2\2\u00b8\u009e\3\2\2\2\u00b8\u009f\3\2\2\2\u00b8\u00a0"+
		"\3\2\2\2\u00b8\u00a1\3\2\2\2\u00b8\u00a2\3\2\2\2\u00b8\u00a3\3\2\2\2\u00b8"+
		"\u00a7\3\2\2\2\u00b8\u00a8\3\2\2\2\u00b8\u00ab\3\2\2\2\u00b8\u00ae\3\2"+
		"\2\2\u00b8\u00af\3\2\2\2\u00b8\u00b2\3\2\2\2\u00b8\u00b5\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00c5\3\2\2\2\u00ba\u00bb\f\16"+
		"\2\2\u00bb\u00bc\7\35\2\2\u00bc\u00c4\5\n\6\17\u00bd\u00be\f\24\2\2\u00be"+
		"\u00c4\5x=\2\u00bf\u00c0\f\n\2\2\u00c0\u00c4\5^\60\2\u00c1\u00c2\f\7\2"+
		"\2\u00c2\u00c4\5b\62\2\u00c3\u00ba\3\2\2\2\u00c3\u00bd\3\2\2\2\u00c3\u00bf"+
		"\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\13\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7\37\2"+
		"\2\u00c9\u00cf\7 \2\2\u00ca\u00cb\7\37\2\2\u00cb\u00cc\5\4\3\2\u00cc\u00cd"+
		"\7 \2\2\u00cd\u00cf\3\2\2\2\u00ce\u00c8\3\2\2\2\u00ce\u00ca\3\2\2\2\u00cf"+
		"\r\3\2\2\2\u00d0\u00d1\7!\2\2\u00d1\17\3\2\2\2\u00d2\u00d3\t\2\2\2\u00d3"+
		"\21\3\2\2\2\u00d4\u00d7\5\20\t\2\u00d5\u00d7\5z>\2\u00d6\u00d4\3\2\2\2"+
		"\u00d6\u00d5\3\2\2\2\u00d7\23\3\2\2\2\u00d8\u00d9\5.\30\2\u00d9\u00da"+
		"\7\30\2\2\u00da\u00db\5\n\6\2\u00db\u00e8\3\2\2\2\u00dc\u00dd\5.\30\2"+
		"\u00dd\u00de\7\30\2\2\u00de\u00df\7\33\2\2\u00df\u00e0\5\n\6\2\u00e0\u00e8"+
		"\3\2\2\2\u00e1\u00e2\5.\30\2\u00e2\u00e3\7\30\2\2\u00e3\u00e4\7\37\2\2"+
		"\u00e4\u00e5\5\n\6\2\u00e5\u00e6\7 \2\2\u00e6\u00e8\3\2\2\2\u00e7\u00d8"+
		"\3\2\2\2\u00e7\u00dc\3\2\2\2\u00e7\u00e1\3\2\2\2\u00e8\25\3\2\2\2\u00e9"+
		"\u00f6\5\30\r\2\u00ea\u00eb\5\30\r\2\u00eb\u00ec\7\7\2\2\u00ec\u00ed\5"+
		"\n\6\2\u00ed\u00f6\3\2\2\2\u00ee\u00ef\5\30\r\2\u00ef\u00f0\7\7\2\2\u00f0"+
		"\u00f1\7\37\2\2\u00f1\u00f2\5\n\6\2\u00f2\u00f3\7 \2\2\u00f3\u00f6\3\2"+
		"\2\2\u00f4\u00f6\5\34\17\2\u00f5\u00e9\3\2\2\2\u00f5\u00ea\3\2\2\2\u00f5"+
		"\u00ee\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\27\3\2\2\2\u00f7\u00fb\5\16\b"+
		"\2\u00f8\u00fb\5\20\t\2\u00f9\u00fb\5F$\2\u00fa\u00f7\3\2\2\2\u00fa\u00f8"+
		"\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb\31\3\2\2\2\u00fc\u00fd\7*\2\2\u00fd"+
		"\u0100\5\n\6\2\u00fe\u0100\7*\2\2\u00ff\u00fc\3\2\2\2\u00ff\u00fe\3\2"+
		"\2\2\u0100\33\3\2\2\2\u0101\u0102\7+\2\2\u0102\35\3\2\2\2\u0103\u0104"+
		"\7,\2\2\u0104\u0105\5$\23\2\u0105\u0106\7-\2\2\u0106\u0107\5 \21\2\u0107"+
		"\u0108\5\f\7\2\u0108\u010d\3\2\2\2\u0109\u010a\5 \21\2\u010a\u010b\5\f"+
		"\7\2\u010b\u010d\3\2\2\2\u010c\u0103\3\2\2\2\u010c\u0109\3\2\2\2\u010d"+
		"\37\3\2\2\2\u010e\u010f\t\3\2\2\u010f!\3\2\2\2\u0110\u0113\7\6\2\2\u0111"+
		"\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0111\3\2\2\2\u0113#\3\2\2\2"+
		"\u0114\u0115\b\23\1\2\u0115\u0118\5&\24\2\u0116\u0118\3\2\2\2\u0117\u0114"+
		"\3\2\2\2\u0117\u0116\3\2\2\2\u0118\u012a\3\2\2\2\u0119\u011a\f\6\2\2\u011a"+
		"\u011b\7\6\2\2\u011b\u0129\5&\24\2\u011c\u011d\f\5\2\2\u011d\u011e\5\""+
		"\22\2\u011e\u011f\7\33\2\2\u011f\u0120\5&\24\2\u0120\u0129\3\2\2\2\u0121"+
		"\u0122\f\4\2\2\u0122\u0123\5\"\22\2\u0123\u0124\7\37\2\2\u0124\u0125\5"+
		"$\23\2\u0125\u0126\5\"\22\2\u0126\u0127\7 \2\2\u0127\u0129\3\2\2\2\u0128"+
		"\u0119\3\2\2\2\u0128\u011c\3\2\2\2\u0128\u0121\3\2\2\2\u0129\u012c\3\2"+
		"\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b%\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012d\u0137\5(\25\2\u012e\u012f\5(\25\2\u012f\u0130\7\r\2\2\u0130"+
		"\u0137\3\2\2\2\u0131\u0132\5(\25\2\u0132\u0133\7\30\2\2\u0133\u0134\5"+
		"\n\6\2\u0134\u0137\3\2\2\2\u0135\u0137\7\r\2\2\u0136\u012d\3\2\2\2\u0136"+
		"\u012e\3\2\2\2\u0136\u0131\3\2\2\2\u0136\u0135\3\2\2\2\u0137\'\3\2\2\2"+
		"\u0138\u013d\5\16\b\2\u0139\u013d\5F$\2\u013a\u013d\5H%\2\u013b\u013d"+
		"\58\35\2\u013c\u0138\3\2\2\2\u013c\u0139\3\2\2\2\u013c\u013a\3\2\2\2\u013c"+
		"\u013b\3\2\2\2\u013d)\3\2\2\2\u013e\u013f\5\n\6\2\u013f\u0140\7\r\2\2"+
		"\u0140+\3\2\2\2\u0141\u014a\5\16\b\2\u0142\u0143\5\60\31\2\u0143\u0144"+
		"\5\62\32\2\u0144\u014a\3\2\2\2\u0145\u0146\5> \2\u0146\u0147\5\62\32\2"+
		"\u0147\u014a\3\2\2\2\u0148\u014a\5F$\2\u0149\u0141\3\2\2\2\u0149\u0142"+
		"\3\2\2\2\u0149\u0145\3\2\2\2\u0149\u0148\3\2\2\2\u014a-\3\2\2\2\u014b"+
		"\u0156\5\16\b\2\u014c\u014d\5\60\31\2\u014d\u014e\5\62\32\2\u014e\u0156"+
		"\3\2\2\2\u014f\u0150\5> \2\u0150\u0151\5\62\32\2\u0151\u0156\3\2\2\2\u0152"+
		"\u0156\5F$\2\u0153\u0156\5H%\2\u0154\u0156\58\35\2\u0155\u014b\3\2\2\2"+
		"\u0155\u014c\3\2\2\2\u0155\u014f\3\2\2\2\u0155\u0152\3\2\2\2\u0155\u0153"+
		"\3\2\2\2\u0155\u0154\3\2\2\2\u0156/\3\2\2\2\u0157\u0158\b\31\1\2\u0158"+
		"\u0167\5\16\b\2\u0159\u015a\7.\2\2\u015a\u0167\5\62\32\2\u015b\u015c\7"+
		".\2\2\u015c\u015d\5B\"\2\u015d\u015e\5\62\32\2\u015e\u0167\3\2\2\2\u015f"+
		"\u0167\5F$\2\u0160\u0167\5H%\2\u0161\u0167\58\35\2\u0162\u0167\5\22\n"+
		"\2\u0163\u0167\5\\/\2\u0164\u0167\5L\'\2\u0165\u0167\5D#\2\u0166\u0157"+
		"\3\2\2\2\u0166\u0159\3\2\2\2\u0166\u015b\3\2\2\2\u0166\u015f\3\2\2\2\u0166"+
		"\u0160\3\2\2\2\u0166\u0161\3\2\2\2\u0166\u0162\3\2\2\2\u0166\u0163\3\2"+
		"\2\2\u0166\u0164\3\2\2\2\u0166\u0165\3\2\2\2\u0167\u0171\3\2\2\2\u0168"+
		"\u0169\f\r\2\2\u0169\u0170\5\62\32\2\u016a\u016b\f\f\2\2\u016b\u016c\5"+
		"@!\2\u016c\u016d\5B\"\2\u016d\u016e\5\62\32\2\u016e\u0170\3\2\2\2\u016f"+
		"\u0168\3\2\2\2\u016f\u016a\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u016f\3\2"+
		"\2\2\u0171\u0172\3\2\2\2\u0172\61\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0175"+
		"\7\13\2\2\u0175\u017f\5\16\b\2\u0176\u0177\7\n\2\2\u0177\u017f\5\16\b"+
		"\2\u0178\u0179\7\b\2\2\u0179\u017f\5\16\b\2\u017a\u017b\7\t\2\2\u017b"+
		"\u017f\5\16\b\2\u017c\u017f\7\b\2\2\u017d\u017f\5\64\33\2\u017e\u0174"+
		"\3\2\2\2\u017e\u0176\3\2\2\2\u017e\u0178\3\2\2\2\u017e\u017a\3\2\2\2\u017e"+
		"\u017c\3\2\2\2\u017e\u017d\3\2\2\2\u017f\63\3\2\2\2\u0180\u0181\7/\2\2"+
		"\u0181\u0182\5\66\34\2\u0182\u0183\7\60\2\2\u0183\u0187\3\2\2\2\u0184"+
		"\u0185\7\61\2\2\u0185\u0187\5\64\33\2\u0186\u0180\3\2\2\2\u0186\u0184"+
		"\3\2\2\2\u0187\65\3\2\2\2\u0188\u018b\5\n\6\2\u0189\u018b\5N(\2\u018a"+
		"\u0188\3\2\2\2\u018a\u0189\3\2\2\2\u018b\67\3\2\2\2\u018c\u018d\7\22\2"+
		"\2\u018d\u018e\5:\36\2\u018e\u018f\5\"\22\2\u018f\u0190\7\23\2\2\u0190"+
		"9\3\2\2\2\u0191\u0192\b\36\1\2\u0192\u0195\5\26\f\2\u0193\u0195\3\2\2"+
		"\2\u0194\u0191\3\2\2\2\u0194\u0193\3\2\2\2\u0195\u01a7\3\2\2\2\u0196\u0197"+
		"\f\6\2\2\u0197\u0198\7\6\2\2\u0198\u01a6\5\26\f\2\u0199\u019a\f\5\2\2"+
		"\u019a\u019b\5\"\22\2\u019b\u019c\7\33\2\2\u019c\u019d\5\26\f\2\u019d"+
		"\u01a6\3\2\2\2\u019e\u019f\f\4\2\2\u019f\u01a0\5\"\22\2\u01a0\u01a1\7"+
		"\37\2\2\u01a1\u01a2\5:\36\2\u01a2\u01a3\5\"\22\2\u01a3\u01a4\7 \2\2\u01a4"+
		"\u01a6\3\2\2\2\u01a5\u0196\3\2\2\2\u01a5\u0199\3\2\2\2\u01a5\u019e\3\2"+
		"\2\2\u01a6\u01a9\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8"+
		";\3\2\2\2\u01a9\u01a7\3\2\2\2\u01aa\u01c7\7\62\2\2\u01ab\u01ac\7\62\2"+
		"\2\u01ac\u01c7\5\f\7\2\u01ad\u01ae\7\62\2\2\u01ae\u01af\7\63\2\2\u01af"+
		"\u01c7\5\n\6\2\u01b0\u01b1\7\62\2\2\u01b1\u01b2\7\63\2\2\u01b2\u01b3\5"+
		"\n\6\2\u01b3\u01b4\5\f\7\2\u01b4\u01c7\3\2\2\2\u01b5\u01b6\7\62\2\2\u01b6"+
		"\u01c7\5,\27\2\u01b7\u01b8\7\62\2\2\u01b8\u01b9\5,\27\2\u01b9\u01ba\5"+
		"\f\7\2\u01ba\u01c7\3\2\2\2\u01bb\u01bc\7\62\2\2\u01bc\u01bd\5,\27\2\u01bd"+
		"\u01be\7\63\2\2\u01be\u01bf\5\n\6\2\u01bf\u01c7\3\2\2\2\u01c0\u01c1\7"+
		"\62\2\2\u01c1\u01c2\5,\27\2\u01c2\u01c3\7\63\2\2\u01c3\u01c4\5\n\6\2\u01c4"+
		"\u01c5\5\f\7\2\u01c5\u01c7\3\2\2\2\u01c6\u01aa\3\2\2\2\u01c6\u01ab\3\2"+
		"\2\2\u01c6\u01ad\3\2\2\2\u01c6\u01b0\3\2\2\2\u01c6\u01b5\3\2\2\2\u01c6"+
		"\u01b7\3\2\2\2\u01c6\u01bb\3\2\2\2\u01c6\u01c0\3\2\2\2\u01c7=\3\2\2\2"+
		"\u01c8\u01c9\b \1\2\u01c9\u01ca\5\60\31\2\u01ca\u01cb\5@!\2\u01cb\u01cc"+
		"\5B\"\2\u01cc\u01d1\3\2\2\2\u01cd\u01d1\7.\2\2\u01ce\u01cf\7.\2\2\u01cf"+
		"\u01d1\5B\"\2\u01d0\u01c8\3\2\2\2\u01d0\u01cd\3\2\2\2\u01d0\u01ce\3\2"+
		"\2\2\u01d1\u01d8\3\2\2\2\u01d2\u01d3\f\5\2\2\u01d3\u01d4\5@!\2\u01d4\u01d5"+
		"\5B\"\2\u01d5\u01d7\3\2\2\2\u01d6\u01d2\3\2\2\2\u01d7\u01da\3\2\2\2\u01d8"+
		"\u01d6\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9?\3\2\2\2\u01da\u01d8\3\2\2\2"+
		"\u01db\u01de\7\64\2\2\u01dc\u01de\3\2\2\2\u01dd\u01db\3\2\2\2\u01dd\u01dc"+
		"\3\2\2\2\u01deA\3\2\2\2\u01df\u01e0\7\65\2\2\u01e0\u01e7\7\66\2\2\u01e1"+
		"\u01e2\7\65\2\2\u01e2\u01e3\5P)\2\u01e3\u01e4\5\"\22\2\u01e4\u01e5\7\66"+
		"\2\2\u01e5\u01e7\3\2\2\2\u01e6\u01df\3\2\2\2\u01e6\u01e1\3\2\2\2\u01e7"+
		"C\3\2\2\2\u01e8\u01e9\t\4\2\2\u01e9E\3\2\2\2\u01ea\u01eb\7\24\2\2\u01eb"+
		"\u01ec\5\16\b\2\u01ecG\3\2\2\2\u01ed\u01ee\7\20\2\2\u01ee\u01f5\7\21\2"+
		"\2\u01ef\u01f0\7\20\2\2\u01f0\u01f1\5P)\2\u01f1\u01f2\5\"\22\2\u01f2\u01f3"+
		"\7\21\2\2\u01f3\u01f5\3\2\2\2\u01f4\u01ed\3\2\2\2\u01f4\u01ef\3\2\2\2"+
		"\u01f5I\3\2\2\2\u01f6\u01f7\t\5\2\2\u01f7K\3\2\2\2\u01f8\u01f9\7\20\2"+
		"\2\u01f9\u01fa\5\n\6\2\u01fa\u01fb\5J&\2\u01fb\u01fc\5\n\6\2\u01fc\u01fd"+
		"\7\21\2\2\u01fdM\3\2\2\2\u01fe\u01ff\5\n\6\2\u01ff\u0200\5J&\2\u0200\u0201"+
		"\5\n\6\2\u0201\u020a\3\2\2\2\u0202\u0203\5\n\6\2\u0203\u0204\5J&\2\u0204"+
		"\u020a\3\2\2\2\u0205\u0206\5J&\2\u0206\u0207\5\n\6\2\u0207\u020a\3\2\2"+
		"\2\u0208\u020a\5J&\2\u0209\u01fe\3\2\2\2\u0209\u0202\3\2\2\2\u0209\u0205"+
		"\3\2\2\2\u0209\u0208\3\2\2\2\u020aO\3\2\2\2\u020b\u020c\b)\1\2\u020c\u0213"+
		"\5R*\2\u020d\u020e\7\37\2\2\u020e\u020f\5P)\2\u020f\u0210\5\"\22\2\u0210"+
		"\u0211\7 \2\2\u0211\u0213\3\2\2\2\u0212\u020b\3\2\2\2\u0212\u020d\3\2"+
		"\2\2\u0213\u0225\3\2\2\2\u0214\u0215\f\6\2\2\u0215\u0216\7\6\2\2\u0216"+
		"\u0224\5R*\2\u0217\u0218\f\5\2\2\u0218\u0219\5\"\22\2\u0219\u021a\7\33"+
		"\2\2\u021a\u021b\5R*\2\u021b\u0224\3\2\2\2\u021c\u021d\f\3\2\2\u021d\u021e"+
		"\5\"\22\2\u021e\u021f\7\37\2\2\u021f\u0220\5P)\2\u0220\u0221\5\"\22\2"+
		"\u0221\u0222\7 \2\2\u0222\u0224\3\2\2\2\u0223\u0214\3\2\2\2\u0223\u0217"+
		"\3\2\2\2\u0223\u021c\3\2\2\2\u0224\u0227\3\2\2\2\u0225\u0223\3\2\2\2\u0225"+
		"\u0226\3\2\2\2\u0226Q\3\2\2\2\u0227\u0225\3\2\2\2\u0228\u022c\5\n\6\2"+
		"\u0229\u022c\5*\26\2\u022a\u022c\7\r\2\2\u022b\u0228\3\2\2\2\u022b\u0229"+
		"\3\2\2\2\u022b\u022a\3\2\2\2\u022cS\3\2\2\2\u022d\u022e\b+\1\2\u022e\u022f"+
		"\5\n\6\2\u022f\u0235\3\2\2\2\u0230\u0231\f\3\2\2\u0231\u0232\7\6\2\2\u0232"+
		"\u0234\5\n\6\2\u0233\u0230\3\2\2\2\u0234\u0237\3\2\2\2\u0235\u0233\3\2"+
		"\2\2\u0235\u0236\3\2\2\2\u0236U\3\2\2\2\u0237\u0235\3\2\2\2\u0238\u0239"+
		"\78\2\2\u0239\u024a\5\f\7\2\u023a\u023b\78\2\2\u023b\u023c\5\f\7\2\u023c"+
		"\u023d\5X-\2\u023d\u024a\3\2\2\2\u023e\u023f\78\2\2\u023f\u0240\5\f\7"+
		"\2\u0240\u0241\79\2\2\u0241\u0242\5\f\7\2\u0242\u024a\3\2\2\2\u0243\u0244"+
		"\78\2\2\u0244\u0245\5\f\7\2\u0245\u0246\5X-\2\u0246\u0247\79\2\2\u0247"+
		"\u0248\5\f\7\2\u0248\u024a\3\2\2\2\u0249\u0238\3\2\2\2\u0249\u023a\3\2"+
		"\2\2\u0249\u023e\3\2\2\2\u0249\u0243\3\2\2\2\u024aW\3\2\2\2\u024b\u024c"+
		"\7:\2\2\u024c\u024d\5\16\b\2\u024d\u024e\5\f\7\2\u024e\u0256\3\2\2\2\u024f"+
		"\u0250\7:\2\2\u0250\u0251\58\35\2\u0251\u0252\5\f\7\2\u0252\u0256\3\2"+
		"\2\2\u0253\u0254\7:\2\2\u0254\u0256\5\f\7\2\u0255\u024b\3\2\2\2\u0255"+
		"\u024f\3\2\2\2\u0255\u0253\3\2\2\2\u0256Y\3\2\2\2\u0257\u0258\7;\2\2\u0258"+
		"\u0259\5\n\6\2\u0259[\3\2\2\2\u025a\u025b\7\16\2\2\u025b\u025c\5\4\3\2"+
		"\u025c\u025d\7\17\2\2\u025d\u0265\3\2\2\2\u025e\u025f\7\16\2\2\u025f\u0260"+
		"\7\37\2\2\u0260\u0261\5\4\3\2\u0261\u0262\7 \2\2\u0262\u0263\7\17\2\2"+
		"\u0263\u0265\3\2\2\2\u0264\u025a\3\2\2\2\u0264\u025e\3\2\2\2\u0265]\3"+
		"\2\2\2\u0266\u0267\7<\2\2\u0267\u0275\5\n\6\2\u0268\u0269\7<\2\2\u0269"+
		"\u026a\5\n\6\2\u026a\u026b\7=\2\2\u026b\u026c\5\n\6\2\u026c\u0275\3\2"+
		"\2\2\u026d\u026e\7>\2\2\u026e\u0275\5\n\6\2\u026f\u0270\7>\2\2\u0270\u0271"+
		"\5\n\6\2\u0271\u0272\7=\2\2\u0272\u0273\5\n\6\2\u0273\u0275\3\2\2\2\u0274"+
		"\u0266\3\2\2\2\u0274\u0268\3\2\2\2\u0274\u026d\3\2\2\2\u0274\u026f\3\2"+
		"\2\2\u0275_\3\2\2\2\u0276\u0277\7?\2\2\u0277\u027b\5\f\7\2\u0278\u0279"+
		"\7?\2\2\u0279\u027b\5\n\6\2\u027a\u0276\3\2\2\2\u027a\u0278\3\2\2\2\u027b"+
		"a\3\2\2\2\u027c\u027d\7@\2\2\u027d\u0282\5L\'\2\u027e\u027f\5d\63\2\u027f"+
		"\u0280\5h\65\2\u0280\u0282\3\2\2\2\u0281\u027c\3\2\2\2\u0281\u027e\3\2"+
		"\2\2\u0282c\3\2\2\2\u0283\u0284\7@\2\2\u0284\u0289\5f\64\2\u0285\u0286"+
		"\7@\2\2\u0286\u0287\7A\2\2\u0287\u0289\5f\64\2\u0288\u0283\3\2\2\2\u0288"+
		"\u0285\3\2\2\2\u0289e\3\2\2\2\u028a\u0290\5(\25\2\u028b\u028c\5(\25\2"+
		"\u028c\u028d\7\6\2\2\u028d\u028e\5(\25\2\u028e\u0290\3\2\2\2\u028f\u028a"+
		"\3\2\2\2\u028f\u028b\3\2\2\2\u0290g\3\2\2\2\u0291\u0292\7B\2\2\u0292\u02b3"+
		"\5\n\6\2\u0293\u0294\7C\2\2\u0294\u02b3\5\n\6\2\u0295\u0296\7B\2\2\u0296"+
		"\u0297\5\n\6\2\u0297\u0298\7=\2\2\u0298\u0299\5\n\6\2\u0299\u02b3\3\2"+
		"\2\2\u029a\u029b\7C\2\2\u029b\u029c\5\n\6\2\u029c\u029d\7=\2\2\u029d\u029e"+
		"\5\n\6\2\u029e\u02b3\3\2\2\2\u029f\u02a0\7B\2\2\u02a0\u02a1\5\n\6\2\u02a1"+
		"\u02a2\7D\2\2\u02a2\u02a3\5\n\6\2\u02a3\u02b3\3\2\2\2\u02a4\u02a5\7B\2"+
		"\2\u02a5\u02a6\5\n\6\2\u02a6\u02a7\7=\2\2\u02a7\u02a8\5\n\6\2\u02a8\u02a9"+
		"\7D\2\2\u02a9\u02aa\5\n\6\2\u02aa\u02b3\3\2\2\2\u02ab\u02ac\7B\2\2\u02ac"+
		"\u02ad\5\n\6\2\u02ad\u02ae\7D\2\2\u02ae\u02af\5\n\6\2\u02af\u02b0\7=\2"+
		"\2\u02b0\u02b1\5\n\6\2\u02b1\u02b3\3\2\2\2\u02b2\u0291\3\2\2\2\u02b2\u0293"+
		"\3\2\2\2\u02b2\u0295\3\2\2\2\u02b2\u029a\3\2\2\2\u02b2\u029f\3\2\2\2\u02b2"+
		"\u02a4\3\2\2\2\u02b2\u02ab\3\2\2\2\u02b3i\3\2\2\2\u02b4\u02b5\7E\2\2\u02b5"+
		"\u02b6\5\n\6\2\u02b6\u02b7\7\37\2\2\u02b7\u02b8\5l\67\2\u02b8\u02b9\7"+
		" \2\2\u02b9\u02cf\3\2\2\2\u02ba\u02bb\7E\2\2\u02bb\u02bc\5\n\6\2\u02bc"+
		"\u02bd\7\37\2\2\u02bd\u02be\5l\67\2\u02be\u02bf\7\36\2\2\u02bf\u02c0\5"+
		"\f\7\2\u02c0\u02c1\7 \2\2\u02c1\u02cf\3\2\2\2\u02c2\u02c3\7E\2\2\u02c3"+
		"\u02c4\7\37\2\2\u02c4\u02c5\5l\67\2\u02c5\u02c6\7 \2\2\u02c6\u02cf\3\2"+
		"\2\2\u02c7\u02c8\7E\2\2\u02c8\u02c9\7\37\2\2\u02c9\u02ca\5l\67\2\u02ca"+
		"\u02cb\7\36\2\2\u02cb\u02cc\5\f\7\2\u02cc\u02cd\7 \2\2\u02cd\u02cf\3\2"+
		"\2\2\u02ce\u02b4\3\2\2\2\u02ce\u02ba\3\2\2\2\u02ce\u02c2\3\2\2\2\u02ce"+
		"\u02c7\3\2\2\2\u02cfk\3\2\2\2\u02d0\u02d1\b\67\1\2\u02d1\u02d2\5n8\2\u02d2"+
		"\u02d7\3\2\2\2\u02d3\u02d4\f\3\2\2\u02d4\u02d6\5n8\2\u02d5\u02d3\3\2\2"+
		"\2\u02d6\u02d9\3\2\2\2\u02d7\u02d5\3\2\2\2\u02d7\u02d8\3\2\2\2\u02d8m"+
		"\3\2\2\2\u02d9\u02d7\3\2\2\2\u02da\u02db\7F\2\2\u02db\u02dc\5T+\2\u02dc"+
		"\u02dd\5\f\7\2\u02dd\u02e4\3\2\2\2\u02de\u02df\7F\2\2\u02df\u02e0\5T+"+
		"\2\u02e0\u02e1\5\f\7\2\u02e1\u02e2\7\33\2\2\u02e2\u02e4\3\2\2\2\u02e3"+
		"\u02da\3\2\2\2\u02e3\u02de\3\2\2\2\u02e4o\3\2\2\2\u02e5\u02e6\b9\1\2\u02e6"+
		"\u02e7\7G\2\2\u02e7\u02e8\5\n\6\2\u02e8\u02e9\5\f\7\2\u02e9\u02f2\3\2"+
		"\2\2\u02ea\u02eb\f\3\2\2\u02eb\u02ec\7\36\2\2\u02ec\u02ed\7G\2\2\u02ed"+
		"\u02ee\5\n\6\2\u02ee\u02ef\5\f\7\2\u02ef\u02f1\3\2\2\2\u02f0\u02ea\3\2"+
		"\2\2\u02f1\u02f4\3\2\2\2\u02f2\u02f0\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3"+
		"q\3\2\2\2\u02f4\u02f2\3\2\2\2\u02f5\u02f6\7H\2\2\u02f6\u02fe\5\n\6\2\u02f7"+
		"\u02f8\7I\2\2\u02f8\u02fe\5\n\6\2\u02f9\u02fa\7\3\2\2\u02fa\u02fe\5\n"+
		"\6\2\u02fb\u02fc\7\26\2\2\u02fc\u02fe\5\n\6\2\u02fd\u02f5\3\2\2\2\u02fd"+
		"\u02f7\3\2\2\2\u02fd\u02f9\3\2\2\2\u02fd\u02fb\3\2\2\2\u02fes\3\2\2\2"+
		"\u02ff\u0300\7\4\2\2\u0300\u0304\5,\27\2\u0301\u0302\7\27\2\2\u0302\u0304"+
		"\5,\27\2\u0303\u02ff\3\2\2\2\u0303\u0301\3\2\2\2\u0304u\3\2\2\2\u0305"+
		"\u0306\5,\27\2\u0306\u0307\7\4\2\2\u0307\u031f\3\2\2\2\u0308\u0309\5,"+
		"\27\2\u0309\u030a\7\27\2\2\u030a\u031f\3\2\2\2\u030b\u030c\5,\27\2\u030c"+
		"\u030d\7J\2\2\u030d\u030e\5\n\6\2\u030e\u031f\3\2\2\2\u030f\u0310\5,\27"+
		"\2\u0310\u0311\7J\2\2\u0311\u0312\7\37\2\2\u0312\u0313\5\n\6\2\u0313\u0314"+
		"\7 \2\2\u0314\u031f\3\2\2\2\u0315\u0316\5,\27\2\u0316\u0317\7J\2\2\u0317"+
		"\u0318\7\33\2\2\u0318\u0319\5\n\6\2\u0319\u031f\3\2\2\2\u031a\u031b\5"+
		",\27\2\u031b\u031c\7\63\2\2\u031c\u031d\5\n\6\2\u031d\u031f\3\2\2\2\u031e"+
		"\u0305\3\2\2\2\u031e\u0308\3\2\2\2\u031e\u030b\3\2\2\2\u031e\u030f\3\2"+
		"\2\2\u031e\u0315\3\2\2\2\u031e\u031a\3\2\2\2\u031fw\3\2\2\2\u0320\u0332"+
		"\7\32\2\2\u0321\u0322\7\26\2\2\u0322\u0332\5\n\6\2\u0323\u0324\7\3\2\2"+
		"\u0324\u0332\5\n\6\2\u0325\u0326\7K\2\2\u0326\u0332\5\n\6\2\u0327\u0328"+
		"\7\25\2\2\u0328\u0332\5\n\6\2\u0329\u032a\7L\2\2\u032a\u0332\5\n\6\2\u032b"+
		"\u032c\7M\2\2\u032c\u0332\5\n\6\2\u032d\u032e\7N\2\2\u032e\u0332\5\n\6"+
		"\2\u032f\u0330\7O\2\2\u0330\u0332\5\n\6\2\u0331\u0320\3\2\2\2\u0331\u0321"+
		"\3\2\2\2\u0331\u0323\3\2\2\2\u0331\u0325\3\2\2\2\u0331\u0327\3\2\2\2\u0331"+
		"\u0329\3\2\2\2\u0331\u032b\3\2\2\2\u0331\u032d\3\2\2\2\u0331\u032f\3\2"+
		"\2\2\u0332y\3\2\2\2\u0333\u0334\t\6\2\2\u0334{\3\2\2\2?~\u0088\u008a\u008f"+
		"\u0094\u00b8\u00c3\u00c5\u00ce\u00d6\u00e7\u00f5\u00fa\u00ff\u010c\u0112"+
		"\u0117\u0128\u012a\u0136\u013c\u0149\u0155\u0166\u016f\u0171\u017e\u0186"+
		"\u018a\u0194\u01a5\u01a7\u01c6\u01d0\u01d8\u01dd\u01e6\u01f4\u0209\u0212"+
		"\u0223\u0225\u022b\u0235\u0249\u0255\u0264\u0274\u027a\u0281\u0288\u028f"+
		"\u02b2\u02ce\u02d7\u02e3\u02f2\u02fd\u0303\u031e\u0331";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}