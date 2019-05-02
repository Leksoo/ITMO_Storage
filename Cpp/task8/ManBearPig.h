#pragma once


#include "Man.h"
#include "Bear.h"
#include "Pig.h"


class ManBearPig : public Man, public Bear, public Pig {
public:
    ManBearPig(size_t mId, size_t mHP, size_t mAP, const std::string& race, size_t age);
    const std::string getMClass() const override;
    const std::string getMKind() const override;
};


