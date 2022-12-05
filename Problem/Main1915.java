import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1915 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static char[][] arr;
    public static int[][] dp;
    public static int N, M;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            String str = br.readLine();
            for(int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1);
            }
        }
        dp[1][1] = arr[1][1] - '0';
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(arr[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        bw.write(Integer.toString(max * max));
        br.close();
        bw.flush();
        bw.close();
    }
}