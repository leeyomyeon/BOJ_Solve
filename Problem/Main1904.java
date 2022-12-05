import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1904 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        int t1 = 1;
        int t2 = 2;

        if(N == 1) {
            bw.write(Integer.toString(t1));
        } else if (N == 2) {
            bw.write(Integer.toString(t2));
        } else {
            int res = t1 + t2;
            for(int i = 3; i < N; i++) {
                t1 = t2;
                t2 = res;
                res = (t1 + t2) % 15746;
            }
            bw.write(Integer.toString(res));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}