


template<class T>
void Array<T>::copy(const Array& array) {
    mData = new T[mSize];
    for (size_t i = 0; i < mSize; ++i) {
        mData[i] = array[i];
    }
}
template<class T>
Array<T>::Array(size_t size) {
    mSize = size;
    mData = new T[size];
}
template<class T>
Array<T>::Array(const Array& array) {
    mSize = array.mSize;
    copy(array);
}
template<class T>
Array<T>& Array<T>::operator=(const Array& array) {
    delete[] mData;
    mSize = array.mSize;
    copy(array);
    return *this;
}
template<class T>
Array<T>::~Array() {
    delete[] mData;
}
template<class T>
size_t Array<T>::size() const {
    return mSize;
}
template<class T>
T& Array<T>::operator[](size_t i) {
    return mData[i];
}
template<class T>
T const& Array<T>::operator[](size_t i) const {
    return mData[i];
}
template<class T>
Array<T>::Array(Array&& array) {
    mSize = array.mSize;
    mData = array.mData;
    array.mData = nullptr;
    array.mSize = 0;
}
template<class T>
Array<T>& Array<T>::operator=(Array&& array) {
    delete[] mData;
    mSize = array.mSize;
    mData = array.mData;
    array.mData = nullptr;
    array.mSize = 0;
    return *this;
}



