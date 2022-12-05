import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1669 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int X, Y;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        if(X == Y) {
            bw.write("0");
        } else if(Y - X == 1) {
            bw.write("1");
        } else {
            int k = Y - X;
            long n = 0;
            while(n * n < k) {
                n++;
            }
            n = (n * n == k) ? n : n - 1;
            long ans = 2 * n - 1;
            k -= n * n;
            while(k > 0) {
                k -= Math.min(n, k);
                ans++;
            }
            bw.write(ans+"");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}