#include <iostream>

using namespace std;
int N;
int dp[1001];
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    dp[0] = 0;
    dp[1] = 0;
    dp[2] = 1;
    for(int i = 3; i <= N; i++) {
        int start = i - 2, end = 0;
        int mex[1001] = { 0, };
        while(start >= end) {
            mex[dp[start] ^ dp[end]] = 1;   // 존재하지 않는 가장 작은 수 찾기
            start--; end++;
        }
        for(int j = 0; j <= i; j++) {
            if(mex[j] == 0) {
                dp[i] = j;
                break;
            }
        }
    }
    cout << (dp[N] == 0 ? 2 : 1);
}
/*
값을 xor합한게 0이면 2, 아니면 1
dp[i] = i개 점이 있을 때 누가 이기는지 xor한 값
dp[1] = 0
dp[2] = 1
dp[3] = dp[2],dp[1]
dp[4] = dp[0],dp[2]
dp[5] = dp[0],dp[3] / dp[1],dp[2]
...
dp[15] = dp[13]^dp[0] / dp[12]^dp[1] ... dp[7]^dp[6]
*/
