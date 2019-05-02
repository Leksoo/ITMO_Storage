{
module Lexer where
}

%wrapper "basic"

$digit = 0-9
$alpha = [A-Z]

tokens :-

  $white+                        ;
  \(                             { \_ -> LeftBr }
  \)                             { \_ -> RightBr }
  \|                             { \_ -> OrBin }
  &                              { \_ -> AndBin }
  "->"                           { \_ -> ImplBin }
  !                              { \_ -> NotUn }
  $alpha [$alpha $digit \']*     { \s -> Variable s }

{

data Token = AndBin | OrBin | ImplBin | NotUn | LeftBr | RightBr | Variable String deriving (Show, Eq)

}