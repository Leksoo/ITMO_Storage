
#ifndef TASK4_POLYNOMIAL_H
#define TASK4_POLYNOMIAL_H


#include <vector>
#include <iostream>
#include <string>

#include "lib_rational.h"

class Polynomial {
public:
    Polynomial();

    ~Polynomial();

    explicit Polynomial(const std::vector<std::string> &coefficients);

    std::string compare(const Rational &q1, const Rational &q2);
	



private:
    std::vector<Rational> mCoefficients;


};


#endif //TASK4_POLYNOMIAL_H
