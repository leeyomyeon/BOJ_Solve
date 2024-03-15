#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T;
    cin >> T;
    while(T-->0) {
        int l, r;
        cin >> l >> r;
        int res;
        if(l == 0) {
            if(r == 1 || r == 2) {
                res = 2;
            } else if (r == 3) {
                res = 5;
            } else {
                res = r;
            }
        } else if(l == 1 && r == 2) {
            res = 3;
        } else if(l == 2 && r == 3) {
            res = 4;
        } else {
            res = r;
        }
        cout << res << '\n';
    }
}
/*
0123 4 5 6  7  8  9  10  11  12  13   14
1248163264128256512102420484096819216384
 0 ~  1  2
 0 ~  2  2
 0 ~  3  5

 0 ~  4  4
 0 ~  5  5
 0 ~  6  6
 ...
 0 ~  r  r
-------------
 1 ~  2  3

 1 ~  3  3
 1 ~  4  4
 1 ~  5  5
 ...
 1 ~  r  r
-------------
 2 ~  3  4

 2 ~  4  4
 2 ~  5  5
 2 ~  6  6
 ...
 2 ~  r  r
-------------
 3 ~  4  4

*/