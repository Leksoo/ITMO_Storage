.PHONY: all compile run pack clean out

SOURCES = $(shell find src -type f -name "*.kt")
CLASSES = $(patsubst src/%.kt,out/%.class,$(SOURCES))
DEPS = lib/better-parse-jvm-0.4.0-alpha-3.jar
MAINCLASS = Main

all: out compile

run:
	${KOTLIN} -cp out:${DEPS} -Djava.io.tmpdir=tmp ${MAINCLASS}

pack:
	zip task2.zip -r Makefile kotlin.mk src lib

clean:
	rm -rf out
	rm -rf tmp

compile: ${SOURCES}
	${KOTLINC} -cp src:${DEPS} -Djava.io.tmpdir=kotlin-tmp $^ -d out

out:
	mkdir -p out

include kotlin.mk