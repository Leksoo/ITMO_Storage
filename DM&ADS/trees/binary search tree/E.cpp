#include <fstream>
#include <iostream>
#include <string>
using namespace std;


struct Tree {
    Tree();

    struct nodet;
    typedef nodet *node;
    node root;

    void split(node, node &, node &, int);

    void merge(node &, node &, node &);

    void append(long long);

    void append(node &, node);

    void out(node &);

    void remove(node &, int);

    long long sum(long long,long long );

    bool exists(long long );

};


// Node--------------------------------
struct Tree::nodet {
    long long x, y, count;
    node left, right;
    long long Sum;

    nodet(long long x) {
        this->x = x;
        count = 1;
        y = rand();
        left = nullptr;
        right = nullptr;
        Sum=x;
    };

    nodet() {};

    void nodeUpdate() {
        if(this!= nullptr) {
            int Lcount = 1;
            int curSum=0;
            if (right != nullptr) {
                Lcount += right->count;
                curSum+=right->Sum;
            }
            if (left != nullptr){
                Lcount += left->count;
                curSum+=left->Sum;
            }
            count = Lcount;
            Sum=curSum+this->x;
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

void Tree::append(long long k) {
    if(exists(k)){
        return;
    }
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

long long Tree::sum(long long l, long long r){
    node nod=root;
    while(true){
        if(nod== nullptr){
            return 0;
        }
        if(r<nod->x){
            nod=nod->left;
        } else if(l>nod->x){
            nod=nod->right;
        }
        else{
            break;
        }
    }
    node lnode=nod;
    long long lsum=0;

    if(lnode->left!= nullptr) {
        lnode=lnode->left;
        while (true) {
            if (lnode == nullptr) {
                break;
            }
            if(lnode->x>=l){
                long long rsum = lnode->Sum;
                if (lnode->left != nullptr) {
                    rsum -= lnode->left->Sum;
                }
                lsum+=rsum;
            }
            if (l > lnode->x) {
                lnode = lnode->right;
            } else {
                lnode = lnode->left;
            }
        }
    }
    node rnode=nod;
    long long rsum=0;
    if(rnode->right!= nullptr) {
        rnode=rnode->right;
        while (true) {
            if (rnode == nullptr) {
                break;
            }
            if(rnode->x<=r){
                long long lsum = rnode->Sum;
                if (rnode->right != nullptr) {
                    lsum -= rnode->right->Sum;
                }
                rsum+=lsum;
            }
            if (r > rnode->x) {
                rnode = rnode->right;
            } else {
                rnode = rnode->left;
            }
        }
    }
    return lsum+rsum+nod->x;


};

bool Tree::exists(long long k){
    node nod=root;
    while(nod!= nullptr){
        if(nod->x==k){
            return true;
        } else if(k>nod->x){
            nod=nod->right;
        } else{
            nod=nod->left;
        }
    }
    return false;
}


int main() {
    int n;
    cin >> n;
    Tree tree;
    string command;
    long long k;
    string lastcommand="";
    long long last=0;
    long long C=1000000000;
    for (int i = 0; i < n; i++) {
        cin >> command;
        if (command == "+") {
            cin >> k;
            if(lastcommand=="?"){
                tree.append((last+k)%C);
            }
            else{
                tree.append(k);
            }
            lastcommand="+";
        } else if (command == "?") {
            long long l,r;
            cin>>l>>r;
            lastcommand="?";
            last=tree.sum(l,r);
            cout<<last<<endl;
        }
    }
    return 0;


}