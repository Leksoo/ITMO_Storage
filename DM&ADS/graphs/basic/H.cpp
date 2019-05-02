#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string fileName = "avia";
ofstream out(fileName + ".out");
ifstream in(fileName + ".in");

class Edge {
public:
    int v;
    int u;
    int w;

    Edge(int v, int u, int w) : v(v), u(u), w(w) {}
};

int n, m;
unsigned int MAX_N = 1000;
vector<vector<Edge>> graph(MAX_N);
vector<vector<Edge>> binGraph(MAX_N);
vector<vector<Edge>> binRevGraph(MAX_N);
vector<bool> visited(MAX_N);
vector<bool> visited2(MAX_N);
int color;
vector<int> sorted;




void input() {
    in >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; j++) {
            int w;
            in >> w;
            if (i == j) {
                continue;
            }
            graph[i].push_back(Edge(i, j, w));
        }
    }
}

void sortDfs(int v) {
    visited[v] = true;
    for (Edge e : binGraph[v]) {
        if (!visited[e.u]) {
            sortDfs(e.u);
        }
    }
    sorted.push_back(v);
}

void resDfs(int v) {
    visited2[v] = true;
    for (Edge e : binRevGraph[v]) {
        if (!visited2[e.u]) {
            resDfs(e.u);
        }
    }
}



void solve() {
    int l = 0;
    int r = 1000010000;
    while (l < r) {
        int m = (l + r) / 2;
        for (int j = 0; j < n ; ++j) {
            binGraph[j].clear();
            binRevGraph[j].clear();
        }
        for (int v = 0; v < n; v++)
        {

            for (Edge e : graph[v])
            {
                if (e.w <= m) {
                    binGraph[v].push_back(e);
                    binRevGraph[e.u].push_back(Edge(e.u, v, e.w));
                }
            }
        }

        visited.assign(MAX_N, 0);
        sorted.clear();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                sortDfs(i);
            }
        }


        visited2.assign(MAX_N, 0);
        color = 0;
        for (int i = 1; i <= n; i++) {
            int v = sorted[n - i];
            if (!visited2[v]) {
                resDfs(v);
                color++;
            }
        }

        if (color == 1) {
            r = m;
        }
        else {

            l = m + 1;
        }

    }
    out << l;

}



int main() {
    input();
    solve();
    // system("pause");
    return 0;
}
