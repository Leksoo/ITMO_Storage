#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string fileName = "";
ofstream out(fileName + ".out");
ifstream in(fileName + ".in");

class Edge {
public:
    int v;
    int u;
    int i;

    Edge(int v, int u, int i) : v(v), u(u), i(i) {}
};

int n, m;
unsigned int MAX_N = 20000;
unsigned int MAX_M = 200000;
vector<vector<Edge>> graph(MAX_N);
vector<bool> visited(MAX_N);
vector<int> up(MAX_N);
vector<int> tin(MAX_N);
vector<Edge> stack;
vector<int> colors(MAX_M);
int time = 0;
int color = 0;



void input() {
    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int v, u;
        cin >> v >> u;
        v--;
        u--;
        graph[v].push_back(Edge(v, u, i));
        graph[u].push_back(Edge(u, v, i));
    }
}


void componentDfs(int v, int p) {
    visited[v] = true;
    tin[v] = time;
    up[v] = time;
    time++;
    for (Edge edge : graph[v]) {
        int u = edge.u;
        if (p == u) {
            continue;
        }
        if (!visited[u]) {
            stack.push_back(edge);
            componentDfs(u, v);
            if (up[u] >= tin[v]) {
                color++;
                while (stack.back().i != edge.i) {
                    for (Edge edge1 : graph[stack.back().v]) {
                        if (edge1.u == stack.back().u)
                            colors[edge1.i] = color;
                    }
                    stack.pop_back();
                }
                for (Edge edge1 : graph[v]) {
                    if (edge1.u == u)
                        colors[edge1.i] = color;
                }
                stack.pop_back();
            }
        }
        else if (tin[u] < tin[v]) {
            stack.push_back(edge);

        }
        up[v] = min(up[v], up[u]);

    }
}




void solve() {
    for (size_t i = 0; i < n; i++)
    {
        if (!visited[i]) {
            time = 0;
            componentDfs(i, -1);
        }
    }

    cout << color << "\n";
    for (size_t i = 0; i < m; i++)
    {
        cout << colors[i] << " ";
    }
    
}



int main() {
    input();
    solve();
   // system("pause");
    return 0;
}