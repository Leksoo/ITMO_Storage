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
int S;
vector<vector<pair<ll, int>>> graph;
vector<vector<pair<ll, int>>> revGraph;
vector<vector<ll>> edges;

vector<bool> reachables;
vector<bool> negCycleReachables;


void reachDfs(int v) {
    reachables[v] = true;
    for (auto pair:graph[v]) {
        if (!reachables[pair.second]) {
            reachDfs(pair.second);
        }
    };
}

void negReachDfs(int v) {
    negCycleReachables[v] = true;
    for (auto pair:graph[v]) {
        if (!negCycleReachables[pair.second]) {
            negReachDfs(pair.second);
        }
    };
}

void solve() {
    //check reachables
    reachables.resize(N, false);
    reachDfs(S);
    vector<vector<ll>> tmp;
    for (auto pair:edges) {
        if (reachables[pair[0]]) {
            tmp.push_back(pair);
        }
    }
    edges = tmp;
    //check reachable from negative cycle
    vector<int> negCycle;
    ll d[N];
    int p[N];
    for (int i = 0; i < N; ++i) {
        d[i] = (ll) 100000 * 100000 * 100000 * 1000;
        p[i] = -1;
    }
    d[S] = 0;
    for (int i = 0; i < N; ++i) {
        for (auto edge: edges) {
            int from = (int) edge[0];
            int to = (int) edge[1];
            ll w = edge[2];
            if (d[to] > d[from] + w) {
                d[to] = d[from] + w;
                p[to] = from;
            }
        }
    }
    for (auto edge: edges) {
        int from = (int) edge[0];
        int to = (int) edge[1];
        ll w = edge[2];
        if (d[to] > d[from] + w) {
            negCycle.push_back(to);
        }
    }
    negCycleReachables.resize(N, false);
    for (auto v : negCycle) {
        negReachDfs(v);
    }
    for (int i = 0; i < N; ++i) {
        if (!reachables[i]) {
            printSingleLn("*");
            continue;
        }
        if (negCycleReachables[i]) {
            printSingleLn("-");
            continue;
        }
        printSingleLn<ll>(d[i]);
    }


}

void input() {
    N = read<int>();
    M = read<int>();
    S = read<int>() - 1;
    graph.resize(N);
    revGraph.resize(N);
    for (int i = 0; i < M; ++i) {
        int v = read<int>() - 1;
        int u = read<int>() - 1;
        ll w = read<ll>();
        if (v == u && w >= 0) {
            continue;
        }
        graph[v].push_back(make_pair(w, u));
        revGraph[u].push_back(make_pair(w, v));
        vector<ll> tmp = {v, u, w};
        edges.push_back(tmp);
    }
}


int main() {
    input();
    solve();
    return 0;
}




