module Task6
  (
    f1
  , f2
  ) where

import Data.Maybe (mapMaybe)
import Task1 (distributivity)

foo :: Char -> Maybe Double
foo char =
  case char == 'o' of
    True  -> Just $ exp pi
    False -> Nothing

f1 :: (Either String a, Either String b)
f1 = distributivity (Left ("harold" ++ " hide " ++ "the " ++ "pain"))
-- whnf : (Left ("harold" ++ " hide " ++ "the " ++ "pain"), Left ("harold" ++ " hide " ++ "the " ++ "pain"))

f2 :: Bool
f2 = null $ mapMaybe foo "pole chudes ochen' chudesno"
-- whnf : false
