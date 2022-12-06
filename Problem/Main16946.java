import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;

public class Main16946 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, cost;
    public static char[][] map;
    public static boolean[][] visited;
    public static ArrayDeque<Point> deque;
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
        arr = new int[N * M];
        cost = new int[N * M];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i;
            cost[i] = 1;
        }
        map = new char[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = fr.nextChar();
            }
        }
        deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == '0' && !visited[i][j]) {
                    visited[i][j] = true;
                    deque.add(new Point(i, j));
                    while(!deque.isEmpty()) {
                        Point current = deque.removeFirst();
                        int from = current.r * M + current.c;
                        for(int d = 0; d < 4; d++) {
                            int nr = current.r + dr[d];
                            int nc = current.c + dc[d];
                            if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == '0') {
                                visited[nr][nc] = true;
                                int to = nr * M + nc;
                                union(from, to);
                                deque.add(new Point(nr, nc));
                            }
                        }
                    }
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == '0') {
                    bw.write(48);
                } else {
                    int sum = 0;
                    for(int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == '0') {
                            int target = find(nr * M + nc);
                            if(!set.contains(target)) {
                                set.add(target);
                                sum += cost[target];
                            }
                        }
                    }
                    set.clear();
                    bw.write(((sum + 1) % 10) + 48);
                }
            }
            bw.newLine();
        }
        bw.write("");
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x < y) {
            arr[y] = x;
            cost[x] += cost[y];
            cost[y] = 0;
        } else {
            arr[x] = y;
            cost[y] += cost[x];
            cost[x] = 0;
        }
    }
    public static int find(int target) {
        if(arr[target] == target) {
            return target;
        }
        return arr[target] = find(arr[target]);
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
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
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

        public char nextChar() throws IOException {
            byte c = read();
            if (c == '\n') {
                c = read();
            }
            return (char) c;
        }

    }
}