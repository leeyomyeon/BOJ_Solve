import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15990 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long[][] dp;
    public static int N;
    public static int MOD = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        init();
        for(int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(Long.toString((dp[1][n] + dp[2][n] + dp[3][n]) % MOD));
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void init() {
        dp = new long[4][100001];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[1][3] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;
        for(int i = 4; i <= 100000; i++) {
            dp[1][i] = (dp[2][i - 1] + dp[3][i - 1]) % MOD;
            dp[2][i] = (dp[1][i - 2] + dp[3][i - 2]) % MOD;
            dp[3][i] = (dp[1][i - 3] + dp[2][i - 3]) % MOD;
        }
    }
}