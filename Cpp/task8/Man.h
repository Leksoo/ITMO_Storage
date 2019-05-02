#pragma once

#include <string>

#include "Unit.h"

class Man: public virtual Unit {
public:
    Man(size_t mId, size_t mHP, size_t mAP, const std::string& race, size_t age);
    const std::string& getRace() const;
    size_t getAge() const;
private:
    std::string mRace;
    size_t mAge;
};


