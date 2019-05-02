#include <iostream>

#include "ManBearPig.h"

//task2
typedef int (*argument)(double);
typedef int *(*result)(char const *);
typedef result (*ComplexFunction)(int, argument);

//task3
template<typename T, typename C>
bool compare(T const& obj1, T const& obj2, C (T::*function)() const) {
    return (obj1.*function)() < (obj2.*function)();
}

//task4
template<class T>
bool isSameObject(T const *p, T const *q) {
    // returns first bite in object address
    return dynamic_cast<const void *>(p) == dynamic_cast<const void *>(q);
}


int main() {
    //task1 test
    ManBearPig manBearPig(1, 100, 30, "blue", 44);
    std::cout << manBearPig.getMKind() << std::endl;
    std::cout << manBearPig.getMClass() << std::endl;
    std::cout << manBearPig.getMId() << std::endl;
    std::cout << manBearPig.getAge() << std::endl;

    //task3 test
    std::string s1("Elf");
    std::string s2("Archer");
    bool r1 = compare(s1, s2, &std::string::size);
    bool r2 = compare(s1, s1, &std::string::size);
    std::cout << r1 << std::endl;
    std::cout << r2 << std::endl;

    return 0;
}