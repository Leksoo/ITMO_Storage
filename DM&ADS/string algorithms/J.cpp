

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

string fileName = "array";
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
void printAllWithSpaces(T vector[], int size, int add = 0) {
    if (fileName.empty()) {
        for (int i = 0; i < size; ++i) {
            cout << vector[i] + add << " ";
        }
    } else {
        for (int i = 0; i < size; ++i) {
            out << vector[i] + add << " ";
        }
    }
}

template<class T>
void printAllLn(T vector[], int size, int add = 0) {
    if (fileName.empty()) {
        for (int i = 0; i < size; ++i) {
            cout << vector[i] + add << "\n";
        }
    } else {
        for (int i = 0; i < size; ++i) {
            out << vector[i] + add << "\n";
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


string s;
int n;
vector<int> suff;
vector<ll> p;
vector<ll> hashes;

ll getHash(int from, int len) {
    return hashes[from + len] - hashes[from] * p[len];
}


int lcp(int i, int j) {
    int l = 1;
    int r = min(n - i, n - j);
    while (l <= r) {
        int m = (r + l) / 2;

        if (getHash(i, m) == getHash(j, m)) {
            l = m + 1;

        } else {
            r = m - 1;
        }
    }
    return r;
}

void solve() {
    s = read<string>();
    n = s.length();
    for (int i = 0; i < n; ++i) {
        suff.push_back(i);
    }
    p.resize(n);
    p[0] = 1;
    for (int i = 1; i < n; ++i) {
        p[i] = p[i - 1] * 43;
    }
    hashes.resize(n + 1);
    for (int j = 1; j <= n; ++j) {
        hashes[j] = hashes[j - 1] * 43 + (int) s[j - 1];
    }
    sort(suff.begin(), suff.end(), [](const int pos1, const int pos2) {
        int common = lcp(pos1, pos2);
        return s[common + pos1] < s[common + pos2];
    });
    int pref[n - 1];
    for (int i = 0; i < n - 1; ++i) {
        pref[i] = lcp(suff[i], suff[i + 1]);
    }
    printAllWithSpaces(suff, 1);
    printSingleLn("");
    printAllWithSpaces(pref, n - 1);


}


int main() {
    solve();
    out.close();
    return 0;
}

