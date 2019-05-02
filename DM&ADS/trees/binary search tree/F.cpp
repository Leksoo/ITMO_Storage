#include <fstream>
#include <iostream>
#include <string>
using namespace std;
int kthAns = 0;
int X = 1;

struct Tree {
    Tree();

    struct nodet;
    typedef nodet *node;
    node root;

    void split(node, node &, node &, int);

    void merge(node &, node &, node &);

    void append(int);

    void append(node &, node);

    void out(node &);

    void remove(node &, int);

    void kth(node &, int);
};


// Node--------------------------------
struct Tree::nodet {
    int x, y, count;
    node left, right;

    nodet(int x) {
        this->x = x;
        count = 1;
        y = rand();
        left = nullptr;
        right = nullptr;
    };

    nodet() {};

    void nodeUpdate() {
        if(this!= nullptr) {
            int Lcount = 1;
            if (right != nullptr)
                Lcount += right->count;
            if (left != nullptr)
                Lcount += left->count;
            count = Lcount;
        }
    };
};


//----------------------------------------------


Tree::Tree() : root(nullptr) {}

void Tree::out(node &nod) {
    if (nod->left != nullptr)
        out(nod->left);
    cout << nod->x << " ";
    if (nod->right != nullptr)
        out(nod->right);
}


void Tree::split(node splitNod, node &res1, node &res2, int x) {
    if (splitNod == nullptr) {
        res1 = res2 = nullptr;
        return;
    }
    if (x < splitNod->x) {
        split(splitNod->left, res1, splitNod->left, x);
        res2 = splitNod;
    } else {
        split(splitNod->right, splitNod->right, res2, x);
        res1 = splitNod;
    }
    if (res1 != nullptr)
        res1->nodeUpdate();
    if (res2 != nullptr)
        res2->nodeUpdate();
}

void Tree::merge(node &nod1, node &nod2, node &res) {
    if (nod1 == nullptr || nod2 == nullptr) {
        if (nod2 == nullptr) {
            res = nod1;
            return;
        } else {
            res = nod2;
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

void Tree::append(node &nod, node added) {
    if (nod == nullptr) {
        nod = added;
        nod->nodeUpdate();
    } else if (added->y > nod->y) {
        split(nod, added->left, added->right, added->x);
        nod = added;
    } else
        append(added->x < nod->x ? nod->left : nod->right, added);
    nod->nodeUpdate();
}

void Tree::append(int k) {
    node a = new nodet(k);
    append(root, a);
}

void Tree::remove(node &nod, int k) {
    if (nod->x == k)
        merge(nod->left, nod->right, nod);
    else {
        remove(k < nod->x ? nod->left : nod->right, k);
    }
    nod->nodeUpdate();

}


void Tree::kth(node &nod, int k) {
    int cnt = nod->count-1;
    if (nod->left != nullptr) {
        cnt -= nod->left->count;
    }
    if(cnt==k-1){
        kthAns=nod->x;
        return;
    }
    if(cnt>=k){
        return kth(nod->right,k);
    } else{
        return kth(nod->left,k-cnt-1);
    }

}


int main() {
    int n;
    cin >> n;
    Tree tree;
    string command;
    int k;
    for (int i = 0; i < n; i++) {
        cin >> command;
        cin >> k;
        if (command == "+1" | command == "1") {
            tree.append(k);
        } else if (command == "0") {
            tree.kth(tree.root, k);
            cout << kthAns << endl;
        } else if (command == "-1") {
            tree.remove(tree.root, k);
        }
    }
    return 0;


}