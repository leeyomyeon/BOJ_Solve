import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main10994 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static boolean[][] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = (2 * (N - 1)) * 2 + 1;
        arr = new boolean[M + 1][M + 1];
        recursive(1);
        for(int i = 1; i <= M; i++) {
            for(int j = 1; j <= M; j++) {
                bw.write(arr[i][j] ? 42 : 32);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void recursive(int idx) {
        if(idx == N) {
            arr[(idx - 1) * 2 + 1][(idx - 1) * 2 + 1] = true;
            return;
        }
        for(int i = (idx - 1) * 2 + 1; i <= M - (idx - 1) * 2; i++) {
            if(i == (idx - 1) * 2 + 1 || i == M - (idx - 1) * 2) {
                for(int j = (idx - 1) * 2 + 1; j <= M - (idx - 1) * 2; j++) {
                    arr[i][j] = true;
                }
            } else {
                arr[i][(idx - 1) * 2 + 1] = true;
                arr[i][M - (idx - 1) * 2] = true;
            }
        }
        recursive(idx + 1);
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 4;
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