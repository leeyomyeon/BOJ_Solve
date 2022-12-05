import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1769 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {

        String str = br.readLine();
        int cnt = 0;
        while(!(str.length() <= 1)) {
            int next = 0;
            for(int i = 0; i < str.length(); i++) {
                int k = str.charAt(i) - '0';
                next += k;
            }
            str = Integer.toString(next);
            cnt++;
        }
        bw.write(cnt + "\n" + (Integer.parseInt(str) % 3 == 0 ? "YES" : "NO"));
        br.close();
        bw.flush();
        bw.close();
    }
}