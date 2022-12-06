import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main7786 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static long[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        arr = new long[10];
        arr[1] = 45;
        getPart();
        /*
        1999999999 = 82000000000
        2000000000

        999999999 = 40500000000
        */
        long r = sum(L - 1);
        long c = sum(U);
        bw.write((c - r)+"");
        br.close();
        bw.flush();
        bw.close();
    }
    public static void getPart() {
        /*  11 12 13 14 2 3 4 5
        0 ~ 10^1 - 1 = 45 * 10^0
        0 ~ 10^2 - 1 = 45 * 10^1 + 45 * 10 = 900
        0 ~ 10^3 - 1 = 45 * 10^2 + 900 * 10 = 13500
        0 ~ 10^4 - 1 = 45 * 10^3 + 13500 * 10 = 180000
        ...
        0 ~ 10^n - 1 = 45 * 10^(n-1) + SUM(0~10^(n-2) - 1) * 10
        */
        for(int i = 2; i < 10; i++) {
            arr[i] = 45 * (long) Math.pow(10, i - 1) + arr[i - 1] * 10;
            // 0 ~ 10^n - 1 = 45 * 10^(n-1) + SUM(0~10^(n-2) - 1) * 10
        }
    }
    public static long sum(long n) {
        /*
        n == 43284
        00000 ~ 09999
        10000 ~ 19999
        20000 ~ 29999
        30000 ~ 39999
        arr[4] * 4 + n / 40000 + 1
        k 1 2 3
        n - (40000)
        */
        if(n <= 0) {
            return 0;
        }
        int pow = (int) Math.log10(n);         // 자리수 10의 n승
        long k = (long) Math.pow(10, pow);    // 현재 숫자의 단위
        long r = n / k;                         // 맨 앞자리수 r = 4
        long remain = (n - (r * k));               // 나머지 수

        long res = (arr[pow] * r) + (r * (remain + 1));
        for(int i = 1; i < r; i++) {
            res += (i * k);
        }

        return res + sum(remain);
    }
}
