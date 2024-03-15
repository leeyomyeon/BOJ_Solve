#include <stdio.h>

int path[1000001] = {0,0,1,1};

int f(int n) {
    if(n <= 1)return 0;
    int r1, r2;
    r1 = f(n / 3) + n % 3 + 1;
    r2 = f(n / 2) + n % 2 + 1;
    if (r1 < r2) {
        path[n - n % 3] = n / 3;
        if (n % 3)
            path[n] = n - n % 3;
        return r1;
    }
    else{
        path[n - n % 2] = n / 2;
        if (n % 2)
            path[n] = n - n % 2;
        return r2;
    }
}

int main() {
    int n;
    scanf("%d",&n);
    printf("%d\n",f(n));
    while (n) {
        printf("%d ",n);
        n = path[n];
    }
    return 0;
}