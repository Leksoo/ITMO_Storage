#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <cmath>
#include <algorithm>

using namespace std;

set<int> tree[1000000];
int root;
set<int> treeColors[1000000];
int colors[1000000];
int ans[1000000];


void dfs(int v) {
    treeColors[v].insert(colors[v]);
    if(!tree[v].empty()) {
        for (int u:tree[v]) {
            dfs(u);
            if(treeColors[v].size()>=treeColors[u].size()) {
                treeColors[v].insert(treeColors[u].cbegin(), treeColors[u].cend());
                treeColors[u].clear();
            }
            else{
                treeColors[v].swap(treeColors[u]);
                treeColors[v].insert(treeColors[u].cbegin(), treeColors[u].cend());
                treeColors[u].clear();
            }
        }
    }
    ans[v]=treeColors[v].size();

}

int main(){
    int n;
    cin>>n;
    for (int i = 0; i < n; ++i) {
        int x,color;
        cin>>x;
        cin>>color;
        x--;
        colors[i]=color;
        if(x==-1){
            root=i;
            continue;
        }
        tree[x].insert(i);
    }
    dfs(root);
    for (int j = 0; j <n ; ++j) {
        cout<<ans[j]<<" ";
    }
    return 0;

}