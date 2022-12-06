import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main10164 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, K;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int res;
        int routeR;
        int routeC;
        int[][] dp = new int[R + 1][C + 1];
        dp[0][1] = 1;
        if(K != 0) {
            /*
            1 2
            3 4
            2 = (1, 2);
            R = 2, C = 2, K = 2
            K / C + 1 = 1
            K / C

            R 2, C 3, K 5
            1 2 3
            4 5 6
            5 = (2, 2)
            */
            routeR = (K - 1) / C + 1;
            routeC = K - (C * (routeR - 1));
            for(int i = 1; i <= routeR; i++) {
                for(int j = 1; j <= routeC; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            int route = dp[routeR][routeC];
            dp[routeR][routeC - 1] = 0;
            dp[routeR - 1][routeC] = 1;
            for(int i = routeR; i <= R; i++) {
                for(int j = routeC; j <= C; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            int arrive = dp[R][C];
            res = route * arrive;
        } else {
            for(int i = 1; i <= R; i++) {
                for(int j = 1; j <= C; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            res = dp[R][C];
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}