//
// Created by lekso on 20.11.2018.
//

#include "Animal.h"

Animal::Animal(size_t mId, size_t mHP, size_t mAP, const std::string& mClass, const std::string& mKind)
        : Unit(mId, mHP, mAP), mClass(mClass), mKind(mKind) {}
const std::string Animal::getMClass() const {
    return mClass;
}
const std::string Animal::getMKind() const {
    return mKind;
}
