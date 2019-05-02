#include "student.h"
#include <iostream>

void Student::takeData(const int& admno, const std::string& sname, const float& eng, const float& math, const float& science) {
    this->admno = admno;
    this->sname = sname;
    this->eng = eng;
    this->math = math;
    this->science = science;
    total = ctotal();
}

void Student::showData() {
    std::cout << "admno: " << admno << "\nsname: " << sname << "\neng: " <<
        eng << "\nmath: " << math << "\nscience: " << science << "\ntotal: " << total;
}

float Student::ctotal() {
    return math + eng + science;
}



