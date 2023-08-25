import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main3783 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static BigDecimal N;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        for(int t = 0; t < T; t++) {
            N = new BigDecimal(fr.readLine());
            // 시작점 이분탐색
            BigDecimal start = new BigDecimal(0);
            BigDecimal end = N.sqrt(MathContext.DECIMAL64);
            BigDecimal mid;
            int cnt = 100;
            while(cnt-->0) {
                mid = start.add(end).divide(BigDecimal.valueOf(2), MathContext.DECIMAL64);
                BigDecimal center = calc(mid);
                if(center.compareTo(BigDecimal.ZERO) <= 0) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            mid = start;
            BigDecimal res = newtonRaphson(mid);
            bw.write(String.valueOf(res.setScale(10, RoundingMode.DOWN)));
            bw.newLine();
        }
        bw.write("");
        bw.flush();
        bw.close();
    }
    public static BigDecimal calc(BigDecimal x) {
        // x³
        return x.pow(3).subtract(N);
    }
    public static BigDecimal calc1(BigDecimal x) {
        // 3x²
        return java.math.BigDecimal.valueOf(3).multiply(x.pow(2));
    }
    public static BigDecimal newtonRaphson(BigDecimal x) {
        int cnt = 0;
        while(true) {
            BigDecimal r1 = calc(x);
            BigDecimal r2 = calc1(x);
            if(r2.compareTo(java.math.BigDecimal.ZERO) == 0) {
                return x;
            }
            x = x.subtract(r1.divide(r2, MathContext.DECIMAL64));
            if(cnt == 100) {
                return x;
            }
            cnt++;
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

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = (ret << 3) + (ret << 1) + (c & 15);
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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
