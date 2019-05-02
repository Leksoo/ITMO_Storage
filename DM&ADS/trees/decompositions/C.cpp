

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>


using namespace std;
long long inf = 0;
int NN;
vector<vector<int>> inpTree;

struct node {
    long long value;
    bool isNew;
    long long newValue;
    long long add;

    node() {
        value = inf;
        isNew = false;
        newValue = 0;
        add = 0;
    }

};
struct SegmentTree {

    int n;
    int N;
    vector<node> tree;
    SegmentTree(int n){
        this->n=n;
        for (N = 1; N <n; N *= 2);
        NN=N;
        tree.resize(2*N);
    }
    SegmentTree(){};

    void push( int i) {
        if (i >= N) {
            tree[i].add = 0;
            if (tree[i].isNew) {
                tree[i].isNew = false;
                tree[i].value = tree[i].newValue;
            }
            return;
        }
        if (tree[i].add != 0) {
            if (tree[2 * i].isNew)
                tree[2 * i].newValue += tree[i].add;
            else
                tree[2 * i].add += tree[i].add;
            tree[2 * i].value += tree[i].add;


            if (tree[2 * i + 1].isNew)
                tree[2 * i + 1].newValue += tree[i].add;
            else
                tree[2 * i + 1].add += tree[i].add;
            tree[2 * i + 1].value += tree[i].add;


            tree[i].add = 0;
        }
        if (tree[i].isNew) {
            tree[2 * i].newValue = tree[i].newValue;
            tree[2 * i].value = tree[i].newValue;
            tree[2 * i].isNew = true;
            //----
            tree[2 * i + 1].newValue = tree[i].newValue;
            tree[2 * i + 1].value = tree[i].newValue;
            tree[2 * i + 1].isNew = true;
            tree[2 * i + 1].add = 0;
            tree[2 * i].add = 0;
            //-----
            tree[i].isNew = false;
        }
        tree[i].value = min(tree[2 * i].value, tree[2 * i + 1].value);
    }

    long long minim( int i, int lb, int rb, int l, int r) {
        push( i);
        if (r <= lb || rb <= l)
            return inf;
        if (l <= lb && rb <= r) {
            return tree[i].value;
        }
        int m = (lb + rb) / 2;
        long long left = minim( i * 2, lb, m, l, r);
        long long right = minim( i * 2 + 1, m, rb, l, r);
        return left+right;
    }


    void set( int i, int lb, int rb, int l, int r, long long value) {
        push( i);
        if (r <= lb || rb <= l)
            return;
        if (l <= lb && rb <= r) {
            tree[i].newValue = value;
            tree[i].isNew = true;
            tree[i].value = value;
            tree[i].add = 0;
            return;
        }
        int m = (lb + rb) / 2;
        set(i * 2, lb, m, l, r, value);
        set( i * 2 + 1, m, rb, l, r, value);
        push(i);

    }

    void add( int i, int lb, int rb, int l, int r, long long value) {
        push( i);
        if (r <= lb || rb <= l)
            return;
        if (l <= lb && rb <= r) {
            tree[i].value += value;
            tree[i].add = value;
            return;
        }
        int m = (lb + rb) / 2;
        add( i * 2, lb, m, l, r, value);
        add( i * 2 + 1, m, rb, l, r, value);
        push( i);
    }


    void init(vector<int> arr) {
        for (size_t i = N; i < N + n; i++)
            tree[i].value = arr[i - N];
        for (size_t i = N + n; i < 2 * N; i++)
            tree[i].value = inf;
        for (size_t i = N - 1; i >= 1; i--)
            tree[i].value = min(tree[2 * i].value, tree[2 * i + 1].value);
        for (size_t i = 0; i < 2 * N; i++) {
            tree[i].add = 0;
            tree[i].isNew = false;
        }
    }
};

struct decomposition {
    int n;
    vector<int> parent;
    vector<int> heavyPath;
    vector<int> depth;
    vector<int> heavyRoot;
    vector<int> indSegTree;
    SegmentTree segmentTree;
    decomposition(int n,SegmentTree & sg){
        this->n=n;
        segmentTree=sg;
        parent.resize(n);
        heavyPath.resize(n);
        depth.resize(n);
        heavyRoot.resize(n);
        indSegTree.resize(n);


        for (int k = 0; k <heavyPath.size() ; ++k) {
            heavyPath[k]=-1;
        }
        parent[0]=-1;
        dfs(0);
        int pos = 1;
        for (int i = 0; i < n; ++i)
            if (parent[i] == -1 || heavyPath[parent[i]] != i)
                for (int j = i; j != -1; j = heavyPath[j]) {
                    heavyRoot[j] = i;
                    indSegTree[j] = pos++;
                }



    }
    int dfs(int v) {
        int sz = 1;
        int sub = 0;
        for (int u:inpTree[v]) {
            if (u !=parent[v]){
                parent[u]=v;
                depth[u]=depth[v]+1;
                int subt=dfs(u);
                if(subt>sub){
                    heavyPath[v]=u;
                    sub=subt;
                }
                sz+=subt;
            }

        }
        return sz;
    }


    void addOnPath(int u, int v,long long add) {

        while(heavyRoot[u] != heavyRoot[v]) {
            if (depth[heavyRoot[u]] > depth[heavyRoot[v]]) {
                int tmp=v;
                v=u;
                u=tmp;
            };
            int left=indSegTree[heavyRoot[v]]-1;
            int right=indSegTree[v];
            segmentTree.add(1,0,NN,left,right,add);
            v = parent[heavyRoot[v]];
        }
        if (depth[u] > depth[v]){
            int tmp=v;
            v=u;
            u=tmp;
        }
        int left=indSegTree[u]-1;
        int right=indSegTree[v];
        segmentTree.add(1,0,segmentTree.N,left,right,add);
    }
    long long get(int v){
        return segmentTree.minim(1, 0, segmentTree.N, indSegTree[v]-1,indSegTree[v]);
    }

};

int main() {
	ios_base::sync_with_stdio(false);
    int n,m;
    cin>>n;
    inpTree.resize(n);
    vector<int> arr(n+10);
    SegmentTree segmentTree(n+10);
    segmentTree.init(arr);
    for (int i = 1; i <n ; ++i) {
        int u,v;
        cin>>u>>v;
        u--;
        v--;
        inpTree[u].push_back(v);
        inpTree[v].push_back(u);
    }
    decomposition dec(n,segmentTree);
    cin>>m;
    char sign;
    for (int j = 0; j <m ; ++j) {
        cin>>sign;
        if(sign=='+'){
            int v,u;
            long long add;
            cin>>v>>u>>add;
            v--;
            u--;
            dec.addOnPath(v,u,add);
        }
        if(sign=='?'){
            int v;
            cin>>v;
            v--;
            cout<<dec.get(v)<<endl;
        }
    }
	//init(tree, n)//l--;//set(tree,1,0,N,l,r,v);//add(tree,1,0,N,l,r,v);//minim(tree,1, 0, N, l, r);
	return 0;
}