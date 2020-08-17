module Task2
  ( ConcurrentHashTable(..)
  , newCHT
  , getCHT
  , putCHT
  , sizeCHT
  ) where

import qualified Data.Vector as V
import Data.Hashable (Hashable(..))
import Data.Foldable (mapM_)
import Control.Concurrent.STM
  ( STM
  , TVar
  , atomically
  , newTVar
  , readTVar
  , writeTVar
  )

-- concurrent hashtable data type
data ConcurrentHashTable k v = ConcurrentHashTable
  { size :: TVar Int
  , buckets :: TVar (V.Vector (TVar [(k,v)]))
  }

create :: STM (ConcurrentHashTable k v)
create = do
  buckets' <- V.replicateM 50 (newTVar [])
  bucketsTVar <- newTVar buckets'
  size' <- newTVar 0
  return $ ConcurrentHashTable size' bucketsTVar

getBucket :: Hashable k => ConcurrentHashTable k v -> k -> STM (TVar [(k,v)])
getBucket table k = do
  buckets' <- readTVar $ buckets table
  let kHash = hash k `mod` V.length buckets'
  return $ buckets' V.! kHash

getElement :: (Eq k, Hashable k) => ConcurrentHashTable k v -> k -> STM (Maybe v)
getElement table k = do
  bucketTVar <- getBucket table k
  bucket <- readTVar $ bucketTVar
  return $ lookup k bucket

doubleBuckets :: (Eq k, Hashable k) => ConcurrentHashTable k v -> STM ()
doubleBuckets table = do
  buckets' <- readTVar $ buckets table
  size' <- readTVar $ size table
  let bucketsCount = V.length buckets'
  if (size' `div` 2 > bucketsCount)
    then do
      newBuckets <- V.replicateM (bucketsCount * 2) (newTVar [])
      lists <- V.mapM readTVar buckets'
      let elems = concat lists
      writeTVar (buckets table) newBuckets
      mapM_ (\(key,val) -> add table key val) elems
    else
      return ()

add :: (Eq k, Hashable k) => ConcurrentHashTable k v -> k -> v -> STM ()
add table k v = do
  bucketTVar <- getBucket table k
  bucket <- readTVar $ bucketTVar
  case lookup k bucket of
    Nothing -> do
      let newBucket = (k,v) : bucket
      writeTVar bucketTVar newBucket
      size' <- readTVar $ size table
      writeTVar (size table) (size' + 1)
    Just _ -> do
      let newBucket = replace bucket k v
      writeTVar bucketTVar newBucket

replace :: (Eq k) => [(k,v)] -> k -> v -> [(k,v)]
replace [] _ _ = []
replace (x:xs) k v = check x k ++ replace xs k v
  where
    check a b = if (fst a == b)
                then [(fst a, v)]
                else [a]

-- creates new hashtable
newCHT :: IO (ConcurrentHashTable k v)
newCHT = atomically $ create

-- gets element by key
getCHT :: (Eq k, Hashable k) => k -> ConcurrentHashTable k v -> IO (Maybe v)
getCHT k table = atomically $ getElement table k

-- puts key and element
putCHT :: (Eq k, Hashable k) => k -> v -> ConcurrentHashTable k v -> IO ()
putCHT k v table = atomically $ doubleBuckets table >> add table k v

-- gets current size of hashtable
sizeCHT :: ConcurrentHashTable k v -> IO Int
sizeCHT table = atomically $ readTVar $ size table
