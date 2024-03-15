#include <iostream>

using namespace std;
int N, M;
int arr[100001];
int find(int target) {
    if(arr[target] == target) {
        return target;
    }
    return arr[target] = find(arr[target]);
}
void u_f(int a, int b) {
    int x = find(a);
    int y = find(b);
    if(x != y) {
        arr[y] = x;
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    for(int i = 1; i <= N; i++) {
        arr[i] = i;
    }
    /*
    트리는 사이클이 없어야하며 모든 노드들이 연결되있어야 하고 N개의 노드들은 N - 1개의 간선을 갖는다
    */
    int conn = 0;
    int cut = 0;
    for(int m = 0; m < M; m++) {
        int from, to;
        cin >> from >> to;
        if(find(from) != find(to)) {
            conn++; // 몇개가 연결되어 있는지
            u_f(from, to);
        } else {    // 두 시냅스가 이미 연결되어 있으면 연결안함(끊음)
            cut++;
        }
    }
    // N - 1 > 트리를 구성하는 간선의 개수
    // conn > 이미 연결된 간선 갯수
    // N - 1 - conn > 추가로 연결해야 할 간선 수
    cout << N - 1 - conn + cut;
}