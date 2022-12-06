import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main10827 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static BigDecimal A;
    public static int B;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new BigDecimal(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        bw.write(dnq(A, B).toPlainString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static BigDecimal dnq(BigDecimal a, int idx) {
        if(idx == 1) {
            return a;
        }

        if(idx % 2 == 1) {
            return dnq(a, idx / 2).multiply(dnq(a, idx / 2)).multiply(a);
        } else {
            return dnq(a, idx / 2).multiply(dnq(a, idx / 2));
        }
    }
}