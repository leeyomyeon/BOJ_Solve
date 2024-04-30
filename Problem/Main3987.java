import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main3987 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static final int MAX = 1_000_000_000;
    public static int N, M;
    public static char[][] arr;
    public static boolean[][][] visited;
    public static ArrayDeque<Node> deque;
    public static class Node {
        int r, c, dir, cnt;
        public Node(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    // U: 0, R : 1, D : 2, L : 3
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new char[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                arr[i][j] = fr.nextChar();
            }
        }
        int r = fr.nextInt() - 1;
        int c = fr.nextInt() - 1;
        Node res = bfs(r, c);
        char[] resChr = {'U', 'R', 'D', 'L'};
        bw.write(resChr[res.dir] + "\n" + (res.cnt == MAX ? "Voyager" : res.cnt));
        bw.flush();
        bw.close();
    }
    public static Node bfs(int r, int c) {
        deque = new ArrayDeque<>();
        Node res = new Node(-1, -1, 0, 1);
        for(int d = 0; d < 4; d++) {
            deque.add(new Node(r, c, d, 1));
            visited = new boolean[4][N][M];
            visited[d][r][c] = true;
            while(!deque.isEmpty()) {
                Node current = deque.removeFirst();
                int nr = current.r + dr[current.dir];
                int nc = current.c + dc[current.dir];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    if(res.cnt < current.cnt) {
                        res = new Node(0, 0, d, current.cnt);
                        break;
                    }
                }
                if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if(visited[current.dir][nr][nc]) {
                        return new Node(0, 0, d, MAX);
                    }
                    visited[current.dir][nr][nc] = true;
                    // U: 0, R : 1, D : 2, L : 3
                    if(arr[nr][nc] == '\\') { // 0 > 3, 3 > 0, 1 > 2, 2 > 1
                        deque.add(new Node(nr, nc, (~current.dir) & 3, current.cnt + 1));
                    } else if(arr[nr][nc] == '/') { // 0 > 1, 1 > 0, 2 > 3, 3 > 2
                        deque.add(new Node(nr, nc, current.dir ^ 1, current.cnt + 1));
                    } else if(arr[nr][nc] == 'C') {
                        if(res.cnt < current.cnt) {
                            res = new Node(0, 0, d, current.cnt);
                        }
                        break;
                    } else { // '.'
                        deque.add(new Node(nr, nc, current.dir, current.cnt + 1));
                    }
                }
            }
        }

        return res;
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
