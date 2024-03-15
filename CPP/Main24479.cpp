#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M, R;
vector<int> v[100001];
int seq[100001];
int cnt;
void dfs(int current) {
    for(int next : v[current]) {
        if(seq[next] == 0) {
            seq[next] = ++cnt;
            dfs(next);
        }
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M >> R;
    for(int i = 0; i < M; i++) {
        int from, to;
        cin >> from >> to;
        v[from].push_back(to);
        v[to].push_back(from);
    }
    for(int i = 1; i <= N; i++) {
        sort(v[i].begin(), v[i].end());
    }
    cnt = 1;
    seq[R] = 1;
    dfs(R);
    for(int i = 1; i <= N; i++) {
        cout << seq[i] << '\n';
    }
}