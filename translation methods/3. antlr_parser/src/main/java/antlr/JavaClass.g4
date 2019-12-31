grammar JavaClass;
classDeclaration  : classModifier CLASS JAVA_NAME (EXTENDS classSuperClass)?
    (IMPLEMENTS classInterfaces)? OPEN_BLOCK classInside CLOSE_BLOCK EOF;
classModifier : (PUBLIC|PRIVATE)?;
classSuperClass : JAVA_NAME;
classInterfaces : JAVA_NAME(COMMA JAVA_NAME)*;
classInside : (field|method)*;

field : modifier variable ;

method: modifier type? name OPEN_BRACKET arguments? CLOSE_BRACKET OPEN_BLOCK methodBody CLOSE_BLOCK;
methodBody: (methodVariable|methodFuncCall|methodReturn|loop|ifelse)*;
methodVariable: finalModifier variable;
methodFuncCall: funcCall SEMICOLON;
methodReturn: RETURN name? SEMICOLON;

loop: WHILE OPEN_BRACKET condition CLOSE_BRACKET OPEN_BLOCK methodBody CLOSE_BLOCK;
ifelse: condIf condElse? ;
condIf: IF OPEN_BRACKET condition CLOSE_BRACKET OPEN_BLOCK methodBody CLOSE_BLOCK ;
condElse : ELSE OPEN_BLOCK methodBody CLOSE_BLOCK;


condition : bool;
funcCall: NEW? name OPEN_BRACKET params? CLOSE_BRACKET;
variable: type name (ASSIGN assign)? SEMICOLON;
type : JAVA_NAME;
name : JAVA_NAME;
param : name;
bool: TRUE|FALSE;
params : param (COMMA param)*;
argument: type name;
arguments : argument (COMMA argument)*;
assign: (STRING|NUMBER|CHAR|JAVA_NAME|funcCall);
modifier : accessModifier staticModifier finalModifier|
    accessModifier finalModifier staticModifier|
    finalModifier staticModifier accessModifier|
    finalModifier accessModifier staticModifier|
    staticModifier accessModifier finalModifier|
    staticModifier finalModifier accessModifier;
accessModifier : (PUBLIC|PRIVATE|PROTECTED)?;
staticModifier : (STATIC)?;
finalModifier : (FINAL)?;


PUBLIC : 'public';
PRIVATE : 'private';
PROTECTED : 'protected';
STATIC : 'static';
FINAL : 'final';
CLASS: 'class';
EXTENDS : 'extends';
IMPLEMENTS : 'implements';
RETURN : 'return';
NEW : 'new';
COMMA: ',' ;
SEMICOLON: ';';
OPEN_BLOCK: '{';
CLOSE_BLOCK: '}';
OPEN_BRACKET: '(';
CLOSE_BRACKET: ')';
ASSIGN: '=';
EQUALS: '==';
IF: 'if';
ELSE : 'else';
WHILE : 'while';
TRUE: 'true';
FALSE: 'false';
STRING : '"'.*?'"';
CHAR : '\''.'\'';
NUMBER : [0-9]+('.'[0-9]+)?;
WS : [ \t\r\n]+ -> skip ;
JAVA_NAME : [$_a-zA-Z][$_a-zA-Z0-9]* ;