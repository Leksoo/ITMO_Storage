//
// Created by lekso on 12.11.2018.
//

#ifndef TASK7_ARRAY_H
#define TASK7_ARRAY_H

#include <cstddef>


template<class T>
class Array {
public:
    explicit Array(size_t size = 0, const T &value = T());

    Array(const Array &);

    ~Array();

    Array &operator=(const Array &);

    size_t size() const;

    T &operator[](size_t);

    const T &operator[](size_t) const;


private:
    T *mData;
    size_t mSize;

    void cleanMemory();

};

#include "Array.hpp"
#endif //TASK7_ARRAY_H
