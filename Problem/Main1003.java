import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1003 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] fib0Cnt, fib1Cnt;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        fib0Cnt = new int[41];
        fib1Cnt = new int[41];

        fib0Cnt[0] = 1;
        fib1Cnt[0] = 0;
        
        fib0Cnt[1] = 0;
        fib1Cnt[1] = 1;

        for(int i = 2; i <= 40; i++) {
            fib0Cnt[i] = fib0Cnt[i - 1] + fib0Cnt[i - 2];
            fib1Cnt[i] = fib1Cnt[i - 1] + fib1Cnt[i - 2];
        }

        for(int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(fib0Cnt[n] + " " + fib1Cnt[n]);
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }

}