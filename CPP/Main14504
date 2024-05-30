#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;

int arr[100001];
vector<int> bucket[317];
int N, M;
int sqrtN;
void update(int target, int value);
int find(int left, int right, int value);
int bsearch(int idx, int value);
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    sqrtN = (int) sqrt(N);
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
        cin >> a;
        if(a == 1) { // find
            int d;
            cin >> b >> c >> d;
            int res = find(b, c, d);
            cout << res << '\n';
        } else {    // update
            cin >> b >> c;
            update(b, c);

        }
    }
}
int bsearch(int idx, int value) {
    int start = 0;
    int end = bucket[idx].size() - 1;
    while(start <= end) {
        int mid = (start + end) / 2;
        if(bucket[idx][mid] > value) {
            end = mid;
        } else if(bucket[idx][mid] < value) {
            start = mid + 1;
        } else {
            return mid;
        }
    }
}
void update(int target, int value) {
    int idx = target / sqrtN;   // 버킷 위치
    // 값 변경
    int updateIdx = bsearch(idx, arr[target]);
    bucket[idx][updateIdx] = value;
    arr[target] = value;
    sort(bucket[idx].begin(), bucket[idx].end());
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
