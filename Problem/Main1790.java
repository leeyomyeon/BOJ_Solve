import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1790 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, K;
    public static long maxLen;
    public static long[] arr;
    public static void main(String[] args) throws Exception {
        arr = new long[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        int res;
        if (K > maxLen) {
            res = -1;
        } else {
            int idx = -1;
            for (int i = 1; i <= 9; i++) {
                if (K <= arr[i]) {
                    idx = i;
                    break;
                }
            }
            int start = (int) Math.pow(10, idx - 1);
            int find = (int) (K - arr[idx - 1] - 1);
            int r = start + find / idx; // r번째 수
            int c = find % idx; // r번째 수의 c번째 자릿수
            res = Integer.toString(r).charAt(c) - '0';
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void init() {
        maxLen = 0;
        for(int i = 1; i <= 9; i++) {
            arr[i] = arr[i - 1] + (i * 9 * (long) Math.pow(10, i - 1));
            if(Math.pow(10, i - 1) <= N && N < Math.pow(10, i)) {
                maxLen = (arr[i - 1] + (N - ((long)Math.pow(10, i - 1) - 1)) * i);
            }
        }
    }
}