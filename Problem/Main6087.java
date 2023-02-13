import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main6087 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C;
    public static char[][] arr;
    public static int[][][] dp;
    public static boolean[][][] visited;
    public static Point start, end;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, cnt, before;

        public Point(int r, int c, int cnt, int before) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.before = before;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        C = fr.nextInt();
        R = fr.nextInt();
        arr = new char[R][C];
        dp = new int[4][R][C];
        deque = new ArrayDeque<>();
        visited = new boolean[4][R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                arr[i][j] = fr.nextChar();
                dp[0][i][j] = Integer.MAX_VALUE;
                dp[1][i][j] = Integer.MAX_VALUE;
                dp[2][i][j] = Integer.MAX_VALUE;
                dp[3][i][j] = Integer.MAX_VALUE;
                if(arr[i][j] == 'C' && start == null) {
                    start = new Point(i, j, 0, 0);
                } else if(arr[i][j] == 'C' && end == null) {
                    end = new Point(i, j, 0, 0);
                }
            }
        }
        // 첫 부분 전부 연결 처리
        for(int d = 0; d < 4; d++) {
            int cnt = 1;
            visited[d][start.r][start.c] = true;
            dp[d][start.r][start.c] = 0;
            while(true) {
                int nr = start.r + dr[d] * cnt;
                int nc = start.c + dc[d] * cnt;
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != '*') {
                    visited[d][nr][nc] = true;
                    dp[d][nr][nc] = 0;
                    deque.add(new Point(nr, nc, 0, d));
                } else {
                    break;
                }
                cnt++;
            }
        }
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                int nCnt = dp[current.before][current.r][current.c] + (d == current.before ? 0 : 1);
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != '*' && (!visited[d][nr][nc] || nCnt < dp[d][nr][nc])) {
                    dp[d][nr][nc] = nCnt;
                    visited[d][nr][nc] = true;
                    deque.add(new Point(nr, nc, nCnt, d));
                }
            }
        }
        bw.write(Math.min(dp[0][end.r][end.c], Math.min(dp[1][end.r][end.c], Math.min(dp[2][end.r][end.c], dp[3][end.r][end.c]))) + "");
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
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