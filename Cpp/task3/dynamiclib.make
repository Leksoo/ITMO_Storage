
CC = g++
CFLAGS = -c -o
OBJECTS = here.o bye.o hello.o
RES_DIR = dynamiclib/
LIB_OBJECTS = $(addprefix ${RES_DIR},${OBJECTS})

all: ${RES_DIR}program

${RES_DIR}program: ${RES_DIR}main.o ${RES_DIR}libmylib.dll.5.1.10
	${CC} -o $@ -L. $^

${RES_DIR}libmylib.dll.5.1.10: ${LIB_OBJECTS}
	$(CC) -shared -Wl,-soname,libmylib.dll.5 -o $@ $^
	
${RES_DIR}main.o: main.cpp
	$(CC) $(CFLAGS) $@ $<
	
${RES_DIR}%.o: %.cpp
	$(CC) -fPIC $(CFLAGS) $@ $<

clean:
	rm -rf ${RES_DIR}*.*
