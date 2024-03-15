#include <iostream>
#include <vector>
#include <queue>
using namespace std;
vector<int> v[100001];
bool fan[100001];
int N, M, S;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    int start = 1;
    for(int m = 0; m < M; m++) {
        int from, to;
        cin >> from >> to;
        v[from].push_back(to);
    }
    cin >> S;
    for(int s = 0; s < S; s++) {
        int k;
        cin >> k;
        fan[k] = true; // 팬클럽이 있는 위치
    }
    queue<pair<int, int>> queue;
    queue.emplace(start, fan[start] ? 1 : 0);
    bool res = false;
    while(!queue.empty()) {
        int current = queue.front().first;
        int chk = queue.front().second;
        queue.pop();
        if(v[current].empty() && chk == 0) {
            res = true;
            break;
        }
        for(int next : v[current]) {
            int nchk = chk;
            if(fan[next]) {
                nchk = chk + 1;
            }
            queue.emplace(next, nchk);
        }
    }


    cout << (res ? "yes" : "Yes");
}