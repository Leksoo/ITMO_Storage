module Block2Spec where

import Test.Hspec
import Test.QuickCheck

import Block1 (BinTree (..), fromList)
import Block2

import Data.Foldable (toList)
import Data.List (sort)
import Data.List.NonEmpty (NonEmpty (..))


spec :: Spec
spec = do
  it "BinTree fromList property test" $
    property $ \list -> toList ((fromList list) :: BinTree Int) `shouldMatchList` sort list
  it "splitOn tests" $ do
    splitOn '/' "ab/cd/ef/" `shouldBe` "ab" :| ["cd","ef",""]
    splitOn '/' "" `shouldBe` "" :| []
  it "joinWith tests" $ do
      joinWith '/' ("a" :| ["b",""]) `shouldBe` "a/b/"
      joinWith '/' ("" :| [""]) `shouldBe` "/"
  it "joinWith/splitOn property test" $
    property $ \ch str -> joinWith ch (splitOn ch str :: NonEmpty String) `shouldBe` str