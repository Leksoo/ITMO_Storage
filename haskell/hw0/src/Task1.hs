{-# LANGUAGE TypeOperators #-}

module Task1
  (
    associator
  , distributivity
  , eitherAssoc
  ) where

distributivity
  :: Either a (b, c)
  -> (Either a b, Either a c)
distributivity (Left a)       = (Left a, Left a)
distributivity (Right (b, c)) = (Right b, Right c)

associator
  :: (a, (b, c))
  -> ((a, b), c)
associator (a, (b, c)) = ((a, b), c)

type (<->) a b = (a -> b, b -> a)

eitherAssocLeftToRight
  :: Either a (Either b c)
  -> Either (Either a b) c
eitherAssocLeftToRight (Left a)          = Left (Left a)
eitherAssocLeftToRight (Right (Left b))  = Left (Right b)
eitherAssocLeftToRight (Right (Right c)) = Right c

eitherAssocRightToLeft
  :: Either (Either a b) c
  -> Either a (Either b c)
eitherAssocRightToLeft (Left (Left a))  = Left a
eitherAssocRightToLeft (Left (Right b)) = Right (Left b)
eitherAssocRightToLeft (Right c)        = Right (Right c)

eitherAssoc
  :: Either a (Either b c)
  <-> Either (Either a b) c
eitherAssoc = (eitherAssocLeftToRight, eitherAssocRightToLeft)
