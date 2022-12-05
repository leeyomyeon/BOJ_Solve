import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2086 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static long[][] fibo = {{1, 1},{1, 0}};
    public static final int MOD = 1000000000;
    public static long start, end;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());
        long[][] res1 = matPow(fibo, start + 1);
        long[][] res2 = matPow(fibo, end + 2);
        bw.write(Long.toString((res2[1][0] - res1[1][0] + MOD) % MOD));
        br.close();
        bw.flush();
        bw.close();
    }

    public static long[][] matPow(long[][] tmp, long exp) {
        if(exp == 1) {
            return tmp;
        } else if(exp == 0) {
            long[][] zero = {{0, 0},{0, 0}};
            return zero;
        }

        long[][] res = matPow(tmp, exp / 2);

        if(exp % 2 == 1) {
            return matMul(matMul(res, res), tmp);
        } else {
            return matMul(res, res);
        }
    }

    public static long[][] matMul(long[][] tmp1, long[][] tmp2) {
        long[][] res = new long[2][2];

        res[0][0] = ( (tmp1[0][0] * tmp2[0][0]) + (tmp1[0][1] * tmp2[1][0]) ) % MOD;
        res[0][1] = ( (tmp1[0][0] * tmp2[0][1]) + (tmp1[0][1] * tmp2[1][1]) ) % MOD;
        res[1][0] = ( (tmp1[1][0] * tmp2[0][0]) + (tmp1[1][1] * tmp2[1][0]) ) % MOD;
        res[1][1] = ( (tmp1[1][0] * tmp2[0][1]) + (tmp1[1][1] * tmp2[1][1]) ) % MOD;

        return res;
    }
}