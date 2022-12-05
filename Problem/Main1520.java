import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1520 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int R, C;
    public static int[][] arr;
    public static int[][] dp;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        dp = new int[R][C];

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;  // 0도 경우의 수기 때문에 -1로 초기화
            }
        }
        int res = dfs(0, 0);
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static int dfs(int r, int c) {
        if(r == R - 1 && c == C - 1) {
            return 1;
        }
        if(dp[r][c] == -1) {
            dp[r][c] = 0;
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[r][c] > arr[nr][nc]) {
                    dp[r][c] += dfs(nr, nc);
                }
            }
        } 
        return dp[r][c];
    }
}