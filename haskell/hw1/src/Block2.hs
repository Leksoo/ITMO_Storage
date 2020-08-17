{-# LANGUAGE InstanceSigs #-}

module Block2
  ( splitOn
  , joinWith
  ) where

import Data.List.NonEmpty (NonEmpty (..))

-- Task 1
-- code from Block1 is used in tests
--instance Foldable BinTree where
--  foldMap :: Monoid m => (a -> m) -> BinTree a -> m
--  foldMap _ Leaf                = mempty
--  foldMap f (Node datalist l r) = foldMap f l <> foldMap f datalist <> foldMap f r
--
--  foldr :: (a -> b -> b) -> b -> BinTree a -> b
--  foldr _ z Leaf                = z
--  foldr f z (Node datalist l r) = foldr f (foldr f (foldr f z r) datalist) l

-- Task 2

splitOn :: Eq a => a -> [a] -> NonEmpty [a]
splitOn delimiter list = foldr f ([] :| []) list
  where
    f ch (x :| xs)
      | delimiter == ch = [] :| (x : xs)
      | otherwise       = (ch : x) :| xs

joinWith :: a -> NonEmpty [a] -> [a]
joinWith delimiter (el :| els) = foldl f el els
  where
    f xs res = xs ++ (delimiter : res)