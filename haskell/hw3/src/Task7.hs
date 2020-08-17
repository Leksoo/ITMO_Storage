{-# LANGUAGE Rank2Types #-}

module Task7
  ( changeExtension
  , getChildrenNames
  , removeSubdir
  , move
  ) where
 
import Lens.Micro
import System.FilePath.Posix (replaceExtension, (</>))
import Data.Maybe (fromMaybe)

import Task5
import Task6 (cd)

-- changes files extensions
changeExtension :: FS -> String -> FS
changeExtension fs ext = fs & contents . traversed . (filtered isFile) . name %~ (flip replaceExtension) ext
  where
    isFile (File _) = True
    isFile _ = False

-- gets names of all subdirectories/subfiles of current directory
getChildrenNames :: FS -> [FilePath]
getChildrenNames fs = concatMap getName (fs ^.. contents.traversed)
  where
    getName (File n) = [n]
    getName dir@(Dir n _) = [n] ++ getChildrenNames dir

-- removes empty subdirectory
removeSubdir :: FS -> FilePath -> FS
removeSubdir fs subdir = fs & contents %~ modifiedDir
  where
    modifiedDir files = filter (\f -> dirFilter f) files
     where
      dirFilter (Dir n []) | n == subdir = False
                           | otherwise   = True
      dirFilter _ = True

-- cd with path memorizing, use name lens in the end to get path
move :: FilePath -> Traversal' FS FS
move path f fs = f (fromMaybe fs ((fs ^? cd path) & mapped . name %~ (root </>)))
  where root = fs ^. name