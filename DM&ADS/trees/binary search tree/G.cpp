#include <fstream>
#include <iostream>

using namespace std;

struct Tree {
    Tree();
    struct nodet;
    typedef nodet* node;
    node root;
    void split(node, node& , node& , int);
    void merge(node& , node& , node& );
    void move(int , int );
    void append(int );
    void out(node&);
};



// Node--------------------------------
struct Tree::nodet {
    int x, y, count;
    node left, right;
    
    nodet(int x){
       this->x=x;
       count=1;
       y=rand();
       left=nullptr;
       right= nullptr;
    };
    nodet(){};
    void nodeUpdate(){
        int Lcount = 1;
        if (right != nullptr)
            Lcount += right->count;
        if (left != nullptr)
            Lcount += left->count;
        count = Lcount;
    };
};


//----------------------------------------------


Tree::Tree(): root(nullptr) {}

void Tree::out(node& nod) {
    if (nod->left != nullptr)
         out(nod->left);
    cout << nod->x << " ";
    if (nod->right != nullptr)
        out(nod->right);
}


void Tree::split(node splitNod, node& res1, node& res2, int x) {
    if (splitNod == nullptr) {
        res1 = res2 = nullptr;
        return;
    }
    int v;
    if(splitNod->left==nullptr) v=0;
    else v=splitNod->left->count;
    if (x <= v) {
        split(splitNod->left, res1, splitNod->left, x);
        res2 = splitNod;
    } else {
        split(splitNod->right, splitNod->right, res2, x - v - 1);
        res1 = splitNod;
    }
    if(res1!= nullptr)
        res1->nodeUpdate();
    if(res2!= nullptr)
        res2->nodeUpdate();
}

void Tree::merge(node& nod1, node& nod2, node& res) {
    if (nod1 == nullptr || nod2 == nullptr) {
        if(nod1== nullptr){
            res=nod2;
            return;
        } else{
            res=nod1;
            return;
        }
    }
    if (nod1->y > nod2->y) {
        merge(nod1->right, nod2, nod1->right);
        res = nod1;
    } else {
        merge(nod1, nod2->left, nod2->left);
        res = nod2;
    }
    res->nodeUpdate();
}

void Tree::append(int x) {
    node a = new nodet(x);
    merge(root, a, root);
}

void Tree::move(int l, int r) {
    node nodMain, nodL, nodR;
    split(root, nodL, nodR, r);
    split(nodL, nodMain, nodL, l);
    merge(nodL, nodMain, nodL);
    merge(nodL, nodR, root);
}


int main() {
    int n, m;
    cin >> n >> m;
    Tree tree;
    for (int i = 0; i < n; i++)
        tree.append(i + 1);
    for (int i = 0; i < m; i++) {
        int l, r;
        cin >> l >> r;
        tree.move(l - 1, r);
    }
    tree.out(tree.root);


    
}