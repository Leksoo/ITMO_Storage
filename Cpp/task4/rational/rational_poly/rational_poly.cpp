#include <iostream>
#include <algorithm>
#include <iostream>
#include <vector>


#include "lib_rational.h"
#include "polynomial.h"

int main() {
    std::vector<std::string> stringPoly;
    std::string str1, str2;
    int degree;
    std::cout << "enter polynomial degree\n";
    std::cin >> degree;
    std::cout << "enter " << degree + 1 << " coefficients in format: +-Ai/Bi\n";
    for (int i = 0; i <= degree; ++i) {
        std::string curCoeff;
        std::cin >> curCoeff;
        stringPoly.push_back(curCoeff);
    }
    std::cout << "enter q1 and q2 in format:  A/B\n";
    std::cin >> str1 >> str2;

    Polynomial poly(stringPoly);
    Rational q1(str1);
    Rational q2(str2);
    std::cout << poly.compare(q1, q2);
    return 0;
}

