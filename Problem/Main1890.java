import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1890 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static int[][] arr;
    public static long[][] dp;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        dp = new long[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = 1;

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if((i == N && j == N) || dp[i][j] == 0) {
                    continue;
                }

                int nr = i + arr[i][j];
                int nc = j + arr[i][j];

                if(nr <= N) {
                    dp[nr][j] += dp[i][j];
                }
                if(nc <= N) {
                    dp[i][nc] += dp[i][j];
                }
            }
        }

        bw.write(Long.toString(dp[N][N]));
        br.close();
        bw.flush();
        bw.close();
    }
} 