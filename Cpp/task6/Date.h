//
// Created by lekso on 08.11.2018.
//

#ifndef TASK6_DATE_H
#define TASK6_DATE_H

#include<iostream>
#include <map>
#include <string>

class Date {
public:
    explicit Date(int mDay, int month, int year);

    explicit Date(int mTime);

    virtual ~Date();

    Date(const Date &other);

    Date &operator=(const Date &other);

    //arithmetic
    Date &operator+=(const Date &other);

    Date &operator-=(const Date &other);

    Date &operator*=(const Date &other);

    Date &operator/=(const Date &other);

    Date &operator%=(const Date &other);

    Date operator+(const Date &other);

    Date operator-(const Date &other);

    Date operator*(const Date &other);

    Date operator/(const Date &other);

    Date operator%(const Date &other);


    Date &operator|=(const Date &other);

    Date &operator^=(const Date &other);

    Date &operator&=(const Date &other);

    Date &operator~();

    Date operator|(const Date &other);

    Date operator^(const Date &other);

    Date operator&(const Date &other);

    friend std::ostream &operator<<(std::ostream &out, const Date &date);

    friend std::istream &operator>>(std::istream &in, Date &date);

    Date &operator++();

    const Date operator++(int);

    Date &operator--();

    const Date operator--(int);

    bool operator==(const Date &rhs) const;

    bool operator!=(const Date &rhs) const;

    bool operator<(const Date &rhs) const;

    bool operator>(const Date &rhs) const;

    bool operator<=(const Date &rhs) const;

    bool operator>=(const Date &rhs) const;

    Date &operator<<=(int);

    Date &operator>>=(int);

    Date operator<<(int);

    Date operator>>(int);

    bool operator&&(const Date &other);

    bool operator||(const Date &other);

    int operator[](size_t) const;

    int operator()(size_t) const;

    explicit operator std::string();


    const Date operator,(Date &v2);


private:
    void recalcDatebyTime();

    void recalcTimebyDate();

    void recalcIsAfter();

    std::map<int, int> daysInMonth;

    void initMap();

    int mTime;
    int mDay;
    int mMonth;
    int mYear;
    int mIsAfter;
};


#endif //TASK6_DATE_H
