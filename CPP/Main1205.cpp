#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, S, P;
struct Score {
    int score;
    int rank;
    int num;
    Score(int s, int n) {
        this->score = s;
        this->rank = 0;
        this->num = n;
    }
};
bool compare(Score i, Score j) {
    if(i.score == j.score) {
        return i.num < j.num;
    }
    return i.score > j.score;
}
vector<Score> v;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> S >> P;
    for(int i = 0; i < N; i++) {
        int k;
        cin >> k;
        v.emplace_back(k, 0);
    }
    v.emplace_back(S, 1);
    sort(v.begin(), v.end(), compare);

    int rank = 1;
    int idx = 0;
    int score = v[0].score;
    int res = -1;
    for(int i = 0; i < v.size(); i++) {
        if(v[i].score == score) {
            v[i].rank = rank;
            idx++;
        } else {
            rank = ++idx;
            v[i].rank = rank;
            score = v[i].score;
        }
        if(v[i].score == S && v[i].num == 1 && idx <= P) {
            res = v[i].rank;
            break;
        }
    }
    cout << res;
}
/*
3
2 3
2
1 2
1 3
*/