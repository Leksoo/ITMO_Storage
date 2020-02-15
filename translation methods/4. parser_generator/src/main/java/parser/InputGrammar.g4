grammar InputGrammar;
@header{
import grammar.*;
}

inputGrammar returns [Grammar gr]:
    {$gr = new Grammar();}
    h=header {$gr.setHeader($h.str);}
    (l=lex {$gr.addTerm($l.term,$l.skip);})+
    e=enter_point {$gr.setStart($e.str);}
    (r=rules_block {$gr.addRulesBlock($r.rules);})+;


header returns [String str] :
    HEADER CODEBLOCK SCOLON {$str = $CODEBLOCK.text;};

enter_point returns [String str] :
    START COLON NONTERM SCOLON {$str = $NONTERM.text;};

lex returns [Term term, boolean skip] :
    {$skip = false;} TERM COLON STRING (IGNORE {$skip=true;})? SCOLON {$term = new Term($TERM.text, $STRING.text);};

rules_block returns [RulesBlock rules] :
    block_name=NONTERM {$rules = new RulesBlock($block_name.text);}
    (ia=INHERIT_ATTR {$rules.setInheritedAttrs($ia.text);})?
    (RETURNS ra=RETURN_ATTR {$rules.setReturnAttrs($ra.text);})?
    COLON
    (
    r=rule_single {$rules.addRule($r.rule);}
    (SPLIT r=rule_single {$rules.addRule($r.rule);})*
    )
    SCOLON;

rule_single returns [Rule rule] :
    {$rule = new Rule();}
    (
    (
        (t=TERM {$rule.addTerm($t.text);} (c=CODEBLOCK {$rule.addCode($c.text);})?)
        | (n=NONTERM {$rule.addNonTerm($n.text);} (attr=INHERIT_ATTR {$rule.addAttr($attr.text);})?
         (c=CODEBLOCK {$rule.addCode($c.text);})?)
    )+
    | e=EPS {$rule.addEps();} (c=CODEBLOCK {$rule.addCode($c.text);})?
    );




HEADER : 'header';
CODEBLOCK : LB .*? RB;
INHERIT_ATTR : LP .*? RP;
RETURNS : '>>';
RETURN_ATTR : LSQ .*? RSQ;
START : 'start';
IGNORE : '--skip';
EPS : 'EPS';

WHITESPACE : [ \t\r\n]+ -> skip;

NONTERM : [a-z][a-z_]*;
TERM : [A-Z][A-Z_]*;
STRING : '\'' .*? '\'';

COMMA  : ',';
COLON  : ':';
DCOLON : '::';
SCOLON : ';';
EQ     : '=';
SPLIT  : '|';
LP  : '(';
RP  : ')';
LB  : '{';
RB  : '}';
LSQ : '[';
RSQ : ']';



