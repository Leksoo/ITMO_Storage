module Block5
 ( Expr (..)
 , ArithmeticError (..)
 , eval
 )where

data Expr
  = Const Int
  | Add Expr Expr
  | Sub Expr Expr
  | Mul Expr Expr
  | Div Expr Expr
  | Pow Expr Expr
  deriving (Show)

data ArithmeticError
  = DivisionByZeroError
  | PowNegativeDegreeError
  deriving (Show, Eq)

calcOp:: (Int -> Int -> Int) -> Int -> Int -> Either ArithmeticError Int
calcOp op a b = Right (op a b)

eval'
  :: (Int -> Int -> Either ArithmeticError Int)
  -> Either ArithmeticError Int
  -> Either ArithmeticError Int
  -> Either ArithmeticError Int
eval' op (Right x) (Right y) = op x y
eval' _ err@(Left _) _       = err
eval' _ _ err@(Left _)       = err

eval :: Expr -> Either ArithmeticError Int
eval (Const a) = Right a
eval (Add a b) = eval' (calcOp (+)) (eval a) (eval b)
eval (Sub a b) = eval' (calcOp (-)) (eval a) (eval b)
eval (Mul a b) = eval' (calcOp (*)) (eval a) (eval b)
eval (Div a b) = eval' checkError (eval a) (eval b)
  where
    checkError x y = if y == 0 then Left DivisionByZeroError else calcOp div x y
eval (Pow a b) = eval' checkError (eval a) (eval b)
  where
    checkError x y = if y < 0 then Left PowNegativeDegreeError else calcOp (^) x y

-- Task 2

