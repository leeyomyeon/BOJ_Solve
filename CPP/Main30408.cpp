#include <iostream>
#include <set>
#include <queue>
#define ll long long
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    ll N, M;
    cin >> N >> M;
    bool res = false;
    set<ll> set;
    queue<ll> q;
    q.push(N);
    while(!q.empty()) {
        ll current = q.front();
        q.pop();
        if(current == M) {
            res = true;
            break;
        }
        if(current % 2 == 0 && set.count(current / 2) == 0 && current / 2 > 0) {
            set.insert(current);
            q.push(current / 2);
        } else if(current % 2 == 1) {
            ll nc1 = (current - 1) / 2;
            ll nc2 =  (current - 1) / 2 + 1;
            if(nc1 >= 1 && set.count(nc1) == 0) {
                set.insert(nc1);
                q.push(nc1);
            }
            if(nc2 >= 1 && set.count(nc2) == 0) {
                set.insert(nc2);
                q.push(nc2);
            }
        }
    }
    cout << (res ? "YES" : "NO");
}