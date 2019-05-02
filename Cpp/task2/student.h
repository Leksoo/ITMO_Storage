#pragma once

#include<string>
#include<iostream>

class Student {
public:
    void takeData(const int& admno, const std::string& sname, const float& eng, const float& math, const float& science);
    void showData();
private:
    int admno;
    std::string sname;
    float eng, math, science;
    float total;
    float ctotal();
};

