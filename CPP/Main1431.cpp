#include <iostream>
#include <utility>
#include "vector"
#include "algorithm"
#include "string"

using namespace std;
struct Serial {
    string str;
    int len;
    int sum;
};
bool compare(Serial o1, Serial o2) {
    if(o1.len == o2.len) {
        if(o1.sum == o2.sum) {
            return o1.str < o2.str;
        }
        return o1.sum < o2.sum;
    }
    return o1.len < o2.len;
}
int N;
vector<Serial> list;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    cin >> N;
    for(int i = 0; i < N; i++) {
        string s;
        cin >> s;
        int len = s.length();
        int sum = 0;
        for(char c : s) {
            if(c >= '0' && c <= '9') {
                sum += (c - '0');
            }
        }
        list.push_back({s, len, sum});
    }
    sort(list.begin(), list.end(), compare);
    for(const auto& current : list) {
        cout << current.str << '\n';
    }
}
