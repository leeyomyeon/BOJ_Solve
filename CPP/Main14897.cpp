#include "iostream"
#include "algorithm"
#include "vector"
#include "map"
#pragma GCC optimize ("O3")
#pragma GCC optimize ("Ofast")
#pragma GCC optimize ("unroll-loops")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,avx,avx2")

using namespace std;
int arr[1000001], ans[1000001], counter[1000001], comp[1000001];
vector<tuple<int, int, int>> query;
vector<int> v;
int N, M, sqrtN;

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
    sqrtN = 1500;
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
        v.push_back(arr[i]);
    }

    sort(v.begin(), v.end());
    v.erase(unique(v.begin(), v.end()), v.end());
    // v = 경로압축된 벡터
    for(int i = 1; i<= N; i++) {
        comp[i] = lower_bound(v.begin(), v.end(), arr[i]) - v.begin();
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
        if(counter[comp[i]] == 0) {
            counter[comp[i]] = 1;
            res++;
        } else {
            counter[comp[i]]++;
        }
    }
    ans[idx] = res;
    for(int i = 1; i < M; i++) {
        auto &c = query[i];
        int aidx = get<0>(c);
        int ns = get<1>(c);
        int ne = get<2>(c);
        while(s < ns) {
            counter[comp[s]] -= 1;
            if(counter[comp[s]] == 0) {
                res--;
            }
            s += 1;
        }
        while(s > ns) {
            s -= 1;
            if(counter[comp[s]] == 0) {
                res++;
            }
            counter[comp[s]] += 1;
        }
        while(e < ne) {
            e += 1;
            if(counter[comp[e]] == 0) {
                res++;
            }
            counter[comp[e]] += 1;
        }
        while(e > ne) {
            counter[comp[e]] -= 1;
            if(counter[comp[e]] == 0) {
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
/*
comp[]
arr  : 1 3 2 1 3 1 3 2 1 3
comp : 0 2 1 0 2 1 0 2 1 0
*/
