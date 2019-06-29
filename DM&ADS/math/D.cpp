#include <string>
#include <iostream>
#include <cmath>

#define ll long long

using namespace std;

ll gcde(ll a, ll b, ll& x, ll& y) {
    if (a == 0L) {
        x = 0L;
        y = 1L;
        return b;
    }
    ll x1, y1;
    ll d = gcde(b % a, a, x1, y1);
    x = y1 - (b / a) * x1;
    y = x1;
    return d;
}

ll mulmod(ll a, ll b, ll mod) {
    ll res = 0;
    while (a != 0) {
        if (a & 1) res = (res + b) % mod;
        a >>= 1;
        b = (b << 1) % mod;
    }
    return res;
}

int main() {
    ll a, b, n, m;
    cin >> a >> b >> n >> m;
    ll max = n * m;
    ll n1 = max / n;
    ll m1 = max / m;
    ll x, y;
    gcde(n1, n, x, y);
    x = (x % n + n) % n;
    ll res = mulmod(mulmod(a, n1, max), x, max);
    gcde(m1, m, x, y);
    x = (x % m + m) % m;
    res += mulmod(mulmod(b, m1, max), x, max);
    cout << (res%max);


    return 0;
}