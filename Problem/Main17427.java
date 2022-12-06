import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main17427 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        long sum = 0;
        for(int i = 1; i <= N; i++) {
            sum += ((long) i * (N / i));
        }
        bw.write(Long.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }
}