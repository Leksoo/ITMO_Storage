grammar Expression;

@header {
package parser;
import expression.*;
}

expression returns [Expression expr] : apply=application LAMBDA var=variable DOT expr1=expression {$expr = new Apply($apply.expr,new Abstr($var.expr,$expr1.expr));}
                                     | LAMBDA var=variable DOT expr1=expression {$expr = new Abstr($var.expr,$expr1.expr);}
                                     | apply=application {$expr = $apply.expr;};

application returns [Expression expr] : apply=application atom1=atom {$expr = new Apply($apply.expr,$atom1.expr);}
                                      | atom1=atom {$expr = $atom1.expr;};

atom returns [Expression expr] : OB expr1=expression CB {$expr = $expr1.expr;}
                                      | var=variable {$expr = $var.expr;};

variable returns [Variable expr] : VAR {$expr = new Variable($VAR.text);};

OB : '(';
CB : ')';
VAR : [a-z]([0-9]|[a-z]|[’'])*;
LAMBDA : '\\';
DOT : '.';
WS: [ \n\t\r]+ -> skip;