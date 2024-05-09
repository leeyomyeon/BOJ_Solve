import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main13705 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static BigDecimal A, B, C;
    public static BigDecimal[] fact = new BigDecimal[31];
    public static BigDecimal PI2 = new BigDecimal("3.14159265358979323846264338327950288419716939937510582097494459").multiply(BigDecimal.valueOf(2));
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        init();
        A = BigDecimal.valueOf(fr.nextLong());
        B = BigDecimal.valueOf(fr.nextLong());
        C = BigDecimal.valueOf(fr.nextLong());
        BigDecimal start = BigDecimal.valueOf(-100000);
        BigDecimal end = BigDecimal.valueOf(100000);
        BigDecimal mid = BigDecimal.ZERO;
        int cnt = 18;
        while(cnt-->0) {
            mid = start.add(end).divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
            BigDecimal center = calc(mid);
            if(center.compareTo(BigDecimal.ZERO) <= 0) {
                start = mid;
            } else {
                end = mid;
            }
        }
        mid = start;
        BigDecimal res = newtonRaphson(mid);
        bw.write(res.setScale(6, RoundingMode.HALF_UP).toPlainString());
        bw.flush();
        bw.close();
    }
    public static BigDecimal sin(BigDecimal x) {
        if(x.compareTo(PI2) >= 0) {
            x = x.remainder(PI2, MathContext.DECIMAL128);
        }
        BigDecimal res = x;
        int cnt = 1;
        for(int i = 3; i <= 30; i += 2) {
            if(cnt % 2 == 1) {
                res = res.subtract(x.pow(i).divide(fact[i], MathContext.DECIMAL128));
            } else {
                res = res.add(x.pow(i).divide(fact[i], MathContext.DECIMAL128));
            }
            cnt++;
        }
        return res;
    }
    public static BigDecimal cos(BigDecimal x) {
        if(x.compareTo(PI2) >= 0) {
            x = x.remainder(PI2, MathContext.DECIMAL128);
        }
        BigDecimal res = BigDecimal.ONE;
        int cnt = 1;
        for(int i = 2; i <= 30; i+= 2) {
            if(cnt % 2 == 1) {
                res = res.subtract(x.pow(i).divide(fact[i], MathContext.DECIMAL128));
            } else {
                res = res.add(x.pow(i).divide(fact[i], MathContext.DECIMAL128));
            }
            cnt++;
        }
        return res;
    }
    public static BigDecimal calc(BigDecimal x) {
        // f(x) = Ax + Bsin(x) - C
        return A.multiply(x).add(B.multiply(sin(x))).subtract(C);
    }
    public static BigDecimal calc1(BigDecimal x) {
        // f'(x) = A + Bcos(x)
        return A.add(B.multiply(cos(x)));
    }
    public static BigDecimal newtonRaphson(BigDecimal k) {
        int cnt = 0;
        while(true) {
            BigDecimal r1 = calc(k);
            BigDecimal r2 = calc1(k);
            if(r2.equals(BigDecimal.ZERO)) {
                return k;
            }
            k = k.subtract(r1.divide(r2, MathContext.DECIMAL128));
            if(cnt == 18) {
                return k;
            }
            cnt++;
        }
    }
    public static void init() {
        fact[0] = BigDecimal.ONE;
        for(int i = 1; i <= 30; i++) {
            fact[i] = fact[i - 1].multiply(BigDecimal.valueOf(i));
        }
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

        public long nextLong() throws IOException {
            long ret = 0;
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