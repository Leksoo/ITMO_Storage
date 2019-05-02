
#ifndef LIB_RATIONAL_LIB_RATIONAL_H
#define LIB_RATIONAL_LIB_RATIONAL_H

#include <string>


class Rational {
public:

    Rational();

    ~Rational();

    explicit Rational(int numerator, int denominator = 1);

    explicit Rational(std::string number);

    int getNumerator() const;

    int getDenominator() const;

    const Rational operator+(const Rational& other) const;

    const Rational operator-(const Rational& other) const;

    const Rational operator/(const Rational& other) const;

    const Rational operator*(const Rational& other) const;

    bool operator==(const Rational &other) const;

    bool operator!=(const Rational &other) const;

    bool operator<(const Rational &other) const;

    bool operator>(const Rational &other) const;

    bool operator<=(const Rational &other) const;

    bool operator>=(const Rational &other) const;


private:
    int mNumerator;
    int mDenominator;
    int gcd(int u, int v);
    void simplify();
};


#endif //LIB_RATIONAL_LIB_RATIONAL_H
