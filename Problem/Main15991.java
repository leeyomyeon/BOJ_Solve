import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main15991 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long[] arr;
    public static int MOD = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        init();
        for(int tc = 0; tc < T; tc++) {
            bw.write(arr[fr.nextInt()] + "\n");
        }
        bw.flush();
        bw.close();
    }
    public static void init() {
        arr = new long[100001];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 2;
        arr[4] = 3;
        arr[5] = 3;
        for(int i = 6; i <= 100000; i++) {
                    // 양끝이1인경우/양끝이2인경우/양끝이3인경우
            arr[i] = (arr[i - 2] + arr[i - 4] + arr[i - 6]) % MOD;
        }
    }
/*
1 = 1
2 = 11 / 2
3 = 111 / 3
4 = 1111 / 121 / 22
5 = 11111 / 131 / 212
6 = 1 1111 1 / 1 121 1 / 2 2 2 / 33 / 1 22 1 / 2 11 2
 */
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
                ret = ret * 10 + c - '0';
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