ANTLR = lib/antlr-4.7.1-complete.jar
MAINCLASS = Main

all: makeout compileantlr compilektexpr compilejava compilekt

run:
	${KOTLIN} -cp out:${ANTLR} -Djava.io.tmpdir=tmp ${MAINCLASS}

pack:
	zip hw5.zip -r Makefile kotlin.mk src lib

clean:
	rm -rf out
	rm -f src/parser/*.java

compileantlr: src/parser/Expression.g4
	java -jar ${ANTLR} $^ -o src/parser

compilejava: src/parser/*.java
	javac -cp src:out:${ANTLR} $^ -d out

compilekt: src/*.kt src/processor/*.kt src/statement/*.kt
	${KOTLINC} -cp out:src:${ANTLR} -Djava.io.tmpdir=kotlin-tmp $^ -d out

compilektexpr: src/expression/*.kt
	${KOTLINC} -cp out:src:${ANTLR} -Djava.io.tmpdir=kotlin-tmp $^ -d out

makeout:
	mkdir -p out

include kotlin.mk