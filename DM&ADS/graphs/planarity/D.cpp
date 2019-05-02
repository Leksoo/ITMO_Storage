#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>
#include <map>
#include <cmath>
#include <bitset>
#include <unordered_set>
#include <regex>


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

bool checkK5(string s) {
    vector<vector<int>> graph;
    graph.resize(6);
    int m = 0;
    if (s[0] == '1') {
        m++;
        graph[0].push_back(1);
        graph[1].push_back(0);
    }
    if (s[1] == '1') {
        m++;
        graph[0].push_back(2);
        graph[2].push_back(0);
    }
    if (s[2] == '1') {
        m++;
        graph[1].push_back(2);
        graph[2].push_back(1);
    }
    for (int i = 0; i < 3; ++i) {
        if (s[3 + i] == '1') {
            m++;
            graph[3].push_back(i);
            graph[i].push_back(3);
        }
    }
    for (int i = 0; i < 4; ++i) {
        if (s[6 + i] == '1') {
            m++;
            graph[4].push_back(i);
            graph[i].push_back(4);
        }
    }
    for (int i = 0; i < 5; ++i) {
        if (s[10 + i] == '1') {
            m++;
            graph[5].push_back(i);
            graph[i].push_back(5);
        }
    }


    for (int i = 0; i < 6; i++) {
        if (m - graph[i].size() == 10)
            return true;
        int count = 0;
        bool used[6];
        for (int j = 0; j < 6; ++j) {
            used[j] = false;
        }
        if (graph[i].size() >= 2) {
            for (int v : graph[i]) {
                for (int u : graph[v])
                    if (!used[u] && find(graph[i].begin(), graph[i].end(), u) != graph[i].end() && u != i) {
                        count++;
                    }
                used[v] = true;
            }
        }
        if (count == 0 && m - graph[i].size() == 9 && graph[i].size() == 2
            || graph[i].size() == count + 1 && m - graph[i].size() == 9 && graph[i].size() > 2) {
            return true;
        }
    }
    return false;

}

void solve() {
    vector<string> k33;
    for (int j = 0; j < (1 << 15); ++j) {
        string s = bitset<15>(j).to_string();
        //string s = "000111111011100";
        vector<vector<int>> graph;
        graph.resize(6);
        if (s[0] == '1') {
            graph[0].push_back(1);
            graph[1].push_back(0);
        }
        if (s[1] == '1') {
            graph[0].push_back(2);
            graph[2].push_back(0);
        }
        if (s[2] == '1') {
            graph[1].push_back(2);
            graph[2].push_back(1);
        }
        for (int i = 0; i < 3; ++i) {
            if (s[3 + i] == '1') {
                graph[3].push_back(i);
                graph[i].push_back(3);
            }
        }
        for (int i = 0; i < 4; ++i) {
            if (s[6 + i] == '1') {
                graph[4].push_back(i);
                graph[i].push_back(4);
            }
        }
        for (int i = 0; i < 5; ++i) {
            if (s[10 + i] == '1') {
                graph[5].push_back(i);
                graph[i].push_back(5);
            }
        }

        if (graph[0].size() == 3) {
            bool res = true;
            int a = graph[0][0];
            int b = graph[0][1];
            int c = graph[0][2];
            vector<int> arr = {a, b, c};
            sort(arr.begin(), arr.end());
            for (int i = 0; i < graph.size(); ++i) {
                if (graph[i].size() != 3) {
                    res = false;
                    break;
                }
                if (i != a && i != b && i != c) {
                    vector<int> arr2;
                    for (int v:graph[i]) {
                        arr2.push_back(v);
                    }
                    sort(arr2.begin(), arr2.end());
                    for (int k = 0; k < 3; ++k) {
                        if (arr[k] != arr2[k]) {
                            res = false;
                            break;
                        }
                    }
                }
            }
            if (res) {
                k33.push_back(s);
            }

        }

    }

    string input;
    getline(cin, input);
    int t = stoi(input);
    for (int i = 0; i < t; ++i) {
        getline(cin, input);
        int m = input.length();
        int n = (int) (sqrt(8 * m + 1) + 1) / 2;
        if (n <= 4 || (n == 5 && input != "1111111111")) {
            printSingleLn("YES");
            continue;
        }
        if (n == 5 && input == "1111111111") {
            printSingleLn("NO");
            continue;
        }

        if (n == 6 && input.substr(0, 10) == "1111111111") {
            printSingleLn("NO");
            continue;
        }

        if (input.substr(2, 1) == "1" && input.substr(4, 2) == "11" && input.substr(7, 3) == "111"
            && input.substr(11, 4) == "1111") {
            printSingleLn("NO");
            continue;
        }

        if (input.substr(1, 1) == "1" && input.substr(3, 1) == "1" && input.substr(5, 2) == "11"
            && input.substr(8, 3) == "111" && input.substr(12, 3) == "111") {
            printSingleLn("NO");
            continue;
        }
        if (input.substr(0, 1) == "1" && input.substr(3, 2) == "11" && input.substr(6, 2) == "11"
            && input.substr(9, 3) == "111" && input.substr(13, 2) == "11") {
            printSingleLn("NO");
            continue;
        }
        if (input.substr(0, 3) == "111" && input.substr(6, 3) == "111" && input.substr(10, 3) == "111"
            && input.substr(14, 1) == "1") {
            printSingleLn("NO");
            continue;
        }
        if (input.substr(0, 6) == "111111" && input.substr(10, 4) == "1111") {
            printSingleLn("NO");
            continue;
        }
        bool a = false;
        for (string templ:k33) {
            bool isK33 = true;
            for (int j = 0; j < 15; ++j) {
                if (templ[j] == '1') {
                    if (templ[j] == input[j]) {

                    } else {
                        isK33 = false;
                        break;
                    }
                }
            }
            if (isK33) {
                printSingleLn("NO");
                a = true;
                break;
            }
        }
        if (a) {
            continue;
        }
        if (checkK5(input)) {
            printSingleLn("NO");
        } else {
            printSingleLn("YES");
        }
    }


}

void input() {

}


int main() {
    input();
    solve();
    return 0;
}
