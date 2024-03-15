#include <iostream>
#include <queue>
using namespace std;
pair<int, int> visit[100001];
int N, K;
int MAX = 10000000;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    fill(visit, visit + 100001, pair(MAX, 1));
    cin >> N >> K;
    visit[N].first = 0;
    queue<pair<int, int>> queue;
    queue.emplace(N, 0);

    while(!queue.empty()) {
        int current = queue.front().first;
        int cnt = queue.front().second;
        queue.pop();
        if(visit[current].first < cnt) {
            continue;
        }
        for(int i = -1; i <= 1; i++) {
            int next = current + (i == 0 ? current : i);
            int nCnt = cnt + 1;
            if(next >= 0 && next <= 100000) {
                if(visit[next].first > nCnt) { // 값 갱신 시
                    visit[next].first = nCnt;
                    visit[next].second = 1;
                    queue.emplace(next, nCnt);
                } else if(visit[next].first == nCnt) { // 같은 값이면
                    visit[next].second += 1;
                    queue.emplace(next, nCnt);
                }
            }
        }
    }
    cout << visit[K].first << "\n" << visit[K].second;
}
