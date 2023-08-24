import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main9735 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static double A, B, C, D;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        for(int t = 0; t < T; t++) {
            A = fr.nextDouble();
            B = fr.nextDouble();
            C = fr.nextDouble();
            D = fr.nextDouble();

            int start = -1000000;
            int end = 1000000;
            int mid = 0;
            for(int k = start; k <= end; k++) {
                if(calc(k)) {
                    mid = k;
                    break;
                }
            }

            /* 2차방정식의 근 판별 */
            double a = A;
            double b = B + mid * A;
            double c = C + mid * (B + mid * A);
            double disc = Math.pow(b, 2) - (4 * a * c);
            double r1 = ((b * -1) + Math.sqrt(disc)) / (2 * a);
            double r2 = ((b * -1) - Math.sqrt(disc)) / (2 * a);
            ArrayList<Double> list = new ArrayList<>();

            list.add((double) mid);
            if(disc > 0) {
                if (mid == r1) {
                    list.add(r2);
                } else if(mid == r2) {
                    list.add(r1);
                } else {
                    list.add(r1);
                    list.add(r2);
                }
            } else if(disc == 0) {
                if(mid != r1 && r1 == r2) {
                    list.add(r1);
                }
            }
            Collections.sort(list);
            for(double res : list) {
                bw.write(String.format("%.9f", res) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static boolean calc(long k) {
        if(A * k * k + B * k + C == -D / k) {
            return true;
        }
        return false;
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
