#include <iostream>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    string str;
    getline(cin, str);
    for(char & c : str) {
        if(c >= 'a' && c <= 'z') {
            c = (char) ((c - 97 + 13) % 26 + 97);
        } else if(c >= 'A' && c <= 'Z') {
            c = (char) ((c - 65 + 13) % 26 + 65);
        }
    }
    cout << str;
}