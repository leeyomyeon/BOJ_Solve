import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main14786 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static double A, B, C;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        A = fr.nextInt();
        B = fr.nextInt();
        C = fr.nextInt();
        int start = -100000;
        int end = 100000;
        int mid = 0;
        int cnt = 100;
        while(cnt-->0) {
            mid = (start + end) / 2;
            double center = calc(mid);
            if(center <= 0) {
                start = mid;
            } else {
                end = mid;
            }
            mid = start;
        }
        double res = newtonRaphson(mid);
        bw.write(String.format("%.6f", res));
        bw.flush();
        bw.close();
    }
    public static double calc(double x) {
        // f(x) = Ax + Bsin(x) - C
        return A * x + B * Math.sin(x) - C;
    }
    public static double calc1(double x) {
        // f'(x) = A + Bcos(x)
        return A + B * Math.cos(x);
    }
    public static double newtonRaphson(double k) {
        int cnt = 0;
        while(true) {
            double r1 = calc(k);
            double r2 = calc1(k);
            if(r2 == 0) {
                return k;
            }
            k = k - r1 / r2;
            if(cnt == 100) {
                return k;
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
