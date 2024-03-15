#include <iostream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;
int N, M;
long cost[1001];
int track[1001];
vector<pair<int, int>> arr[1001];
queue<pair<int, long>> q;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    for(int m = 0; m < M; m++) {
        int from, to, w;
        cin >> from >> to >> w;
        arr[from].emplace_back(to, w);
    }
    int start, end;
    cin >> start >> end;
    fill(cost, cost + N + 1, 10000000001);
    cost[start] = 0;
    q.emplace(start, 0);
    while(!q.empty()) {
        int current = q.front().first;
        long totCost = q.front().second;
        q.pop();
        if(totCost > cost[current]) {
            continue;
        }
        for(auto p : arr[current]) {
            int next = p.first;
            int w = p.second;
            if(cost[next] > totCost + w) {
                cost[next] = totCost + w;
                track[next] = current;
                q.emplace(next, cost[next]);
            }
        }
    }
    stack<int> stack;
    stack.push(end);
    cout << cost[end] << "\n";
    while(stack.top() != start) {
        stack.push(track[stack.top()]);
    }
    cout << stack.size() << "\n";
    while(!stack.empty()) {
        cout << stack.top() << " ";
        stack.pop();
    }
}