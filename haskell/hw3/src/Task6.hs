{-# LANGUAGE Rank2Types #-}

module Task6
  ( cd
  , ls
  , file
  ) where

import Lens.Micro

import Task5

-- go to subdirectory
cd :: FilePath -> Traversal' FS FS
cd subdir = contents . traversed . filtered isCorrectDir
  where
    isCorrectDir (Dir name' _) = name' == subdir
    isCorrectDir _ = False

-- list all objects in current directory
ls :: Traversal' FS FilePath
ls = contents . traversed . name

-- get file from current directory
file :: FilePath -> Traversal' FS FilePath
file f = (contents . traversed . filtered isCorrectFile) . name
  where
    isCorrectFile (File name') = name' == f
    isCorrectFile _ = False