module Task5
  ( FS(..)
  , scanDir
  , name
  , contents
  ) where

import Control.Monad (forM)
import Data.Maybe (catMaybes)
import System.Directory (listDirectory, doesDirectoryExist, doesFileExist)
import System.FilePath.Windows ((</>), splitDirectories)
import Lens.Micro (Traversal', Lens', lens)

data FS
    = Dir
    { _name     :: FilePath  -- название папки, не полный путь
    , _contents :: [FS]
    }
    | File
    { _name     :: FilePath  -- название файла, не полный путь
    } deriving (Eq, Show)

scanDir :: FilePath -> IO (Maybe FS)
scanDir path = do
  isFile <- doesFileExist path
  if (isFile)
    then do
      return $ pure $ File (last $ splitDirectories path)
    else do
      isDirectory <- doesDirectoryExist path
      if (isDirectory)
        then do
          content <- listDirectory path
          files <- forM content (\file -> scanDir $ path </> file)
          return $ pure $ Dir (last $ splitDirectories path) (catMaybes files)
        else do
          return Nothing

_Dir :: Traversal' FS (FilePath, [FS])
_Dir f (Dir n c) = (\(n', c') -> Dir n' c') <$> f (n, c)
_Dir _ file = pure file

_File :: Traversal' FS FilePath
_File f (File n) = (\n'-> File n') <$> f n
_File _ dir = pure dir

name :: Lens' FS FilePath
name = lens getter setter
  where
    getter fs = _name fs
    setter fs newName = fs {_name = newName}
    
contents :: Lens' FS [FS]
contents = lens getter setter
  where
    getter (Dir _ c) = c
    getter (File _) = []
    setter fs@(File _) _ = fs
    setter fs@(Dir _ _) newContents = fs {_contents = newContents}