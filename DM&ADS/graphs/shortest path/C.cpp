#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>

using namespace std;

typedef long long ll;

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
vector<vector<int>> graph;


void solve() {
    vector<int> negCycle;
    int d[N];
    int p[N];
    for (int i = 0; i < N; ++i) {
        d[i] = 1000000;
        p[i] = -1;
    }
    d[0] = 0;
    for (int i = 0; i < N; ++i) {
        for (auto edge: graph) {
            int from = edge[0];
            int to = edge[1];
            int w = edge[2];
            if (d[to] > d[from] + w) {
                d[to] = d[from] + w;
                p[to] = from;
            }
        }
    }
    for (auto edge: graph) {
        int from = edge[0];
        int to = edge[1];
        int w = edge[2];
        if (d[to] > d[from] + w) {
            for (int i = 0; i < N; ++i) {
                to=p[to];
            }
            from=to;
            while (from!=p[to]){
                negCycle.push_back(to);
                to=p[to];
            }
            negCycle.push_back(to);
            break;
        }
    }
    if(negCycle.empty()){
        printSingle("NO");
        return;
    }
    printSingleLn("YES");
    printSingleLn(negCycle.size());
    for (int j = negCycle.size()-1; j >=0 ; --j) {
        printSingle(negCycle[j]+1);
    }


}

void input() {
    N = read<int>();
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            int w = read<int>();
            if (w == 100000) {
                continue;
            }
            if(i==j && w >=0){
                continue;
            }
            vector<int> tmp = {i, j, w};
            graph.push_back(tmp);
        }
    }
}


int main() {
    input();
    solve();
    return 0;
}

