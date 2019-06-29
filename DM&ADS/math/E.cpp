#include <string>
#include <iostream>
#include <cmath>
#include <vector>


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

ll modpow(ll base, ll deg, ll mod) {
    base %= mod;
    ll res = 1;
    while (deg > 0) {
        if (deg & 1) {
            res = mulmod(res,base,mod);
        }
        base = mulmod(base,base,mod);
        deg >>= 1;
    }
    return res;
}

int main() {
    ll n, e, C;
    cin >> n >> e >> C;
    //factorization
    pair<ll, ll> dividers;
    for (int i = 2; i <= sqrt(n); ++i) {
        if (n % i == 0) {
            dividers = make_pair(i, n / i);
            break;
        }
    }
    ll eulerFun = (dividers.first - 1) * (dividers.second - 1);
    // find d
    ll x, y;
    gcde(e, eulerFun, x, y);
    x = (x % eulerFun + eulerFun) % eulerFun;
    ll M = modpow(C,x,n);
    cout << M;



}
