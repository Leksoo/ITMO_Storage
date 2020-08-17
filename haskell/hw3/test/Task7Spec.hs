module Task7Spec where

import Test.Hspec
import Lens.Micro
import Data.Maybe (fromJust)

import Task5(FS(..), name)
import Task6(ls, cd)
import Task7

toyFS :: FS
toyFS
  = Dir
  { _name = "dirA"
  , _contents =
    [ File { _name = "fileAB.ext1"},
      File { _name = "fileBC.ext2"},
      File { _name = "fileNoExt"},
      Dir { _name = "dirAB.ext", _contents = []},
      Dir
      { _name = "dirAC"
      , _contents =
        [ File {_name = "fileACD"},
          File {_name = ""},
          Dir {_name = "dirACD", _contents = []},
          Dir {_name = "dirACF", _contents = [File {_name = "fileACFE"}]}
        ]
      }

    ]
  }


toyFSAfterRemove :: FS
toyFSAfterRemove
  = Dir
  { _name = "dirA"
  , _contents =
    [ File { _name = "fileAB.ext1"},
      File { _name = "fileBC.ext2"},
      File { _name = "fileNoExt"},
      Dir
      { _name = "dirAC"
      , _contents =
        [ File {_name = "fileACD"},
          File {_name = ""},
          Dir {_name = "dirACD", _contents = []},
          Dir {_name = "dirACF", _contents = [File {_name = "fileACFE"}]}
        ]
      }

    ]
  }

spec :: Spec
spec = do
  it "changeExtension" $ do
    (changeExtension toyFS "new") ^.. ls `shouldBe`
      ["fileAB.new", "fileBC.new" , "fileNoExt.new", "dirAB.ext", "dirAC"]
    (changeExtension toyFS "") ^.. ls `shouldBe`
      ["fileAB", "fileBC" , "fileNoExt", "dirAB.ext", "dirAC"]
  it "getChildrenNames" $ do
    (getChildrenNames toyFS) `shouldBe`
      ["fileAB.ext1", "fileBC.ext2" , "fileNoExt", "dirAB.ext", "dirAC",
       "fileACD", "", "dirACD", "dirACF", "fileACFE"]
    getChildrenNames (fromJust $ toyFS ^? cd "dirAC" . cd "dirACD") `shouldBe` []
  it "getChildrenNames & changeExtension" $ do
    getChildrenNames (changeExtension (fromJust $ toyFS ^? cd "dirAC" . cd "dirACF") "new")
      `shouldBe` ["fileACFE.new"]
  it "removeSubdir" $ do
    removeSubdir toyFS "smth" `shouldBe` toyFS
    removeSubdir toyFS "fileBC.ext2" `shouldBe` toyFS
    removeSubdir toyFS "dirAC" `shouldBe` toyFS
    removeSubdir toyFS "dirAB.ext" `shouldBe` toyFSAfterRemove
  it "getPath" $ do
    toyFS ^? move "dirAB.ext" . name `shouldBe` Just "dirA/dirAB.ext"
    toyFS ^? move "fileAB.ext1" . name `shouldBe` Just "dirA"
    toyFS ^? move "dirAC" . move "dirACF". move "fileACFE" . name 
      `shouldBe` Just "dirA/dirAC/dirACF"

