ANTLR = lib/antlr-runtime-4.7.2.jar
MAINCLASS = Main

all: makeout changesizes compilektexpr compilekt

run:
	${KOTLIN} -cp out:${ANTLR} -Djava.io.tmpdir=tmp ${MAINCLASS}

pack:
	zip hw5.zip -r Makefile kotlin.mk src lib

clean:
	rm -rf out
	rm -f src/parser/*.java

changesizes:
	export JAVA_OPTS="-Xmx2g -Xss4m"

compilejava: src/parser/*.java
	javac -cp src:out:${ANTLR} $^ -d out

compilekt: src/*.kt src/logic/*.kt src/parser/*.kt
	${KOTLINC} -cp out:src:${ANTLR} -Djava.io.tmpdir=kotlin-tmp $^ -d out

compilektexpr: src/expression/*.kt
	${KOTLINC} -cp out:src:${ANTLR} -Djava.io.tmpdir=kotlin-tmp $^ -d out

makeout:
	mkdir -p out

include kotlin.mk