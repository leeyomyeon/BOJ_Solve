#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;
struct Point {
    int x;
    int y;
    Point(int x, int y) {
        this-> x = x;
        this-> y = y;
    }
};
Point start = Point(0, 0);
vector<Point> list;
stack<Point> s;
int CCW(Point p1, Point p2, Point p3) {
    int res = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
    return res;
}
bool compare(Point a, Point b) {
    if(a.y == b.y) {
        return b.x > a.x;
    }
    return b.y < a.y;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T;
    cin >> T;
    for(int t = 0; t < T; t++) {
        int n;
        cin >> n;
        for(int i = 0; i < n; i++) {
            int x, y;
            cin >> x >> y;
            list.emplace_back(x, y);
        }
        sort(list.begin(), list.end(), compare); //y좌표가 가장 큰것, y좌표가 같으면 x좌표가 작은것
        start = list[0];
        s.push(start);
        sort(list.begin(), list.end(), [](Point a, Point b){
            int ccw = CCW(start, a, b);
            if(ccw) {
                return ccw > 0;
            } else if(a.y != b.y) {
                return a.y > b.y;
            } else {
                return a.x < b.x;
            }
        }); // 시작점 기준 반시계방향으로 정렬
        s.push(list[1]);
        for(int i = 2; i < list.size(); i++) {
            while(s.size() >= 2) {
                Point p1 = s.top(); // 전
                s.pop();
                Point p2 = s.top(); // 전전
                if(CCW(p2, p1, list[i]) > 0) {
                    s.push(p1);
                    break;
                }
            }
            s.push(list[i]);
        }
        cout << s.size() << '\n';
        s.push(start);
        while(s.size() != 1) {
            Point p = s.top();
            s.pop();
            cout << p.x << " " << p.y << '\n';
        }
        s.pop();
        list.clear();
    }
}