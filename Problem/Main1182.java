import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1182 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int N, S, cnt;
    public static int[] arr;
    public static boolean[] selected;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        cnt = 0;
        selected = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        subset(0);
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void subset(int idx) {
        if(idx == N) {
            int sum = 0;
            int selCnt = 0;
            for(int i = 0; i < N; i++) {
                if(selected[i]) {
                    selCnt++;
                    sum += arr[i];
                }
            }
            if(sum == S && selCnt > 0) {
                cnt++;
            }
            return;
        }

        selected[idx] = true;
        subset(idx + 1);
        selected[idx] = false;
        subset(idx + 1);
    }
}