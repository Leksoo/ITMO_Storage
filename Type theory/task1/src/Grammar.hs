module Grammar where

data Expr
  = Abstraction Expr Expr
  | Application Expr Expr
  | Var String

instance Show Expr where
    show(Application a b) = "(" ++ show a ++ " " ++ show b ++ ")"
    show(Abstraction a b) = "(\\" ++  show a ++ "." ++ show b ++ ")"
    show(Var a) = a