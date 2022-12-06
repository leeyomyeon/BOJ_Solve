import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
// 파스칼의 삼각형(BigInteger)
public class Main2407 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        BigInteger N = BigInteger.ONE;
        BigInteger R = BigInteger.ONE;

        for(int i = 0; i < r; i++) {
            N = N.multiply(new BigInteger(String.valueOf(n - i)));
            R = R.multiply(new BigInteger(String.valueOf(r - i)));
        }
        bw.write(N.divide(R).mod(new BigInteger("10007")).toString());
        br.close();
        bw.flush();
        bw.close();
    }
}