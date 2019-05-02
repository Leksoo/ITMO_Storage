#include <iostream>
#include <algorithm>
#include <iostream>
#include <vector>


#include "lib_rational.h"
#include "polynomial.h"

int main(int argc , char* argv[]) {
    std::vector<std::string> stringPoly;
    std::string str1, str2;
    int degree;
    std::cout << "enter polynomial degree\n";
    degree = std::stoi(std::string(argv[1]));
    std::cout << "enter " << degree + 1 << " coefficients in format: +-Ai/Bi\n";
    for (int i = 0; i <= degree; ++i) {
        std::string curCoeff;
        curCoeff = argv[i+2];
        stringPoly.push_back(curCoeff);
    }
    std::cout << "enter q1 and q2 in format:  A/B\n";
    str1 = argv[degree+3];
	str2 = argv[degree+4];

    Polynomial poly(stringPoly);
    Rational q1(str1);
    Rational q2(str2);
    std::string res = poly.compare(q1, q2);
	std::string ans;
	ans = argv[degree+5];
    return ans == res ? 0 : 1;
}