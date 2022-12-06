import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2512 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, max, sum;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        max = 0;
        sum = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }
        M = Integer.parseInt(br.readLine());
        if(sum <= M) {
            bw.write(Integer.toString(max));
        } else {
            bw.write(Integer.toString(search(1, max)));
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static int search(int start, int end) {
        while(start + 1 < end) {
            int mid = (start + end) / 2;
            if(chk(mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return start;
    }
    public static boolean chk(int m) {
        int tmpSum = 0;
        for(int i = 0; i < N; i++) {
            tmpSum += Math.min(arr[i], m);
        }

        return tmpSum <= M;
    }
}