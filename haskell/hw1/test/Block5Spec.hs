module Block5Spec where

import Test.Hspec

import Block5

spec :: Spec
spec = do
  it "eval tests" $ do
    let expr = Add (Const 5) (Const 6) in
      eval expr `shouldBe` Right 11
    let expr = Mul (Sub (Const 10) (Const 5)) (Const 6) in
      eval expr `shouldBe` Right 30
    let expr = Div (Const 5) (Const 6) in
      eval expr `shouldBe` Right 0
    let expr = Div (Const 5) (Const 0) in
      eval expr `shouldBe` Left DivisionByZeroError
    let expr = Pow (Const 5) (Const 3) in
      eval expr `shouldBe` Right 125
    let expr = Pow (Const 5) (Const (-42)) in
      eval expr `shouldBe` Left PowNegativeDegreeError