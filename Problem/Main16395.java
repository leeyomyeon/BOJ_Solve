import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 파스칼의 삼각형
public class Main16395 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        double N = 1;
        double R = 1;

        for(int i = 0; i < r; i++) {
            N *= (n - i);
            R *= (r - i);
        }
        bw.write(String.format("%.0f", N / R));
        br.close();
        bw.flush();
        bw.close();
    }
}