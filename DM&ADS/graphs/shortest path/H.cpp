#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>
#include <map>


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
vector<int> costs;
vector<vector<vector<int>>> graph;
vector<vector<pair<int, int>>> crafts;
ll MAX = (ll) INT_MAX * 1000000;


void solve() {
    ll d[N];
    set<pair<ll, int>> set1;
    for (int i = 0; i < N; ++i) {
        d[i] = costs[i];
    }
    for (int j = 0; j < N; ++j) {
        set1.insert(make_pair(costs[j], j));
    }
    while (!set1.empty()) {
        pair<ll, int> m = *set1.begin();
        ll weight = m.first;
        int v = m.second;
        set1.erase(set1.begin());
        for (auto vect : graph[v]) {
            int vPair = vect[2];
            int u = vect[1];
            int weight2=vect[0];
            if (d[u] > weight + d[vPair]) {
                set1.erase(make_pair(d[u], u));
                d[u] = weight + d[vPair];
                set1.insert(make_pair(d[u], u));
            }
        }
    }

    ll res = d[0];
    for (auto v:crafts[0]) {
        res = min(res, d[v.first] + d[v.second]);
    }
    printSingle(res);
}

void input() {
    N = read<int>();
    M = read<int>();
    for (int i = 0; i < N; ++i) {
        costs.push_back(read<int>());
    }
    graph.resize(N);
    crafts.resize(N);
    for (int j = 0; j < M; ++j) {
        int res = read<int>() - 1;
        int source1 = read<int>() - 1;
        int source2 = read<int>() - 1;
        graph[source1].push_back(vector<int>{costs[source1],res,source2});
        graph[source2].push_back(vector<int>{costs[source2],res,source1});
        crafts[res].push_back(make_pair(source1, source2));
    }
}

int main() {
    input();
    solve();
    return 0;
}


