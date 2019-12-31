{
module Parser where

import Grammar
import Lexer
}

%name      parse
%tokentype { Token }
%error     { parseError }
%monad     { Either String }{ >>= }{ return }

%token VAR    { Variable $$ }
%token IMPL   { ImplBin }
%token OR     { OrBin }
%token AND    { AndBin }
%token NOT    { NotUn }
%token LEFTBR  { LeftBr }
%token RIGHTBR { RightBr }

%%

Expr
  : Disj                 { $1 }
  | Disj IMPL Expr       { Binary Impl $1 $3 }

Disj
  : Conj                 { $1 }
  | Disj OR Conj         { Binary Or $1 $3 }

Conj
  : Neg                  { $1 }
  | Conj AND Neg         { Binary And $1 $3 }

Neg
  : NOT Neg              { Not $2 }
  | LEFTBR Expr RIGHTBR  { $2 }
  | VAR                  { Var $1 }

{
parseError = fail "Parse error"
}