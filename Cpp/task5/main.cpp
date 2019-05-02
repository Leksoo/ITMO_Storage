#include <iostream>

#include "String.h"

int main() {

    String a("abs");
    String b(10, 'b');
    a.append(a);
    a.append(b);
    for (int i = 0; i < 100000; ++i) {
        String a("asdasfaf");
        String b = a;
        String c(a);
        c.append(c);
        c.append(a);
        c.append(b);
    }
    std::cout << a.getStr();
    return 0;
}