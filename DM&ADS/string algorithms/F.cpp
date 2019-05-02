

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

int K;
vector<string> str;
vector<ll> p;
pair<int, int> ans;
vector<vector<ll>> hashes;


ll getHash(string& s, int from, int len, vector<ll>& hashes) {
    return hashes[from + len + 1] - hashes[from] * p[len + 1];
}

bool checkHash(int len) {
    vector<unordered_set<ll>> hashS19;
    hashS19.resize(K);
    for (int k = 1; k < K; ++k) {
        for (int j = 0; j <= str[k].length(); ++j) {
            if (j + len > str[k].length()) {
                break;
            }
            ll hash = getHash(str[k], j, len - 1, hashes[k]);
            hashS19[k].insert(hash);
        }
    }
    for (int i = 0; i <= str[0].length(); ++i) {
        if (i + len > str[0].length()) {
            break;
        }
        ll hash = getHash(str[0], i, len - 1, hashes[0]);
        bool found = true;
        for (int k = 1; k <K ; ++k) {
            if (hashS19[k].find(hash) == hashS19[k].end()) {
                found = false;
                break;
            }
        }
        if(found){
            ans = make_pair(i, len);
            return true;
        }

    }
    return false;
}


void solve() {
    K = read<int>();
    int maxN = 0;
    int minN = INT32_MAX;
    for (int i = 0; i < K; ++i) {
        string s = read<string>();
        if (s.length() > maxN) {
            maxN = s.length();
        }
        if (s.length() < minN) {
            minN = s.length();
        }
        str.push_back(s);
    }

    p.resize(maxN);
    p[0] = 1;
    for (int i = 1; i < maxN; ++i) {
        p[i] = p[i - 1] * 43;
    }
    hashes.resize(K);
    for (int i = 0; i < K; ++i) {
        string s = str[i];
        int n = s.length();
        hashes[i].resize(n + 1);
        for (int j = 1; j <= n; ++j) {
            hashes[i][j] = hashes[i][j - 1] * 43 + (int) s[j - 1];
        }
    }


    int l = -1;
    int r = minN + 1;
    while (l < r - 1) {
        int m = (l + r) / 2;
        if (checkHash(m)) {
            l = m;
        } else {
            r = m;
        }
    }
    if (r == 0) {
        cout << "";
    } else {
        cout << str[0].substr(ans.first, ans.second);
    }


}


int main() {
    solve();
    out.close();
    return 0;
}

