import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Main11967 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static boolean[][] arr;
    public static boolean[][] visited;
    public static boolean[][] nextVisited;
    public static ArrayList<Point>[][] list;
    public static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        nextVisited = new boolean[N + 1][N + 1];
        list = new ArrayList[N + 1][N + 1];
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                list[i][j] = new ArrayList<>();
            }
        }
        // (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미
        for(int i = 0; i < M; i++) {
            int x = fr.nextInt();
            int y = fr.nextInt();
            int a = fr.nextInt();
            int b = fr.nextInt();
            list[x][y].add(new Point(a, b));
        }
        arr[1][1] = true;
        int cnt = 1;
        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(1, 1));
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(Point next : list[current.r][current.c]) {
                if(!arr[next.r][next.c]) {
                    arr[next.r][next.c] = true;
                    cnt++;
                    if(nextVisited[next.r][next.c]) {
                        visited[next.r][next.c] = true;
                        deque.add(new Point(next.r, next.c));
                    }
                }
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 1 && nr <= N && nc >= 1 && nc <= N && !visited[nr][nc]) {
                    if(!arr[nr][nc]) {
                        nextVisited[nr][nc] = true;
                    } else {
                        visited[nr][nc] = true;
                        deque.add(new Point(nr, nc));
                    }
                }
            }
        }
        bw.write(Integer.toString(cnt));
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