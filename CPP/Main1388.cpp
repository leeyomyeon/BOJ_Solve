#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;
queue<pair<int, int>> q;

int dr[] = {-1, 1, 0, 0};   // |
int dc[] = {0, 0, -1, 1};   // -
int N, M;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    char arr[N][M];
    bool visited[N][M];
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            visited[i][j] = false;
            cin >> arr[i][j];
        }
    }

    int cnt = 0;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            if(!visited[i][j]) {
                visited[i][j] = true;
                cnt++;
                q.push(make_pair(i, j));
                while(!q.empty()) {
                    int r = q.front().first;
                    int c = q.front().second;
                    q.pop();
                    int k = (arr[r][c] == '-' ? 2 : 0);
                    for(int d = 0 + k; d <= 1 + k; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && arr[nr][nc] == arr[r][c]) {
                            visited[nr][nc] = true;
                            q.push(make_pair(nr, nc));
                        }
                    }
                }
            }
        }
    }

    cout << cnt;
}