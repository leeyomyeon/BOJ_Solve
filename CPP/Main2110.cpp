#include "iostream"
#include "vector"
#include "algorithm"
#define ll long long
using namespace std;

int N, C;
vector<ll> v;
int main() {
    cin >> N >> C;
    for(int i = 0; i < N; i++) {
        ll k;
        cin >> k;
        v.push_back(k);
    }
    sort(v.begin(), v.end());
    ll start = 1;
    ll end = v[N - 1] + 1;
    ll res = 0;
    while(start < end) {
        ll mid = (start + end) / 2;
        int cnt = 1;
        ll p = v[0];
        for(int i = 1; i < N; i++) {
            if(v[i] - p >= mid ) {
                cnt++;
                p = v[i];
            }
        }
        if(cnt < C) {
            end = mid;
        } else {
            start = mid + 1;
            res = max(res, mid);
        }
    }
    cout << res;
}
