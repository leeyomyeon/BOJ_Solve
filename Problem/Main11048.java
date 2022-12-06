import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main11048 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static StringBuffer sb = new StringBuffer();
    public static int[][] arr, dp;
    public static int R, C;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R + 1][C + 1];
        dp = new int[R + 1][C + 1];
        for(int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= C; j++) {
                if(i == 1 && j == 1) {
                    dp[1][1] = arr[1][1];
                }
                arr[i][j] = Integer.parseInt(st.nextToken());
                int top = arr[i][j] + dp[i - 1][j];
                // 하단
                int bottom = arr[i][j] + dp[i][j - 1];
                // 대각
                int mid = arr[i][j] + dp[i - 1][j - 1];
                dp[i][j] = Math.max(top, Math.max(bottom, mid));
            }
        }
        bw.write(Integer.toString(dp[R][C]));
        br.close();
        bw.flush();
        bw.close();
    }
}