module Benchmark where

import Criterion.Main (defaultMain)
import Task1Benchmark (benchmarks)
import Task2Benchmark (benchmarks)

main :: IO ()
main = do
  defaultMain $ concat $ [Task1Benchmark.benchmarks, Task2Benchmark.benchmarks]