import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main9527 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long A, B;
    public static long[] D, S;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = new long[55];
        S = new long[55];
        D[0] = 1;
        S[0] = 1;
        for(int i = 1; i < 55; i++) {
            D[i] = (long) Math.pow(2, i) + S[i - 1];
            S[i] = S[i - 1] + D[i];
        }
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        bw.write(Long.toString(getDigit(B) - getDigit(A - 1)));
        br.close();
        bw.flush();
        bw.close();
    }
    public static long getDigit(long num) {
        if(num <= 0) {
            return 0;
        }
        if(num == 1) {
            return 1;
        }
        long res = 0;
        int idx = 0;
        for(int i = 0; i < 55; i++) {
            if(num < (long) Math.pow(2, i) - 1) {
                num -= ((long) Math.pow(2, i - 1) - 1);
                idx = i - 1;
                break;
            }
        }
        res += S[idx - 1];  // 2 ^ idx승까지의 갯수 구하기
        res += num;     // 맨 앞자리 1의개수만큼 더해주고 1부터 num까지의 자릿수 합 재귀로 계산
        return res + getDigit(num - 1);
    }
}
/*
idx = 0 / 1
 1 = 1      1       D[0]

idx = 1 / 4
 2 = 10
 3 = 11     3(2 + 1) D(1) = 2^1 + D[0]

idx = 2 / 12
 4 = 100
 5 = 101
 6 = 110
 7 = 111    8(4 + 4) D(2) = 2^2 + D[1] + D[0]

idx = 3 / 32
 8 = 1000
 9 = 1001
10 = 1010
11 = 1011
12 = 1100
13 = 1101
14 = 1110
15 = 1111   20(8 + 12)

idx = 4 / 80
16 = 10000
17 = 10001
18 = 10010
19 = 10011
20 = 10100
21 = 10101
22 = 10110
23 = 10111
24 = 11000
25 = 11001
26 = 11010
27 = 11011
28 = 11100
29 = 11101
30 = 11110
31 = 11111  48

 */
