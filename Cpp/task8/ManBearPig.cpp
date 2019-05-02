//
// Created by lekso on 20.11.2018.
//

#include "ManBearPig.h"

ManBearPig::ManBearPig(size_t mId, size_t mHP, size_t mAP, const std::string& race, size_t age)
        : Man(mId, mHP, mAP, race, age), Bear(mId, mHP, mAP), Pig(mId, mHP, mAP), Unit(mId, mHP, mAP) {}
const std::string ManBearPig::getMClass() const {
    return Bear::getMClass() + "+" + Pig::getMClass();
}
const std::string ManBearPig::getMKind() const {
    return Bear::getMKind() + "+" + Pig::getMKind();

}
