#include <iostream>
#include <vector>
#include <queue>
#define MAX 1000000000
using namespace std;
int stoe[1001], etos[1001];
vector<pair<int, int>> list[1001];
vector<pair<int, int>> reverseList[1001];
int N, M, X, res;
void dijk(int start, bool trig);
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    cin >> N >> M >> X;
    for(int i = 0; i < M; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        list[u].emplace_back(v, w);
        reverseList[v].emplace_back(u, w);
    }
    fill(stoe, stoe + 1001, MAX);
    fill(etos, etos + 1001, MAX);
    dijk(X, true);
    dijk(X, false);
    for(int i = 1; i <= N; i++) {
        res = max(res, stoe[i] + etos[i]);
    }
    cout << res;
}
void dijk(int start, bool trig) {
    queue<int> q;
    q.push(start);
    trig ? stoe[start] = 0 : etos[start] = 0;
    while(!q.empty()) {
        int current = q.front(); q.pop();

        for(auto next : trig ? list[current] : reverseList[current]) {
            if(trig ? stoe[next.first] > stoe[current] + next.second : etos[next.first] > etos[current] + next.second) {
                trig ? stoe[next.first] = stoe[current] + next.second : etos[next.first] = etos[current] + next.second;
                q.push(next.first);
            }
        }
    }
}
