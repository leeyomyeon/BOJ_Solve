#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
int N;
int arr[200001];
vector<pair<int, int>> v;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N;
    int MAX = 0;
    cin >> arr[1];
    int oneCnt = arr[1] == 1 ? 1 : 0, twoCnt = arr[1] == 2 ? 1 : 0;
    for(int i = 2; i <= N; i++) {
        cin >> arr[i];
        if(arr[i] == 1 && arr[i] == arr[i - 1]) {
            // 이전 값하고 같은 값일시
            oneCnt++;
        } else if(arr[i] == 2 && arr[i] == arr[i - 1]) {
            twoCnt++;
        } else if(arr[i] == 1 && arr[i] != arr[i - 1]) { // 이전값이 2이고 현재값이 1일때
            v.emplace_back(2, twoCnt);
            oneCnt = 1;
            twoCnt = 0;
        } else if(arr[i] == 2 && arr[i] != arr[i - 1]) { // 이전값이 1이고 현재값이 2일때
            // 지금까지 연속된 1의 개수 v에 insert
            v.emplace_back(1, oneCnt);
            oneCnt = 0;
            twoCnt = 1;
        }
        if(i == N) {
            if(oneCnt > 0) {
                v.emplace_back(1, oneCnt);
            } else {
                v.emplace_back(2, twoCnt);
            }
        }
    }
    if(N == 1) {
        cout << arr[1];
    } else {
        int e = 0;
        for(auto p : v) {
            if(p.first == 1 && p.second % 2 == 1) {
                e += (p.second - 1) / 2;
                MAX = max(MAX, e);
                e = (p.second - 1) / 2;
            } else {
                e += p.first == 1 ? p.second / 2 : p.second;
                MAX = max(MAX, e);
            }
        }
        int res = (int) pow(2, int(log2((double) MAX)));
        cout << res * 2;
    }
}