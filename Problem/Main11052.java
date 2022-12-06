import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main11052 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr, dp;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = arr[1];
        /*
        1 : 1
        2 : dp[1] dp[1] / 2
        3 : dp[1] dp[2] / 3
        4 : dp[1] dp[3] / dp[2] dp[2] / 4
        5 : dp[1] dp[4] / dp[2] dp[3] / 5
        6 : dp[1] dp[5] / dp[2] dp[4] / dp[3] dp[3] / 6
        */
        for(int i = 2; i <= N; i++) {
            int j = 1;
            int k = i - 1;
            dp[i] = arr[i];
            while (j <= i / 2) {
                dp[i] = Math.max(dp[j] + dp[k], dp[i]);
                j++;
                k--;
            }
        }
        // N을 만드는 방법
        bw.write(Integer.toString(dp[N]));
        br.close();
        bw.flush();
        bw.close();
    }
}