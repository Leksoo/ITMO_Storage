#include <iostream>
#include <typeinfo>
#include <string>
#include <tuple>

#include "Array.h"

//task1
template<class T>
void print_values(T value) {
    std::cout << typeid(value).name() << ": " << value << "\n";
}
template<class T, class... Args>
void print_values(T value, Args... args) {
    print_values(value);
    print_values(args...);
}

//task3
template<size_t first, size_t second, class... Args>
auto to_pair(std::tuple<Args...>& tuple) ->
    decltype(std::make_pair(std::get<first>(tuple), std::get<second>(tuple))) {
    return std::make_pair(std::get<first>(tuple), std::get<second>(tuple));

}


int main() {
    //task1 test
    print_values(0, 3.5, "Hello");

    //task2 test
    Array<std::string> array(3);
    for (int i = 0; i < 1; ++i) {
        Array<std::string> stringArray(100);
        for (int j = 10; j < 50; ++j) {
            stringArray[j] = "ahdfhk;aj";
        }
        for (int k = 0; k < stringArray.size() ; ++k) {
            std::cout << stringArray[k] << " ";
        };
        std::cout << "\n\n";
        Array<std::string> stringArray2 = std::move(stringArray);
        for (int k = 0; k < stringArray2.size() ; ++k) {
            std::cout << stringArray2[k] << " ";
        };
        std::cout << "\n\n";

        Array<std::string> stringArray3(std::move(stringArray2));
        for (int k = 0; k < stringArray3.size() ; ++k) {
            std::cout << stringArray3[k] << " ";
        };
        std::cout << "\n\n";

    }

    //task3 test
    auto t = std::make_tuple(0, 3.5, "Hello");
    std::pair<double, char const*> p = to_pair<0, 2>(t);
    std::cout << p.first << " " << p.second << "\n";

    return 0;
}