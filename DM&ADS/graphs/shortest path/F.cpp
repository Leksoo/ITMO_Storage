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
int A;
int B;
int C;
vector<vector<pair<ll, int>>> graph;
ll MAX = (ll) INT_MAX * 300000;
ll res = MAX;

ll algo(int start, int oth1, int oth2) {
    ll d[N];
    set<pair<ll, int>> set1;
    for (int i = 0; i < N; ++i) {
        d[i] = MAX;
    }
    d[start] = 0;
    set1.insert(make_pair(0, start));
    while (!set1.empty()) {
        pair<ll, int> m = *set1.begin();
        set1.erase(set1.begin());
        for (auto u : graph[m.second]) {
            if (d[u.second] > d[m.second] + (ll) u.first) {
                set1.erase(make_pair(d[u.second], u.second));
                d[u.second] = d[m.second] + u.first;
                set1.insert(make_pair(d[u.second], u.second));
            }
        }
    }
    ll AB = d[oth1];
    ll AC = d[oth2];
    if (AB == MAX || AC == MAX) {
        return -1;
    }

    ll d2[N];
    set<pair<ll, int>> set2;
    for (int i = 0; i < N; ++i) {
        d2[i] = MAX;
    }
    if (AB < AC) {
        d2[oth1] = 0;
        set2.insert(make_pair(0, oth1));
    } else {
        d2[oth2] = 0;
        set2.insert(make_pair(0, oth2));
    }
    while (!set2.empty()) {
        pair<ll, int> m = *set2.begin();
        set2.erase(set2.begin());
        for (auto u : graph[m.second]) {
            if (d2[u.second] > d2[m.second] + (ll) u.first) {
                set2.erase(make_pair(d2[u.second], u.second));
                d2[u.second] = d2[m.second] + u.first;
                set2.insert(make_pair(d2[u.second], u.second));
            }
        }
    }
    ll BC;
    if (AB < AC) {
        BC = d2[oth2];
        return AB + BC;
    } else {
        BC = d2[oth1];
        return AC + BC;
    }

}

void solve() {
    res = algo(A,B,C);
    if(res==-1){
        printSingle("-1");
        return;
    }
    ll tmp = algo(B,A,C);
    if(tmp<res){
        res = tmp;
    }
    tmp = algo(C,A,B);
    if(tmp<res){
        res = tmp;
    }
    printSingle(res);

}

void input() {
    N = read<int>();
    M = read<int>();
    graph.resize(N);
    for (int i = 0; i < M; ++i) {
        int v = read<int>() - 1;
        int u = read<int>() - 1;
        ll w = read<ll>();
        graph[v].push_back(make_pair(w, u));
        graph[u].push_back(make_pair(w, v));
    }
    A = read<int>() - 1;
    B = read<int>() - 1;
    C = read<int>() - 1;

}


int main() {
    input();
    solve();
    return 0;
}


