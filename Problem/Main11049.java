import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main11049 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int[] p;
    public static int[][] dp;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        p = new int[N + 1];
        dp = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(i != N) {
                p[i - 1] = r;
            } else {
                p[i - 1] = r;
                p[i] = c;
            }
        }

        for(int r = 1; r <= N - 1; r++) {
            for(int i = 1; i <= N - r; i++) {
                int j = i + r;
                int min = Integer.MAX_VALUE;
                for(int k = i; k <= j - 1; k++) {
                    min = Math.min(min, dp[i][k] + dp[k + 1][j] + p[i - 1]*p[k]*p[j]);
                }
                dp[i][j] = min;
            }
        }
        bw.write(dp[1][N] + "");
        br.close();
        bw.flush();
        bw.close();
    }
}