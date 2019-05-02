#pragma once

#include <string>

template<class T>
class Array {
public:
    explicit Array(size_t size = 0);
    Array(const Array& array);
    Array& operator=(const Array& array);
    ~Array();
    size_t size() const;
    T& operator[](size_t i);
    T const& operator[](size_t i) const;
    Array(Array&& array);
    Array& operator=(Array&& array);

private:
    size_t mSize;
    T* mData;
    void copy(const Array& array);
};

#include "Array.hpp"

