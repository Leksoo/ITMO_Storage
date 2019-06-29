#include <string>
#include <vector>
#include <algorithm>

#define ll long long

using namespace std;


int main() {
    const int size = 1000000 + 10;
    vector<int> primes(size);
    for (int j = 0; j <size ; ++j) {
        primes[j]=j;
    }
    for (int i = 2; i*i <= size; ++i) {
        if (primes[i] == i) {
            ll k = 1LL * i * i;
            while (k <= size-1) {
                primes[k] = i;
                k += i;
            }
        }
    }
    int n;
    scanf("%d",&n);
    vector<int> res;
    for (int i = 0; i < n; ++i) {
        int a;
        scanf("%d",&a);
        while (a != 1) {
            res.push_back(primes[a]);
            a /= primes[a];
        }
        sort(res.begin(),res.end());
        for (auto& el:res) {
            printf("%d ",el);
        }
        printf("\n");
        res.clear();
    }
    return 0;
}
