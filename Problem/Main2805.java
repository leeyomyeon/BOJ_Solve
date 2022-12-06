import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2805 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, max;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        long res = search();
        bw.write(Long.toString(res - 1));
        br.close();
        bw.flush();
        bw.close();
    }
    public static long search() {
        long start = 0;
        long end = max;
        long mid;
        while(start < end) {
            mid = (start + end) / 2;
            long sum = 0;
            for(int i = 0; i < N; i++) {
                sum += Math.max(0, arr[i] - mid);
            }
            if(sum < M) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}