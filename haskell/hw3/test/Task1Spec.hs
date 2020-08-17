module Task1Spec where

import Test.Hspec

import Task1(perimeter, doubleArea, Point(..))

makePoints :: [(Int, Int)] -> [Point]
makePoints list = map (\(a, b) -> Point a b) $ list

spec :: Spec
spec = do
  it "perimeter tests" $ do
    let points = makePoints [(0,0), (0,10), (10,0)] in
      perimeter points `shouldBe` (10.0 + 10.0 + sqrt 200)
    let points = makePoints [(0,1), (0,0), (1,0), (1,1)] in
      perimeter points `shouldBe` 4.0
  it "doubleArea tests" $ do
      let points = makePoints [(10,0), (0,10), (0,0)] in
        doubleArea points `shouldBe` (10 * 10)
      let points = makePoints [(0,1), (0,0), (1,0), (1,1)] in
        doubleArea points `shouldBe` 2