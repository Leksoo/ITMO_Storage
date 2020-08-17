{-# LANGUAGE InstanceSigs #-}

module Block1
  ( WeekDay (..)
  , nextDay
  , afterDays
  , isWeekend
  , daysToParty

  , Nat (..)
  , natSum
  , natSub
  , natMul
  , intToNat
  , natToInt
  , natDiv
  , natIsEven
  , natMod

  , BinTree (..)
  , treeInsert
  , treeSize
  , treeIsEmpty
  , treeFind
  , treeDelete
  , fromList
  ) where

import Data.List.NonEmpty (NonEmpty (..))

-- Task 1

data WeekDay
  = Monday
  | Tuesday
  | Wednesday
  | Thursday
  | Friday
  | Saturday
  | Sunday
  deriving (Show)
  
instance Eq WeekDay where
  day1 == day2 = dayToInt day1 == dayToInt day2

dayToInt :: WeekDay -> Int
dayToInt day = case day of
  Monday    -> 0
  Tuesday   -> 1
  Wednesday -> 2
  Thursday  -> 3
  Friday    -> 4
  Saturday  -> 5
  Sunday    -> 6

intToDay :: Int -> WeekDay
intToDay num = case num of
  0 -> Monday
  1 -> Tuesday
  2 -> Wednesday
  3 -> Thursday
  4 -> Friday
  5 -> Saturday
  6 -> Sunday
  _ -> error "Invalid day number"

afterDays :: WeekDay -> Int -> WeekDay
afterDays day num = intToDay $ (dayToInt day + num) `mod` 7

nextDay :: WeekDay -> WeekDay
nextDay day = afterDays day 1

isWeekend :: WeekDay -> Bool
isWeekend Saturday = True
isWeekend Sunday   = True
isWeekend _        = False

daysToParty :: WeekDay -> Int
daysToParty day = ((dayToInt Friday + 7) - (dayToInt day)) `mod` 7

-- Task 2

data Nat
  = Z
  | S Nat
  deriving (Show)

natSum :: Nat -> Nat -> Nat
natSum a Z     = a
natSum a (S b) = S $ natSum a b

natMul :: Nat -> Nat -> Nat
natMul _ Z     = Z
natMul a (S b) = natSum a (natMul a b)

natSub :: Nat -> Nat -> Nat
natSub a Z         = a
natSub Z _         = Z
natSub (S a) (S b) = natSub a b

natToInt :: Nat -> Int
natToInt Z       = 0
natToInt (S nat) = natToInt nat + 1

intToNat :: Int -> Nat
intToNat a
  | a == 0 = Z
  | a > 0 = S $ intToNat (a - 1)
  | otherwise = error "Input number is not natural"

instance Eq Nat where
  Z     == Z     = True
  (S a) == (S b) = a == b
  _     == _     = False

instance Ord Nat where
  compare Z Z         = EQ
  compare _ Z         = GT
  compare Z _         = LT
  compare (S a) (S b) = compare a b

natIsEven :: Nat -> Bool
natIsEven Z         = True
natIsEven (S Z)     = False
natIsEven (S (S a)) = natIsEven a

natDiv :: Nat -> Nat -> Nat
natDiv _ Z = error "Dividing by Z error"
natDiv a b
  | a < b     = Z
  | otherwise = S $ (natDiv (natSub a b) b)

natMod :: Nat -> Nat -> Nat
natMod a b
  | a < b = a
  | otherwise = natSub a (natMul (natDiv a b) b)

-- Task 3

data BinTree a
  = Leaf
  | Node (NonEmpty a) (BinTree a) (BinTree a)
  deriving (Show)

instance Eq a => Eq (BinTree a) where
  _ == Leaf = True
  Leaf == Node _ _ _ = False
  Node datalist1 l1 r1 == Node datalist2 l2 r2 =
    datalist1 == datalist2 && l1 == l2 && r1 == r2



treeIsEmpty :: BinTree a -> Bool
treeIsEmpty Leaf = True
treeIsEmpty _    = False

treeSize :: BinTree a -> Int
treeSize Leaf                = 0
treeSize (Node datalist l r) = length datalist + treeSize l + treeSize r

treeFind :: Ord a => BinTree a -> a -> Maybe [a]
treeFind Leaf _ = Nothing
treeFind (Node (el :| els) l r) x
  | el == x   = Just (el : els)
  | el < x    = treeFind r x
  | otherwise = treeFind l x

treeInsert :: Ord a => BinTree a -> a -> BinTree a
treeInsert Leaf x = Node (x :| []) Leaf Leaf
treeInsert (Node datalist@(el :| els) l r) x
  | el == x   = Node (x :| (el : els)) l r
  | el < x    = Node datalist l (treeInsert r x)
  | otherwise = Node datalist (treeInsert l x) r

fromList :: Ord a => [a] -> BinTree a
fromList []       = Leaf
fromList (x : xs) = treeInsert (fromList xs) x

treeDelete :: Ord a => BinTree a -> a -> BinTree a
treeDelete Leaf _ = Leaf
treeDelete (Node datalist@(el :| els) l r) x
  | el < x  = Node datalist l (treeDelete r x)
  | el > x  = Node datalist (treeDelete l x) r
  | el == x = checkDatalist els
    where
      checkDatalist [] = if r == Leaf
                         then l
                         else let (newR, newData) = getNextNode r
                              in Node newData l newR
      checkDatalist (z : zs) = Node (z :| zs) l r
treeDelete _ _ = error "error"

getNextNode :: BinTree a -> (BinTree a, NonEmpty a)
getNextNode Leaf = error "unexpected Leaf"
getNextNode (Node datalist Leaf _) = (Leaf, datalist)
getNextNode (Node datalist l r)    = (Node datalist newL r, newData)
  where
    (newL, newData) = getNextNode l


-- Block2 Task1 - it is here to not duplicate instance declarations
instance Foldable BinTree where
  foldMap :: Monoid m => (a -> m) -> BinTree a -> m
  foldMap _ Leaf                = mempty
  foldMap f (Node datalist l r) = foldMap f l <> foldMap f datalist <> foldMap f r

  foldr :: (a -> b -> b) -> b -> BinTree a -> b
  foldr _ z Leaf                = z
  foldr f z (Node datalist l r) = foldr f (foldr f (foldr f z r) datalist) l
