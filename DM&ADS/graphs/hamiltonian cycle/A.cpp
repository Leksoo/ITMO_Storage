#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

string fileName = "fullham";
ofstream out(fileName + ".out");
ifstream in(fileName + ".in");

int N;
int graph[4001][4001];
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
        if (graph[f][s]== 0) {
            vector<int> queue;
            deque<int> deque1(q);
            while (!deque1.empty()){
                queue.push_back(deque1.front());
                deque1.pop_front();
            }

            int j = 2;
            while (graph[queue[0]][queue[j]] == 0 ||
                   (j + 1 < N && graph[queue[1]][queue[j + 1]] == 0)) {
                j++;
            }
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
            for(auto a : queue){
                q.push_back(a);
            }
        }
        q.push_back(q.front());
        q.pop_front();
    }
    for (int i = 0; i < q.size(); i++) {
        out << q[i] + 1 << " ";
    }
}

void input() {
    in >> N;
    for (int i = 1; i < N; i++) {
        string line;
        in >> line;
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
