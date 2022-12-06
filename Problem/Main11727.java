import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main11727 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[] dp;
    public static int N;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        if(N == 1) {
            bw.write("1");
        } else if(N == 2) {
            bw.write("3");
        } else {
            dp = new int[N + 1];
            dp[1] = 1;
            dp[2] = 3;
            for(int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007;
            }
            bw.write(Integer.toString(dp[N]));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}