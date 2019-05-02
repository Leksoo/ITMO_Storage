#include <iostream>
#include <vector>
#include <cstdlib>
#include <string>

using namespace std;


struct Tree {

    Tree();

    void insert(int);

    void delete1(int);

    bool exists(int);

    bool hasNext(int);

    bool hasPrev(int);

    int next(int);

    int prev(int);

    struct Nodet;
    typedef Nodet *Node;
    Node root;

    int getMax(Node &);

    int getMin(Node &);

    void splay(int);

    void insertNode(Node &, int);

    void merge(Node &, Node &, Node &);

    void zigleft(Node &);

    void zigright(Node &);

    void zigzigleft(Node &);

    void zigzigright(Node &);

    void zigzagleft(Node &);

    void zigzagright(Node &);

};

Tree::Tree() {
    root = nullptr;
}

struct Tree::Nodet {


    int data;
    Node left;
    Node right;

    Nodet(int val){
        left = nullptr;
        right = nullptr;
        data = val;
    };


};

// ROTATIONS ----------------------------------------

void Tree::zigleft(Node &a) {
    int b = a->data;
    a->data = a->left->data;
    a->left->data = b;

    Node tmp = a->right;
    a->right = a->left;
    a->left = a->right->left;
    a->right->left = a->right->right;
    a->right->right = tmp;
}

void Tree::zigright(Node &a) {
    int b = a->data;
    a->data = a->right->data;
    a->right->data = b;

    Node tmp = a->left;
    a->left = a->right;
    a->right = a->left->right;
    a->left->right = a->left->left;
    a->left->left = tmp;
}

void Tree::zigzagleft(Node &a) {
    zigright(a->left);
    zigleft(a);

}

void Tree::zigzagright(Node &a) {
    zigleft(a->right);
    zigright(a);

}

void Tree::zigzigleft(Node &a) {
    zigleft(a);
    zigleft(a);

}

void Tree::zigzigright(Node &a) {
    zigright(a);
    zigright(a);

}


void Tree::splay(int val) {
    vector<Node> list;
    // 1 - right , 0 - left
    vector<int> turns;
    Node cur = root;
    while ((cur != nullptr) && cur->data != val) {
        list.push_back(cur);
        if (val > cur->data) {
            cur = cur->right;
            turns.push_back(1);
        } else {
            turns.push_back(0);
            cur = cur->left;
        }
    }

    if (cur != nullptr) {


        for (int i = list.size(); i > 1; i -= 2) {
            int first = turns[i - 2];
            int sec = turns[i - 1];
            Node node = list[i - 2];
            if (first == 0) {
                if (sec == 0) {
                    zigzigleft(node);
                } else {
                    zigzagleft(node);
                }
            } else {
                if (sec == 1) {
                    zigzigright(node);
                } else {
                    zigzagright(node);
                }
            }
        }

        if (list.size() % 2 == 1) {
            if (turns[0] == 0) {
                zigleft(list[0]);
            } else zigright(list[0]);
        }
    }
}

//--------------------------------------------------

bool Tree::exists(int val) {
    splay(val);
    return root != nullptr && val == root->data;
}

void Tree::merge(Node &left, Node &right, Node &r) {
    if (left == nullptr) {
        r = right;
        return;
    }
    r = left;
    getMax(r);
    r->right = right;

}

void Tree::insertNode(Node &node, int value) {
    if (node == nullptr) {
        node = new Nodet(value);
    } else if (node->data < value) {
        insertNode(node->right, value);
    } else {
        insertNode(node->left, value);
    }

}

void Tree::insert(int value) {
    if (exists(value)) {
        splay(value);
    } else {
        insertNode(root, value);
        splay(value);
    }

}


void Tree::delete1(int val) {
    if (!exists(val)) {
        return;
    }
    splay(val);
    if (root != nullptr && root->data == val) {
        Node tmp = root;
        merge(root->left, root->right, root);
        delete tmp;
    }

}

int Tree::getMin(Node &node) {
    Node a = node;
    Node current = node->left;
    while (current != nullptr) {
        a = current;
        current = current->left;
    }

    int ans = a->data;
    splay(ans);
    return ans;
}

int Tree::getMax(Node &node) {
    Node a = node;
    Node current = node->right;
    while (current != nullptr) {
        a = current;
        current = current->right;
    }

    int ans = a->data;
    splay(ans);
    return ans;
}

bool Tree::hasNext(int val) {
    return root != nullptr && getMax(root) > val;
}

bool Tree::hasPrev(int val) {
    return root != nullptr && getMin(root) < val;
}

int Tree::next(int val) {
    bool insertFlag = false;
    if (!exists(val)) {
        insert(val);
        insertFlag = true;
    }
    splay(val);
    Node tmp = root->right;
    while (tmp->left != nullptr) {
        tmp = tmp->left;
    }
    int res = tmp->data;
    splay(res);
    if (insertFlag) {
        delete1(val);
    }
    return res;

}


int Tree::prev(int val) {
    bool insertFlag = false;
    if (!exists(val)) {
        insert(val);
        insertFlag = true;
    }
    splay(val);
    Node tmp = root->left;
    while (tmp->right != nullptr) {
        tmp = tmp->right;
    }
    int res = tmp->data;
    splay(res);
    if (insertFlag) {
        delete1(val);
    }
    return res;

}


int main() {
    Tree tree;
    string st;
    int value;
    while (cin >> st >> value) {
        if (st == "insert") {

            tree.insert(value);
        }
        else if (st == "delete") {
            tree.delete1(value);
        }
        else if (st == "exists") {
            if (tree.exists(value)) cout << "true" << "\n";
            else cout << "false" << "\n";
        }
        else if (st == "next") {
            if (tree.hasNext(value)) {
                cout << tree.next(value) << "\n";
            } else {
                cout << "none" << "\n";
            }
        }
        else if (st == "prev") {
            if (tree.hasPrev(value)) {
                cout << tree.prev(value) << "\n";

            } else {
                cout << "none" << "\n";
            }
        }
    }

    return 0;


}



