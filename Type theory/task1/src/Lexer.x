{
module Lexer where

import Grammar
}

%wrapper "basic"

$digit = 0-9
$alpha = [a-z]
$eol = [\n]
tokens :-
  $white+                       ;
  $eol                          ;
  \.                            { \_ -> TokenDot }
  \\                            { \_ -> TokenSlash }
  \(                            { \_ -> TokenLB }
  \)                            { \_ -> TokenRB }
  $alpha [$alpha $digit \']*    { \s -> TokenVar s }

{
data Token
  = TokenDot
  | TokenSlash
  | TokenVar String
  | TokenLB
  | TokenRB
  deriving (Eq)
}