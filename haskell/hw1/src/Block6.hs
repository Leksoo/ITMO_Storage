module Block6
  ( Parser (..)
  , ok
  , eof
  , satisfy
  , element
  , stream
  ) where

import Control.Arrow(first)
import Control.Applicative(Alternative, empty, (<|>))

data Parser s a = Parser { runParser :: [s] -> Maybe (a, [s]) }

instance Functor (Parser s) where
  fmap f (Parser p) = Parser (fmap (first f) . p)

instance Applicative (Parser s) where
  pure a = Parser $ \s -> Just (a, s)

  p1 <*> p2 = Parser $ \s ->
    runParser p1 s
    >>= \(a, xs) -> runParser p2 xs
    >>= \(b, zs) -> Just (a b, zs)

instance Monad (Parser s) where
  p >>= f = Parser $ \s ->
    runParser p s
    >>= \(a, xs) -> runParser (f a) xs

instance Alternative (Parser s) where
  empty = Parser $ const Nothing
  (Parser p1) <|> (Parser p2) = Parser $ \s -> p1 s <|> p2 s

-- Task 2

ok :: Parser s ()
ok = Parser $ \s -> Just ((), s)

eof :: Parser s ()
eof = Parser $ \s -> case s of
    [] -> Just ((), s)
    _  -> Nothing

satisfy :: (s -> Bool) -> Parser s s
satisfy pr = Parser $ \s -> case s of
    []     -> Nothing
    (x:xs) -> if pr x then Just (x, xs) else Nothing

element :: Eq s => s -> Parser s s
element el = satisfy (== el)

stream :: Eq s => [s] -> Parser s [s]
stream [] = Parser $ \s -> Just ([], s)
stream (x:xs) = element x >>= \_ -> stream xs
