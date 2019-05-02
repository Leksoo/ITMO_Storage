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

string fileName = "common";
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


string s1, s2;
int N1;
int N2;
vector<ll> p;

vector<ll> hashS1;
vector<ll> hashS2;


vector<pair<int, int>> res;

ll getHash(string& s, int from, int len, vector<ll>& hashes) {
    return hashes[from+len + 1] - hashes[from] * p[len+ 1];
}

bool checkHash(int len) {
    unordered_set<ll> hashesS1;
    bool found = false;
    vector<pair<int, int>> localres;
    for (int i = 0; i <= s1.length(); ++i) {
        if (i + len > s1.length()) {
            break;
        }
        ll hash = getHash(s1, i, len-1, hashS1);
        hashesS1.insert(hash);
    }
    for (int j = 0; j <= s2.length(); ++j) {
        if (j + len > s2.length()) {
            break;
        }
        ll hash = getHash(s2, j, len-1, hashS2);
        if (hashesS1.find(hash) != hashesS1.end()) {
            auto ans = make_pair(j, len);
            found = true;
            localres.push_back(ans);
        }
    }
    if (found) {
        res = localres;
        return true;
    }
    return false;
}


void solve() {


    s1 = read<string>();
    s2 = read<string>();
    N1 = s1.length();
    N2 = s2.length();
    int maxN = max(N1, N2);
    int minN = min(N1, N2);
    p.resize(maxN);
    p[0] = 1;
    for (int i = 1; i < maxN; ++i) {
        p[i] = p[i - 1] * 43;
    }

    hashS1.resize(N1+1);
    for (int i = 1; i <= N1; ++i) {
        hashS1[i] = hashS1[i-1] * 43 + (int) s1[i-1];
    }

    hashS2.resize(N2+1);
    for (int i = 1; i <= N2; ++i) {
        hashS2[i] = hashS2[i-1] * 43 + (int) s2[i-1];
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
        out << "";
    } else {
        vector<string> ans;
        for (auto pair: res) {
            ans.push_back(s2.substr(pair.first, pair.second));
        }
        sort(ans.begin(), ans.end());
        out << ans[0];
    }


}


int main() {
    solve();
    out.close();
    return
}