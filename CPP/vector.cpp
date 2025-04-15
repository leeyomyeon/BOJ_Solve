#include <bits/stdc++.h>
using namespace std;
template <class T>
class _vector {
public:
    int _size;
    int capacity;
    T *arr;
    _vector() {
        _size = 0;
        capacity = 32;
        arr = new T[capacity];
    }
    _vector(int k) {
        _size = k;
        capacity = k;
        arr = new T[capacity];
    }
    ~_vector() {
        delete[] arr;
    }
    void clear() {
        delete[] arr;
        _size = 0;
        capacity = 32;
        arr = new T[capacity];
    }
    void resize(int k) {
        T *tmp;
        tmp = new T[k];
        for(int i = 0; i < _size; i++) {
            tmp[i] = arr[i];
        }
        delete[] arr;
        arr = tmp;
        _size = k;
        capacity = k;
    }
    int size() {
        return _size;
    }
    T* begin() {
        return &arr[0];
    }
    T* end() {
        return &arr[0] + _size;
    }
    void push_back(T val) {
        if(_size == capacity) {
            resize(_size * 2);
            _size /= 2;
        }
        arr[_size++] = val;
    }
    void pop_back() {
        _size--;
    }
    T& operator [](int idx) {
        return arr[idx];
    }
    T operator [](int idx) const {
        return arr[idx];
    }
};