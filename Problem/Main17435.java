import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main17435 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, Q, maxLog;
    public static int[][] table;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        maxLog = (int) (Math.log(500000) / Math.log(2));
        table = new int[maxLog + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            table[0][i] = fr.nextInt(); // 한 칸 이동하는 경우
        }
        for(int k = 1; k <= maxLog; k++) {
            for(int i = 1; i <= N; i++) {
                table[k][i] = table[k - 1][table[k - 1][i]];
            }
        }
        Q = fr.nextInt();
        for(int q = 0; q < Q; q++) {
            int n = fr.nextInt();
            int x = fr.nextInt();
            for(int k = 0; k <= maxLog; k++) {
                if((n & (1 << k)) > 0) {
                    x = table[k][x];
                }
            }
            bw.write(x + "\n");
        }
        bw.flush();
        bw.close();
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