#include <iostream>
#include "algorithm"
#include <vector>
using namespace std;
int N, K;
int arr[10001];
vector<int> v;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    cin >> N >> K;
    if(K >= N) {
        cout << "0";
    } else {
        for(int i = 0; i < N; i++) {
            cin >> arr[i];
        }
        sort(arr, arr + N);
        for(int i = 1; i < N ; i++) {
            int diff = abs(arr[i - 1] - arr[i]);
            v.push_back(diff);
        }
        sort(v.begin(), v.end());
        int size = v.size() - (K - 1);
        int sum = 0;
        for(int i = 0; i < size; i++) {
            sum += v[i];
        }
        cout << sum;
    }
}
