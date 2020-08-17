module Block4
 ( stringSum
 )
 where

import Text.Read (readMaybe)
import Control.Applicative(liftA2)

-- Task 1

stringSum :: String -> Maybe Int
stringSum str = summarize (traverse readMaybe (words str))
  where
    summarize Nothing     = Nothing
    summarize (Just list) = Just (sum list)

-- Task 2

data Tree a
  = Branch (Tree a) (Tree a)
  | Leaf a

instance Functor Tree where
  fmap f (Leaf a)     = Leaf (f a)
  fmap f (Branch l r) = Branch (fmap f l) (fmap f r)

instance Applicative Tree where
  pure a = Leaf a
  (Leaf a)     <*> tree = fmap a tree
  (Branch l r) <*> tree = Branch (l <*> tree) (r <*> tree)

instance Foldable Tree where
  foldr f z (Leaf a)     = f a z
  foldr f z (Branch l r) = foldr f (foldr f z r) l

instance Traversable Tree where
  traverse f (Leaf a)     = fmap Leaf (f a)
  traverse f (Branch l r) = liftA2 Branch (traverse f l) (traverse f r)

-- Task 3

data NonEmpty a = a :| [a]

instance Functor NonEmpty where
  fmap f (x :| xs) = (f x) :| (fmap f xs)

instance Applicative NonEmpty where
  pure x = x :| []
  (x :| xs) <*> (z :| zs) = (x z) :| ((fmap x zs) ++ (xs <*> (z : zs)))

head' :: NonEmpty a -> a
head' (x :| _) = x

tail' :: NonEmpty a -> [a]
tail' (_ :| xs) = xs

instance Monad NonEmpty where
  (x :| xs) >>= f = (head' (f x)) :| ((tail' (f x)) ++ (foldr makeList [] xs))
    where
      makeList el res = res ++ toList (f el)
        where
          toList (z :| zs) = z : zs

instance Foldable NonEmpty where
  foldr f z (x :| xs) = f x (foldr f z xs)

instance Traversable NonEmpty where
  traverse f (x :| xs) = liftA2 (:|) (f x) (traverse f xs)