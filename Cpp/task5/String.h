
#ifndef TASK5_STRING_H
#define TASK5_STRING_H

#include <cstring>

class String {
public:
    explicit String(const char *str = "");

    String(size_t n, char symbol);

    ~String();

    String(const String &str);

    String &operator=(const String &str);

    size_t size() const;

    const char *getStr() const;

    void append(String &str);

private:
    size_t mSize;
    char *mStr;

};


#endif //TASK5_STRING_H
