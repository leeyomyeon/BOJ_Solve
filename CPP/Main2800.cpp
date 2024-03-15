#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>
using namespace std;
char arr[201];
int selected[11];
vector<pair<int, int>> list;
vector<string> res;
int N;
void makeString() {
    string str;
    bool chk[201] = {false, };
    for(int i : selected) {
        if(i == -1) {
            break;
        }
        chk[list[i].first] = true;
        chk[list[i].second] = true;
    }
    for(int i = 0; i < 201; i++) {
        if(arr[i] == '\0') {
            break;
        }
        if(chk[i]) {
            continue;
        }
        str += arr[i];
    }
    res.push_back(str);
}
void comb(int idx, int cnt, int K) {
    if(cnt == K) {
        makeString();
        return;
    }
    for(int i = idx; i < N; i++) {
        selected[cnt] = i;
        comb(i + 1, cnt + 1, K);
    }
}
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> arr;
    stack<pair<char, int>> stack;

    for(int i = 0; i < 201; i++) {
        if(arr[i] == '\0') {
            break;
        } else if(arr[i] == '(') {
            stack.emplace(arr[i], i);
        } else if(arr[i] == ')') {
            list.emplace_back(stack.top().second, i);
            stack.pop();
        }
    }
    N = list.size();
    for(int i = 1; i <= N; i++) {
        fill(selected, selected + 11, -1);
        comb(0, 0, i);
    }
    sort(res.begin(), res.end());
    res.erase(unique(res.begin(), res.end()), res.end());
    for(int i = 0; i < res.size(); i++) {
        cout << res[i] << "\n";
    }
}