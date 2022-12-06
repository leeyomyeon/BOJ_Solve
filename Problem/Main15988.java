import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15988 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static long[] dp;
    public static int MOD = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        // 전처리
        dp = new long[1000001];
        dp[1] = 1;  // 1
        dp[2] = 2;  // 1 + 1 / 2
        dp[3] = 4;  // 1 + 1 + 1 / 2 + 1 / 1 + 2 / 3
        for(int i = 4; i <= 1000000; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}