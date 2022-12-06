import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15989 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 전처리
        dp = new int[10001][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        for(int i = 4; i <= 10000; i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
/*



*/