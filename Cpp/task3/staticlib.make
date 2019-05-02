
CC = g++
CFLAGS = -c -o
OBJECTS = here.o bye.o hello.o
RES_DIR = staticlib/
LIB_OBJECTS = $(addprefix ${RES_DIR},${OBJECTS})

all: ${RES_DIR}program

${RES_DIR}program: ${RES_DIR}main.o ${RES_DIR}mylib.lib
	${CC} -o $@ -L. $^

${RES_DIR}mylib.lib: ${LIB_OBJECTS}
	ar rcs $@ $^
	
${RES_DIR}%.o: %.cpp
	${CC} ${CFLAGS} $@ $<
	
clean:
	rm -rf ${RES_DIR}*.*
