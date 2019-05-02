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
vector<vector<pair<int,int>>> graph;


void solve() {
    ll d[N];
    bool used[N];
    set<pair<ll,int>> set;
    for (int i = 0; i <N ; ++i) {
        d[i] = (ll)INT_MAX*1000;
    }
    d[0] = 0;
    set.insert(make_pair(0,0));
    while(!set.empty()) {
        pair<ll,int> m = *set.begin();
        set.erase(set.begin());
        for(pair<int,int> u : graph[m.second]){
            if(d[u.second]>d[m.second]+(ll)u.first){
                set.erase(make_pair(d[u.second],u.second));
                d[u.second]=d[m.second]+u.first;
                set.insert(make_pair(d[u.second],u.second));
            }
        }
    }

    for(ll el:d){
        printSingle(el);
    }


}

void input() {
    N = read<int>();
    M = read<int>();
    graph.resize(N);
    for (int i = 0; i <M ; ++i) {
        int v = read<int>()-1;
        int u = read<int>()-1;
        int w = read<int>();
        graph[v].push_back(make_pair(w,u));
        graph[u].push_back(make_pair(w,v));
    }
}


int main() {
    input();
    solve();
    return 0;
}



