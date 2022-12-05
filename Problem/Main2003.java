import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2003 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static long M;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        int start = 1;
        while(start <= N) {
            int sum = 0;
            for(int i = start; i <= N; i++) {
                sum += arr[i];
                if(sum == M) {
                    cnt++;
                    break;
                } else if (sum > M) {
                    break;
                }
            }
            start++;
        }
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}