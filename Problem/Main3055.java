import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main3055 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, endR, endC;
    public static char[][] arr;
    public static boolean[][] visited;
    public static int[][] move;
    public static ArrayDeque<Point> deque, waterDeque;
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
        R = fr.nextInt();
        C = fr.nextInt();
        arr = new char[R][C];
        move = new int[R][C];
        visited = new boolean[R][C];
        deque = new ArrayDeque<>();
        waterDeque = new ArrayDeque<>();
        // S -> D
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                move[i][j] = Integer.MAX_VALUE;
                arr[i][j] = fr.nextChar();
                if(arr[i][j] == 'S') {
                    visited[i][j] = true;
                    deque.add(new Point(i, j, 0));
                } else if(arr[i][j] == '*') {
                    move[i][j] = -1;
                    waterDeque.add(new Point(i, j, 0));
                } else if(arr[i][j] == 'D') {
                    move[i][j] = Integer.MAX_VALUE;
                    endR = i;
                    endC = j;
                }
            }
        }
        while(!waterDeque.isEmpty()) {
            Point current = waterDeque.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && move[nr][nc] == Integer.MAX_VALUE && arr[nr][nc] == '.') {
                    move[nr][nc] = current.cnt + 1;
                    waterDeque.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
        arr[endR][endC] = '.';
        int res = -1;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.r == endR && current.c == endC) {
                res = current.cnt;
            }

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && current.cnt + 1 < move[nr][nc] && arr[nr][nc] == '.') {
                    visited[nr][nc] = true;
                    deque.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
        bw.write(res == -1 ? "KAKTUS" : Integer.toString(res));
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
