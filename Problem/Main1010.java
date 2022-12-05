import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1010 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());   // 13
            int n = Integer.parseInt(st.nextToken());   // 29

            double N = 1;
            double R = 1;
            for(int i = 0; i < r; i++) {
                N *= (n - i);
                R *= (r - i);
            }
            bw.write(String.format("%.0f", N / R));
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
/* 
n(n-1)(n-2) ... (n-r-1)
r(r-1)(r-2) ... 2 1
*/