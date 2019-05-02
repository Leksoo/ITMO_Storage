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

string fileName = "cycles";
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


void solve() {


}


void input() {

    vector<int> weights;
    vector<pair<int, int>> sortedWeights;
    int N = read<int>();
    int M = read<int>();
    unordered_set<bitset<20>> sets;
    for (int k = 0; k < N; ++k) {
        int w = read<int>();
        weights.push_back(w);
        sortedWeights.push_back(make_pair(w, k));
    }
    sort(sortedWeights.begin(), sortedWeights.end(), greater<pair<int, int>>());
    for (int i = 0; i < M; ++i) {
        int c = read<int>();
        int set = 0;
        for (int j = 0; j < c; ++j) {
            int a = read<int>() - 1;
            set |= (1 << a);
        }
        sets.insert(bitset<20>(set));
    }
    bitset<20> curSet(0);
    int sum = 0;
    for (int i = 0; i < N; ++i) {
        bitset<20> tmpSet = curSet | bitset<20>(1 << sortedWeights[i].second);
        bool isSubSet = false;
        for (auto cycle : sets) {
            if((tmpSet & cycle) == cycle) {
                isSubSet = true;
                break;
            }
        }
        if(!isSubSet){
            curSet = tmpSet;
            sum+=sortedWeights[i].first;
        }

    }
    printSingle(sum);


}


int main() {
    input();
    solve();
    out.close();
    return 0;
}

