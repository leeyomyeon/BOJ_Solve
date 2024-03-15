#include <iostream>
#include <set>
using namespace std;
int N, K, M, MAX;
int arr[51];
char selected[7];
char list[21];
set<char> s;
void backTrack(int idx, int cnt) {
    if(cnt == K) {  // K개 골랐으면
        int alphabet = 532741;  // antic
        for(int i = 0; i < K; i++) {
            alphabet |= 1 << (selected[i] - 'a');
        }
        int get = 0;
        for(int i = 0; i < N; i++) {
            if((arr[i] & alphabet) == arr[i]) {
                get++;
            }
        }
        MAX = max(MAX, get);
        return;
    }
    for(int i = idx; i < M; i++) {
        selected[cnt] = list[i];
        backTrack(i + 1, cnt + 1);
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> K;
    if(K < 5) {
        cout << 0;
    } else {
        for(int i = 0; i < N; i++) {
            string str;
            cin >> str;
            for(char j : str) {
                if(j == '\0') break;
                arr[i] |= 1 << (j - 'a');
                if(j == 'a' || j == 'n' || j == 't' || j == 'i' || j == 'c') continue;
                if(!s.count(j)) {
                    s.insert(j);
                }
            }
        }
        M = 0;
        for(char c : s) {
            list[M] = c;
            M++;
        }
        K -= 5;
        MAX = 0;
        if(K >= M) {
            cout << N;
        } else {
            backTrack(0, 0);
            cout << MAX;
        }
    }
}