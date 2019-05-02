//
// Created by lekso on 12.11.2018.
//



#include<iostream>

#include "Array.h"

template<class T>
Array<T>::Array(size_t size, const T &value) {
    mSize = size;
//    mData = new T[size]; <--- default constructor used
//    for (size_t i = 0; i < size; ++i) {
//        mData[i] = value; <--- assignment operator used
//    }
    mData = (T *) new int[size * sizeof(T)];
    for (int i = 0; i < size; ++i) {
        new(mData + i) T(value);
    }

}

template<class T>
Array<T>::Array(const Array &other) {
    mSize = other.mSize;
    mData = (T *) new int[mSize * sizeof(T)];
    for (size_t i = 0; i < mSize; ++i) {
        new(mData + i) T(other[i]);
    }
}

template<class T>
Array<T>::~Array() {
    cleanMemory();
}

template<class T>
Array<T> &Array<T>::operator=(const Array &other) {
    cleanMemory();
    mSize = other.mSize;
    mData = (T *) new int[mSize * sizeof(T)];
    for (size_t i = 0; i < mSize; ++i) {
        new(mData + i) T(other[i]);
    }
    return *this;

}

template<class T>
size_t Array<T>::size() const {
    return mSize;
}

template<class T>
T &Array<T>::operator[](size_t position) {
    return mData[position];
}

template<class T>
const T &Array<T>::operator[](size_t position) const {
    return mData[position];
}

template<class T>
void Array<T>::cleanMemory() {
    for (size_t i = 0; i < mSize; ++i) {
        mData[i].~T();
    }
    delete[] (int *) mData;
}


template<class T, class C>
T minimum(Array<T> &data, C comparator) {
    size_t minIndex = 0;
    for (size_t i = 1; i < data.size(); ++i) {
        if (comparator(data[i], data[minIndex])) {
            minIndex = i;
        }
    }
    return data[minIndex];
}


template<class T>
void flatten(const Array<T> &data, std::ostream &out) {
    for (size_t i = 0; i < data.size(); ++i) {
        out << data[i] << " ";
    }
}

template<class T>
void flatten(const Array<Array<T>> &data, std::ostream &out) {

    for (size_t i = 0; i < data.size(); ++i) {
        flatten(data[i], out);
    }
}






