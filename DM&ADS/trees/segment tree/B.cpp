

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>


using namespace std;

long long arr[500001];
long long tree[500001*4];
int n;
long long sum(int i, int lboard, int rboard, int left, int right) {
	if (lboard > rboard) return 0;
	if (left == lboard && right == rboard) return tree[i];
	int mid = (left + right) / 2;
	long long leftS = sum(i * 2, lboard, min(rboard, mid), left, mid);
	long long rightS= sum(i * 2 + 1, max(lboard, mid + 1), rboard, mid + 1, right);
	return leftS+rightS;
}

void set(int i, int index, int value, int left, int right) {
	if (left == right) {
		tree[i] = value;
		return;
	}
	int mid = (left + right) / 2;
	if (index <= mid) {
		set(i * 2, index, value, left, mid);
	}
	else {
		set(i * 2+1, index, value, mid+1, right);
	}
	tree[i] = tree[i * 2] + tree[i * 2 + 1];

}

void init(int i,int left,int right) {
	if (left == right) tree[i] = arr[left];
	else {
	int mid = (left + right) / 2;
		init(i * 2, left, mid);
		init(i * 2 + 1, mid + 1, right);
		tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}
}

int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	ifstream cin("rsq.in");
	ofstream fout("rsq.out");
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin>> arr[i];
	}
	init(1, 0, n - 1);
	
	while (!cin.eof())
	{
		string command;
		cin >> command;

		if (command == "set") {
			int l;
			long long r;
			cin >> l>> r;
			l--;
			set(1,l,r,0,n-1);
		}
		else {
			int l, r;
			cin >> l>>r;
			if (cin.eof()) break;
			fout << sum(1,l-1,r-1,0,n-1)<<endl;
		}
	}
	fout.close();
	return 0;
}