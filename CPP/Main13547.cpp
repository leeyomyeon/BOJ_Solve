#include "iostream"
#include "cmath"
#include "algorithm"
#include "vector"
#include "map"
using namespace std;
int arr[100001], ans[100001], counter[1000001];
vector<tuple<int, int, int>> query;
int N, M, sqrtN;
// map -> TLE라서 counter array로 변경
bool compare(tuple<int, int, int> t1, tuple<int, int, int> t2) {
    int t1s = get<1>(t1) / sqrtN;
    int t2s = get<1>(t2) / sqrtN;
    if(t1s == t2s) {
        int t1e = get<2>(t1);
        int t2e = get<2>(t2);
        return t1e < t2e;
    }
    return t1s < t2s;
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    cin >> N;
    sqrtN = (int) sqrt(N);
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }
    cin >> M;
    for(int i = 1; i <= M; i++) {
        int s, e;
        cin >> s >> e;
        query.emplace_back(i, s, e);
    }
    sort(query.begin(), query.end(), compare);
    int idx = get<0>(query[0]);
    int s = get<1>(query[0]);
    int e = get<2>(query[0]);
    int res = 0;
    for(int i = s; i <= e; i++) {
        if(counter[arr[i]] == 0) {
            counter[arr[i]] = 1;
            res++;
        } else {
            counter[arr[i]]++;
        }
    }
    ans[idx] = res;
    for(int i = 1; i < M; i++) {
        auto &c = query[i];
        int aidx = get<0>(c);
        int ns = get<1>(c);
        int ne = get<2>(c);
        while(s < ns) {
            counter[arr[s]] -= 1;
            if(counter[arr[s]] == 0) {
                res--;
            }
            s += 1;
        }
        while(s > ns) {
            s -= 1;
            if(counter[arr[s]] == 0) {
                res++;
            }
            counter[arr[s]] += 1;
        }
        while(e < ne) {
            e += 1;
            if(counter[arr[e]] == 0) {
                res++;
            }
            counter[arr[e]] += 1;
        }
        while(e > ne) {
            counter[arr[e]] -= 1;
            if(counter[arr[e]] == 0) {
                res--;
            }
            e -= 1;
        }
        ans[aidx] = res;
    }

    for(int i = 1; i <= M; i++) {
        cout << ans[i] << '\n';
    }
}
