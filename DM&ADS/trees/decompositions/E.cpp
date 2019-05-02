
#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>

using namespace std;

vector<vector<int>> inpTree;

const int N=200000;
int centroid,levelC[N], parent[N], sizeC[N];
bool centrfound= false;

int findCentr( int v, int parentV, int n ) {
    int size = 1;
    for (int x : inpTree[v])
        if (x != parentV && levelC[x] == -1){
            size += findCentr(x, v, n);
            }

    if (!centrfound && size * 2 >= n ){
        centroid = v;
        centrfound= true;
    }

    return size;
}

int dfs(int v, int parentV, int n){
    int size = 1;
    for (int x : inpTree[v]) {
        if (x != parentV && levelC[x] == -1) {
            size += dfs(x, v, n);

        }
    }
    sizeC[v] = size;
    return size;
}

void build( int v, int parentV, int centrLevel, int n ) {
    centrfound = false;
    findCentr(v, -1, n);
    int c = centroid;
    levelC[c] = centrLevel;
    parent[c] = parentV;
    dfs(c,-1,n);
    for (int u : inpTree[c])
        if (levelC[u] == -1)
            build(u, c, centrLevel + 1, sizeC[u]);
}


int main() {
    int n;
    cin>>n;
    inpTree.resize(n);
     for (int i = 1; i <n ; ++i) {
        int u,v;
        cin>>u>>v;
        u--;
        v--;
        inpTree[u].push_back(v);
        inpTree[v].push_back(u);
    }
    for (int j = 0; j <N ; ++j) {
        levelC[j]=-1;
        sizeC[j]=0;
    }
    build(0, -1, 0, n);
    for (int k = 0; k <n ; ++k) {
        cout<<parent[k]+1<<" ";
    }
}