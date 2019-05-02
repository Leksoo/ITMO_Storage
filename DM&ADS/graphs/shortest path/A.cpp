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
int p[100][100];


void solve() {
    for (int k = 0; k < N; ++k) {
        for (int u = 0; u < N; ++u) {
            for (int v = 0; v < N; ++v) {
                if(p[u][v] > p[u][k]+p[k][v]){
                    p[u][v] = p[u][k]+p[k][v];
                }
            }
        }
    }
    for (int i = 0; i <N ; ++i) {
        for (int j = 0; j < N; ++j) {
            printSingle(p[i][j]);
        }
        printSingle('\n');
    }
}

void input() {
    N = read<int>();
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; ++j) {
            int w = read<int>();
            p[i][j] = w;
        }
    }
}


int main() {
    input();
    solve();
    return 0;
}



