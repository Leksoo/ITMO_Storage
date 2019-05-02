//
// Created by lekso on 20.11.2018.
//

#include "Unit.h"

Unit::Unit(size_t mId, size_t mHP, size_t mAP) : mId(mId), mHP(mHP), mAP(mAP) {}

size_t Unit::getMId() const {
    return mId;
}
size_t Unit::getMHP() const {
    return mHP;
}
size_t Unit::getMAP() const {
    return mAP;
}
