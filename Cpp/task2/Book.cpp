#include <iostream>
#include <string>

#include "Book.h"

Book::Book() {}

Book::~Book() {}

void Book::input() {
    std::cout << "enter book number\n";
    std::cin >> mBookNumber;
    std::cout << "enter book title\n";
    std::string bookTitleInput;
    std::cin >> bookTitleInput;
    mBookTitle = bookTitleInput.substr(0, BOOK_TITLE_LENGTH);
    std::cout << "enter book price\n";
    std::cin >> mPrice;
}

void Book::purchase() {
    std::cout << "enter number of copies\n";
    int n;
    std::cin >> n;
    std::cout << "total cost: " << mTotalCost(n) << "\n";
}

float Book::mTotalCost(const int & n) const {
    return n * mPrice;
}

