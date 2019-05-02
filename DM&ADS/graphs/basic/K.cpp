#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;
#define ll long long


class Edge {
public:
    int v;
    int u;
    int w;

    Edge(int v, int u, int w) : v(v), u(u), w(w) {}
};

int N, M;
vector<Edge> inputEdges;
vector<bool> visited;
vector<int> sorted;
vector<int> comp;
int num;
ll localRes;


void input() {
    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        int v, u, w;
        cin >> v >> u >> w;
        v--;
        u--;
        inputEdges.push_back(Edge(v, u, w));
    }
}

void checkDfs(int v, vector<Edge> &es) {
    visited[v] = true;
    for (Edge e:es) {
        if (e.v == v && !visited[e.u]) {
            checkDfs(e.u, es);

        }
    }
}


bool check(int v, int n, vector<Edge> &es) {
    visited.clear();
    visited.resize(n, false);
    checkDfs(v, es);
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            return false;
        }
    }
    return true;
}


void sortDfs(int v, vector<Edge> es) {
    visited[v] = true;
    for (Edge e : es) {
        if (e.v == v && !visited[e.u]) {
            sortDfs(e.u, es);

        }
    }
    sorted.push_back(v);
}

void resDfs(int v, vector<Edge> &es) {
    comp[v] = num;
    for (Edge e : es) {
        if (e.u == v) {
            if (comp[e.v] == -1) {
                resDfs(e.v, es);
            }
        }
    }
}

void compSearch(int n, vector<Edge> &es) {
    comp.clear();
    comp.resize(n, -1);
    visited.clear();
    visited.resize(n, false);
    sorted.clear();
    num = 0;
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            sortDfs(i, es);
        }
    }
    for (int i = 1; i <= n; i++) {
        int v = sorted[n - i];
        if (comp[v] == -1) {
            resDfs(v, es);
            num++;
        }
    }
}

void countDfs(int v, vector<Edge> &es) {
    visited[v] = true;
    for (Edge e:es) {
        if (e.v == v && !visited[e.u]) {
            localRes += e.w;
            checkDfs(e.u, es);

        }
    }
}


ll count(int v, int n, vector<Edge> &es) {
    visited.clear();
    visited.resize(n, false);
    localRes = 0;
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            countDfs(v, es);
        }
    }
    return localRes;
}


ll mst(vector<Edge> &edges, int n, int r) {
    ll res = 0;
    vector<int> mins;
    mins.resize(n, numeric_limits<int>::max());
    for (Edge e:edges) {
        mins[e.u] = min(e.w, mins[e.u]);
    }
    for (int i = 0; i <n ; ++i) {
        if(i!=r){
            res+=mins[i];
        }
    }
    vector<Edge> zeros;
    for (Edge e:edges) {
        if (e.w == mins[e.u]) {
            zeros.push_back(Edge(e.v, e.u, 0));
        }
    }
    if (check(r, n, zeros)) {

        return res;
    }
    compSearch(n, zeros);
    vector<int> components = comp;
    vector<Edge> news;
    for (Edge e:edges) {
        if (components[e.u] != components[e.v]) {
            news.push_back(Edge(components[e.v], components[e.u], e.w - mins[e.u]));
        }
    }
    res += mst(news, num, components[r]);
    return res;


}

void solve() {
    if (!check(0, N, inputEdges)) {
        cout << "NO";
        return;
    }
    ll res = mst(inputEdges, N, 0);
    cout << "YES\n";
    cout << res;
}


int main() {
    input();
    solve();
    system("pause");
    return 0;
}
