import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1789 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long S;
    public static int n;
    public static void main(String[] args) throws Exception {
        S = Long.parseLong(br.readLine());

        n = (int) ((Math.sqrt(1 + 8 * S) - 1) / 2);

        bw.write(Integer.toString(n));
        br.close();
        bw.flush();
        bw.close();
    }
}
/*

n(n+1) = 2 * S
n^2 + n - (2 * S) = 0

-1 + Math.sqrt(1 + 8 * S )/ 2

*/