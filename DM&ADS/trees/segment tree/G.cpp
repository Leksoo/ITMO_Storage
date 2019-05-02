

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>


using namespace std;


int n;
int shift = 2 * 100000 + 1;
long long inf = -(1 << 30);
int N;
int res = inf;
int flag = 0;
int y = 0;

struct node {
	int x;
	int YDown;
	int YUp;
	int isOpen;
	node(int x, int YDown, int YUp, int isOpen) {
		this->x = x;
		this->YDown = YDown;
		this->YUp = YUp;
		this->isOpen = isOpen;
	}
	node() {
		x = 0;
		YDown = 0;
		YUp = 0;
		isOpen = 0;
	}
};

struct nodet {
	int value;
	int add;

	nodet() {
		value = inf;
		add = 0;
	}


};

vector<node> v;

struct sort_class
{
	bool operator() (node i, node j)
	{
		if(i.x<j.x) return true;
		if (i.x > j.x) return false;
		if (i.isOpen >= j.isOpen) return true;
		return false;
	}
} sort_object;






void push(vector<nodet>& tree, int i)
{
	if (i >= N)
	{
		tree[i].add = 0;
		return;
	}
	if (tree[i].add != 0)
	{
		tree[2 * i].add += tree[i].add;
		tree[2 * i].value += tree[i].add;

		tree[2 * i + 1].add += tree[i].add;
		tree[2 * i + 1].value += tree[i].add;


		tree[i].add = 0;
	}
	
	tree[i].value = max(tree[2 * i].value, tree[2 * i + 1].value);
}
long long minim(vector<nodet>& tree, int i, int lb, int rb, int l, int r) {
	push(tree, i);
	if (r <= lb || rb <= l)
		return inf;
	if (l <= lb && rb <= r) {
		return tree[i].value;
	}
	int m = (lb + rb) / 2;
	long long left = minim(tree, i * 2, lb, m, l, r);
	long long right = minim(tree, i * 2 + 1, m, rb, l, r);
	return max(left, right);
	push(tree, i);
}



void add(vector<nodet>& tree, int i, int lb, int rb, int l, int r, long long value) {
	push(tree, i);
	if (r <= lb || rb <= l)
		return;
	if (l <= lb && rb <= r) {
		tree[i].value += value;
		tree[i].add = value;
		return;
	}
	int m = (lb + rb) / 2;
	add(tree, i * 2, lb, m, l, r, value);
	add(tree, i * 2 + 1, m, rb, l, r, value);
	push(tree, i);
}



void search(vector<nodet>& tree, int i, int lb, int rb, int l, int r, long long max) {
	push(tree, i);
	if (lb + 1 == rb) {
		if (tree[i].value > res) {
			flag = 1;
			y = lb + 1;
			res = tree[i].value;
		}
		return;
	}
	int m = (lb + rb) / 2;
	if (max == tree[2 * i + 1].value) {
		search(tree, i * 2 + 1, m, rb, l, r, max);

	}
	else {
		search(tree, i * 2, lb, m, l, r, max);
	}
	push(tree, i);

}

void init(vector<nodet>& tree, int k) {
	for (size_t i = N; i < N + k; i++)
		tree[i].value = 0;
	for (size_t i = N + k; i < 2 * N; i++)
		tree[i].value = inf;
	for (size_t i = N - 1; i >= 1; i--)
		tree[i].value = max(tree[2 * i].value, tree[2 * i + 1].value);
	for (size_t i = 0; i < 2 * N; i++)
	{
		tree[i].add = 0;
	}
}

int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	cin >> n;

	int m = 1000007;
	for (N = 1; N <m; N *= 2);
	for (int i = 0; i < n; i++)
	{
		int x1, x2, y1, y2;
		cin >> x1 >> y1 >> x2 >> y2;
		x1 += shift;
		x2 += shift;
		y1 += shift;
		y2 += shift;
		v.push_back(node(x1,y1,y2,1));
		v.push_back(node(x2, y1, y2, -1));
	}
	sort(v.begin(), v.end(), sort_object);
	
	int x = 0;



	vector<nodet> tree(N*2);
	init(tree, m);
	for (int i = 0; i < v.size(); i++)
	{
		node a = v[i];
		add(tree, 1, 0, N, a.YDown - 1, a.YUp, a.isOpen);
		search(tree, 1, 0, N, 0, m, tree[1].value);
		if(flag == 1) {
			x = a.x;
			flag = 0;
		}
	}
	cout << res << "\n" << x-shift << " "<< y-shift;
	//system("pause");
	return 0;
}