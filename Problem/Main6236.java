import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main6236 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int left = 0;
        int right = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            right += arr[i];
            left = Math.max(arr[i], left);
        }

        while(left <= right) {
            int mid = (left + right) / 2;
            int cnt = 1;
            int tmpSum = mid;

            for(int i = 0; i < N; i++) {
                if(tmpSum < arr[i]) {
                    tmpSum = mid;
                    cnt++;
                }
                tmpSum -= arr[i];
            }

            if(cnt <= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(Integer.toString(left));
        br.close();
        bw.flush();
        bw.close();
    }
}