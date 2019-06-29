#include <string>
#include <iostream>
#include <cmath>
#include <vector>
#include <complex>
#include <algorithm>


#define ll long long
#define PI  3.14159265359

using namespace std;

void fft(vector<complex<double>>& in, bool isBack) {
    int n = in.size();
    if (n == 1) return;

    vector<complex<double>> a1(n / 2);
    vector<complex<double>> b1(n / 2);
    for (int i = 0, j = 0; i < n; i += 2, ++j) {
        a1[j] = in[i];
        b1[j] = in[i + 1];
    }
    fft(a1, isBack);
    fft(b1, isBack);

    double ang = 2 * PI / n;
    if (isBack) {
        ang *= -1;
    }
    complex<double> w(1, 0), wn(cos(ang), sin(ang));
    for (int i = 0; i < n / 2; ++i) {
        in[i] = a1[i] + w * b1[i];
        in[i + n / 2] = a1[i] - w * b1[i];
        if (isBack) {
            in[i] /= 2;
            in[i + n / 2] /= 2;
        }
        w *= wn;
    }
}


int main() {
    string s1, s2;
    vector<complex<double>> a;
    vector<complex<double>> b;
    cin >> s1 >> s2;
    int sign = 1;
    if (s1[0] == '-') {
        sign *= -1;
        for (int i = 1; i < s1.length(); ++i) {
            a.push_back(complex<double>(s1[i] - '0'));
        }
    } else {
        for (auto el : s1) {
            a.push_back(complex<double>(el - '0'));
        }
    }
    if (s2[0] == '-') {
        sign *= -1;
        for (int i = 1; i < s2.length(); ++i) {
            b.push_back(complex<double>(s2[i] - '0'));
        }
    } else {
        for (auto el : s2) {
            b.push_back(complex<double>(el - '0'));
        }
    }
    reverse(a.begin(), a.end());
    reverse(b.begin(), b.end());
    int m = max(a.size(), b.size());
    int size = 1;
    while (size < m) {
        size <<= 1;
    }
    size <<= 1;
    a.resize(size);
    b.resize(size);
    fft(a, false);
    fft(b, false);
    for (int i = 0; i < size; ++i) {
        a[i] *= b[i];
    }
    fft(a, true);
    vector<int> res;
    res.resize(size);
    for (size_t i = 0; i < size; ++i) {
        res[i] = int(a[i].real() + 0.5);
    }
    int carry = 0;
    for (int i = 0; i < size; ++i) {
        res[i] += carry;
        carry = res[i] / 10;
        res[i] %= 10;
    }
    int i = res.size() - 1;
    while (i > 0 && res[i] == 0) {
        --i;
    }
    if (i == 0 && res[i] == 0) {
        printf("0");
        return 0;
    }
    if (sign == -1) {
        printf("-");
    }
    while (i >= 0) {
        printf("%d", res[i]);
        i--;
    }
    return 0;
}


