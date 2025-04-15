#include <bits/stdc++.h>
using namespace std;
template <class T>
class _stack {
public:
    struct Node {
        T val;
        Node *next;
        Node() {}
        Node(T val) : val(val), next(nullptr){}
    };
    Node *head;
    int _size;
    _stack() {
        head = nullptr;
        _size = 0;
    }
    void push(T val) {
        Node *tmp = new Node(val);
        if(head == nullptr) {
            head = tmp;
        } else {
            tmp->next = head;
            head = tmp;
        }
        _size++;
    }
    void pop() {
        Node *tmp = head;
        head = head->next;
        delete tmp;
        _size--;
    }
    bool empty() const { return _size == 0; }
    T top() const { return head->val; }
    int size() const { return _size; }
};