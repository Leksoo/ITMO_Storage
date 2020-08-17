module Block3
  ( maybeConcat
  , eitherConcat
  , NonEmpty (..)
  , ThisOrThat (..)
  , Name (..)
  , Endo (..)
  ) where

--Task 1

maybeConcat :: [Maybe [a]] -> [a]
maybeConcat lists = foldr f [] lists
  where
    f Nothing xs     = xs
    f (Just list) xs = list ++ xs

eitherConcat :: (Monoid a, Monoid b) => [Either a b] -> (a,b)
eitherConcat list = foldr f (mempty, mempty) list
  where
    f (Left l) (p1, p2)  = (l <> p1, p2)
    f (Right r) (p1, p2) = (p1, r <> p2)

-- Task 2

data NonEmpty a = a :| [a]
  deriving (Show, Eq)

instance Semigroup (NonEmpty a) where
  (x :| xs) <> (z :| zs) = x :| (xs ++ (z : zs))

data ThisOrThat a b
  = This a
  | That b
  | Both a b
  deriving (Show, Eq)

instance (Semigroup a, Semigroup b) => Semigroup (ThisOrThat a b) where
  (Both a b) <> (Both c d) = Both (a <> c) (b <> d)
  (Both a b) <> (This c)   = Both (a <> c) b
  (Both a b) <> (That c)   = Both a (b <> c)
  (This a) <> (Both b c)   = Both (a <> b) c
  (That a) <> (Both b c)   = Both b (a <> c)
  (This a) <> (This b)     = This (a <> b)
  (This a) <> (That b)     = Both a b
  (That a) <> (This b)     = Both b a
  (That a) <> (That b)     = That (a <> b)

data Name
  = Name String
  deriving (Show, Eq)

instance Semigroup Name where
  (Name a) <> (Name "") = Name a
  (Name "") <> (Name b) = Name b
  (Name a) <> (Name b)  = Name (a ++ "." ++ b)

instance Monoid Name where
  mempty = Name ""

newtype Endo a = Endo { getEndo :: a -> a }

instance Semigroup (Endo a) where
  (Endo {getEndo = f}) <> (Endo {getEndo = g}) = Endo (f . g)

instance Monoid (Endo a) where
  mempty = Endo id


