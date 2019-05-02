//
// Created by lekso on 18.12.2018.
//

#include "BadFromString.h"

BadFromString::BadFromString(const std::string& message) : runtime_error(message) {
}
BadFromString::BadFromString(const char* message) : runtime_error(message) {
}

BadFromString::~BadFromString() = default;
