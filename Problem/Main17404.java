import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17404 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, MAX;
    public static int[][] arr;
    public static int[][] dp;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][3];
        dp = new int[N + 1][3];
        MAX = 1000 * 1000 + 1;
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        int res = MAX;
        for(int c = 0; c < 3; c++) { // c = 0, R / c = 1, G / c = 2, B
            dp[1][c] = arr[1][c];
            dp[1][(c + 1) % 3] = MAX;
            dp[1][(c + 2) % 3] = MAX;

            for(int i = 2; i <= N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
            }
            res = Math.min(res, Math.min(dp[N][(c + 1) % 3], dp[N][(c + 2) % 3]));
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}