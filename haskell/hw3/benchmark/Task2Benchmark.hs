module Task2Benchmark
  ( benchmarks
  ) where
  
import Criterion.Main (Benchmark, bgroup, bench, env, nfIO)
import Control.Concurrent.Async (async, wait)
import Data.Foldable (forM_)
import Control.Monad (forM)

import Task2

benchmarks :: [Benchmark]
benchmarks = [singleThreadBench, concurrentBench]

testData :: IO [(Int,Int)]
testData = return $ map (\a -> (a, a)) [0..10000] -- 10^4

singleThreadBench :: Benchmark
singleThreadBench = env testData $ \vals -> bgroup "single-thread"
  [ bench "10^4 write ops" $ nfIO $ writeTest vals,
    bench "10^4/2 read ops, 10^4/2 write ops" $ nfIO $ writeReadTest vals]
  where
    writeTest list = do
      cht <- newCHT :: IO (ConcurrentHashTable Int Int)
      forM_ list (\(k,v) -> putCHT k v cht)
    writeReadTest list = do
      cht <- newCHT :: IO (ConcurrentHashTable Int Int)
      let slices = splitAt ((length list) `div` 2) list
      forM_ (fst slices) (\(k,v) -> putCHT k v cht)
      forM_ (snd slices) (\(k,_) -> getCHT k cht)

concurrentBench :: Benchmark
concurrentBench = env testData $ \_ -> bgroup "multi-threaded"
  [ bench "20 threads - 10^5 write ops" $ nfIO $ writeTest,
    bench "10 threads - 10^5/2 write ops, 10 threads - 10^5/2 read ops" $ nfIO $ writeReadTest]
  where
    writeTest = do
      cht <- newCHT :: IO (ConcurrentHashTable Int Int)
      threads <- forM [1..20 :: Int] (\_ -> async $ forM_ [0..5000] (\v -> putCHT v v cht))
      forM_ threads wait
    writeReadTest = do
      cht <- newCHT :: IO (ConcurrentHashTable Int Int)
      threads1 <- forM [1..10 :: Int] (\_ -> async $ forM_ [0..5000] (\v -> putCHT v v cht))
      threads2 <- forM [1..10 :: Int] (\_ -> async $ forM_ [0..5000] (\v -> getCHT v cht))
      forM_ threads1 wait
      forM_ threads2 wait