#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
using namespace std;
// SCC
vector<vector<int>> res;
vector<int> list[10001];
int visited[10001], scc[10001];
bool finished[10001];
stack<int> s;
int V, E, id = 1;
int dfs(int current);
bool compare(vector<int> a, vector<int> b) {
    return a[0] < b[0];
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> V >> E;
    for(int i = 0; i < E; i++) {
        int u, v;
        cin >> u >> v;
        list[u].push_back(v);
    }
    for(int i = 1; i <= V; i++) {
        sort(list[i].begin(), list[i].end());
    }
    for(int i = 1; i <= V; i++) {
        if(scc[i] == 0) {
            dfs(i);
        }
    }
    cout << res.size() << '\n';
    sort(res.begin(), res.end(), compare);
    for(const auto& k : res) {
        for(auto n : k) {
            cout << n << ' ';
        }
        cout << "-1\n";
    }
}
int dfs(int current) {
    scc[current] = id++;
    s.push(current);
    int pid = scc[current];
    for(int next : list[current]) {
        if(scc[next] == 0) {
            pid = min(pid, dfs(next));
        } else if(!finished[next]) {
            pid = min(pid, scc[next]);
        }
    }

    if(pid == scc[current]) {   // 사이클이 생긴 경우
        vector<int> resList;
        while(current) {
            int y = s.top(); s.pop();
            resList.push_back(y);
            finished[y] = true;
            if(y == current) {
                break;
            }
        }
        sort(resList.begin(), resList.end());
        res.push_back(resList);
    }
    return pid;
}