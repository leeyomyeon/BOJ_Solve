import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main9465 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr;
    public static int[][] dp;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[2][N + 1];
            dp = new int[2][N + 1];
            for(int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 0 = 위 시작, 1 = 아래 시작
            dp[0][0] = 0;
            dp[1][0] = 0;
            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];
            for(int i = 2; i <= N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
            }
            bw.write(Math.max(dp[0][N], dp[1][N]) + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}