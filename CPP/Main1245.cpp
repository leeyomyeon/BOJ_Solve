#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;
int N, M;
int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};
// N(1 < N ≤ 100), M(1 < M ≤ 70)
int arr[100][70];
bool visited[100][70] = {false, };
bool peak = true;
void dfs(int r, int c) {
    for(int d = 0; d < 8; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] > 0) {
            if(arr[r][c] < arr[nr][nc]) { // 더 큰곳이 있다면
                peak = false;
            }
            if(!visited[nr][nc] && arr[nr][nc] == arr[r][c]) {  // 같은 높이인 봉우리 탐색
                visited[nr][nc] = true;
                dfs(nr, nc);
            }
        }
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            cin >> arr[i][j];
        }
    }
    int cnt = 0;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            if(!visited[i][j] && arr[i][j] > 0) {
                peak = true;
                dfs(i, j);
                if(peak) {
                    cnt++;
                }
            }
        }
    }

    cout << cnt;
}
/*
5 5
1 3 1 0 0
0 1 0 0 0
3 0 3 4 0
0 2 1 0 0
0 0 0 0 1

4

1 5
1 1 1 0 1
2


*/