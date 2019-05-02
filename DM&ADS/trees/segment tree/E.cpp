

#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>


using namespace std;

struct matrix
{
	int b1[2][2];

	matrix() {}

	matrix(int a, int b, int c, int d)
	{
		b1[0][0] = a;
		b1[0][1] = b ;
		b1[1][0] = c ;
		b1[1][1] = d ;
	}

};



int n;
int r;
int N;

matrix null(1, 0, 0, 1);


matrix mul(const matrix &  b1, const matrix &   b2)
{
	return matrix(
		(b1.b1[0][0] * b2.b1[0][0] + b1.b1[0][1] * b2.b1[1][0])%r,
		(b1.b1[0][0] * b2.b1[0][1] + b1.b1[0][1] * b2.b1[1][1]) % r,
		(b1.b1[1][0] * b2.b1[0][0] + b1.b1[1][1] * b2.b1[1][0]) % r,
		(b1.b1[1][0] * b2.b1[0][1] + b1.b1[1][1] * b2.b1[1][1]) % r
	);
}

matrix res( vector<matrix> &  tree ,int i, int lb, int rb, int left, int right)
{
	if (right <= lb || rb <= left)
		return null;
	if (left <= lb && rb <= right)
		return tree[i];
	return mul( 
		res(tree, 2 * i, lb, (lb + rb) / 2, left, right),

		res(tree, 2 * i + 1, (lb + rb) / 2, rb, left, right)
	);
}




int main(int argc, char **argv) {
	ios_base::sync_with_stdio(false);
	ifstream cin("crypto.in");
	ofstream fout("crypto.out");
	int m;
	cin >> r>> n>>m;

	for (N = 1; N < n; N *= 2);

	vector<matrix> tree(2 * N);

	

	for (int i = N; i < N + n; i++) {
		matrix p;
		cin >> p.b1[0][0] >> p.b1[0][1] >> p.b1[1][0] >>p.b1[1][1];
		tree[i] = p;
	}
	for (int i = N + n; i < 2 * N; i++)
		tree[i] = null;
	for (int i = N - 1; i >= 1; i--)
		tree[i] = mul(tree[2 * i], tree[2 * i + 1]);


	for (int i = 0; i < m; i++)
	{
		int left, right;
		cin >> left >> right;
		left--;
		matrix ans = res(tree, 1, 0,N, left, right);
		fout << ans.b1[0][0] << " " << ans.b1[0][1] << '\n' << ans.b1[1][0] << " " << ans.b1[1][1] << '\n';

	}
	fout.close();
	return 0;
}