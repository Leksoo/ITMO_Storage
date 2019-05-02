

#include "polynomial.h"

Polynomial::Polynomial() = default;

Polynomial::~Polynomial() = default;

Polynomial::Polynomial(const std::vector<std::string> &coefficients) {
    for (auto &coeff:coefficients) {
        mCoefficients.push_back(Rational(coeff));
    }
}

std::string Polynomial::compare(const Rational &q1, const Rational &q2) {
    Rational last1 = Rational("0");
    Rational last2 = Rational("0");

    for (auto &coefficient : mCoefficients) {
        last1 = coefficient + last1 * q1;
        last2 = coefficient + last2 * q2;

    }

    if (last1 > last2) {
        return "bigger";
    } else if (last1 < last2) {
        return "smaller";
    } else {
        return "equal";
    }

}






