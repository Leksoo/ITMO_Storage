#include <iostream>
#include <vector>
#include <set>
#include <cmath>
#include <algorithm>
#include <fstream>

using namespace std;

set<int> tree[2 * 100000];
int mins[2 * 100000][50];
int counter = 0;
int dp[2 * 100000][50];
int a;
int list[2][2 * 100000];


void dfs(int cur) {
    counter++;
    list[0][cur] = counter;
    if (!tree[cur].empty()) {
        for (int u:tree[cur]) {
            dfs(u);
        }
    }
    counter++;
    list[1][cur] = counter;
}

int main() {
    ifstream cin("minonpath.in");
    ofstream cout("minonpath.out");
    int n;
    cin >> n;
    for (int l = 0; l < 200000; ++l) {
        for (int i = 0; i < 50; ++i) {
            mins[l][i] = numeric_limits<int>::max();
        }
    }
    for (int i = 1; i < n; ++i) {
        int x, y;
        cin >> x >> y;
        x--;
        mins[i][0] = y;
        dp[i][0] = x;
        tree[x].insert(i);
    }
    a = (int) (log(n) / log(2));
    dfs(0);
    for (int j = 1; j <= a; ++j) {
        for (int i = 0; i < n; ++i) {
            dp[i][j] = dp[dp[i][j - 1]][j - 1];
            mins[i][j] = min(mins[i][j - 1], mins[dp[i][j - 1]][j - 1]);
        }
    }


    int m;
    cin >> m;
    for (int j = 0; j < m; j++) {
        int v1, v2;
        cin >> v1 >> v2;
        v1--;
        v2--;
        int res = numeric_limits<int>::max();
        //v1---->up
        for (int i = a; i >= 0; i--)
            if (list[0][dp[v1][i]] > list[0][v2] | list[1][v2] > list[1][dp[v1][i]]) {
                res = min(res, mins[v1][i]);
                v1 = dp[v1][i];
            }
        if (list[0][v1] > list[0][v2] | list[1][v2] > list[1][v1]) {
            res = min(res, mins[v1][0]);
            v1 = dp[v1][0];
        }
        //v2---->up
        for (int i = a; i >= 0; i--)
            if (list[0][dp[v2][i]] > list[0][v1] | list[1][v1] > list[1][dp[v2][i]]) {
                res = min(res, mins[v2][i]);
                v2 = dp[v2][i];
            }
        if (list[0][v2] > list[0][v1] | list[1][v1] > list[1][v2]) {
            res = min(res, mins[v2][0]);
            v2 = dp[v2][0];
        }
            
        cout << res << endl;

    }


    cout.close();
    cin.close();
}