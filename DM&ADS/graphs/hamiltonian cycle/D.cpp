#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

string fileName = "guyaury";
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
        cout << el;
    } else {
        out << el;
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

template <class T>
T read(){
    T tmp;
    if (fileName.empty()) {
        cin >> tmp;
    } else {
        in >> tmp;
    }
    return tmp;
}


int N;
int graph[2001][2001];
vector<int> hamPath;
vector<int> hamCycle;
vector<int> pathPiece;


void solve() {
    hamPath.push_back(0);
    for (int u = 1; u < N; ++u) {

        int v;
        for (v = 0; v < hamPath.size(); ++v) {
            if (!graph[hamPath[v]][u]) {
                break;
            }
        }
        auto it = (hamPath.begin() + v);
        hamPath.insert(it, u);

    }

    //printAllWithSpaces(hamPath);

    int k;
    for (k = hamPath.size() - 1; k >= 2; --k) {
        if (graph[hamPath[k]][hamPath[0]]) {
            break;
        }
    }
    //stopIndex = min(stopIndex, N - 1);
    for (int i = 0; i <= k; ++i) {
        hamCycle.push_back(hamPath[i]);
    }
    for (int i = k + 1; i < hamPath.size(); ++i) {
        pathPiece.push_back(hamPath[i]);
    }

    for (int i = 0; i < pathPiece.size();) {
        int v = pathPiece[i];
        int stopIndex = -5;
        for (int j = 0; j < hamCycle.size(); ++j) {
            int u = hamCycle[j];
            if (graph[v][u]) {
                stopIndex = j;
                break;
            }
        }
        if (stopIndex != -5) {
            auto itCycle = (hamCycle.begin() + stopIndex);
            auto itPiece = (pathPiece.begin() + i);
            hamCycle.insert(itCycle, pathPiece.begin(), itPiece + 1);
            pathPiece.erase(pathPiece.begin(), itPiece + 1);
            i = 0;
        } else {
            i++;
        }
    }

    printAllWithSpaces(hamCycle, 1);


}

void input() {
    in >> N;
    for (int i = 1; i < N; i++) {
        string line;
        in >> line;
        for (int j = 0; j < line.length(); j++) {
            if (line[j] == '1') {
                graph[i][j] = 1;
            } else {
                graph[j][i] = 1;
            }
        }
    }
}


int main() {
    input();
    solve();
    return 0;
}



