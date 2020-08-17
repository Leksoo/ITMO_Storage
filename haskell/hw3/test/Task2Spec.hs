module Task2Spec where

import Test.Hspec
import Data.Foldable (forM_)
import Control.Monad (forM)

import Task2

spec :: Spec
spec = do
  it "empty size" $ do
    cht <- newCHT :: IO (ConcurrentHashTable Int Int)
    size' <- sizeCHT cht
    size' `shouldBe` (0 :: Int)
  it "many different size" $ do
    cht <- newCHT :: IO (ConcurrentHashTable Int Int)
    forM_ [1..100] (\i -> putCHT i i cht)
    size' <- sizeCHT cht
    size' `shouldBe` (100 :: Int)
  it "many same size" $ do
    cht <- newCHT :: IO (ConcurrentHashTable Int Int)
    forM_ [1..100] (\i -> putCHT 0 i cht)
    size' <- sizeCHT cht
    size' `shouldBe` (1 :: Int)
  it "get from empty" $ do
    cht <- newCHT :: IO (ConcurrentHashTable Int Int)
    el <- getCHT 0 cht
    el `shouldBe` (Nothing :: Maybe Int)
  it "get element" $ do
    cht <- newCHT :: IO (ConcurrentHashTable Int Int)
    _ <- putCHT 0 1 cht
    el <- getCHT 0 cht
    el `shouldBe` (Just 1 :: Maybe Int)
  it "get many elements" $ do
    cht <- newCHT :: IO (ConcurrentHashTable Int Int)
    forM_ [1..100] (\i -> putCHT i i cht)
    els <- forM [1..100] (\i -> getCHT i cht)
    let expected = map Just [1..100]
    els `shouldBe` (expected :: [Maybe Int])
  it "rewrite element" $ do
    cht <- newCHT :: IO (ConcurrentHashTable Int Int)
    _ <- putCHT 0 1 cht
    _ <- putCHT 0 2 cht
    el <- getCHT 0 cht
    el `shouldBe` (Just 2 :: Maybe Int)