
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

string fileName = "search4";
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

struct Node {
    Node* kids[26]{};
    Node* links[26]{};
    Node* parent;
    Node* suffLink;
    char ch;
    vector<int> patternIndexes;
    bool checked;

    Node() {
        for (auto& kid : kids) {
            kid = nullptr;
        }
        for (auto& link : links) {
            link = nullptr;
        }
        checked = false;
        parent = nullptr;
        suffLink = nullptr;
        ch = 0;
    }


};

vector<bool> res;

Node root;

Node* getLink(Node* node, char ch);


Node* getSuffLink(Node* node) {
    if (!node->suffLink) {
        if (node == &root || node->parent == &root) {
            node->suffLink = &root;

        } else {
            node->suffLink = getLink(getSuffLink(node->parent), node->ch);
        }
    }

    return node->suffLink;
}

Node* getLink(Node* node, char ch) {
    if (!node->links[ch - 'a']) {
        if (node->kids[ch - 'a']) {
            node->links[ch - 'a'] = node->kids[ch - 'a'];
        } else if (node == &root) {
            node->links[ch - 'a'] = &root;
        } else {
            node->links[ch - 'a'] = getLink(getSuffLink(node), ch);
        }
    }
    getSuffLink(node->links[ch - 'a']);
    if (!node->links[ch - 'a']->suffLink->checked) {
        for (auto i:node->links[ch - 'a']->suffLink->patternIndexes) {
            res[i] = true;
        }
        node->links[ch - 'a']->suffLink->checked = true;
    }
    return node->links[ch - 'a'];
}


void solve() {
    int N;
    vector<string> s;
    string t;
    N = read<int>();
    for (int i = 0; i < N; ++i) {
        s.push_back(read<string>());
    }
    t = read<string>();
    res.resize(N);
    for (int i = 0; i < N; ++i) {
        string str = s[i];
        Node* node = &root;
        for (int j = 0; j < str.length(); ++j) {
            char ch = str[j];
            if (!node->kids[ch - 'a']) {
                node->kids[ch - 'a'] = new Node();
                node->kids[ch - 'a']->parent = node;
                node->kids[ch - 'a']->ch = ch;
            }
            node = node->kids[ch - 'a'];
        }
        node->patternIndexes.push_back(i);
    }
    Node* node = &root;
    for (int i = 0; i < t.length(); ++i) {
        char ch = t[i];
        node = getLink(node, ch);
        if (!node->checked) {
            for (int k:node->patternIndexes) {
                res[k] = true;
            }
            node->checked = true;
        }

    }
    for (auto i:res) {
        if (i) {
            printSingle("YES");
        } else {
            printSingle("NO");
        }
    }

}


int main() {
    solve();
    out.close();
    return 0;
}