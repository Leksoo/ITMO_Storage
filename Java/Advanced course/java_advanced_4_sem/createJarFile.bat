javac -cp artifacts/info.kgeorgiy.java.advanced.implementor.jar -d ./ src/ru/ifmo/rain/feponov/implementor/*.java

SET artifacts_path=info/kgeorgiy/java/advanced/implementor/


jar xf artifacts/info.kgeorgiy.java.advanced.implementor.jar %artifacts_path%Impler.class %artifacts_path%JarImpler.class %artifacts_path%ImplerException.class
jar cfm Implementor.jar Manifest.txt ru/ifmo/rain/feponov/implementor/*.class %artifacts_path%*.class

java -cp . -jar implementor.jar java.util.List