module Task1Benchmark
  ( benchmarks
  ) where
  
import Criterion.Main (Benchmark, bgroup, bench, env, nf)
import Task1 ( perimeter 
             , perimeterStrict
             , doubleArea
             , doubleAreaStrict
             , Point(..)
             )
             

benchmarks :: [Benchmark]
benchmarks = [perimeterBench, doubleAreaBench]

testData :: IO [Point]
testData = return $ map (\a -> Point a a) $ [0..10000000] -- 10^7

perimeterBench :: Benchmark
perimeterBench = env testData $ \points -> bgroup "perimeter"
  [ bench "simple" $ nf perimeter points,
    bench "strict" $ nf perimeterStrict points]
    
doubleAreaBench :: Benchmark
doubleAreaBench = env testData $ \points -> bgroup "doubleArea"
  [ bench "simple" $ nf doubleArea points,
    bench "strict" $ nf doubleAreaStrict points]