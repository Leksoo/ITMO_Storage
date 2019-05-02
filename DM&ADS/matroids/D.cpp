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


using namespace std;

typedef long long ll;

string fileName = "check";
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

int N;
int M;

vector<int> bitStringToSet(const string& str) {
    vector<int> curSet;
    char bitmap[N];
    strcpy(bitmap, str.c_str());
    for (int i = 0; i < N; ++i) {
        if (bitmap[i] == '1') {
            curSet.push_back(i);
        }
    }
    return curSet;
}

string charArrayToString(vector<char> arr) {
    string strSet = "";
    for (char el : arr) {
        strSet += el;
    }
    return strSet;
}

vector<char> createCharArray() {
    vector<char> arr;
    for (int i = 0; i < N; ++i) {
        arr.push_back('0');
    }
    return arr;
}

int countOnes(string str) {
    int res = 0;
    for (char ch:str) {
        if (ch == '1') {
            res++;
        }
    }
    return res;
}


void input() {

    unordered_set<string> sets;
    N = read<int>();
    M = read<int>();
    for (int i = 0; i < M; ++i) {
        int c = read<int>();
        vector<char> bitset = createCharArray();
        for (int j = 0; j < c; ++j) {
            int a = read<int>() - 1;
            bitset[a] = '1';
        }

        sets.insert(charArrayToString(bitset));
    }
    {
        string zeros = charArrayToString(createCharArray());
        if (sets.find(zeros) == sets.end()) {
            printSingle("NO");
            return;
        }

    }

    {
        for (auto it = sets.begin(); it != sets.end(); it++) {
            vector<int> curSet = bitStringToSet(*it);
            for (int i = 1; i < curSet.size(); ++i) {
                for (int j = 0; j < curSet.size(); ++j) {
                    vector<int> subset;
                    for (int k = j; k < j + i && k < curSet.size(); ++k) {
                        subset.push_back(curSet[k]);
                    }
                    if (subset.size() != i) {
                        continue;
                    }
                    vector<char> bitset = createCharArray();
                    for (auto el:subset) {
                        bitset[el] = '1';
                    }
                    if (sets.find(charArrayToString(bitset)) == sets.end()) {
                        printSingle("NO");
                        return;
                    }
                }
            }
        }
    }

    {
        for (auto it = sets.begin(); it != sets.end(); it++) {
            string A(*it);
            for (auto it2 = sets.begin(); it2 != sets.end(); it2++) {
                string B(*it2);
                bool ok = false;
                if (countOnes(A) > countOnes(B)) {
                    for (int i = 0; i < A.size(); ++i) {
                        if (A[i] != '0' && B[i] == '0') {
                            string BandX(B);
                            BandX[i] = '1';
                            if (sets.find(BandX) != sets.end()) {
                                ok = true;
                            }

                        }
                    }
                    if (!ok) {
                        printSingle("NO");
                        return;
                    }
                }

            }
        }
    }
    printSingle("YES");


}


int main() {
    input();
    solve();
    out.close();
    return 0;
}

