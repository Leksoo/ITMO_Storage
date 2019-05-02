#include <iostream>
#include <vector>
#include <string>
#include <deque>

using namespace std;
#define ll long long


struct Edge {
    int from;
    int to;
    ll cap;
    ll flow = 0;
    ll cost;
    int revInd = 0;


    Edge(int from, int to, ll cap, ll cost) : from(from), to(to), cap(cap), cost(cost) {}
};

int N;
int M;
int S;
int T;

vector<vector<Edge>> graph;
bool finish = false;
ll inf = INT64_MAX;
ll findNegCycle() {
    vector<Edge*> cycle;
    cycle.resize(N, nullptr);
    ll d[N];
    deque<int> q;
    int id[N];
    for (int j = 0; j < N; ++j) {
        d[j] = inf;
        id[j] = 0;
    }
    q.push_back(S);
    d[S] = 0;
    while (!q.empty()) {
        auto v = q.back();
        q.pop_back();
        id[v] = 1;
        for (auto& edge : graph[v]) {
            if (edge.flow < edge.cap && d[edge.to] > d[v] + edge.cost) {
                d[edge.to] = d[v] + edge.cost;
                if (id[edge.to] == 0) {
                    q.push_back(edge.to);
                } else if (id[edge.to] == 1) {
                    q.push_front(edge.to);
                }
                id[edge.to] = 1;
                cycle[edge.to] = &edge;
            }
        }

    }
    if (d[T] == inf) {
        finish = true;
        return 0;
    }
    ll localRes = 0;
    ll canAdd = INT64_MAX;
    int i = T;
    while (i != S) {
        auto edge = cycle[i];
        if (edge->cap - edge->flow < canAdd) {
            canAdd = edge->cap - edge->flow;
        }
        i = cycle[i]->from;

    }
    i = T;
    while (i != S) {
        auto edge = cycle[i];
        edge->flow += canAdd;
        graph[edge->to][edge->revInd].flow -= canAdd;
        localRes += edge->cost * canAdd;
        i = cycle[i]->from;

    }
    return localRes;
}

void add(int from, int to, ll cap, ll cost) {
    graph[from].push_back(Edge(from, to, cap, cost));
    graph[to].push_back(Edge(to, from, 0, -cost));
    graph[from].back().revInd = graph[to].size() - 1;
    graph[to].back().revInd = graph[from].size() - 1;
}

int main() {
    cin >> N >> M;
    S = N + N;
    T = N + N + 1;
    graph.resize(N + N + 2);
    for (int i = 0; i < N; ++i) {
        // start to cities
        add(S, i, 1, 0);
        //cities to end
        add(N + i, T, 1, 0);
    }
    for (int i = 0; i < N; ++i) {
        int cityCost;
        cin >> cityCost;
        //live in the city
        add(i, N + i, inf, cityCost);
        // pass the city
        add(N+i,i,inf,0);
    }
    for (int i = 0; i < M; ++i) {
        int from, to;
        ll cost;
        cin >> from >> to >> cost;
        from--;
        to--;
        //start1 -> end2
        add(from, to+N, inf, cost);

    }
    N = N+N+2;
    ll res = 0;
    while (true) {
        res += findNegCycle();
        if (finish) {
            break;
        }

    }
    cout << res;
    return 0;
}

