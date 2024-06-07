#include "iostream"
#include "algorithm"
#include "vector"
#pragma GCC optimize ("O3")
#pragma GCC optimize ("Ofast")
#pragma GCC optimize ("unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,avx,avx2")

using namespace std;
int arr[100001];
vector<int> segTree[262144];

int N, M;
void makeSegTree(int start, int end, int idx);
int find(int start, int end, int idx, int left, int right, int target);
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    makeSegTree(1, N, 1);
    cin >> M;
    int res = 0;
    for(int q = 0; q < M; q++) {
        int a, b, c; cin >> a >> b >> c;
        res = find(1, N, 1, a ^ res, b ^ res, c ^ res);
        cout << res << '\n';
    }
}
void makeSegTree(int start, int end, int idx) {
    if(start == end) {
        segTree[idx].push_back(arr[start]);
        return;
    }
    int mid = (end + start) / 2;
    makeSegTree(start, mid, idx << 1);
    makeSegTree(mid + 1, end, idx << 1 | 1);
    segTree[idx].insert(segTree[idx].end(), segTree[idx << 1].begin(), segTree[idx << 1].end());
    segTree[idx].insert(segTree[idx].end(), segTree[idx << 1 | 1].begin(), segTree[idx << 1 | 1].end());
    sort(segTree[idx].begin(), segTree[idx].end());
}
int find(int start, int end, int idx, int left, int right, int target) {
    if(right < start || end < left) {
        return 0;
    }
    if(left <= start && end <= right) {
        int t = upper_bound(segTree[idx].begin(), segTree[idx].end(), target) - segTree[idx].begin();
        return segTree[idx].size() - t;
    }

    int mid = (start + end) / 2;
    return find(start, mid, idx << 1, left, right, target) + find(mid + 1, end, idx << 1 | 1, left, right, target);
}
