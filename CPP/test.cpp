#include <bits/stdc++.h>
using namespace std;
const int SIZE = 1 << 12;
char buffer[SIZE];
int idx;
char read() {
    if(idx == SIZE) {
        int res = fread(buffer, sizeof(char), SIZE, stdin);
        if(res == -1){
            buffer[0] = -1;
        }
        idx = 0;
    }
    return buffer[idx++];
}
int nextInt() {
    int ret = 0;
    char c = read();
    while(c <= 32) {
        c = read();
    }
    bool neg = (c == '-' );
    if(neg) {
        c = read();
    }
    do {
        ret = (ret << 3) + (ret << 1) + (c & 15);
    } while ((c = read()) > 32);
    return neg ? ~ret + 1 : ret;
}
int N, M;
int arr[101];
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    N = nextInt(); M = nextInt();
    for(int i = 1; i <= N; i++) {   // init
        arr[i] = i;
    }
    for(int q = 0; q < M; q++) {    // query
        int l = nextInt(); int r = nextInt();
        // 2 3 4 5
        if(l != r) {
            int tmp[r - l + 1];
            for(int i = 0; i <= r - l; i++) {
                tmp[i] = arr[l + i];
            }
            for(int i = r - l; i >= 0; i--) {
                arr[r - i] = tmp[i];
            }
        }
    }
    for(int i = 1; i <= N; i++) {
        cout << arr[i] << ' ';
    }
}