module Task2
  (
    doubleNeg
  , doubleNegElim
  , excludedNeg
  , pierce
  , thirdNegElim
  ) where

import Data.Void (Void)

type Neg a = a -> Void

doubleNeg :: a -> Neg (Neg a)
doubleNeg a v = v a

contr :: (a -> b) -> Neg b -> Neg a
contr f g = g . f

-- 1. a -> a | !a , ax
-- 2. !a -> a | !a , ax
-- 3. (a -> a | !a) -> (!(a | !a) -> !a) , contr
-- 4. (!(a | !a) -> !a) , m.p.
-- 5. (!(a | !a) -> !!a) , m.p.
-- 6. (!(a | !a) -> !a) -> (!(a | !a) -> !!a) -> !!(a | !a), ax
-- 7. !!(a | !a), m.p.
excludedNeg :: Neg (Neg (Either a (Neg a)))
excludedNeg = ax1 (contr ax2) (contr ax3)
  where
    ax1 :: (a -> b) -> (a -> Neg b) -> Neg a
    ax1 f g a = g a $ f a
    ax2 :: a -> Either a b
    ax2 = Left
    ax3 :: b -> Either a b
    ax3 = Right

-- не обитаем
pierce :: ((a -> b) -> a) -> a
pierce = undefined

-- не обитаем
doubleNegElim :: Neg (Neg a) -> a
doubleNegElim = undefined

-- 1. a -> !!a
-- 2. (a -> !!a) -> (!!!a -> !a), contr
-- 3. (!!!a -> !a), m.p
thirdNegElim :: Neg (Neg (Neg a)) -> Neg a
thirdNegElim = contr doubleNeg
