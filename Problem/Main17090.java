import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main17090 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C;
    public static int[][] arr;
    public static int[][] dp;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        R = fr.nextInt();
        C = fr.nextInt();
        arr = new int[R][C];
        dp = new int[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int k = fr.nextCharToInt();
                // R = 1, D = 3, U = 0, L = 2
                arr[i][j] = k == 11 ? 2 : k % 4;
                dp[i][j] = -1;
            }
        }
        int sum = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(dp[i][j] == -1) {
                    dp[i][j] = dfs(i, j);
                }
            }
        }
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sum += dp[i][j];
            }
        }
        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
    public static int dfs(int r, int c) {
        if(dp[r][c] != -1) {
            return dp[r][c];
        }
        dp[r][c] = 0;
        int nr = r + dr[arr[r][c]];
        int nc = c + dc[arr[r][c]];
        int tmp;
        if(0 <= nr && nr < R && 0 <= nc && nc < C) {
            tmp = dfs(nr, nc);
        } else {
            tmp = 1;
        }
        return dp[r][c] = tmp;
    }
    public static int[] dr = {-1, 0, 0, 1};
    public static int[] dc = {0, 1, -1, 0};
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
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
        }
        public int nextCharToInt() throws IOException {
            byte c = read();
            if(c == '\n') {
                c = read();
            }
            return c - 'A';
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