lexer grammar CoffeeScriptLexerGrammar;

@lexer::members {
                 
private Token last;
private Boolean prevSpaced = false;
private java.util.Queue<Token> tokens = new java.util.LinkedList<Token>();
private java.util.Stack<Integer> indentStack = new java.util.Stack<Integer>();

private boolean areRegularExpressionsEnabled() {

    if (last == null) {
        return true;
    }
    
    switch (last.getType()) {
        case NUMBER:
        case REGEX:
        case TRUE:
        case FALSE:
        case DOUBLE_PLUS_TOK:
        case DOUBLE_MINUS_TOK:
        case RBRACKET:
            return !prevSpaced;
        case RPAR:
        case RBRACE:
        case THIS_TOK:
        case IDENTIFIER:
        case STRING: 
            return prevSpaced;
        default:
            return true;
    }
}

@Override
public void emit(Token t) {
    setState(t.getType());
    tokens.offer(t);
}

private Token getNextToken() {
    if(_input.LA(1) == -1) {   
        setCharPositionInLine(getCharPositionInLine() + getText().length());
        setText("");
        while(!indentStack.empty()) {                           
            jump(OUTDENT);
            jump(TERMINATOR);
            indentStack.pop();
        }    
    }
    if (tokens.isEmpty()) super.nextToken();
    return tokens.isEmpty() ? new CommonToken(Token.EOF,"EOF") : tokens.poll();
}

@Override
public Token nextToken() {
    Token token = getNextToken();
    prevSpaced = (token.getType() == SPACES);
    if(!prevSpaced) last = token;
    return token;
}

private void jump(int ttype) {
    jump(ttype,-1);
}

private void jumpIndent(int n) {
    jump(INDENT);
    indentStack.push(n);
}

private void jump(int ttype, int length) {
    int tokenEnd = (length == -1) ? _tokenStartCharIndex + getText().length() : _tokenStartCharIndex;
    Token tok = _factory.create(_tokenFactorySourcePair, ttype, getText(), _channel, _tokenStartCharIndex, tokenEnd -1, _tokenStartLine, _tokenStartCharPositionInLine);
    setToken(tok);
    emit(tok);
}

}

// keywords
NULL: 'null';
THIS_TOK: 'this';
NEW: 'new';
DELETE: 'delete';
TYPEOF: 'typeof';
IN: 'in';
INSTANCEOF: 'instanceof';
RETURN_TOK: 'return';
THROW_TOK: 'throw';
BREAK: 'break';
CONTINUE: 'continue';
DEBUGGER: 'debugger';
IF_TOK: 'if';
ELSE: 'else';
SWITCH_TOK: 'switch';
FOR_TOK: 'for';
WHILE_TOK: 'while';
DO: 'do';
TRY_TOK: 'try';
CATCH: 'catch';
FINALLY: 'finally';
CLASS_TOK: 'class';
EXTENDS: 'extends';
SUPER: 'super';
UNDEFINED: 'undefined';
THEN: 'then';
UNLESS: 'unless';
UNTIL: 'until';
LOOP: 'loop';
OF: 'of';
BY: 'by';
WHEN: 'when';
AND: 'and';
OR: 'or';
IS: 'is';
ISNT: 'isnt';
NOT: 'not';
YES: 'yes';
NO: 'no';
ON: 'on';
OFF: 'off';
CASE: 'case';
DEFAULT: 'default';
FUNCTION: 'function';
VAR: 'var';
VOID: 'void';
WITH: 'with';
CONST: 'const';
LET: 'let';
ENUM: 'enum';
EXPORT: 'export';
IMPORT: 'import';
NATIVE: 'native';
U_HASPROP: '__hasProp';
U_EXTENDS: '__extends';
U_SLICE: '__slice';
U_BIND: '__bind';
U_INDEXOF: '__indexOf';
IMPLEMENTS: 'implements';
INTERFACE: 'interface';
PUBLIC: 'public';
STATIC: 'static';
YIELD: 'yield';
OWN: 'own';
TRUE: 'true';
FALSE: 'false';

//operators
MINUS_ASSIGN_TOK: '-=';
PLUS_ASSIGN_TOK: '+=';
DIV_ASSIGN_TOK: '/=';
MULT_ASSIGN_TOK: '*=';
MOD_ASSIGN_TOK: '%=';
OR_ASSIGN_TOK: '||=';
AND_ASSIGN_TOK: '&&=';
QUEST_ASSIGN_TOK: '?=';
LSHIFT_ASSIGN_TOK: '<<=';
RSHIFT_ASSIGN_TOK: '>>=';
RSHIFT3_ASSIGN_TOK: '>>>=';
AMP_ASSIGN_TOK: '&=';
XOR_ASSIGN_TOK: '^=';
PIPE_ASSIGN_TOK: '|=';
AMP_TOK: '&';
DOUBLE_AMP_TOK: '&&';
PIPE_TOK: '|';
DOUBLE_PIPE_TOK: '||';
XOR: '^';
EQUALS_TOK: '==';
NOT_EQUALS_TOK: '!=';
LT_TOK: '<';
GT_TOK: '>';
LE_TOK: '<=';
GE_TOK: '>=';
LSHIFT_TOK: '<<';
RSHIFT_TOK: '>>';
RSHIFT3_TOK: '>>>';
DIV_TOK: '/';
MULT_TOK: '*';
MOD_TOK: '%';
EQUATING_TOK: '=';
COLON_TOK: ':' ;
FUNC_DOUBLE_ARROW: '=>';
FUNC_SINGLE_ARROW: '->';
TRIPLE_DOT_TOK: '...';
DOT_TOK: '.';
QUEST_DOT_TOK: '?.';
DOUBLE_COLON_TOK: '::';
QUEST_DOUBLE_COLON_TOK: '?::';
AT_TOK: '@';
DOUBLE_DOT_TOK: '..';
PLUS_TOK: '+';
MINUS_TOK: '-';
DOUBLE_PLUS_TOK: '++';
DOUBLE_MINUS_TOK: '--';
QUEST_TOK: '?';
TILDE_TOK: '~';
EXCLAM_TOK: '!';

//separators
COMMA_TOK: ',';
LBRACE: '{';
RBRACE: '}';
LBRACKET: '[';
RBRACKET: ']';
LPAR: '(';
RPAR: ')';

//special tokens
NUMBER
    : '0'[b][01]+
    | '0'[o][0-7]+
    | '0'[x](DIGIT | [a-fA-F])+
    | (DIGIT*('.')?DIGIT+)([e][-+]?DIGIT+)?
;

STRING
    : [']ESC_AMP*?[']
    | ["](INTERPOLATION | ESC_QUOTE)*?["]
    |  '\'\'\''.*?'\'\'\''
    | '"""'.*?'"""'
;

JSTOKEN
    : '`' ESC_BACKTICK*? '`'
;

REGEX
    : HEREGEX
    | REGEXP     
;

REGEXP
    : {areRegularExpressionsEnabled()}? DIV_TOK REGEXP_FIRST REGEXP_CONTENT* DIV_TOK REGEXP_FLAGS*
    | {areRegularExpressionsEnabled()}? DIV_TOK DIV_TOK REGEXP_FLAGS*
;
COMMENT
    : HERECOMMENT
    | LINE_COMMENT
;

IDENTIFIER
    : [$A-Za-z_\u007f-\uffff][$A-Za-z_0-9\u007f-\uffff]* 
;

CLASS_FIELD
    : '@'[$A-Za-z_\u007f-\uffff][$A-Za-z_0-9\u007f-\uffff]* 
;

//whitespaces
NEW_LINE
    : '\n'
;

SKIP
    : '\r' -> channel(HIDDEN)
;

SPACES
    : (' ' | '\t')+
;

TERMINATOR: (NEW_LINE)+ (SPACES)?
{   

Integer indentOffset = 0;
if(!indentStack.empty()) indentOffset = indentStack.peek();
int n = getText() == null ? 0 : getText().replace("\n","").length();

if(n > indentOffset) {
    jumpIndent(n);
} else if(n < indentOffset) {
    while (n < indentOffset) {
        jump(OUTDENT);       
        jump(TERMINATOR);
        indentStack.pop();
        indentOffset = (!indentStack.empty()) ? indentStack.peek() : 0;
    }		       
} else if(_input.LA(1) == -1) {  
    while(!indentStack.empty()) {                           
        jump(OUTDENT);
        jump(TERMINATOR);
        indentStack.pop();
    }
} 

}
;

INDENT
    : ''
;

OUTDENT
    : ''
;

//fragments
fragment ESC_BACKTICK
    : '\\`'
    | '\\\\'
    |(~'`')
;

fragment ESC_AMP
    : '\\\''
    | '\\\\'
    |(~['])
;

fragment ESC_QUOTE
    : '\\"'
    | '\\\\'
    |~["]                      
;

fragment INTERPOLATION
    : '#{' .*? '}'               
;
                         
fragment DIGIT
    : [0-9]
;

fragment LINE_COMMENT
    : ('#'(~[\n])*?'\n')+
;

fragment HERECOMMENT
    :'###'(~[#])*?'###'
;

fragment HEREGEX
    : '///'.*?'///' REGEXP_FLAGS*
;

fragment LineTerminator
	: CR | LF | LS | PS
	;

fragment LF // Line Feed
	: '\n'
	;

fragment CR // Carriage Return
	: '\r'
	;

fragment LS // Line Separator
	: '\u2028'
	;
	
fragment PS // Paragraph Separator
	: '\u2029'
	;

fragment BACKSLASH_SEQ
	: BSLASH ~( [\n\r\u2028\u2029] )
	;

fragment REGEXP_FIRST
	: ~ ( [\n\r\u2028\u2029*= \t\\/])
	| BACKSLASH_SEQ
	;

fragment REGEXP_CONTENT 
    : BRACKETS_REGEX
    | ~( [\n\r\u2028\u2029\\/] )
    | BACKSLASH_SEQ
;

fragment REGEXP_FLAGS
    : [imgy]
;

fragment BSLASH
    : '\\'
;

fragment BRACKETS_REGEX
    : LBRACKET .*? RBRACKET
;
