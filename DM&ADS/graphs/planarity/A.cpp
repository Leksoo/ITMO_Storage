#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>
#include <map>
#include <unordered_set>


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


struct Vertex {
    double x;
    double y;

    Vertex(double x, double y) : x(x), y(y) {}

    Vertex() = default;

    bool operator==(const Vertex& rhs) const {
        return x == rhs.x &&
               y == rhs.y;
    }
    bool operator<(const Vertex& rhs) const {
        if (x < rhs.x)
            return true;
        if (rhs.x < x)
            return false;
        return y < rhs.y;
    }

};

struct Edge {
    int ind;
    int v1;
    int v2;
    bool added;
    double centreX;
    double centreY;
    Edge(int v1, int v2, int ind) : v1(v1), v2(v2), ind(ind) {
        added = false;
    }


};

int N;
int M;
vector<int> hamCycle;
vector<vector<int>> graph;

map<Vertex, int> pointToIndex;
map<int, Vertex> indexToPoint;

vector<Edge> edges;

vector<int> sides;
bool canSplit = true;

void splitInto2Parts(int edge, int side) {
    sides[edge] = side;
    double Xv2 = indexToPoint[edges[edge].v2].x;
    double Xv1 = indexToPoint[edges[edge].v1].x;
    for (int edge2 = 0; edge2 < M; ++edge2) {
        double otherXv2 = indexToPoint[edges[edge2].v2].x;
        double otherXv1 = indexToPoint[edges[edge2].v1].x;
        if (Xv2 > otherXv2 && Xv1 > otherXv1 && Xv1 < otherXv2 ||
            Xv1 < otherXv1 && Xv2 > otherXv1 && Xv2 < otherXv2) {
            if (sides[edge2] != 0) {
                if (sides[edge] == sides[edge2]) {
                    canSplit = false;
                    return;
                }
            } else {
                splitInto2Parts(edge2, side == 1 ? -1 : 1);
            }
        }
    }

}

void solve() {

//    for (int i = 0; i < hamCycle.size(); ++i) {
//        int v;
//        int u;
//        if (i == hamCycle.size() - 1) {
//            v = hamCycle[i];
//            u = hamCycle[0];
//        } else {
//            v = hamCycle[i];
//            u = hamCycle[i + 1];
//        }
//
//        for (int j = 0; j < edges.size(); ++j) {
//            if(edges[j].added){
//                continue;
//            }
//            if ((edges[j].v1 == v && edges[j].v2 == u) || (edges[j].v2 == v && edges[j].v1 == u)) {
//                edges[j].added = true;
//                double x2 = max(indexToPoint[v].x, indexToPoint[u].x);
//                double x1 = min(indexToPoint[v].x, indexToPoint[u].x);
//                edges[j].centreX = x1 + (x2 - x1) / 2;
//                edges[j].centreY = (x2 - x1) / 2;
//                upPart.push_back(j);
//                break;
//            }
//        }
//    }

    for (auto& edge:edges) {
        double Xv2 = indexToPoint[edge.v2].x;
        double Xv1 = indexToPoint[edge.v1].x;
        if (Xv1 > Xv2) {
            swap(edge.v1, edge.v2);
        }
    }

    sides.resize(M, 0);
    for (int i = 0; i < M; ++i) {
        if (sides[i] == 0) {
            splitInto2Parts(i, 1);
        }
    }

    if (!canSplit) {
        printSingleLn("NO");
        return;
    }
    printSingleLn("YES");
    for (int k = 0; k < hamCycle.size(); ++k) {
        printSingle(indexToPoint[k].x);
        printSingle(indexToPoint[k].y);
    }
    printSingleLn("");
    for (int j = 0; j < M; ++j) {
        printSingle(indexToPoint[edges[j].v1].x + (indexToPoint[edges[j].v2].x - indexToPoint[edges[j].v1].x) / 2);
        printSingleLn((indexToPoint[edges[j].v2].x - indexToPoint[edges[j].v1].x) / 2 * sides[j]);
    }


}

void input() {
    N = read<int>();
    M = read<int>();
    graph.resize(N);
    for (int i = 0; i < M; ++i) {
        int v = read<int>() - 1;
        int u = read<int>() - 1;
        graph[v].push_back(u);
        graph[u].push_back(v);
        edges.push_back(Edge(v, u, i));
    }
    for (int j = 0; j < N; ++j) {
        int v = read<int>() - 1;
        hamCycle.push_back(v);
        Vertex p(j * 2, 0);
        pointToIndex[p] = v;
        indexToPoint[v] = p;
    }


}


int main() {
    input();
    solve();
    return 0;
}
