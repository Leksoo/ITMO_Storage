#include <string>
#include <iostream>
#include <cmath>
#include <vector>

#define ll long long

using namespace std;


int main() {
    const int size = 2 * 10000000 + 10;
    vector<int> primes(size);
    for (int j = 0; j < size; ++j) {
        primes[j] = j;
    }
    for (int i = 2; i * i <= size; ++i) {
        if (primes[i] == i) {
            ll k = 1LL * i * i;
            while (k <= size - 1) {
                primes[k] = i;
                k += i;
            }
        }
    }
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) {
        int a;
        scanf("%d", &a);
        if (primes[a] == a) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;

        }
    }
    return 0;
}
