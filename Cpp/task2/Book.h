#pragma once
class Book {
public:
    Book();
    ~Book();
    void input();
    void purchase();
private:
    static const int BOOK_TITLE_LENGTH = 20;
    int mBookNumber;
    std::string mBookTitle;
    float mPrice;
    float mTotalCost(const int& n) const;
};

