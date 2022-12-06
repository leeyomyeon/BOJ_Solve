import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main14916 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int res = 0;
        if(n == 1 || n == 3) {
            res = -1;
        } else if(n % 5 == 0) {
            res = n / 5;
        } else if(n % 5 == 1) {
            res = (n - 6) / 5 + 3;
        } else if(n % 5 == 2) {
            res = (n - 2) / 5 + 1;
        } else if(n % 5 == 3) {
            res = (n - 8) / 5 + 4;
        } else if(n % 5 == 4) {
            res = (n - 4) / 5 + 2;
        }

        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}