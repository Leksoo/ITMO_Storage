#pragma once

#include <string>

class Unit {
public:
    Unit(size_t mId, size_t mHP, size_t mAP);
    size_t getMId() const;
    size_t getMHP() const;
    size_t getMAP() const;
private:
    size_t mId;
    size_t mHP;
    size_t mAP;
};


