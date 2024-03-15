#include <iostream>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    long long res;
    cin >> n;
    res = 1;
    for(int i = 1; i <= n; i++) {
        res *= i;
    }
    cout << res;
}