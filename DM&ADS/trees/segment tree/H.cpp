

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>


using namespace std;
long long inf = numeric_limits<int>::max();
long long maxConst = -21474836480;

struct node {
    long long value;
    bool isNew;
    long long newValue;
    long long add;
    long long maxim;

    node() {
        value = inf;
        isNew = false;
        newValue = 0;
        add = 0;
        maxim = maxConst;
    }

};



int n;
int N;

long long minim(vector<node> &tree, int i, int lb, int rb, int l, int r) {
    if (r <= lb || rb <= l)
        return inf;
    if (l <= lb && rb <= r) {
        return tree[i].value;
    }
    int m = (lb + rb) / 2;
    long long left = minim(tree, i * 2, lb, m, l, r);
    long long right = minim(tree, i * 2 + 1, m, rb, l, r);
    return min(left, right);
}


void pushspec(vector<node> &tree, int i) {
    if (i >= N) {
        return;
    }
    tree[2*i].value=max(tree[2*i].value,tree[i].value);
    tree[2*i+1].value=max(tree[2*i+1].value,tree[i].value);

}

void make(vector<node> &tree, int i, int lb, int rb, int l, int r, long long value) {
    pushspec(tree, i);
    if (r <= lb || rb <= l)
        return;
    if (l <= lb && rb <= r) {
        tree[i].value = max(tree[i].value, value);
        return;
    }
    int m = (lb + rb) / 2;
    make(tree, i * 2, lb, m, l, r, value);
   make(tree, i * 2 + 1, m, rb, l, r, value);
    pushspec(tree, i);
}

void make2(vector<node> &tree, int i, int lb, int rb, int l, int r) {
    pushspec(tree, i);
    if (r <= lb || rb <= l)
        return;
    if(rb-lb==1){
        return;
    }
    int m = (lb + rb) / 2;
    make2(tree, i * 2, lb, m, l, r);
    make2(tree, i * 2 + 1, m, rb, l, r);
    pushspec(tree, i);
}


void init(vector<node> &tree, int n) {
    for (size_t i = N; i < N + n; i++)
        tree[i].value = maxConst;
    for (size_t i = N + n; i < 2 * N; i++)
        tree[i].value = maxConst;
    for (size_t i = N - 1; i >= 1; i--)
        tree[i].value = maxConst;
    for (size_t i = 0; i < 2 * N; i++) {
        tree[i].maxim = maxConst;
    }
}

void init2(vector<node> &tree, int n) {
    for (size_t i = N; i < N + n; i++)
        tree[i].value = tree[i].value==maxConst ? inf: tree[i].value;
    for (size_t i = N + n; i < 2 * N; i++)
        tree[i].value = inf;
    for (size_t i = N - 1; i >= 1; i--)
        tree[i].value = min(tree[2 * i].value, tree[2 * i + 1].value);
}

int main(int argc, char **argv) {
    ios_base::sync_with_stdio(false);
    ifstream cin("rmq.in");
    ofstream cout("rmq.out");
    int m;
    cin >> n >> m;
    N = 0;
    for (N = 1; N < n; N *= 2);
    vector<node> tree(2 * N);
    init(tree, n);
    vector<int> is;
    vector<int> js;
    vector<long long> qs;
    for (int f = 0; f < m; ++f) {
        int i, j;
        long long q;

        cin >> i >> j >> q;
        is.push_back(i);
        js.push_back(j);
        qs.push_back(q);
        make(tree, 1, 0, N, i - 1, j, q);
    }
    make2(tree,1,0,N,0,N);
    init2(tree,n);

    for (int k = 0; k <m ; ++k) {
        int i, j;
        long long q;
        i=is[k];
        j=js[k];
        q=qs[k];
        if(minim(tree,1,0,N,i-1,j)!=q){
            cout<<"inconsistent";
            return 0;
        }
    }
    cout << "consistent"<<endl;
    for (size_t l = N; l < N + n; l++) {
        cout<<tree[l].value<<" ";
    }
    return 0;
}