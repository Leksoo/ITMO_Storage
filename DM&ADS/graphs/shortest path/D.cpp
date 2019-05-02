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
int M;
int K;
int S;
vector<vector<pair<int,int>>> graph;
vector<vector<pair<int,int>>> revGraph;



void solve() {
    ll d[K+1][N];
    for (int i = 0; i <=K ; ++i) {
        for (int j = 0; j <N ; ++j) {
            d[i][j] = (ll)INT_MAX*1000;
        }
    }
    d[0][S] = 0;
    for (int k = 1; k <=K ; ++k) {
        for (int v = 0; v <N ; ++v) {
            for (pair<int, int> pair: revGraph[v]){
                d[k][v] = min(d[k][v],d[k-1][pair.second]+(ll)pair.first);
            }
        }
    }

    for (int l = 0; l < N ; ++l) {
        if(d[K][l]>=(ll)INT_MAX*10){
            printSingleLn("-1");
        } else {
            printSingleLn(d[K][l]);
        }
    }



}

void input() {
    N = read<int>();
    M = read<int>();
    K = read<int>();
    S = read<int>()-1;
    graph.resize(N);
    revGraph.resize(N);
    for (int i = 0; i <M ; ++i) {
        int v = read<int>()-1;
        int u = read<int>()-1;
        int w = read<int>();
        graph[v].push_back(make_pair(w,u));
        revGraph[u].push_back(make_pair(w,v));

    }
}


int main() {
    input();
    solve();
    return 0;
}




