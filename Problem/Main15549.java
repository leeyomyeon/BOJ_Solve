import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15549 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);

    public static void main(String[] args) throws Exception {
        int x = -2147483648;
        if(x != 0 && x == -x) {
            bw.write("True");
        } else {
            bw.write("False");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}