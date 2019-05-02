

#include <iostream>
#include <fstream>
#include <algorithm>


using namespace std;

int a[10000001];
int b[20000003];
int c[20000003];
long long sums[10000001];

int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	ifstream fin("sum0.in");
	int V = ((1 << 30) - 1);
	int P = ((1 << 16) - 1);
	int n, x, y;
	cin >> n >> x >> y;
	cin >> a[0];
	int m, z, t;
	cin >> m >> z >> t;
	cin >> b[0];
	sums[0] = a[0];
	c[0] = b[0] % n;
	for (int i = 1; i < n; i++) {
		a[i] = (x*a[i - 1] + y) & (P);
		sums[i] = sums[i - 1] + a[i];
	}
	for (int i = 1; i < 2 * (m)+1; i++) {
		long long d = z * b[i - 1] + t;
		b[i] = d &(V);
		c[i] = b[i] % n;
	}
	long long res = 0;
	for (int i = 0; i < m; i++) {
		int ind = min(c[i * 2], c[2 * i + 1]) - 1;
		res += sums[max(c[i * 2], c[2 * i + 1])]-sums[ind];
	}
	ofstream fout("sum0.out");
	cout << res;
	fout.close();
	system("pause");
	return 0;
}