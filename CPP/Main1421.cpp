#include  <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, c, w;
    cin >> n >> c >> w;
    int arr[50] = {0, };
    int MAX = 0;
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
        MAX = max(MAX, arr[i]);
    }
    long long res = 0;
    for(int k = 1; k <= MAX; k++) {
        long long sum = 0;
        long long cut = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] >= k) {
                int tmp;
                if(arr[i] % k > 0) {
                    tmp = arr[i] / k;
                } else {
                    tmp = arr[i] / k - 1;
                }
                // 자르는 비용이 얻는 비용보다 더 클 경우 자르지 않고 넘어감
                if(c * tmp >= (arr[i] - (arr[i] % k)) * w) {
                    continue;
                }
                cut += tmp;
                sum += (arr[i] - (arr[i] % k)) * w;
            }
        }
        cut *= c;
        res = max(sum - cut, res);
    }
    cout << res;
}
/*
3 2 1
2
3
2
*/