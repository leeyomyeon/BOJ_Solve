import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main17425 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[] sum;
    public static long[] dpSum;
    public static void main(String[] args) throws Exception {
        sum = new int[1000001];
        dpSum = new long[1000001];
        init();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(dpSum[n] + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void init() {
        sum[1] = 1;
        for(int i = 2; i <= 1000000; i++) {
            sum[i] += 1;
            int k = 1;
            while(k * i <= 1000000) {
                sum[k * i] += i;
                k++;
            }
        }

        dpSum[1] = 1;
        for(int i = 2; i <= 1000000; i++) {
            dpSum[i] = dpSum[i - 1] + sum[i];
        }
    }
}