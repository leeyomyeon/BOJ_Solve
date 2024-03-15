#include <iostream>
#include <vector>
using namespace std;
int N, root;
long long depthCnt;
vector<int> v[500001];
bool visited[500001];
void dfs(int current, int depth) {
    bool isLeaf = true;
    for(int next : v[current]) {
        if(!visited[next]) {
            isLeaf = false;
            visited[next] = true;
            dfs(next, depth + 1);
        }
    }

    if(isLeaf) {
        depthCnt += depth;
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N;
    for(int i = 0; i < N - 1; i++) {
        int from, to;
        cin >> from >> to;
        v[from].push_back(to);
        v[to].push_back(from);
    }
    root = 1;
    depthCnt = 0;
    visited[root] = true;
    dfs(1, 0);
    cout << (depthCnt % 2 == 1 ? "Yes" : "No");
    // 성원이가 먼저 시작하고 형석이가 나중에 시작한다
    // depthCnt + leafCnt
}