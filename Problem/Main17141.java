import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main17141 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, emptyCnt, MIN;
    public static int[][] arr;
    public static boolean[][] visited;
    public static ArrayDeque<Point> deque;
    public static ArrayList<Point> virusList;
    public static int[] selected;
    public static class Point {
        int r, c, cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N][N];
        virusList = new ArrayList<>();
        emptyCnt = 0;
        MIN = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = fr.nextInt();
                if(arr[i][j] == 2) {
                    arr[i][j] = 0;
                    virusList.add(new Point(i, j, 0));
                }
                if(arr[i][j] != 1) {
                    emptyCnt++;
                }
            }
        }
        selected = new int[M];
        combination(0, 0);
        bw.write(MIN == Integer.MAX_VALUE ? "-1" : Integer.toString(MIN));
        bw.flush();
        bw.close();
    }
    public static void combination(int idx, int cnt) {
        if(cnt == M) { // M개 골랐을 경우
            bfs();
            return;
        }
        for(int i = idx; i < virusList.size(); i++) {
            selected[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }
    public static void bfs() {
        visited = new boolean[N][N];
        deque = new ArrayDeque<>();

        int cnt = 0;    //  바이러스 갯수 카운트
        for(int idx : selected) {
            Point next = virusList.get(idx);
            visited[next.r][next.c] = true;
            deque.add(next);
            cnt++;
        }
        int minTime = 0;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && arr[nr][nc] != 1) {
                    visited[nr][nc] = true;
                    cnt++;
                    minTime = (current.cnt + 1);
                    if(minTime >= MIN) {
                        return;
                    }
                    deque.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
        if(cnt == emptyCnt) {
            MIN = Math.min(MIN, minTime);
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