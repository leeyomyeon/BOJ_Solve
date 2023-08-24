import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class Main15682 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static BigDecimal A, B, C, D;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        for(int t = 0; t < T; t++) {
            String[] str = fr.readLine().split(" ");
            A = new BigDecimal(str[0]);
            B = new BigDecimal(str[1]);
            C = new BigDecimal(str[2]);
            D = new BigDecimal(str[3]);
            BigDecimal start = BigDecimal.valueOf(-1000001);
            BigDecimal end = BigDecimal.valueOf(1000001);
            BigDecimal mid = BigDecimal.ZERO;
            int cnt = 100;
            if(A.compareTo(BigDecimal.ZERO) < 0) {
                A = A.multiply(BigDecimal.valueOf(-1));
                B = B.multiply(BigDecimal.valueOf(-1));
                C = C.multiply(BigDecimal.valueOf(-1));
                D = D.multiply(BigDecimal.valueOf(-1));
            }
            while(cnt-->0) {   // 이분법으로 근사해 찾기
                mid = start.add(end).divide(BigDecimal.valueOf(2), MathContext.DECIMAL64);
                BigDecimal center = calc(mid);
                if(center.compareTo(BigDecimal.ZERO) <= 0) {
                    start = mid;
                } else {
                    end = mid;
                }

            }
            mid = start;
            // 해 하나 찾으면 (x - k)(ax² + bx + c) 꼴로 만들 수 있음
            /*
                (x - k)(ax² + bx + c) = Ax³ + Bx² + Cx + D
                ax³ + bx² + cx - akx² - bkx - ck
                ax³ + (b - ak)x² + (c - bk)x - ck = 0

                A = a
                B = b - ak, b = B + Ak
                C = c - bk, c = C + (B + Ak)k
             */
            HashSet<BigDecimal> set = new HashSet<>();
            BigDecimal b = B.add(mid.multiply(A));
            BigDecimal c = C.add(mid.multiply(b));
            BigDecimal disc = b.multiply(b).subtract(BigDecimal.valueOf(4).multiply(A).multiply(c)); // b² - 4ac

            set.add(mid.setScale(9, RoundingMode.DOWN));

            if(disc.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal distSqrt = disc.sqrt(MathContext.DECIMAL64);
                BigDecimal r1 = b.multiply(BigDecimal.valueOf(-1));
                r1 = r1.add(distSqrt);
                r1 = r1.divide(BigDecimal.valueOf(2).multiply(A), MathContext.DECIMAL64);
                BigDecimal r2 = b.multiply(BigDecimal.valueOf(-1));
                r2 = r2.subtract(distSqrt);
                r2 = r2.divide(BigDecimal.valueOf(2).multiply(A), MathContext.DECIMAL64);

                set.add(r1.setScale(9, RoundingMode.DOWN));
                set.add(r2.setScale(9, RoundingMode.DOWN));

            } else if (disc.compareTo(BigDecimal.ZERO) == 0) {
                BigDecimal r = b.multiply(BigDecimal.valueOf(-1));
                r = r.divide(BigDecimal.valueOf(2).multiply(A), MathContext.DECIMAL64);
                set.add(r.setScale(9, RoundingMode.DOWN));
            }

            ArrayList<BigDecimal> list = new ArrayList<>(set);

            Collections.sort(list);
            for(BigDecimal k : list) {
                bw.write(k.toPlainString() + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static BigDecimal calc(BigDecimal k) {   // f(x)
        // Ax³ + Bx² + Cx + D
        BigDecimal res = A.multiply(k).multiply(k).multiply(k);
        res = res.add(B.multiply(k).multiply(k));
        res = res.add(C.multiply(k));
        res = res.add(D);
        return res;
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 16;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // input line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = (ret << 3) + (ret << 1) + (c & 15);
            } while ((c = read()) > 32);

            return neg ? ~ret + 1 : ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
    }
}