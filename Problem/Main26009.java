import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main26009 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, K;
    public static boolean[][] visited;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, dist;
        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        R = fr.nextInt();
        C = fr.nextInt();
        K = fr.nextInt();
        deque = new ArrayDeque<>();
        visited = new boolean[R + 1][C + 1];
        for(int i = 0; i < K; i++) {
            int r = fr.nextInt();
            int c = fr.nextInt();
            int t = fr.nextInt();
            int n = 0;
            for(int k = 0; k <= t * 2; k++) {
                int startR = r + (k - t);
                int dc = (k < t ? n++ : n--);
                int startC1 = c - dc;
                int startC2 = c + dc;
                if(startR > 0 && startR <= R) {
                    if(startC1 > 0 && startC1 <= C) {
                        visited[startR][startC1] = true;
                    }
                    if(startC2 > 0 && startC2 <= C) {
                        visited[startR][startC2] = true;
                    }
                }
            }
        }
        visited[1][1] = true;
        deque.add(new Point(1, 1, 0));
        int res = Integer.MAX_VALUE;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.r == R && current.c == C) {
                res = current.dist;
                break;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    deque.add(new Point(nr, nc, current.dist + 1));
                }
            }
        }
        if(res == Integer.MAX_VALUE) {
            bw.write("NO");
        } else {
            bw.write("YES\n" + res);
        }
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0 ,0};
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
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
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