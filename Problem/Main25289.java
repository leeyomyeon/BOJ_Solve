import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main25289 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] dp;
    public static int[] arr;
    public static int N;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(arr[i], max);
        }
        int res = Integer.MIN_VALUE;
        // 숫자의 최대치는 넘길 수 없음
        for(int cnt = -Math.abs(max); cnt <= Math.abs(max); cnt++) {
            dp = new int[101];
            for(int i = 0; i < N; i++) {
                if(arr[i] - cnt < 1 || arr[i] - cnt > 100) {
                    // 해당 숫자부터 시작해서 공비를 더한 결과가 숫자의 범위 벗어나면 무조건 1개(자기자신)
                    dp[arr[i]] = 1;
                } else {
                    // 범위 안이면 현재 수에 공비를 계산한 값에 1개 더함
                    dp[arr[i]] = dp[arr[i] - cnt] + 1;
                }
                res = Math.max(res, dp[arr[i]]);
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}