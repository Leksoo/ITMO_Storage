//
// Created by lekso on 20.11.2018.
//

#include "Man.h"

Man::Man(size_t mId, size_t mHP, size_t mAP, const std::string& race, size_t age)
        : Unit(mId, mHP, mAP), mRace(race), mAge(age) {}
const std::string& Man::getRace() const {
    return mRace;
}
size_t Man::getAge() const {
    return mAge;
}
