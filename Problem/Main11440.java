import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main11440 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static long[][] fibo = {{1, 1},{1, 0}};
    public static final int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        long N = Long.parseLong(br.readLine());
        long[][] res = matPow(fibo, N);
        bw.write(Long.toString(res[0][0] * res[1][0] % MOD));
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