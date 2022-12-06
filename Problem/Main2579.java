import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2579 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if(n >= 1) {
            dp[1] = Math.max(arr[0] + arr[1], arr[1]);  // 그냥 오는지 이전 계단을 밟고 오는지
        }
        if(n >= 2) {
            dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]); // 1칸짜리 계단을 밟고 왔는지 2칸짜리 계단을 밟고왔는지
        }
        if(n >= 3) {
            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);
            }
        }

        System.out.println(dp[n]);
    }
}
