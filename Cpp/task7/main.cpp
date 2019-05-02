#include <iostream>
#include <string>

#include "Array.h"


struct SpecialObject {
    explicit SpecialObject(const size_t value) : smth(value) {};

    SpecialObject &operator=(const SpecialObject &) = delete;

    size_t smth;
};

bool less(int a, int b) { return a < b; }

struct Greater {
    bool operator()(int a, int b) { return b < a; }
};

int main() {
    //Array test
    Array<int> array1(10, 15);
    for (int i = 0; i < 10; ++i) {
        std::cout << array1[i] << " ";
    }
    std::cout << std::endl;
    Array<SpecialObject> array2(20, SpecialObject(10));
    for (int i = 0; i < 10; ++i) {
        std::cout << array2[i].smth << " ";
    }
    std::cout << std::endl;
    for (int j = 0; j < 1000; ++j) {
        Array<SpecialObject> arrayA(1000, SpecialObject(25));
        Array<SpecialObject> arrayB(1000, SpecialObject(70));
        Array<SpecialObject> arrayC(arrayA);
        arrayB = arrayC;
        std::cout << arrayB[j].smth << " ";
    }
    std::cout << std::endl;

    // minimum test
    Array<int> ints(3);
    ints[0] = 10;
    ints[1] = 2;
    ints[2] = 15;
    int min = minimum(ints, less);
    int max = minimum(ints, Greater());
    std::cout << "min = " << min << ", max = " << max << std::endl;

    //flatten test
    Array<int> ints2(2, 0);
    ints2[0] = 10;
    ints2[1] = 20;
    flatten(ints2, std::cout);
    std::cout << std::endl;
    Array<Array<int>> array_of_ints(2, ints2);
    flatten(array_of_ints, std::cout);
    std::cout << std::endl;
    Array<double> doubles(10, 1.1);
    flatten(doubles, std::cout);

    return 0;
}

