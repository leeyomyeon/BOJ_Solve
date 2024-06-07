#include <iostream>
#include "cmath"

#define ll long long
using namespace std;
int N;
ll arr[11], res[10]; // arr[i][j] = i번째 자릿수에 0 ~ 9가 몇개나 있는지 count
void getPart();
void calc(ll n);
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    cin >> N;
    getPart();
    calc(N);

    for(ll r : res) {
        cout << r << ' ';
    }
}
void getPart() {
    for(int i = 1; i <= 10; i++) {   // 자릿수
        ll k = (ll) pow(10, i - 1) * i;
        arr[i] = k;
    }
}
void calc(ll n) {
    int nk = (int) log10(n);
    double t = 1.11111111111111111;
    res[0] -= (int) (t * pow(10, nk));  // 자릿수에 맞춰 0의 갯수 빼줌
    while(nk >= 0) {
        ll k = (ll) pow(10, nk);
        ll r = (n / k) % 10; // 자리수
        for(int i = 0; i < 10 && r != 0; i++) {
            res[i] += arr[nk] * r;
        }
        for(int i = 0; i < r; i++) {
            res[i] += k;
        }
        ll remain = n % k;
        res[r] += remain + 1;
        nk--;
    }
}
