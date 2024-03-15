#include  <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T;
    cin >> T;
    while(T-->0) {
        int c, v;
        cin >> c >> v;
        cout << "You get " << c / v << " piece(s) and your dad gets " << c % v << " piece(s).\n";
    }
}