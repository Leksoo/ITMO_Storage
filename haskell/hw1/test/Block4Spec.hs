module Block4Spec where

import Test.Hspec

import Block4

spec :: Spec
spec = do
  it "stringSum tests" $ do
    stringSum "1 2 3 4" `shouldBe` Just 10
    stringSum "" `shouldBe` Just 0
    stringSum "1 2 1a" `shouldBe` Nothing