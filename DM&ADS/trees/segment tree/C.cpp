

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>


using namespace std;
long long inf = numeric_limits<long long>::max();
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

long long arr[100001*100];


int n;
int N;

void push(vector<node>& tree, int i)
{
	if (i >= N)
	{
		tree[i].add = 0;
		if (tree[i].isNew)
		{
			tree[i].isNew = false;
			tree[i].value = tree[i].newValue;
		}
		return;
	}
	if (tree[i].add != 0)
	{
		if (tree[2*i].isNew)
			tree[2*i].newValue += tree[i].add;
		else
			tree[2*i].add += tree[i].add;
		tree[2*i].value += tree[i].add;


		if (tree[2*i+1].isNew)
			tree[2*i+1].newValue += tree[i].add;
		else
			tree[2*i+1].add += tree[i].add;
		tree[2*i+1].value += tree[i].add;


		tree[i].add = 0;
	}
	if (tree[i].isNew)
	{
		tree[2*i].newValue = tree[i].newValue;
		tree[2*i].value = tree[i].newValue;
		tree[2*i].isNew = true;
		//----
		tree[2*i+1].newValue = tree[i].newValue;
		tree[2*i+1].value = tree[i].newValue;
		tree[2*i+1].isNew = true;
		tree[2*i+1].add = 0;
		tree[2 * i].add = 0;
		//-----
		tree[i].isNew = false;
	}
	tree[i].value = min(tree[2*i].value, tree[2*i+1].value);
}
long long minim(vector<node>& tree, int i, int lb, int rb, int l, int r) {
	push(tree,i);
	if (r <= lb || rb <= l)
		return inf;
	if (l <= lb && rb <= r) {
		return tree[i].value;
	}
	int m = (lb + rb) / 2;
	long long left=minim(tree, i * 2, lb, m, l, r);
	long long right=minim(tree, i * 2 + 1, m, rb, l, r);
	return min(left, right);
}



void set(vector<node>& tree, int i, int lb, int rb, int l, int r, long long value) {
	push(tree,i);
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
	set(tree, i * 2, lb, m, l, r, value);
	set(tree, i * 2 + 1, m, rb, l, r, value);
	push(tree,i);

}

void add(vector<node>& tree,int i, int lb, int rb, int l, int r, long long value) {
	push(tree,i);
	if (r<=lb ||  rb<=l)
		return;
	if (l <= lb && rb <= r) {
		tree[i].value += value;
		tree[i].add = value;
		return;
	}
	int m = (lb + rb) / 2;
	add(tree,i * 2, lb, m, l,r,value);
	add(tree, i * 2 + 1, m, rb, l, r, value);
	push(tree,i);
}



void init(vector<node>& tree , int n) {
	for (size_t i = N; i < N + n; i++)
		tree[i].value = arr[i - N];
	for (size_t i = N + n; i < 2 * N; i++)
		tree[i].value = inf;
	for (size_t i = N - 1; i >= 1; i--)
		tree[i].value = min(tree[2 * i].value, tree[2 * i + 1].value);
	for (size_t i = 0; i < 2 * N; i++)
	{
		tree[i].add = 0;
		tree[i].isNew = false;
	}
}

int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	ifstream cin("rmq2.in");
	ofstream fout("rmq2.out");
	cin >> n;
	N = 0;
	for (N = 1; N <n; N *= 2);
	vector<node> tree(2*N);
	for (int i = 0; i < n; i++)
	{
		cin>> arr[i];
	}
	init(tree, n);

	while (!cin.eof())
	{
		string command;
		cin >> command;

		if (command == "set") {
			int l;
			int r;
			long long v;
			cin >> l>> r >>v;
			l--;
			set(tree,1,0,N,l,r,v);
		}
		else if(command=="add"){
			int l, r;
			long long v;
			cin >> l >> r >> v;
			l--;
			add(tree,1,0,N,l,r,v);
		}
		else {
			int l,r;
			cin >> l >> r;
			l--;
			if (cin.eof()) break;
			fout<< minim(tree,1, 0, N, l, r)<<endl;
		}
	}
	fout.close();
	return 0;
}