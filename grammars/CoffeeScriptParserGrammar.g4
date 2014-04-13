parser grammar CoffeeScriptParserGrammar;

tokens {
    MINUS_TOK, DOUBLE_MINUS_TOK, FUNC_SINGLE_ARROW, COMMA_TOK, COLON_TOK, 
    DOUBLE_COLON_TOK, QUEST_DOUBLE_COLON_TOK, QUEST_DOT_TOK, DOT_TOK, 
    DOUBLE_DOT_TOK, TRIPLE_DOT_TOK, LPAR, RPAR, LBRACKET, RBRACKET, LBRACE, 
    RBRACE, AT_TOK, DOUBLE_STAR, PLUS_TOK, DOUBLE_PLUS_TOK, EQUATING_TOK, 
    FUNC_DOUBLE_ARROW, QUEST_TOK, TERMINATOR, STATEMENT, POST_IF, ELSE, 
    INDENT, OUTDENT, IDENTIFIER, NUMBER, STRING, JS, REGEX, DEBUGGER, 
    UNDEFINED, NULL, BOOL, RETURN, HERECOMMENT, PARAM_START, PARAM_END, 
    SUPER, INDEX_START, INDEX_END, INDEX_SOAK, CLASS, EXTENDS, FUNC_EXIST, 
    CALL_START, CALL_END, THIS, TRY, FINALLY, CATCH, THROW, WHILE, WHEN, UNTIL,
    LOOP, FOR, OWN, FORIN, FOROF, BY, SWITCH, LEADING_WHEN, IF, UNARY, 
    UNARY_MATH, COMPOUND_ASSIGN, MATH, SHIFT, COMPARE, LOGIC, RELATION, BAD_TOKEN
}

root
    : body
    |
    ;

body
    : line bodyRest 
    ;
 
 bodyRest
    : TERMINATOR line bodyRest
    | TERMINATOR bodyRest
    |
    ;
 
line
    : expression
    | statement
    ;

statement
    : returnRule
    | comment 
    | STATEMENT
    ;

// All the different types of expressions in our language. The basic unit of
// CoffeeScript is the **expression** -- everything that can be an expression
// is one. Blocks serve as the building blocks of many other rules making
// them somewhat circular.
expression
    : value expressionRest
    | invocation expressionRest
    | code expressionRest
      
    //operation rule 
    | unaryOperation expressionRest
    | doubleSignOperation expressionRest
      
    // The existential operator(http://jashkenas.github.com/coffee-script/#existence).
    | simpleAssignableOperation expressionRest
    // end operation rule   
    
    //assign rule
    | assign expressionRest 
    
    //if rule
    | ifBlock expressionRest
    | ifBlock ELSE block expressionRest
    | statement POST_IF expression expressionRest
    //end if rule   
    
    //try rule
    | tryRule expressionRest 

    // while rule
    | whileSource block expressionRest
    | statement whileSource expressionRest
    | loop expressionRest
    // end while rule

    // for rule
    | statement forBody expressionRest
    | forBody block expressionRest
    // end for rule   
    
    | switchRule expressionRest 
    | classRule expressionRest
    | throwRule expressionRest
    ;
 
 expressionRest
    : operationRest expressionRest
    | POST_IF expression expressionRest   
    | whileSource expressionRest
    | forBody expressionRest
    |
    ;

// An indented block of expressions. Note that the Rewriter(rewriter.html)
// will convert some postfix forms into blocks for us by adjusting the
// token stream.
block
    : INDENT OUTDENT
    | INDENT body OUTDENT 
    ;


// A literal identifier a variable name or property.
identifier
    : IDENTIFIER
    ;


// Alphanumerics are separated from the other **literal** matchers because
// they can also serve as keys in object literals.
alphaNumeric
    : NUMBER
    | STRING
    ;


// All of our immediate values. Generally these can be passed straight
// through and printed to JavaScript.
literal
    : alphaNumeric
    | literalTokens
    ;

literalTokens
    : JS
    | REGEX
    | DEBUGGER
    | UNDEFINED
    | NULL
    | BOOL
    ;


// Assignment of a variable property or index to a value.
assign
    : assignable EQUATING_TOK expression 
    | assignable EQUATING_TOK TERMINATOR expression
    | assignable EQUATING_TOK INDENT expression OUTDENT
    ;


// Assignment when it happens within an object literal. The difference from
// the ordinary **assign** is that these allow numbers and strings as keys.
assignObj
    : objAssignable 
    | objAssignable COLON_TOK expression
    | objAssignable COLON_TOK INDENT expression OUTDENT  
    | comment
    ;


objAssignable
    : identifier 
    | alphaNumeric
    | thisProperty
    ; 


// A return statement from a function body.
returnRule
    : RETURN expression 
    | RETURN 
    ;


// A block comment.
comment
    : HERECOMMENT
    ;


// The **code** node is the function literal. Its defined by an indented block
// of **block** preceded by a function arrow with an optional parameter
// list.
code
    : PARAM_START paramList PARAM_END funcGlyph block
    | funcGlyph block 
    ;


// CoffeeScript has two different symbols for functions. `->` is for ordinary
// functions and `=>` is for functions bound to the current value of *this*.
funcGlyph
    : FUNC_DOUBLE_ARROW
    | FUNC_SINGLE_ARROW
    ;


// An optional trailing comma.
optComma
    : COMMA_TOK
    |
    ;


// The list of parameters that a function accepts can be of any length.
paramList
    : param paramListRest
    | paramListRest
    ;
 
 paramListRest
 	:
    | COMMA_TOK param paramListRest	
    | optComma TERMINATOR param paramListRest
    | optComma INDENT paramList optComma OUTDENT paramListRest
    |
 	;


// A single parameter in a function definition can be ordinary or a splat
// that hoovers up the remaining arguments.
param
    : paramVar
    | paramVar TRIPLE_DOT_TOK
    | paramVar EQUATING_TOK expression 
    | TRIPLE_DOT_TOK
    ;


// Function Parameters
paramVar
    : identifier
    | thisProperty
    | array
    | object
    ;


// A splat that occurs outside of a parameter list.
splat
    : expression TRIPLE_DOT_TOK
    ;


// Variables and properties that can be assigned to.
simpleAssignable
    : identifier
    | value accessor
    | invocation accessor 
    | thisProperty
    ;

//// Everything that can be assigned to.
assignable
    : identifier
    | value accessor
    | invocation accessor 
    | thisProperty
    | array 
    | object
    ;


// The types of things that can be treated as values -- assigned to invoked
// as functions indexed into named as a class etc.
value
    : identifier valueRest
    | SUPER accessor valueRest
    | SUPER arguments accessor valueRest
    | thisProperty valueRest
    | array valueRest
    | object valueRest
    | literal valueRest
    | parenthetical valueRest
    | range valueRest
    | thisRule valueRest
    ;
 
 valueRest
    : accessor valueRest
    | optFuncExist arguments accessor valueRest
    |
    ;


// The general group of accessors into an object by property by prototype
// or by array index or slice.
accessor
    : DOT_TOK identifier 
    | QUEST_DOT_TOK identifier 
    | DOUBLE_COLON_TOK identifier 
    | QUEST_DOUBLE_COLON_TOK identifier
    | DOUBLE_COLON_TOK
    | index
    ;


// Indexing into an object or array using bracket notation.
index
    : INDEX_START indexValue INDEX_END
    | INDEX_SOAK  index 
    ;


indexValue
    : expression
    | slice 
    ;


// In CoffeeScript an object literal is simply a list of assignments.
object
    : LBRACE assignList optComma RBRACE
    ;


// Assignment of properties within an object literal can be separated by
// comma as in JavaScript or simply by newline.
assignList
    : assignObj assignListRest
    | assignListRest
    ;
 
 assignListRest
    : COMMA_TOK assignObj assignListRest
    | optComma TERMINATOR assignObj assignListRest
    | optComma INDENT assignList optComma OUTDENT assignListRest
    |
 	;
 	


// class definitions have optional bodies of prototype property assignments
// and optional references to the superclass.
classRule
    : CLASS
    | CLASS block
    | CLASS EXTENDS expression 
    | CLASS EXTENDS expression block 
    | CLASS simpleAssignable 
    | CLASS simpleAssignable block 
    | CLASS simpleAssignable EXTENDS expression
    | CLASS simpleAssignable EXTENDS expression block
    ;


// Ordinary function invocation or a chained series of calls.
invocation
    : value optFuncExist arguments invocationRest
    | SUPER invocationRest
    | SUPER arguments invocationRest
    ;
 
 invocationRest
    : optFuncExist arguments invocationRest
    |
 	;


// An optional existence check on a function.
optFuncExist
    : FUNC_EXIST
    |
    ;


// The list of arguments to a function call.
arguments
    : CALL_START CALL_END 
    | CALL_START argList optComma CALL_END
    ;


// A reference to the *this* current object.
thisRule
    : THIS
    | AT_TOK
    ;


// A reference to a property on *this*.
thisProperty
    : AT_TOK identifier
    ;


// The array literal.
array
    : LBRACKET RBRACKET
    | LBRACKET argList optComma RBRACKET
    ;


// Inclusive and exclusive range dots.
rangeDots
    : DOUBLE_DOT_TOK
    | TRIPLE_DOT_TOK
    ;


// The CoffeeScript range literal.
range
    : LBRACKET expression rangeDots expression RBRACKET
    ;


// array slice literals.
slice
    : expression rangeDots expression 
    | expression rangeDots
    | rangeDots expression
    | rangeDots 
    ;


// The **argList** is both the list of objects passed into a function call
// as well as the contents of an array literal
// (i.e. comma-separated expressions). Newlines work as well.
argList
    : arg argListRest
    | INDENT argList optComma OUTDENT argListRest
    ;
 
 argListRest
    : COMMA_TOK arg argListRest
    | optComma TERMINATOR arg argListRest
    | optComma INDENT argList optComma OUTDENT argListRest
 	|
 	;


// Valid arguments are Blocks or Splats.
arg
    : expression
    | splat
    | TRIPLE_DOT_TOK
    ;


// Just simple comma-separated required arguments (no fancy syntax). We need
// this to be separate from the **argList** for use in **switch** blocks where
// having the newlines wouldnt make sense.
simpleArgs
    : expression simpleArgsRest
    ;
 
 simpleArgsRest
    : COMMA_TOK expression
 	| 
 	;


// The variants of *try/catch/finally* exception handling blocks.
tryRule
    : TRY block
    | TRY block catchRule 
    | TRY block FINALLY block 
    | TRY block catchRule FINALLY block 
    ;


// A catch clause names its error and runs a block of code.
catchRule
    : CATCH identifier block
    | CATCH object block
    | CATCH block 
    ;


// throw an exception object.
throwRule
    : THROW expression
    ;


// parenthetical expressions. Note that the **parenthetical** is a **value**
// not an **expression** so if you need to use an expression in a place
// where only values are accepted wrapping it in parentheses will always do
// the trick.
parenthetical
    : LPAR body RPAR
    | LPAR INDENT body OUTDENT RPAR
    ;

 
// The condition portion of a while loop.
whileSource
    : WHILE expression
    | WHILE expression WHEN expression
    | UNTIL expression
    | UNTIL expression WHEN expression
    ;


// The while loop can either be normal with a block of expressions to execute
// or postfix with a single expression. There is no do..while.
loop
    : LOOP block
    | LOOP expression 
    ;


// array object and range comprehensions at the most generic level.
// Comprehensions can either be normal with a block of expressions to execute
// or postfix with a single expression.
forBody
    :FOR range 
    | forStart forSource
    ;


forStart
    : FOR forVariables
    | FOR OWN forVariables
    ;


// An array of all accepted values for a variable inside the loop.
// this enables support for pattern matching.
forValue
    : identifier
    | thisProperty
    | array 
    | object
    ;


// An array or range comprehension has variskip();ables for the current element
// and (optional) reference to the current index. Or *key value* in the case
// of object comprehensions.
forVariables
    : forValue
    | forValue COMMA_TOK forValue 
    ;


// The source of a comprehension is an array or object with an optional guard
// clause. if its an array comprehension you can also choose to step through
// in fixed-size increments.
forSource
    : FORIN expression
    | FOROF expression
    | FORIN expression WHEN expression
    | FOROF expression WHEN expression
    | FORIN expression BY expression
    | FORIN expression WHEN expression BY expression
    | FORIN expression BY expression WHEN expression
    ;


switchRule
    : SWITCH expression INDENT whens OUTDENT 
    | SWITCH expression INDENT whens ELSE block OUTDENT
    | SWITCH INDENT whens OUTDENT
    | SWITCH INDENT whens ELSE block OUTDENT 
    ;

whens
    : when whensRest
    ;
 
 whensRest
 	: when whensRest
    |
 	;

// An individual **when** clause with action.
when
    : LEADING_WHEN simpleArgs block 
    | LEADING_WHEN simpleArgs block TERMINATOR
    ;

// The most basic form of *if* is a condition and an action. The following
// if-related rules are broken up along these lines in order to avoid
// ambiguity.
ifBlock
    : IF expression block ifBlockRest
    ;
 
 ifBlockRest
 	: ELSE IF expression block ifBlockRest
    | 
	;

// The full complement of *if* expressions including postfix one-liner
// *if* and *unless*.


// Arithmetic and logical operators working on one or more operands.
// Here they are grouped by order of precedence. The actual precedence rules
// are defined at the bottom of the page. It would be shorter if we could
// combine most of these rules into a single generic *Operand OpSymbol Operand*
// -type rule but in order to make the precedence binding possible separate
// rules are necessary.

unaryOperation
    : UNARY expression
    | UNARY_MATH expression
    | MINUS_TOK expression
    | PLUS_TOK expression
    ;

doubleSignOperation
    : DOUBLE_MINUS_TOK simpleAssignable
    | DOUBLE_PLUS_TOK simpleAssignable
    ;

simpleAssignableOperation
    : simpleAssignable DOUBLE_MINUS_TOK
    | simpleAssignable DOUBLE_PLUS_TOK
    | simpleAssignable COMPOUND_ASSIGN expression
    | simpleAssignable COMPOUND_ASSIGN INDENT expression OUTDENT 
    | simpleAssignable COMPOUND_ASSIGN TERMINATOR expression
    | simpleAssignable EXTENDS expression 
    ;

operationRest
    : QUEST_TOK
    | PLUS_TOK expression
    | MINUS_TOK expression
    | MATH expression
    | DOUBLE_STAR expression
    | SHIFT expression
    | COMPARE expression
    | LOGIC expression
    | RELATION expression
    ;