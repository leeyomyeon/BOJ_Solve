import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main25307 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, K;
    public static int[][] arr;
    public static boolean[][] visited;
    public static Point start;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
//  아무것도 없는 칸은 0, 기둥이 있는 칸은 1, 의자가 있는 칸은 2, 마네킹이 있는 칸은 3, 시루의 시작 위치는 4로 주어진다
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        R = fr.nextInt();
        C = fr.nextInt();
        K = fr.nextInt();
        arr = new int[R][C];
        visited = new boolean[R][C];
        deque = new ArrayDeque<>();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                arr[i][j] = fr.nextInt();
                if(arr[i][j] == 4) { // 시작위치
                    start = new Point(i, j, 0);
                } else if(arr[i][j] == 3) { // 마네킹위치
                    visited[i][j] = true;
                    deque.add(new Point(i, j, 0));
                }
            }
        }
        setMannequin();
        visited[start.r][start.c] = true;
        deque.add(start);
        int res = -1;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            if(arr[current.r][current.c] == 2) {
                res = current.cnt;
                break;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && (arr[nr][nc] == 0 || arr[nr][nc] == 2)) {
                    visited[nr][nc] = true;
                    deque.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
        bw.write(res + "");
        bw.flush();
        bw.close();
    }
    public static void setMannequin() {
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            // K
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && current.cnt + 1 <= K) {
                    visited[nr][nc] = true;
                    deque.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
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