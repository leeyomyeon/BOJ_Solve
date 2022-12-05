import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Main1944 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[] arr;
    public static int[][] map;
    public static boolean[][] visited;
    public static boolean[] selected;
    public static int N, M;
    public static Point start;
    public static ArrayDeque<Point> deque;
    public static ArrayList<Node> list;
    public static Point[] keys;
    public static class Point {
        int r, c, cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static class Node implements Comparable<Node>{
        int from, to, dist;
        public Node(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        map = new int[N][N];
        arr = new int[M + 1];
        for(int i = 0; i <= M; i++) {
            arr[i] = i;
        }
        keys = new Point[M + 1];
        int k = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                char c = fr.nextChar();
                if(c == 'S') { // 시작위치 = 0;
                    map[i][j] = 0;
                    start = new Point(i, j, 0);
                    keys[0] = new Point(i, j, 0);
                } else if(c == 'K') {
                    map[i][j] = k;
                    keys[k] = new Point(i, j, 0);
                    k++;
                } else if(c == '1'){
                    map[i][j] = -1;
                } else {
                    map[i][j] = -2;
                }
            }
        }

        selected = new boolean[M + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= M; i++) {
            selected[i] = true;
            deque = new ArrayDeque<>();
            deque.add(keys[i]);
            visited = new boolean[N][N];
            visited[keys[i].r][keys[i].c] = true;
            while(!deque.isEmpty()) {
                Point current = deque.removeFirst();

                for(int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] != -1) {
                        visited[nr][nc] = true;
                        deque.add(new Point(nr, nc, current.cnt + 1));
                        if(map[nr][nc] >= 0 && !selected[map[nr][nc]]) {
                            list.add(new Node(i, map[nr][nc], current.cnt + 1));
                        }
                    }
                }
            }
        }

        Collections.sort(list);
        int sum = 0;
        int cnt = 0;
        for(Node current : list) {
            int x = find(current.from);
            int y = find(current.to);
            if(x == y) {
                continue;
            }
            cnt++;
            sum += current.dist;
            union(x, y);
        }
        bw.write(cnt != M ? "-1" : Integer.toString(sum));
        bw.flush();
        bw.close();
    }
    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);

        if(x < y) {
            arr[y] = x;
        } else {
            arr[x] = y;
        }
    }
    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
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

        public char nextChar() throws IOException {
            byte c = read();
            if (c == '\n') {
                c = read();
            }
            return (char) c;
        }
    }
}