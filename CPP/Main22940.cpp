#include "iostream"
#include "vector"
#include "algorithm"
#include "queue"
using namespace std;
int N;
double arr[7][8], res[7];
// 가우스 소거법
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N + 1; j++) {
            cin >> arr[i][j];
        }
    }
    for(int i = 0; i < N - 1; i++) {
        for(int j = i + 1; j < N; j++) {
            double diff = arr[j][i] / arr[i][i];
            for(int k = 0; k < N + 1; k++) {
                arr[j][k] = arr[j][k] - arr[i][k] * diff;
            }
        }
    }
    for(int i = N - 1; i >= 0; i--) {
        for(int j = N - 1; j > i; j--) {
            arr[i][N] = arr[i][N] - (arr[i][j] * res[j]);
        }
        res[i] = arr[i][N] / arr[i][i];
    }
    for(int i = 0; i < N; i++) {
        cout << res[i] << " ";
    }
}
