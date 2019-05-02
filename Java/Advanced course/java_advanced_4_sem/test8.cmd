::set PATH=c:\Program Files\Java\jdk-11.0.2\bin;%PATH%
javac -cp artifacts/info.kgeorgiy.java.advanced.%1.jar;artifacts/info.kgeorgiy.java.advanced.concurrent.jar -d out/production src/ru/ifmo/rain/feponov/%1/*.java
java -cp out/production;artifacts/info.kgeorgiy.java.advanced.%1.jar;../lib/*^
 -p artifacts/info.kgeorgiy.java.advanced.base.jar;artifacts/info.kgeorgiy.java.advanced.concurrent.jar;artifacts/info.kgeorgiy.java.advanced.%1.jar;^
lib/hamcrest-core-1.3.jar;lib/junit-4.11.jar;lib/quickcheck-0.6.jar;lib/jsoup-1.8.1.jar;^
 -m info.kgeorgiy.java.advanced.%1 %3 ru.ifmo.rain.feponov.%1.%2,ru.ifmo.rain.feponov.%1.IterativeParallelism
