module Task4
  (
    fibonacci
  , factorial
  , iterateElement
  , mapFix
  ) where

import Data.Function (fix)

iterateElement :: a -> [a]
iterateElement x = fix (x :)

fibonacci :: Integer -> Integer
fibonacci a = fix (\rec n -> if n < 2 then 1 else rec (n - 1) + rec (n - 2)) a

factorial :: Integer -> Integer
factorial a = fix (\rec n -> if n < 2 then 1 else n * rec (n - 1)) a

mapFix :: (a -> b) -> [a] -> [b]
mapFix = fix (\rec f l -> if null l then [] else next rec f l)
  where
    next _ _ []         = []
    next rec f (x : xs) = f x : rec f xs
