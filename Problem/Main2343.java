import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2343 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int left = 0;
        int right = 0;
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, arr[i]);
            right += arr[i];
        }

        while(left <= right) {
            int cnt = 0;
            int mid = (left + right) / 2;

            int tmpSum = 0;
            for(int i = 1; i <= N; i++) {
                if(tmpSum + arr[i] > mid) {
                    tmpSum = 0;
                    cnt++;
                }
                tmpSum += arr[i];
            }
            if(tmpSum != 0) {
                cnt++;
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