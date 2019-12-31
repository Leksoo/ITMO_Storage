grammar Expression;

@header {
package parser;
import expression.*;
}

expression returns [Expression expr] : disj=disjunction {$expr = $disj.expr;}
                                     | disj1=disjunction IMPLIES exp1=expression {$expr = new Impl($disj1.expr, $exp1.expr);};

disjunction returns [Expression expr] : conj=conjunction {$expr = $conj.expr;}
                                      | disj1=disjunction OR conj1=conjunction {$expr = new Or($disj1.expr, $conj1.expr);};

conjunction returns [Expression expr] : unar=unary {$expr = $unar.expr;}
                                      | conj1=conjunction AND unar1=unary{$expr = new And($conj1.expr, $unar1.expr);};

unary returns [Expression expr] :  pred=predicate {$expr = $pred.expr;}
                                   | NOT unar=unary {$expr = new Not($unar.expr);}
                                   | OB exp=expression {$expr = $exp.expr;} CB
                                   | ALL var1=variable DOT expr1=expression {$expr = new All($var1.expr, $expr1.expr);}
                                   | EXIST var2=variable DOT expr2=expression {$expr = new Exist($var2.expr, $expr2.expr);};

variable returns [Variable expr] : VAR {$expr = new Variable($VAR.text);};

predicate returns [Expression expr] : PRED OB manyterm1=manyterm {$expr = new Pred($PRED.text, $manyterm1.list);} CB
                                      | PRED {$expr = new Pred($PRED.text, new ArrayList<Expression>());}
                                      | term1=term EQUALS term2=term {$expr = new Equal($term1.expr,$term2.expr);};

manyterm returns [ArrayList<Expression> list] : term1=term COMMA manyterm1=manyterm{$list = new ArrayList<Expression>();$list.add($term1.expr); $list.addAll($manyterm1.list);}
                                                | term2=term {$list = new ArrayList<Expression>();$list.add($term2.expr);};

term returns [Expression expr] : sum1=sum {$expr = $sum1.expr;}
                                 | term1=term PLUS sum2=sum {$expr = new Sum($term1.expr,$sum2.expr);};

sum returns [Expression expr] : mul1=mul {$expr = $mul1.expr;}
                                | sum1=sum MULT mul2=mul  {$expr = new Mul($sum1.expr,$mul2.expr);};

mul returns [Expression expr] : VAR OB manyterm1=manyterm {$expr = new Func($VAR.text, $manyterm1.list);} CB
                                | variable1=variable {$expr = $variable1.expr;}
                                | OB term1=term {$expr = $term1.expr;} CB
                                | ZERO {$expr = new Zero();}
                                | mul1=mul NEXT {$expr = new Apostrophe($mul1.expr);};


IMPLIES : '->';
OR : '|';
AND : '&';
NOT : '!';
OB : '(';
CB : ')';
VAR : [a-z]([0-9])*;
PRED : [A-Z]([0-9])*;
ALL : '@';
EXIST : '?';
DOT : '.';
COMMA : ',';
EQUALS : '=';
PLUS : '+';
MULT : '*';
ZERO : '0';
NEXT : '\'';
WS: [ \n\t\r]+ -> skip;