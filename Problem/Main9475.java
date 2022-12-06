import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main9475 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tc = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            BigInteger res = comb(n, r);
            BigInteger l = new BigInteger(String.valueOf(dnq(P, (n - r))));
            sb.append(tc).append(" ").append(res.multiply(l)).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static BigInteger comb(int n, int r) {
        if(r < 0 || r > n) {
            return BigInteger.ZERO;
        }
        if(r == 0 || r == n) {
            return BigInteger.ONE;
        }
        BigInteger dn = new BigInteger(String.valueOf(n));
        BigInteger dk = BigInteger.ONE;
        if(r > (n / 2)) {
            r = n - r;
        }
        BigInteger res = BigInteger.ONE;
        for(int i = 1; i <= r; i++) {
            res = res.multiply(dn).divide(dk);
            dn = dn.subtract(BigInteger.ONE);
            dk = dk.add(BigInteger.ONE);
        }

        return res;
    }

    public static long dnq(long k, int pow) {
        if(pow == 1) {
            return k;
        } else if(pow == 0) {
            return 1;
        }

        long res = dnq(k, pow / 2);

        if(pow % 2 == 1) {
            return res * res * k;
        } else {
            return res * res;
        }
    }
}
