#include <iostream>
#include <sstream>
#include <string>

#include "BadFromString.h"

template<class T>
T fromString(const std::string& string);

template<>
std::string fromString<std::string>(const std::string& string) {
    return string;
};

template<class T>
T fromString(const std::string& string) {
    std::istringstream stringStream(string);
    stringStream >> std::noskipws;
    T convertedValue;
    if (!(stringStream >> convertedValue))
        throw BadFromString("converting error");
    else if (stringStream.get() && !stringStream.eof())
        throw BadFromString("eof error");
    return convertedValue;


};


int main() {
    std::string s1("123");
    int a1 = fromString<int>(s1);
    double b1 = fromString<double>(s1);
    std::string c1 = fromString<std::string>(s1);
    std::cout << a1 << " " << b1 << " " << c1 << std::endl;

    std::string s2("12.3");
    try {
        int a2 = fromString<int>(s2);
    }
    catch (BadFromString& e) {
        std::cout << e.what() << " ";
    }
    double b2 = fromString<double>(s2);
    std::string c2 = fromString<std::string>(s2);
    std::cout << b2 << " " << c2 << std::endl;

    std::string s3("adsad");
    try {
        int a3 = fromString<int>(s3);
    }
    catch (BadFromString& e) {
        std::cout << e.what() << " ";
    }
    try {
        double b3 = fromString<double>(s3);
    }
    catch (BadFromString& e) {
        std::cout << e.what() << " ";
    }
    std::string c3 = fromString<std::string>(s1);
    std::cout << c3 << std::endl;

    std::string s4(" 1");
    try {
        int a4 = fromString<int>(s4);
        std::cout << a4;
    }
    catch (BadFromString& e) {
        std::cout << e.what() << " ";
    }
    try {
        double b4 = fromString<double>(s4);
        std::cout << b4;
    }
    catch (BadFromString& e) {
        std::cout << e.what() << " ";
    }
    try {
        std::string c4 = fromString<std::string>(s4);
        std::cout << c4 << std::endl;

    }
    catch (BadFromString& e) {
        std::cout << e.what() << " ";
    }


    return 0;
}