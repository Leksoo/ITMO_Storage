module Task1
  ( Point(..)
  , plus
  , minus
  , scalarProduct
  , crossProduct
  , perimeter
  , perimeterStrict
  , doubleArea
  , doubleAreaStrict
  ) where

import Control.DeepSeq (rnf, NFData)

data Point = Point {
  x :: Int,
  y :: Int
} deriving Show

instance NFData Point where
  rnf (Point a b) = rnf a `seq` rnf b `seq` ()

plus :: Point -> Point -> Point
plus p1 p2 = Point (x p1 + x p2) (y p1 + y p2)

minus :: Point -> Point -> Point
minus p1 p2 = Point (x p1 - x p2) (y p1 - y p2)

scalarProduct :: Point -> Point -> Int
scalarProduct p1 p2 = x p1 * x p2 + y p1 * y p2

crossProduct :: Point -> Point -> Int
crossProduct p1 p2 = x p1 * y p2 - x p2 * y p1

distance :: Point -> Point -> Double
distance p1 p2 = sqrt $ fromIntegral $ scalarProduct diff diff
  where diff = minus p1 p2

perimeter' :: [Point] -> Point -> Double -> Bool -> Double
perimeter' [] _ _ _ = 0.0
perimeter' [p1] pStart acc _ = acc + distance p1 pStart
perimeter' (p1:l@(p2:_)) pStart acc strict = if (strict) then acc `seq` next else next
  where next = perimeter' l pStart (acc + distance p1 p2) strict

-- pass at least 3 vertices
-- calculates perimeter
perimeter :: [Point] -> Double
perimeter []      = 0.0
perimeter l@(p:_) = perimeter' l p 0.0 False

-- pass at least 3 vertices
-- calculates perimeter with strict evaluations
perimeterStrict :: [Point] -> Double
perimeterStrict []      = 0.0
perimeterStrict l@(p:_) = perimeter' l p 0.0 True

doubleArea' :: [Point] -> Point -> Int -> Bool -> Int
doubleArea' [] _ _ _ = 0
doubleArea' [p1] pStart acc _ = acc + crossProduct p1 pStart
doubleArea' (p1:l@(p2:_)) pStart acc strict = if (strict) then acc `seq` next else next
  where next = doubleArea' l pStart (acc + crossProduct p1 p2) strict

-- pass at least 3 vertices
-- calculates double area
doubleArea :: [Point] -> Int
doubleArea []      = 0
doubleArea l@(p:_) = doubleArea' l p 0 False

-- pass at least 3 vertices
-- calculates double area with strict evaluations
doubleAreaStrict :: [Point] -> Int
doubleAreaStrict []      = 0
doubleAreaStrict l@(p:_) = doubleArea' l p 0 True