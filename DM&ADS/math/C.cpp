#include <string>
#include <iostream>
#include <cmath>

#define ll long long

using namespace std;

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

bool millerRabinTest(ll a) {
    if (a == 2 || a == 3) {
        return true;
    }
    if (a < 2 || a % 2 == 0) {
        return false;
    }
    ll a2 = a - 1;
    ll countTwo = 0;
    while (a2 % 2 == 0) {
        a2 /= 2;
        countTwo++;
    }
    for (int p = 0; p < 20; ++p) {
        ll random = 2 + (rand() % (a - 2));
        ll x = modpow(random, a2, a);
        if (x == 1 || x == a - 1) {
            continue;
        }
        for (int k = 0; k < countTwo - 1; ++k) {
            x = modpow(x, 2, a);
            if (x == 1) {
                return false;
            }
            if (x == a - 1) {
                break;
            }
        }
        if (x != a - 1) {
            return false;
        }
    }
    return true;

}

int main() {
    srand(2331412);
    int n;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        ll a;
        cin >> a;
        if (millerRabinTest(a)) {
            printf("YES\n");
        } else {
            printf("NO\n");
        }
    }
    return 0;
}

