#pragma once

#include <stdexcept>
#include <string>

class BadFromString : public std::runtime_error {
public:
    explicit BadFromString(const std::string&);
    explicit BadFromString(const char*);
    ~BadFromString();

};


