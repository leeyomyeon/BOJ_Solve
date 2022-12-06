import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main4375 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);

    public static void main(String[] args) throws Exception {
        int N;
        String str;
        while((str = br.readLine()) != null) {
            N = Integer.parseInt(str);
            int cnt = 1;
            long num = 1;
            while (num % N != 0) {
                cnt++;
                num = (num * 10 + 1) % N;
            }
            bw.write(Integer.toString(cnt));
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}