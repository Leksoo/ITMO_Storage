#pragma once

#include <string>

#include "Unit.h"

class Animal : public virtual Unit {
public:
    Animal(size_t mId, size_t mHP, size_t mAP, const std::string& mClass, const std::string& mKind);
    virtual const std::string getMClass() const;
    virtual const std::string getMKind() const;
private:
    std::string mClass;
    std::string mKind;
};


