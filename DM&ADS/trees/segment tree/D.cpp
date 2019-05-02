

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>


using namespace std;
int shift = 500001;
int lg[2000000];

struct node {
	int value;
	int left;
	int right;
	int count;
	int sum;
	bool isNew;

	node() {
		sum = 0;
		value = 0;
		left = 0;
		right = 0;
		count = 0;
		isNew = false;
	}

};

long long arr[100001 * 100];


int n;
int N;

void push(vector<node>& tree, int i)
{
	if (i >= N)
	{
		tree[i].left = tree[i].right = tree[i].value;
		tree[i].isNew = false;
		tree[i].count = tree[i].value == 1 ? 1 : 0;
		return;
	}
	if (tree[i].isNew) {
		tree[2 * i].value = tree[i].value;
		tree[2 * i + 1].value = tree[i].value;
		tree[2 * i].isNew = true;
		tree[2 * i + 1].isNew = true;
		tree[i].isNew = false;
		tree[i].sum= tree[i].value * (1 << ((int)lg[N] - (int)lg[ i]));
		tree[2*i].sum = tree[2*i].value * (1 << ((int)lg[N] - (int)lg[2 * i]));
		tree[2*i+1].sum = tree[2 * i + 1].value * (1 << ((int)lg[N] - (int)lg[2 * i + 1]));
		//-----
		tree[i].left = tree[i].right = tree[i].value;
		tree[2 * i].left = tree[2 * i].right = tree[2 * i].value;
		tree[2 * i + 1].left = tree[2 * i + 1].right = tree[2 * i + 1].value;
		tree[2 * i].count = tree[2 * i + 1].count = tree[i].value == 1 ? 1 : 0;
	}

	tree[i].left = tree[2 * i].left;
	tree[i].right =tree[2 * i + 1].right;
	tree[i].sum = tree[2 * i].sum + tree[2 * i + 1].sum;
	if (tree[2 * i].right == 1 && tree[2 * i + 1].left == 1) {
		tree[i].count = tree[2 * i].count + tree[2 * i + 1].count - 1;
	}
	else {
		tree[i].count = tree[2 * i].count + tree[2 * i + 1].count ;
	}
}
long long sum(vector<node>& tree, int i, int lb, int rb, int l, int r) {
	push(tree, i);
	if (r <= lb || rb <= l)
		return 0;
	if (l <= lb && rb <= r) {
		return tree[i].sum;
	}
	int m = (lb + rb) / 2;
	long long left = sum(tree, i * 2, lb, m, l, r);
	long long right = sum(tree, i * 2 + 1, m, rb, l, r);
	return left +right;
}

long long count(vector<node>& tree, int i, int lb, int rb, int l, int r) {
	push(tree, i);
	if (r <= lb || rb <= l)
		return 0;
	if (l <= lb && rb <= r) {
		return tree[i].count;
	}
	int m = (lb + rb) / 2;
	long long left = count(tree, i * 2, lb, m, l, r);
	long long right = count(tree, i * 2 + 1, m, rb, l, r);
	if (tree[2 * i].right == 1 && tree[2 * i + 1].left == 1) {
		return tree[2 * i].count + tree[2 * i + 1].count - 1;
	}
	else {
		return tree[2 * i].count + tree[2 * i + 1].count;
	}
}



void set(vector<node>& tree, int i, int lb, int rb, int l, int r, long long value) {
	push(tree, i);
	if (r <= lb || rb <= l)
		return;
	if (l <= lb && rb <= r) {
		tree[i].value = value;
		tree[i].sum = value * (1 << ((int)lg[N] - (int)lg[i]));
		tree[i].left = tree[i].right = tree[i].value;
		tree[i].isNew = true;
		if (value == 1) {
			tree[i].count = 1;
		}
		else {
			tree[i].count = 0;
		}
		return;
	}
	int m = (lb + rb) / 2;
	set(tree, i * 2, lb, m, l, r, value);
	set(tree, i * 2 + 1, m, rb, l, r, value);
	push(tree, i);

}





int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	ifstream cin("painter.in");
	ofstream fout("painter.out");


	int m;
	cin >> m;
	N = 0;
	n = 1000002;
	for (N = 1; N <n; N *= 2);
	vector<node> tree(2 * N);

	for (int i = 0; i <=N*2+5 ; i++)
		lg[i] = (i > 1 ? lg[i / 2] + 1 : 0);

	while (!cin.eof())
	{
		string command;
		cin >> command;

		if (command == "W") {
			int l;
			int r;
			cin >> l >> r;
			l += shift;
			r = l + r-1;
			l--;
			set(tree, 1, 0, N, l, r, 0);
			fout << count(tree, 1, 0, N, 0, n) << " " << sum(tree, 1, 0, N, 0, n) << endl;
		}
		else if (command == "B") {
			int l, r;
			cin >> l >> r;
			l += shift;
			r = l + r-1;
			l--;
			set(tree, 1, 0, N, l, r, 1);
			fout << count(tree, 1, 0, N, 0, n ) << " " << sum(tree, 1, 0, N, 0, n) << endl;
		}
		else {
			if (cin.eof()) break;
		}
	}
	fout.close();
	return 0;
}