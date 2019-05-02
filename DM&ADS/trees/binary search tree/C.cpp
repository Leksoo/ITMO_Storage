#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;
struct Tree{
    struct nodet;
    typedef nodet* node;
    node root;
    vector<node> nodes;
    vector<node> unsorted;
    struct sort_classX;
    Tree();
};
struct Tree::nodet{
    int x,y,num;
    node left,right,parent;
    nodet(int x1, int y1,int num){
        left= nullptr;
        right= nullptr;
        parent= nullptr;
        x=x1;
        y=y1;
        this->num=num;
    };
};

struct Tree::sort_classX
{
    bool operator() (node i, node j)
    {
        if(i->x<j->x) return true;
        return false;
    }
} sort_objectX;



Tree::Tree() {
    int n;
    cin>>n;
    int count=0;
    for (int i = 0; i <n ; ++i) {
        int a,b;
        cin>>a>>b;
        node c = new nodet(a,-b,count+1);
        nodes.push_back(c);
        unsorted.push_back(c);
        count++;
    }
    sort(nodes.begin(),nodes.end(),sort_objectX);
    //build


    root = nodes[0];
    node last = root;
    for (int i = 1; i < n; i++){
        while (last->parent != nullptr && nodes[i]->y > last->y){
            last = last->parent;
        }
        if (nodes[i]->y > last->y){
            nodes[i]->left = last;
            last->parent = nodes[i];
            root = nodes[i];

        } else {
            nodes[i]->left = last->right;
            nodes[i]->parent = last;
            if (last->right != nullptr){
                last->right->parent = nodes[i];
            }
            last->right = nodes[i];
        }
        last = nodes[i];
    }

    // res

    cout << "YES" << endl;
    for (int i = 0; i < n; i++){
        if (unsorted[i]->parent!= nullptr) {
            cout<< unsorted[i]->parent->num<<" ";
        } else {
            cout<<"0 ";
        }

        if (unsorted[i]->left){
            cout<<unsorted[i]->left->num<<" ";
        }  else {
            cout<<"0 ";
        }

        if (unsorted[i]->right) {
            cout<< unsorted[i]->right->num;
        } else {
            cout<<"0";
        }

        cout << endl;
    }
}


int main() {
    Tree tree;
    return 0;
}