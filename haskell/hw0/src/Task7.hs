
module Task7
  (
    f1
  , f2
  , f3
  ) where

import Data.Either (lefts, rights)

type S = String -> String

f1 :: Bool
--f1 = null . head $ map (uncurry id) [((++) "Dorian ", " Grey")]
f1 = apply1 map2
  where
    apply1 = apply compos2 :: [String] -> Bool
    apply = ($) :: ([String] -> Bool) -> [String] -> Bool

    compos2 = compos1 _head :: [String] -> Bool
    compos1 = compos _null :: ([String] -> String) -> [String] -> Bool
    compos = (.) :: (String -> Bool) -> ([String] -> String) -> [String] -> Bool
    _null = null :: String -> Bool
    _head = head :: [String] -> String

    map2 = map1 list :: [String]
    map1 = _map uncId :: [(S,String)] -> [String]
    _map = map :: ((S,String) -> String) -> [(S,String)] -> [String]

    uncId = _uncurry _id :: (S,String) -> String
    _uncurry = uncurry :: (S -> S) -> (S, String) -> String
    _id = id :: S -> S

    list = [pair] :: [(S, String)]
    pair = (concat1, str2) :: (S, String)
    concat1 = _concat str1 :: S
    _concat = (++) :: String -> String -> String
    str1 = "Dorian " :: String
    str2 = " Grey" :: String

f2 :: [(Integer, Integer)]
--f2 = (\x -> zip (lefts x) (rights x)) [Left (1 + 2), Right (2 ^ 6)]
f2 = lambda list
  where
    lambda :: [Either Integer Integer] -> [(Integer, Integer)]
    lambda x =
      let ls = lefts :: [Either a b] -> [a] in
      let rs = rights :: [Either a b] -> [b] in
      let z = zip :: [a] -> [b] -> [(a, b)] in
      let rs1 = rs x :: [Integer] in
      let ls1 = ls x :: [Integer] in
      let z1 = z ls1 :: [Integer] -> [(Integer, Integer)] in
      let z2 = z1 rs1 :: [(Integer, Integer)] in
      z2

    list = [l,r] :: [Either Integer Integer]
    l = Left pl2 :: Either Integer a
    r = Right pow2 :: Either Integer Integer

    pow2 = pow1 n3 :: Integer
    pow1 = pow n2 :: Integer -> Integer
    pow = (^) :: Integer -> Integer -> Integer
    n3 = 6 :: Integer

    pl2 = pl1 n2 :: Integer
    pl1 = pl n1 :: Integer -> Integer
    pl = (+) :: Integer -> Integer -> Integer
    n1 = 1 :: Integer
    n2 = 2 :: Integer

f3 :: Integer -> Bool
f3 =
  let n = not :: Bool -> Bool in
  let dis = (||) :: Bool -> Bool -> Bool in
  let impl = (\x y -> (n x :: Bool) `dis` y) :: Bool -> Bool -> Bool in
  let num1 = 2 :: Integer in
  let num2 = 0 :: Integer in
  let m = (mod) :: Integer -> Integer -> Integer in
  let e = (==) :: Integer -> Integer -> Bool in
  let isMod2 = (\x -> (x `m` num1 :: Integer) `e` num2) :: Integer -> Bool in
  let num3 = 4 :: Integer in
  let isMod4 = (\x -> (x `m` num3 :: Integer) `e` num2) :: Integer -> Bool in
  \x -> (isMod4 x :: Bool) `impl` (isMod2 x :: Bool) :: Bool
