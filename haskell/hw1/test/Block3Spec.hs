module Block3Spec where

import Test.Hspec

import Block3

spec :: Spec
spec = do
  it "maybeConcat test" $
    maybeConcat [Just [1,2,3], Nothing, Just [4,5]] `shouldMatchList` ([1,2,3,4,5] :: [Int])
  it "eitherConcat test" $
    eitherConcat [Left [1,3], Right [1,2,3], Left [5,5], Right [4,5]]
      `shouldBe` (([1,3,5,5], [1,2,3,4,5]) :: ([Int], [Int]))
  it "NonEmpty test" $
    ((1 :: Int) :| [2,3]) <> (4 :| []) `shouldBe` (1 :| [2,3,4])
  it "ThisOrThat tests" $ do
    (This [1]) <> (That [2]) `shouldBe` ((Both [1] [2]) :: ThisOrThat [Int] [Int])
    (This [1]) <> (This [2]) `shouldBe` ((This [1,2]) :: ThisOrThat [Int] [Int])
    (Both [1] [2]) <> (This [2]) `shouldBe` ((Both [1,2] [2]) :: ThisOrThat [Int] [Int])
  it "Name tests" $ do
    (Name "root") <> (Name "server") `shouldBe` (Name "root.server")
    (Name "root") <> (Name "") `shouldBe` (Name "root")