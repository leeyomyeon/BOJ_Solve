#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int arr[100001];
vector<int> bucket[84];
int N, M, sqrtN;
void update(int target, int value);
int find(int left, int right, int value);
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    sqrtN = 1200;
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    cin >> M;
    for(int i = 1; i <= N; i++) {
        bucket[i / sqrtN].push_back(arr[i]);
    }
    for(auto & i : bucket) {
        sort(i.begin(), i.end());
    }
    for(int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        if(a == 1)  {
            update(b, c);
        } else {
            int d; cin >> d;
            int res = find(b, c, d);
            cout << res << '\n';
        }
    }
}
void update(int target, int value) {
    int idx = target / sqrtN;   // 버킷 위치
    // 값 변경
    for(int i = 0; i < bucket[idx].size(); i++) {
        if(bucket[idx][i] == arr[target]) {
            bucket[idx].erase(bucket[idx].begin() + i);
            break;
        }
    }
    for(int i = 0; i <= bucket[idx].size(); i++) {
        if(i == bucket[idx].size() || bucket[idx][i] >= value) {
            bucket[idx].insert(bucket[idx].begin() + i, value);
            break;
        }
    }
    arr[target] = value;
}
int find(int left, int right, int value) {
    int res = 0;

    while(left % sqrtN != 0 && left <= right) { // 왼쪽 구간
        res += arr[left] > value ? 1 : 0;
        left++;
    }
    while((right + 1) % sqrtN != 0 && left <= right) { // 오른쪽 구간
        res += arr[right] > value ? 1 : 0;
        right--;
    }
    while(left <= right) {
        int idx = left / sqrtN;
        res += bucket[idx].size() - (upper_bound(bucket[idx].begin(), bucket[idx].end(), value) - bucket[idx].begin());
        left += sqrtN;
    }
    return res;
}
