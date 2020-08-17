module Task5
  (
    churchPlus
  , churchMult
  , churchToInt
  , succChurch
  , zero
    ) where

type Nat a = (a -> a) -> a -> a

zero :: Nat a
zero _ x = x

succChurch :: Nat a -> Nat a
succChurch n s x = s (n s x)

churchPlus :: Nat a -> Nat a -> Nat a
churchPlus n m s x = n s (m s x)

churchMult :: Nat a -> Nat a -> Nat a
churchMult n m s x = n (m s) x

churchToInt :: Nat Integer -> Integer
churchToInt x = x (+1) 0
