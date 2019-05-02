//
// Created by lekso on 05.11.2018.
//

#include "String.h"

String::String(const char *str) {
    mSize = strlen(str);
    mStr = new char[mSize + 1];
    strcpy(mStr, str);
}

String::String(const size_t n, const char symbol) {
    mSize = n;
    mStr = new char[mSize + 1];
    for (int i = 0; i < mSize; ++i) {
        mStr[i] = symbol;
    }
    mStr[mSize + 1] = '\0';
}

String::~String() {
    delete[] mStr;
}

size_t String::size() const {
    return mSize;
}

void String::append(String &str) {
    size_t size = mSize + str.size();
    char *newStr = new char[size + 1];
    strcpy(newStr, mStr);
    newStr = strcat(newStr, str.getStr());
    delete[] mStr;
    mSize = size;
    mStr = newStr;
}

const char *String::getStr() const {
    return mStr;
}

String::String(const String &str) {
    mSize = str.size();
    mStr = new char[mSize + 1];
    strcpy(mStr, str.getStr());
}

String &String::operator=(const String &str) {
    mSize = str.size();
    strcpy(mStr, str.getStr());
    return *this;
}
