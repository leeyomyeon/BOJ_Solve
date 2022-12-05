import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1654 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int K, N;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];
        long max = Integer.MIN_VALUE;
        for(int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        long res = search(1, max + 1);

        bw.write(Long.toString(res - 1));
        br.close();
        bw.flush();
        bw.close();
    }
    public static long search(long start, long end) {
        long res = 0;
        while(start < end) {
            long mid = (start + end) / 2;   // 중간값 찾기
            long cnt = 0;
            for(int i = 0; i < K; i++) {
                cnt += arr[i] / mid;
            }

            if(cnt < N) {
                end = mid;
            } else {
                start = mid + 1;
                res = start;
            }
        }

        return res;
    }
}