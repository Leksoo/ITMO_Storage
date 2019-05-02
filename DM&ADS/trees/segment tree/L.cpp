

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>


using namespace std;

long long arr[100101];
long long tree[1001001 * 4];
int n;
long long res = 0;
bool flag = false;
long long inf = (1 << 30);



long long minim(int i, int lboard, int rboard, int left, int right) {
	if (lboard > rboard) return inf;
	if (left == lboard && right == rboard) {
		long long ss = tree[i];
		return ss;
	}
	int mid = (left + right) / 2;
	long long leftS = minim(i * 2, lboard, min(rboard, mid), left, mid);
	long long rightS = minim(i * 2 + 1, max(lboard, mid + 1), rboard, mid + 1, right);
	return min(leftS , rightS);
}

void upd(int i, int index, int value, int left, int right) {
	if (left == right) {
		tree[i] = value;
		return;
	}
	int mid = (left + right) / 2;
	if (index <= mid) {
		upd(i * 2, index, value, left, mid);
	}
	else {
		upd(i * 2 + 1, index, value, mid + 1, right);
	}
	tree[i] = min(tree[i * 2], tree[i * 2 + 1]);

}
void upd2(int i, int index, int value, int left, int right) {
	if (left == right) {
		tree[i] = value;
		return;
	}
	int mid = (left + right) / 2;
	if (index <= mid) {
		upd2(i * 2, index, value, left, mid);
	}
	else {
		upd2(i * 2 + 1, index, value, mid + 1, right);
	}
	tree[i] = min(tree[i * 2], tree[i * 2 + 1]);

}

void init(int i, int left, int right) {
	if (left == right) tree[i] = arr[left];
	else {
		int mid = (left + right) / 2;
		init(i * 2, left, mid);
		init(i * 2 + 1, mid + 1, right);
		tree[i] = min(tree[i * 2], tree[i * 2 + 1]);
	}
}

int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	ifstream cin("parking.in");
	ofstream fout("parking.out");
	int m;
	cin >> n >> m;
	for (int i = 0; i <= n; i++)
	{
		arr[i] = i+1;
	}
	init(1,0,n-1);
	for (int i = 0; i < m; i++)
	{
		string command;
		cin >> command;

		if (command == "enter") {
			int i;
			cin >> i;
			i--;
			res = minim(1, i, n - 1, 0, n - 1);
			if ( res != inf) {
				upd(1, res-1, inf, 0, n - 1);
			}
			else {
				res=minim(1, 0, i - 1, 0, n - 1);
				upd(1, res-1, inf, 0, n - 1);
			}
			fout << res << "\n";
		}
		else {
			int i;
			cin >> i;
			upd2(1, i-1, i, 0, n - 1);
		}
	}
	fout.close();
	return 0;
}