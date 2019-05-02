


#include "lib_rational.h"

Rational::Rational() = default;

Rational::~Rational() = default;

Rational::Rational(int numerator, int denominator) {
    mNumerator = numerator;
    mDenominator = denominator;
    if (mDenominator < 0) {
        mNumerator *= -1;
        mDenominator *= -1;
    }
    this->simplify();
}

Rational::Rational(std::string number) {
    int sign = 1;
    if (number[0] == '-') {
        sign = -1;
        number = number.substr(1);
    } else if (number[0] == '+') {
        number = number.substr(1);
    }
    unsigned int splitterIndex = number.find('/');
    if (splitterIndex == std::string::npos) {
        mNumerator = sign * std::stoi(number);
        mDenominator = 1;
    } else {
        mNumerator = sign * std::stoi(number.substr(0, splitterIndex));
        mDenominator = std::stoi(number.substr(splitterIndex + 1));
    }
    this->simplify();
}

int Rational::getNumerator() const {
    return mNumerator;
}

int Rational::getDenominator() const {
    return mDenominator;
}

void Rational::simplify() {
    int multiplier = gcd(mNumerator, mDenominator);
    mNumerator /= multiplier;
    mDenominator /= multiplier;
}

int Rational::gcd(int u, int v) {
    while (v != 0) {
        int r = u % v;
        u = v;
        v = r;
    }
    return u;
}

bool Rational::operator==(const Rational &other) const {
    return mNumerator == other.mNumerator &&
           mDenominator == other.mDenominator;
}

bool Rational::operator!=(const Rational &other) const {
    return !(other == *this);
}

bool Rational::operator<(const Rational &other) const {
    int first = mNumerator * other.mDenominator;
    int second = other.mNumerator * mDenominator;
    return first < second;
}

bool Rational::operator>(const Rational &other) const {
    return other < *this;
}

bool Rational::operator<=(const Rational &other) const {
    return !(other < *this);
}

bool Rational::operator>=(const Rational &other) const {
    return !(*this < other);
}

const Rational Rational::operator+(const Rational &other) const {
    Rational ans(mNumerator * other.mDenominator + other.mNumerator * mDenominator,
                            mDenominator * other.mDenominator);
    ans.simplify();
    return ans;
}

const Rational Rational::operator-(const Rational &other) const {
    return *this + Rational(other.mNumerator * -1, other.mDenominator);
}

const Rational Rational::operator*(const Rational &other) const {
    Rational ans(mNumerator * other.mNumerator, mDenominator * other.mDenominator);
    ans.simplify();
    return ans;
}

const Rational Rational::operator/(const Rational &other) const {
    return *this * Rational(other.mDenominator, other.mNumerator);
}




