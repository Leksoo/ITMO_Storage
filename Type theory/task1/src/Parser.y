{
module Parser  where

import Lexer
import Grammar
}

%name parse
%tokentype { Token }
%monad { Either String } { (>>=) } { return }
%error { parseError }

%token
    '\\'  { TokenSlash }
    '\.'  { TokenDot }
    '('   { TokenLB }
    ')'   { TokenRB }
    VAR   { TokenVar $$ }

%%

Expr : '\\' VAR '\.' Expr          { Abstraction (Var $2) $4 }
	 | Apply '\\' VAR '\.' Expr    { Application $1 (Abstraction (Var $3) $5) }
     | Apply                       { $1 }

Apply : Apply Atom                 { Application $1 $2 }
      | Atom                       { $1 }

Atom : '(' Expr ')'                { $2 }
     | VAR                         { Var $1 }

{
parseError = fail "parsing error"
}
