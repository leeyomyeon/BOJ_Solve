#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    int arr[101] = { 0, };
    while(m-->0) {
        int i, j, k;
        cin >> i >> j >> k;
        for(int l = i; l <= j; l++) {
            arr[l] = k;
        }
    }
    for(int i = 1; i <= n; i++) {
        cout << arr[i] << " ";
    }
}