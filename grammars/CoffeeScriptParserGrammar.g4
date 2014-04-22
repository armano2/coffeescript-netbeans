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
    : line
    | body TERMINATOR line
    | body TERMINATOR
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

expression
    : value
    | invocation
    | code
      
    //operation rule
    | unaryOperation 
    | doubleSignOperation
    | expression operationRest
    | simpleAssignableOperation
    // end operation rule
      
    | assign

    //if rule
    | ifBlock
    | ifBlock ELSE block
    | statement POST_IF expression 
    | expression POST_IF expression
    //end if rule

    | tryRule
      
    // while rule
    | whileSource block 
    | statement  whileSource
    | expression whileSource
    | loop
    // end while rule

    // for rule
    | statement  forBody
    | expression forBody
    | forBody  block
    // end for rule

    | switchRule
    | classRule
    | throwRule
    ;

block
    : INDENT OUTDENT
    | INDENT body OUTDENT 
    ;


identifier
    : IDENTIFIER
    ;


alphaNumeric
    : NUMBER
    | STRING
    ;

literal
    : alphaNumeric
    | literalTokens
    ;

assign
    : assignable EQUATING_TOK expression 
    | assignable EQUATING_TOK TERMINATOR expression
    | assignable EQUATING_TOK INDENT expression OUTDENT
    ;

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

returnRule
    : RETURN expression 
    | RETURN
    ;

comment
    : HERECOMMENT 
    ;

code
    : PARAM_START paramList PARAM_END funcGlyph block
    | funcGlyph block
    ;

funcGlyph
    : FUNC_DOUBLE_ARROW
    | FUNC_SINGLE_ARROW
    ;

optComma
    : COMMA_TOK
    | 
    ;

paramList
    : param 
    | paramList COMMA_TOK param 
    | paramList optComma TERMINATOR param 
    | paramList optComma INDENT paramList optComma OUTDENT
    |
    ;

param
    : commonAssignable
    | commonAssignable TRIPLE_DOT_TOK
    | commonAssignable EQUATING_TOK expression 
    | TRIPLE_DOT_TOK
    ;

//paramVar
//    : identifier
//    | thisProperty
//    | array
//    | object
//    ;

commonAssignable
    : identifier
    | thisProperty
    | array
    | object
    ;

splat
    :  expression TRIPLE_DOT_TOK
    ;

simpleAssignable
    : identifier
    | value accessor
    | invocation accessor 
    | thisProperty
    ;

assignable
    : identifier
    | value accessor
    | invocation accessor 
    | thisProperty
    | array 
    | object
    ;

value
    : identifier
    | value accessor
    | value optFuncExist arguments accessor
    | SUPER accessor
    | SUPER arguments accessor 
    | thisProperty
    | array 
    | object
    | literal 
    | parenthetical 
    | range 
    | thisRule
    ;

accessor
    : DOT_TOK  identifier 
    | QUEST_DOT_TOK identifier 
    | DOUBLE_COLON_TOK identifier 
    | QUEST_DOUBLE_COLON_TOK identifier
    | DOUBLE_COLON_TOK
    | index
    ;

index
    : INDEX_START indexValue INDEX_END
    | INDEX_SOAK  index 
    ;

indexValue
    : expression
    | slice 
    ;

object
    : LBRACE assignList optComma RBRACE
    ;

assignList
    : assignObj 
    | assignList COMMA_TOK assignObj
    | assignList optComma TERMINATOR assignObj
    | assignList optComma INDENT assignList optComma OUTDENT
    |
    ;

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

invocation
    : value optFuncExist arguments
    | invocation optFuncExist arguments 
    | SUPER 
    | SUPER arguments 
    ;

optFuncExist
    : FUNC_EXIST
    |
    ;

arguments
    : CALL_START CALL_END 
    | CALL_START argList optComma CALL_END
    ;

thisRule
    : THIS
    | AT_TOK
    ;

thisProperty
    : AT_TOK identifier
    ;

array
    : LBRACKET RBRACKET
    | LBRACKET argList optComma RBRACKET
    ;

rangeDots
    : DOUBLE_DOT_TOK
    | TRIPLE_DOT_TOK
    ;

range
    : LBRACKET expression rangeDots expression RBRACKET
    ;

slice
    : expression rangeDots expression 
    | expression rangeDots
    | rangeDots expression
    | rangeDots 
    ;

argList
    : arg 
    | argList COMMA_TOK arg 
    | argList optComma TERMINATOR arg 
    | INDENT argList optComma OUTDENT 
    | argList optComma INDENT argList optComma OUTDENT
    ;

arg
    : expression
    | splat
    | TRIPLE_DOT_TOK
    ;

simpleArgs
    : expression
    | simpleArgs COMMA_TOK expression 
    ;

tryRule
    : TRY block 
    | TRY block catchRule 
    | TRY block FINALLY block 
    | TRY block catchRule FINALLY block 
    ;

catchRule
    : CATCH identifier block
    | CATCH object block
    | CATCH block 
    ;

throwRule
    : THROW expression
    ;

parenthetical
    : LPAR body RPAR
    | LPAR INDENT body OUTDENT RPAR
    ;

whileSource
    : WHILE expression
    | WHILE expression WHEN expression
    | UNTIL expression
    | UNTIL expression WHEN expression
    ;

loop
    : LOOP block
    | LOOP expression 
    ;

forBody
    : FOR range 
    | forStart forSource
    ;

forStart
    : FOR forVariables
    | FOR OWN forVariables
    ;

//forValue
//    : identifier
//    | thisProperty
//    | array 
//    | object
//    ;

forVariables
    : commonAssignable
    | commonAssignable COMMA_TOK commonAssignable 
    ;

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
    : when
    | whens when
    ;

when
    : LEADING_WHEN simpleArgs block 
    | LEADING_WHEN simpleArgs block TERMINATOR
    ;

ifBlock
    : IF expression block 
    | ifBlock ELSE IF expression block
    ;

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

literalTokens
    : JS
    | REGEX
    | DEBUGGER
    | UNDEFINED
    | NULL
    | BOOL
    ;
