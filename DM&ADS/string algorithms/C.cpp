#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>
#include <map>
#include <unordered_set>
#include <cstring>
#include <bitset>


using namespace std;

typedef long long ll;

string fileName = "";
ofstream out(fileName + ".out");
ifstream in(fileName + ".in");


template<class T>
void printAllWithSpaces(vector<T>& vector, int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << " ";
        }
    } else {
        for (auto el : vector) {
            out << el + add << " ";
        }
    }
}

template<class T>
void printAllLn(vector<T>& vector, int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << '\n';
        }
    } else {
        for (auto el : vector) {
            out << el + add << '\n';
        }
    }
}

template<class T>
void printAllWithSpaces(T vector[], int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << " ";
        }
    } else {
        for (auto el : vector) {
            out << el + add << " ";
        }
    }
}

template<class T>
void printAllLn(T vector[], int add = 0) {
    if (fileName.empty()) {
        for (auto el : vector) {
            cout << el + add << '\n';
        }
    } else {
        for (auto el : vector) {
            out << el + add << '\n';
        }
    }
}

template<class T>
void printSingle(T el) {
    if (fileName.empty()) {
        cout << el << " ";
    } else {
        out << el << " ";
    }
}

template<class T>
void printSingleLn(T el) {
    if (fileName.empty()) {
        cout << el << '\n';
    } else {
        out << el << '\n';
    }
}

template<class T>
T read() {
    T tmp;
    if (fileName.empty()) {
        cin >> tmp;
    } else {
        in >> tmp;
    }
    return tmp;
}

int N;
int M;

void solve() {
    string input;
    cin >> input;
    N = input.length();
    vector<int> zFun(N);
    zFun[0] = N;
    int l = 0, r = 0;
    for (int i = 1; i < input.length(); ++i) {
        zFun[i] = min(zFun[i - l], r - i);
        if (zFun[i] < 0) {
            zFun[i] = 0;
        }
        while (i + zFun[i] < N && input[zFun[i]] == input[i + zFun[i]]) {
            zFun[i]++;
        }
        int newR = i + zFun[i];
        if ( newR > r) {
            l = i;
            r = newR;
        }
    }
    for (int j = 1; j < zFun.size(); ++j) {
        printSingle(zFun[j]);
    }
}


int main() {
    solve();
    out.close();
    return 0;
}

