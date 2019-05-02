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

string fileName = "";
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

template< class T>
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
    string pattern;
    cin>> pattern;
    string input;
    cin >> input;
    input = pattern + "%" + input;
    int N = input.length();
    vector<int> prefFun(N);
    prefFun[0] = 0;
    for (int i = 1; i < input.length(); ++i) {
        int k = prefFun[i - 1];
        while (k > 0 && input[i] != input[k]) {
            k = prefFun[k - 1];
        }
        if (input[i] == input[k]) {
            prefFun[i] = k + 1;
        } else {
            prefFun[i] = k;
        }
    }
    vector<int> ans;
    for (int j = 0; j <N ; ++j) {
        if(prefFun[j]== pattern.length()){
            ans.push_back(j-(pattern.length()+1)-(pattern.length()-1));
        }
    }
    printSingleLn(ans.size());
    printAllWithSpaces(ans,1);
}


int main() {
    solve();
    out.close();
    return 0;
}



