import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main2417 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long N;
    public static void main(String[] args) throws Exception {
        N = Long.parseLong(br.readLine());

        long k = (long) Math.ceil(Math.sqrt(N));
        if((long) Math.pow(k, 2) < N) {
            k++;
        }
        bw.write(Long.toString(k));
        br.close();
        bw.flush();
        bw.close();
    }
}