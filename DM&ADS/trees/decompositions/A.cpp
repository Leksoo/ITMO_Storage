#include <iostream>
#include <vector>
#include <set>
#include <cmath>
#include <algorithm>

using namespace std;

    const int sz=2*100000;
    set<int> tree[2*100000];
    int depth[2*100000];
    int counter=0;
    int dp[2*100000][50];
    int a;
    int list[2][2*100000];


    void dfs(int cur, int parent ) {
        counter++;
        list[0][cur]=counter;
        dp[cur][0]=parent;
        for (int k = 1; k <=a ; k++) {
            dp[cur][k] = dp[dp[cur][k - 1]][k - 1];
        }
        if(!tree[cur].empty()) {
            for (int u:tree[cur]) {
                dfs(u,cur);
            }
        }
        counter++;
        list[1][cur]=counter;
    }

    int main(){
            int n;
            cin>>n;
            for (int i = 1; i < n; ++i) {
                int x;
                cin>>x;
                x--;
                tree[x].insert(i);
            }
            a = (int) (log(n)/log(2));
            dfs(0,0);


            int m;
            cin>>m;
            for (int j = 0; j < m; j++) {
                int v1,v2;
                cin>>v1>>v2;
                v1--;
                v2--;
                if(list[0][v1]<=list[0][v2] && list[1][v1]>=list[1][v2]){
                    cout<<v1+1<<endl;
                    continue;
                }
                if(list[0][v2]<=list[0][v1] && list[1][v2]>=list[1][v1]){
                    cout<<v2+1<<endl;
                    continue;
                }
                for (int i = a; i >=0 ; i--) {
                    int step=dp[v1][i];
                    if(!(list[0][step]<=list[0][v2] && list[1][step]>=list[1][v2])){
                        v1=step;
                    }

                }
                cout<<(dp[v1][0]+1)<<endl;
            }
    }