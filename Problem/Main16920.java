import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main16920 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, P;
    public static char[][] arr;
    public static int[] S, res;
    public static boolean[][] visited;
    public static ArrayDeque<Point>[] dequeList;
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
        P = fr.nextInt();
        S = new int[P + 1];
        res = new int[P + 1];
        dequeList = new ArrayDeque[P + 1];
        for(int p = 1; p <= P; p++) {
            S[p] = fr.nextInt();
            dequeList[p] = new ArrayDeque<>();
        }
        arr = new char[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                arr[i][j] = fr.nextChar();
                if('1' <= arr[i][j] && arr[i][j] <= '9') {
                    visited[i][j] = true;
                    dequeList[arr[i][j] - '0'].add(new Point(i, j, 0));
                    res[arr[i][j] - '0']++;
                }
            }
        }
        while(true) {
            boolean flag = false;
            for(int p = 1; p <= P; p++) {
                if(dequeList[p].size() != 0) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                break;
            }
            for(int p = 1; p <= P; p++) { // 턴 돌아가면서 함
                ArrayDeque<Point> deque = dequeList[p];
                ArrayDeque<Point> nextDeque = new ArrayDeque<>();
                while(!deque.isEmpty()) {
                    Point current = deque.removeFirst();

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];
                        int sIdx = arr[current.r][current.c] - '0';
                        if(nr >= 1 && nr <= N && nc >= 1 && nc <= M && !visited[nr][nc] && arr[nr][nc] == '.' && current.cnt + 1 <= S[sIdx]) {
                            deque.add(new Point(nr, nc, current.cnt + 1));
                            arr[nr][nc] = arr[current.r][current.c];
                            visited[nr][nc] = true;
                            res[sIdx]++;
                            if(current.cnt + 1 == S[sIdx]) {
                                nextDeque.add(new Point(nr, nc, 0));
                            }
                        }
                    }
                }
                dequeList[p] = nextDeque;
            }
        }
        for(int i = 1; i <= P; i++) {
            bw.write(res[i] + " ");
        }
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
            while(c <= ' ') {
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