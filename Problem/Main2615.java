import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class Main2615 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, cnt;
    public static int[][] arr;
    public static int[][][] sum;
    public static int[] di = {1, 0, 1, -1};
    public static int[] dj = {0, 1, 1, 1};
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = 19;
        arr = new int[N][N];
        sum = new int[4][N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = fr.nextInt();
            }
        }
        int res = 0;
        int r = 0, c = 0;
        Loop1:
        for(int j = 0; j < N; j++) {
            for(int i = 0; i < N; i++) {
                if(arr[i][j] != 0) {
                    for(int d = 0; d < 4; d++) {
                        if(sum[d][i][j] == 0) {
                            cnt = 1;
                            dfs(i, j, d, arr[i][j]);
                            if(cnt == 5) {
                                res = arr[i][j];
                                r = i + 1;
                                c = j + 1;
                                break Loop1;
                            }
                        }
                    }
                }
            }
        }
        bw.write(Integer.toString(res));
        if(res != 0) {
            bw.newLine();
            bw.write(r + " " + c);
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int i, int j, int dir, int color) {
        int ni = i + di[dir];
        int nj = j + dj[dir];
        if(ni >= 0 && ni < N && nj >= 0 && nj < N && arr[ni][nj] == color) {
            cnt++;
            dfs(ni, nj, dir, color);
        }
        sum[dir][i][j] = cnt;
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