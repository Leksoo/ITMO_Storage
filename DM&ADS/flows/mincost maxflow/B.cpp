#include <fstream>
#include <vector>
#include <string>
#include <deque>

using namespace std;
#define ll long long

ifstream cin("assignment.in");
ofstream cout("assignment.out");

int N = 0;
vector<vector<int>> table;
int inf = INT32_MAX;
vector<int> matching;
vector<int> rowPot;
vector<int> columnPot;
vector<int> path;
int main() {
    cin >> N;
    table.resize(N + 1);
    matching.resize(N + 1);
    rowPot.resize(N + 1);
    columnPot.resize(N + 1);
    path.resize(N + 1);
    for (int i = 1; i <= N; ++i) {
        table[i].resize(N + 1);
        for (int j = 1; j <= N; ++j) {
            int a;
            cin >> a;
            table[i][j] = a;
        }
    }
    for (int row = 1; row <= N; ++row) {
        vector<int> visited(N + 1);
        vector<int> mins(N + 1, inf);
        matching[0] = row;
        int newColumn = 0;
        while (true) {
            visited[newColumn] = true;
            int newRow = matching[newColumn];
            int minDiff = inf;
            int minDiffCol = -1;
            for (int col = 1; col <= N; ++col) {
                if (!visited[col]) {
                    int tmp = table[newRow][col] - columnPot[col] - rowPot[newRow];
                    if (tmp < mins[col]) {
                        path[col] = newColumn;
                        mins[col] = tmp;
                    }
                    if (mins[col] < minDiff) {
                        minDiff = mins[col];
                        minDiffCol = col;
                    }
                }
            }
            for (int col = 0; col <= N; ++col) {
                if (visited[col]) {
                    rowPot[matching[col]] += minDiff;
                    columnPot[col] -= minDiff;
                } else {
                    mins[col] -= minDiff;
                }
            }
            newColumn = minDiffCol;
            if (matching[minDiffCol] == 0) {
                break;
            }
        }
        while (true) {
            int next = path[newColumn];
            matching[newColumn] = matching[next];
            newColumn = next;
            if (newColumn == 0) {
                break;
            }
        }

    }

    cout << -columnPot[0] << endl;
    for (int col = 1; col <= N; ++col) {
        cout << matching[col] << " " << col << endl;
    }

    return 0;
}


