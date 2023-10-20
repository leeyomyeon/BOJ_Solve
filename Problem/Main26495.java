import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main26495 {
    public static String[] arr;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long N;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        init();
        N = fr.nextLong();
        String str = Long.toString(N);
        for(int i = 0; i < str.length(); i++) {
            bw.write(arr[str.charAt(i) - '0']);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void init() {
        arr = new String[]{
            "0000\n0  0\n0  0\n0  0\n0000\n",
            "   1\n   1\n   1\n   1\n   1\n",
            "2222\n   2\n2222\n2\n2222\n",
            "3333\n   3\n3333\n   3\n3333\n",
            "4  4\n4  4\n4444\n   4\n   4\n",
            "5555\n5\n5555\n   5\n5555\n",
            "6666\n6\n6666\n6  6\n6666\n",
            "7777\n   7\n   7\n   7\n   7\n",
            "8888\n8  8\n8888\n8  8\n8888\n",
            "9999\n9  9\n9999\n   9\n   9\n",
        };
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
