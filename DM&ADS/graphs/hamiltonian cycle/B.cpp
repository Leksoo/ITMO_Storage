#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

string fileName = "chvatal";
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
int graph[101][101];
deque<int> q;


void solve() {
    for (int i = 0; i < N; i++) {
        q.push_back(i);
    }
    for (int i = 0; i < N * (N - 1); i++) {
        int f = q.front();
        q.pop_front();
        int s = q.front();
        q.push_front(f);
        if (graph[f][s] == 0) {
            vector<int> queue;
            deque<int> deque1(q);
            while (!deque1.empty()) {
                queue.push_back(deque1.front());
                deque1.pop_front();
            }

            int j = 2;
            while (graph[queue[0]][queue[j]] == 0 ||
                   (j + 1 < N && graph[queue[1]][queue[j + 1]] == 0)) {
                j++;
            }
            if (j + 1 != queue.size())   {
                vector<int> newQueue;
                newQueue.push_back(queue[0]);
                for (int k = j; k >= 1; k--) {
                    newQueue.push_back(queue[k]);
                }
                for (int k = j + 1; k < queue.size(); k++) {
                    newQueue.push_back(queue[k]);
                }
                queue = newQueue;
                q.resize(0);
                for (auto a : queue) {
                    q.push_back(a);
                }
            } else {
                int m = 2;
                while (m < N && graph[queue[0]][queue[m]] == 0) {
                    m++;
                }
                vector<int> newQueue;
                newQueue.push_back(queue[0]);
                for (int k = m; k >= 1; k--) {
                    newQueue.push_back(queue[k]);
                }
                for (int k = m + 1; k < queue.size(); k++) {
                    newQueue.push_back(queue[k]);
                }
                queue = newQueue;
                q.resize(0);
                for (auto a : queue) {
                    q.push_back(a);
                }
            }
        }
        q.push_back(q.front());
        q.pop_front();
    }
    for (int i : q) {
        printSingle((i + 1));
    }

}

void input() {
    N = read<int>();
    for (int i = 1; i < N; i++) {
        string line = read<string>();
        for (int j = 0; j < line.length(); j++) {
            if (line[j] == '1') {
                graph[i][j] = 1;
                graph[j][i] = 1;
            }
        }
    }
}


int main() {
    input();
    solve();
    return 0;
}



