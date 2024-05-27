#include "iostream"
#include "vector"
#include "algorithm"
#include "queue"
using namespace std;
int N;
vector<pair<int, int>> v;
bool compare(pair<int, int> p1, pair<int, int> p2) {
    return p1.first == p2.first ? p1.second < p2.second : p1.first < p2.first;
}

priority_queue<int, vector<int>, greater<>> pq;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    for(int i = 0; i < N; i++) {
        int s, t;
        cin >> s >> t;
        v.emplace_back(s, t);
    }

    sort(v.begin(), v.end(), compare);

    pq.push(v[0].second);

    for(int i = 1; i < N; i++) {
        int nextS = v[i].first; // 다음에 있는 강의 시작시간
        if(pq.top() <= nextS) { // 시작시간 이전에 있던 강의면 꺼냄
            pq.pop();
        }
        pq.push(v[i].second);// 끝 시간 기준으로 다시 넣어줌
    }
    cout << pq.size();
}
/*

5
1 5
2 4
3 7
3 8
4 5

1       5
  2   4
    3      7
    3         8
      4 5
*/
