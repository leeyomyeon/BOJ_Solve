#include  <iostream>

using namespace std;
int N, M;
int arr[1000001], segTree[2097153];
void makeSegTree(int start, int end, int idx) {
    if(start == end) {
        segTree[idx] = arr[start];
        return;
    }
    int mid = (start + end) / 2;
    makeSegTree(start, mid, idx * 2);
    makeSegTree(mid + 1, end, idx * 2 + 1);
    segTree[idx] = max(segTree[idx * 2], segTree[idx * 2 + 1]);
}
int find(int start, int end, int idx, int left, int right) {
    if(right < start || end < left) {
        return 0;
    }
    if(left <= start && end <= right) {
        return segTree[idx];
    }
    int mid = (start + end) / 2;
    int lMax = find(start, mid, idx * 2, left, right);
    int rMax = find(mid + 1, end, idx * 2 + 1, left, right);
    return max(lMax, rMax);
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    makeSegTree(1, N, 1);
    for(int i = M; i <= N - M + 1; i++) {
        int k = find(1, N, 1, i - (M - 1), i + (M - 1));
        cout << k << " ";
    }
}