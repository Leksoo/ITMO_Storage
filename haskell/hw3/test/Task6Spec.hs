module Task6Spec where

import Test.Hspec
import Lens.Micro

import Task5(FS(..))
import Task6

toyFS :: FS
toyFS
  = Dir
  { _name = "dirA"
  , _contents =
    [ File { _name = "fileAB"},
      File { _name = "fileBC"},
      Dir { _name = "dirAB", _contents = []},
      Dir
      { _name = "dirAC"
      , _contents =
        [ File {_name = "fileACD"},
          File {_name = ""},
          Dir {_name = "dirACD", _contents = []},
          Dir {_name = "dirACD", _contents = [File {_name = "fileACDE"}]}
        ]
      }

    ]
  }
spec :: Spec
spec = do
  it "ls dir" $ do
    toyFS ^.. ls `shouldBe` ["fileAB", "fileBC", "dirAB", "dirAC"]
  it "ls file" $ do
    toyFS ^.. cd "fileAB" . ls `shouldBe` []
  it "cd dir" $ do
    toyFS ^? cd "dirAB" `shouldBe` Just Dir { _name = "dirAB", _contents = []}
  it "cd file" $ do
    toyFS ^? cd "fileBC" `shouldBe` Nothing
  it "get file" $ do
    toyFS ^? cd "dirAC" . cd "dirACD" . file "fileACDE" `shouldBe` Just "fileACDE"
  it "get no file" $ do
    toyFS ^? cd "dirAC" . cd "dirACD" . file "NoFile" `shouldBe` Nothing
