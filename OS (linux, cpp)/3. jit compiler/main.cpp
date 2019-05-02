#include <iostream>
#include <sys/mman.h>
#include <cstring>


using namespace std;

typedef unsigned char (*func)(unsigned char);


//unsigned char main(unsigned char val){
//    return val & 2;
//}
//00000000000005fa <main>:
//5fa:	55                   	push   %rbp
//5fb:	48 89 e5             	mov    %rsp,%rbp
//5fe:	89 f8                	mov    %edi,%eax
//600:	88 45 fc             	mov    %al,-0x4(%rbp)
//603:	0f b6 45 fc          	movzbl -0x4(%rbp),%eax
//607:	83 e0 02             	and    $0x2,%eax
//60a:	5d                   	pop    %rbp
//60b:	c3                   	retq
unsigned char func_code[] = {
        0x55,
        0x48, 0x89, 0xe5,
        0x89, 0xf8,
        0x88, 0x45, 0xfc,
        0x0f, 0xb6, 0x45, 0xfc,
        0x83, 0xe0, 0x02,
        0x5d,
        0xc3
};

size_t func_size = sizeof(func_code);

void printErrorMsg(const string &msg) {
    cerr << msg << endl;
}

void printMsg(const string &msg) {
    cout << msg << endl;
}


void printInfo() {
    const char *message =
            "The jit-compiled function is: input_number & A, A is 2 by default \n"
            "Arguments to run with default A: [input_number]\n"
            "Arguments to run with your A: [input_number] [A]\n"
            "input numbers should be unsigned chars";
    printMsg(message);
}

int main(int argc, char *argv[]) {
    printInfo();
    if (argc < 2) {
        printErrorMsg("Wrong number of arguments");
    }
    unsigned char input = static_cast<unsigned char>(stoi(argv[1]));
    // change
    if (argc == 3) {
        unsigned char a = static_cast<unsigned char>(stoi(argv[2]));
        memcpy(func_code + 15, &a, 1);
    }
    // allocate
    auto mem = mmap(nullptr, func_size,
                    PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (mem == MAP_FAILED) {
        printErrorMsg("Error while mapping memory");
        return 1;
    }
    // write to memory
    memcpy(mem, func_code, func_size);
    // change protection
    if (mprotect(mem, func_size, PROT_READ | PROT_EXEC) == -1) {
        printErrorMsg("Error while changing memory protection");
        return 1;
    }
    // run function
    auto f = (func) mem;
    cout << "result: " << to_string(f(input)) << endl;
    // clean memory
    if (munmap(mem, func_size) == -1) {
        printErrorMsg("Error while cleaning memory");
        return 1;
    }
    return 0;
}





