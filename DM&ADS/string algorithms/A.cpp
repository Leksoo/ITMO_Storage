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
    ll p[N];
    p[0] = 1;
    for (int i = 1; i < N; ++i) {
        p[i] = p[i-1] * 43;
    }
    ll hashes[N];
    hashes[0] = input[0];
    for (int i = 1; i < N; ++i) {
        hashes[i] = hashes[i - 1] + p[i] * (int) input[i];
    }
    cin >> M;
    ll max = (ll) 1 << 32;
    for (int i = 0; i < M; ++i) {
        int a, b, c, d;
        cin >> a >> b >> c >> d;
        if (b - a != d - c) {
            printSingleLn("No");
            continue;
        }
        int len = b - a + 1;
        a--;
        b--;
        c--;
        d--;
        ll abHash = (hashes[b] - (a == 0 ? 0 : hashes[a - 1]));
        ll cdHash = (hashes[d] - (c == 0 ? 0 : hashes[c - 1]));
        if (abHash * p[c] ==
            cdHash * p[a]) {
            printSingleLn("Yes");

        } else {
            printSingleLn("No");
        }

    }
}


int main() {
    solve();
    out.close();
    return 0;
}

