#include <iostream>

using namespace std;
int N;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    int res = 0;
    for(int i = 0; i < N; i++) {
        int t;
        cin >> t;
        res ^= t;
    }
    cout << (res != 0 ? "koosaga" : "cubelover");
}
