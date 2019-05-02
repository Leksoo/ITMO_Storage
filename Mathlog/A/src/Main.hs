module Main where

import Grammar
import Lexer
import Parser

main :: IO ()
main = do
  input <- getLine
  case parse (alexScanTokens input) of
    Right expr -> putStrLn $ show expr