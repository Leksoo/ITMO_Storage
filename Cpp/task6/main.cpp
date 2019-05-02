#include <iostream>

#include "Date.h"

int main() {

    Date date1(-1,-1,-1);
    std::cout << date1;

    Date date1t(0);
    std::cout << date1t;

    Date date2(2,2,2);
    std::cout << date2;

    Date date2t(397);
    std::cout << date2t;

    std::cout << (date2 + date2t);
    std::cout << date2t(2) << std::endl;
    std::cout << static_cast<std::string>(date2t) << std::endl;

    return 0;
}