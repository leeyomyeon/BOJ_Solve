#include <iostream>

using namespace std;
int N, res;
int arr[9], selected[9];
bool visited[9];
void backTrack(int idx);
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    cin >> N;

    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    backTrack(1);
    cout << res;
}
void backTrack(int idx) {
    if(idx == N + 1) {
        // to do something
        int sum = 0;
        for(int i = 1; i <= N - 1; i++) {
            sum += abs(selected[i] - selected[i + 1]);
        }
        res = max(res, sum);
        return;
    }
    for(int i = 1; i <= N; i++) {
        if(!visited[i]) {
            visited[i] = true;
            selected[idx] = arr[i];
            backTrack(idx + 1);
            visited[i] = false;
        }
    }
}
