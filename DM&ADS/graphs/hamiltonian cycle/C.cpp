#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

string fileName = "";
ofstream out(fileName + ".out");
ifstream in(fileName + ".in");

template<class T>
void printAllWithSpaces(vector<T> &vector, int add = 0) {
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
void printAllLn(vector<T> &vector, int add = 0) {
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
void printSingle(T &el) {
    if (fileName.empty()) {
        cout << el << " ";
    } else {
        out << el << " ";
    }
}

template<class T>
void printSingleLn(T &el) {
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
vector<int> path;

void bs(int el) {
    int l = -1;
    int r = path.size();
    while (l < r - 1) {
        int m = (l + r) / 2;

        cout << 1 << ' ' << el << ' ' << path[m] << '\n';
        cout.flush();
        string resp;
        cin >> resp;
        if (resp == "YES") {
            r = m;
        } else {
            l = m;
        }

    }
    path.insert(path.begin() + r, el);
}

void solve() {
    path.push_back(1);
    for (int i = 2; i <= N; ++i) {
        bs(i);
    }
    printSingle("0");
    printAllWithSpaces(path);

}

void input() {
    N = read<int>();
}


int main() {
    input();
    solve();
    return 0;
}

