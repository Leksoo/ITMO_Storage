
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

	node() {
		value = 0;
		isNew = false;
		newValue = 0;
	}

};

long long arr[100001 * 100];


int n;
int N;
int currIndex=0;
long long currMin=0;

void push(vector<node>& tree, int i)
{
	if (i >= N)
	{	
		if (tree[i].isNew)
		{
			tree[i].isNew = false;
			tree[i].value = max(tree[i].newValue,tree[i].value);
		}
		return;
	}
	if (tree[i].isNew)
	{
		/*
		if(i>=N/2){

			tree[2 * i].newValue = tree[i].newValue;
			tree[2 * i].value = max(tree[i].newValue, tree[2*i].value);
			tree[2 * i].isNew = true;
			//----
			tree[2 * i + 1].newValue = tree[i].newValue;
			tree[2 * i + 1].value = max(tree[i].newValue, tree[i].value);
			tree[2 * i + 1].isNew = true;
			//-----
			tree[i].isNew = false;
		}
		*/
		//else {
			tree[2 * i].newValue = max(tree[i].newValue, tree[2*i].value);
			tree[2 * i].value = max(tree[i].newValue, tree[2*i].value);
			tree[2 * i].isNew = true;
			//----
			tree[2 * i + 1].newValue = max(tree[i].newValue, tree[2*i+1].value);
			tree[2 * i + 1].value = max(tree[i].newValue, tree[2*i+1].value);
			tree[2 * i + 1].isNew = true;
			//-----
			tree[i].isNew = false;
	//	}
	}
	tree[i].value = min(tree[2 * i].value, tree[2 * i + 1].value);
}


void search(vector<node>& tree, int i, int lb, int rb, int l, int r, long long min) {
	push(tree, i);
	if (lb+1==rb) {
		currIndex = lb;
		return;
	}
	int m = (lb + rb) / 2;
	if (min == tree[2 * i+1].value) {
		search(tree, i * 2 + 1, m, rb, l, r, min);
		
	}
	else {
		search(tree, i * 2, lb, m, l, r, min);
	}
	push(tree, i);

}


long long minim(vector<node>& tree, int i, int lb, int rb, int l, int r) {
	push(tree, i);
	if (r <= lb || rb <= l)
		return inf;	
	if (l <= lb && rb <= r) {
		if (tree[i].value <= currMin) {
			currMin = tree[i].value;
			search(tree, i, lb, rb, l, r, currMin);
		}
		return tree[i].value;
	}
	int m = (lb + rb) / 2;
	long long left = minim(tree, i * 2, lb, m, l, r);
	long long right = minim(tree, i * 2 + 1, m, rb, l, r);
	return min(left, right);
}



void set(vector<node>& tree, int i, int lb, int rb, int l, int r, long long value) {
	push(tree, i);
	if (r <= lb || rb <= l)
		return;
	if (l <= lb && rb <= r) {
		long long val = max(tree[i].value, value);
		tree[i].newValue = val;
		tree[i].isNew = true;
		tree[i].value = val;
		return;
	}
	int m = (lb + rb) / 2;
	set(tree, i * 2, lb, m, l, r, value);
	set(tree, i * 2 + 1, m, rb, l, r, value);
	push(tree, i);

}






void init(vector<node>& tree, int n) {
	for (size_t i = N; i < N + n; i++)
		tree[i].value = arr[i - N];
	for (size_t i = N + n; i < 2 * N; i++)
		tree[i].value = inf;
	for (size_t i = N - 1; i >= 1; i--)
		tree[i].value = min(tree[2 * i].value, tree[2 * i + 1].value);
	for (size_t i = 0; i < 2 * N; i++)
	{
		tree[i].isNew = false;
	}
}

int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	cin >> n;
	int m;
	cin >> m;
	for (N = 1; N <n; N *= 2);
	vector<node> tree(2 * N);
	init(tree, n);

	for (int i = 0; i < m; i++)
	{
		string command;
		cin >> command;

		if (command == "defend") {
			int l;
			int r;
			long long v;
			cin >> l >> r >> v;
			l--;
			set(tree, 1, 0, N, l, r, v);
		}
		if (command == "attack") {
			currMin = std::numeric_limits<long long>::max();
			int l, r;
			cin >> l >> r;
			l--;
			long long a=minim(tree, 1, 0, N, l, r);
			bool b = a == currMin ? true : false;
			cout << a << " " << currIndex+1 << "\n";
		}
	}
	return 0;
}
/*
10 17defend 1 1 45defend 10 10 2defend 1 10 32defend 2 9 34defend 4 5 55defend 5 5 2defend 1 1 22attack 1 1attack 1 2attack 1 3attack 1 4attack 1 5attack 1 6attack 1 7attack 1 8attack 1 9attack 1 10*/