import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main15644 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, res;
    public static char[][] arr;
    public static boolean[][][][] visited;
    public static long[][][][] track;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int redR, redC, blueR, blueC, dir, cnt;

        public void setRedR(int redR) {
            this.redR = redR;
        }
        public void setRedC(int redC) {
            this.redC = redC;
        }
        public void setBlueR(int blueR) {
            this.blueR = blueR;
        }
        public void setBlueC(int blueC) {
            this.blueC = blueC;
        }

        public Point(int redR, int redC, int blueR, int blueC, int dir, int cnt) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        R = fr.nextInt();
        C = fr.nextInt();
        Point start = new Point(0, 0, 0, 0, -1, 0);
        arr = new char[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                arr[i][j] = fr.nextChar();
                if(arr[i][j] == 'R') {
                    arr[i][j] = '.';
                    start.setRedR(i);
                    start.setRedC(j);
                } else if(arr[i][j] == 'B') {
                    arr[i][j] = '.';
                    start.setBlueR(i);
                    start.setBlueC(j);
                }
            }
        }
        Point res = move(start);
        bw.write(res == null ? "-1" : Integer.toString(res.cnt));
        if(res != null) {
            bw.newLine();
            // 상 우 하 좌
            char[] t = {' ', 'U', 'R', 'D', 'L'};
            long k = track[res.redR][res.redC][res.blueR][res.blueC];
            long div = (long) Math.pow(10, res.cnt - 1);
            while(k != 0) {
                int p = (int) (k / div);
                bw.write(t[p]);
                k -= (p * div);
                div /= 10;
            }
        }
        bw.flush();
        bw.close();
    }
    public static Point move(Point start) {
        // 왔던 방향, 반대방향은 갈 필요 없음
        visited = new boolean[R][C][R][C];
        track = new long[R][C][R][C];
        visited[start.redR][start.redC][start.blueR][start.blueC] = true;
        deque = new ArrayDeque<>();
        deque.add(start);
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(arr[current.redR][current.redC] == 'O') {
                return current;
            }
            int[] next = current.dir == -1 ? new int[]{0, 1, 2, 3} : nextDir[current.dir % 2];
            for(int d : next) {
                int nRedR = current.redR;
                int nRedC = current.redC;
                int nBlueR = current.blueR;
                int nBlueC = current.blueC;

                while(arr[nRedR + dr[d]][nRedC + dc[d]] != '#' && arr[nRedR][nRedC] != 'O') {
                    nRedR += dr[d];
                    nRedC += dc[d];
                }
                while(arr[nBlueR + dr[d]][nBlueC + dc[d]] != '#' && arr[nBlueR][nBlueC] != 'O') {
                    nBlueR += dr[d];
                    nBlueC += dc[d];
                }
                if(nRedR == nBlueR && nRedC == nBlueC) {
                    if(arr[nRedR][nRedC] == 'O') {
                        continue;
                    }
                    if(Math.abs(nRedR - current.redR) + Math.abs(nRedC - current.redC) > Math.abs(nBlueR - current.blueR) + Math.abs(nBlueC - current.blueC)) {
                        nRedR -= dr[d];
                        nRedC -= dc[d];
                    } else {
                        nBlueR -= dr[d];
                        nBlueC -= dc[d];
                    }
                }
                if(!visited[nRedR][nRedC][nBlueR][nBlueC] && current.cnt + 1 <= 10) {
                    visited[nRedR][nRedC][nBlueR][nBlueC] = true;
                    track[nRedR][nRedC][nBlueR][nBlueC] = track[current.redR][current.redC][current.blueR][current.blueC] * 10 + (d + 1);
                    deque.add(new Point(nRedR, nRedC, nBlueR, nBlueC, d, current.cnt + 1));
                }
            }
        }

        return null;
    }
    // 상 우 하 좌
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    public static int[][] nextDir = {{1, 3}, {0, 2}};
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
        private char nextChar() throws IOException {
            byte c = read();
            if(c == '\n') {
                c = read();
            }

            return (char) c;
        }
    }
}