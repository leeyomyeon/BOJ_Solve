import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main14502 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, MAX, safeCnt;
    public static int[][] arr;
    public static boolean[][] visitedOrigin;
    public static int[] selected;
    public static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static ArrayList<Point> list;
    public static ArrayDeque<Point> originDeque;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N][M];
        visitedOrigin = new boolean[N][M];
        list = new ArrayList<>();
        // 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치
        originDeque = new ArrayDeque<>();
        MAX = Integer.MIN_VALUE;
        safeCnt = -3;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                arr[i][j] = fr.nextInt();
                if(arr[i][j] == 0) {
                    safeCnt++;
                    list.add(new Point(i, j));
                } else if(arr[i][j] == 2) {
                    visitedOrigin[i][j] = true;
                    originDeque.add(new Point(i, j));
                }
            }
        }
        selected = new int[3];
        comb(0, 0);
        bw.write(Integer.toString(MAX));
        bw.flush();
        bw.close();
    }
    public static void makeWall(int n) {
        for(int k : selected) {
            Point c = list.get(k);
            arr[c.r][c.c] = n;
        }
    }
    public static void comb(int idx, int cnt) {
        if(cnt == 3) {
            makeWall(1);
            bfs();
            makeWall(0);
            return;
        }
        for(int i = idx; i < list.size(); i++) {
            selected[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }
    public static void bfs() {
        ArrayDeque<Point> deque = originDeque.clone();
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            visited[i] = Arrays.copyOf(visitedOrigin[i], M);
        }
        int cnt = 0;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && arr[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    cnt++;
                    deque.add(new Point(nr, nc));
                }
            }
        }
        MAX = Math.max(MAX, safeCnt - cnt);
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