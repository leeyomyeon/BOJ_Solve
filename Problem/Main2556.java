import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main2556 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                bw.write(42);
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}