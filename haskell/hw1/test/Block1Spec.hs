module Block1Spec where

import Block1
import Test.Hspec

import Data.Foldable (toList)
import Data.List.NonEmpty (NonEmpty (..))

spec :: Spec
spec = do
  it "WeekDay tests" $ do
    nextDay Tuesday `shouldBe` Wednesday
    nextDay Sunday `shouldBe` Monday
    afterDays Monday 3 `shouldBe` Thursday
    afterDays Sunday 5 `shouldBe` Friday
    isWeekend Sunday `shouldBe` True
    isWeekend Monday `shouldBe` False
    daysToParty Thursday `shouldBe` 1
    daysToParty Friday `shouldBe` 0
    daysToParty Sunday `shouldBe` 5
  it "Nat tests" $ do
    natSum (S $ S Z) (S $ S $ S Z) `shouldBe` (S $ S $ S $ S $ S Z)
    natSum (intToNat 5) (intToNat 6) `shouldBe` (intToNat 11)
    natSub (intToNat 20) (intToNat 7) `shouldBe` (intToNat 13)
    natMul (intToNat 11) (intToNat 5) `shouldBe` (intToNat 55)
    natIsEven (S $ S Z) `shouldBe` True
    natIsEven (intToNat 5) `shouldBe` False
    natDiv (intToNat 11) (intToNat 5) `shouldBe` (intToNat 2)
    natMod (intToNat 11) (intToNat 5) `shouldBe` (intToNat 1)
    natMod (intToNat 20) (intToNat 21) `shouldBe` (intToNat 20)
  it "BinTree tests" $ do
    toList ((fromList [-5,-8,4,10,1]) :: BinTree Int) `shouldMatchList` [-8,-5,1,4,10]
    toList (fromList [] :: BinTree Int) `shouldMatchList` []
    treeIsEmpty Leaf `shouldBe` True
    treeIsEmpty (Node ((1 :: Int) :| []) Leaf Leaf) `shouldBe` False
    treeSize (fromList [1,1,2,3] :: BinTree Int) `shouldBe` 4
    treeFind (fromList [1,1,2,3] :: BinTree Int) 1 `shouldBe` Just [1,1]
    treeFind (fromList [1,1,2,3] :: BinTree Int) 5 `shouldBe` Nothing
    let tree = fromList [1,4,5,2,9] in
      treeDelete (treeInsert tree  5) 5 `shouldBe` (tree :: BinTree Int)
    let tree = fromList [1,4,5,5,2,9] in
      treeDelete (treeInsert tree  5) 5 `shouldBe` (tree :: BinTree Int)
