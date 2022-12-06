import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main10942 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] arr;
    public static int N, M;
    public static boolean[][] dp;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        dp = new boolean[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {   // i = start;
            dp[i][i] = true;
            for(int j = i + 1; j <= N; j++) {   // j = end;
                boolean isOK = true;
                for(int start = i, end = j; end - start >= 0; start++, end--) {
                    if(arr[start] != arr[end]) {
                        isOK = false;
                        break;
                    }
                }
                if(isOK) {
                    dp[i][j] = true;
                }
            }
        }
        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(dp[from][to]) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}