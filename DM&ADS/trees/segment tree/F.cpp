

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>


using namespace std;

int u[1000105];
int v[1000105];
int ans[100010005];
int table[30][1000107];
int n;
int lg[1000105];
int a[1000105];
int fl(int a) {
	if (a == 1) return 0;
	else {
		return fl(a / 2) + 1;
	}
}

int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	ifstream cin("sparse.in");
	ofstream fout("sparse.out");
	int m,a1;
	cin >> n>>m>>a1;
	a[0] = a1;
	for (int i = 1; i < n; i++)
	{
		a[i] = (23 * a[i - 1] + 21563) % 16714589;
	}
	for (int i = 0; i <= n; i++)
		lg[i] = (i > 1 ? lg[i / 2] + 1 : 0);
	for (int p = 0; (1 << p) <= n; p++) {
		for (int i = 0; i + (1 << p) <= n; i++)
			table[p][i] = (p ? min(table[p - 1][i], table[p - 1][i + (1 << p - 1)]) : a[i]);
	}
	cin>> u[1] >> v[1];
	int start;
	int end;
	int log;
	for (int i = 1; i <= m+5; i++)
	{
		start = u[i] - 1;
		end = v[i] - 1;
		if (start > end) {
			swap(start, end);
		}
		int p = lg[end - start + 1];
		ans[i]= min(table[p][start], table[p][end - (1 << p) + 1]);
		u[i+1] = ((17 * u[i] + 751 + ans[i] + 2 * (i)) % n) + 1;
		v[i+1] = ((13 * v[i] + 593 + ans[i] + 5 * (i)) % n) + 1;

	}
	fout << u[m] <<" "<< v[m] <<" "<< ans[m];
	fout.close();
	return 0;
}