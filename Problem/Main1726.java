import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main1726 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C;
    public static int[][] arr;
    public static boolean[][][] visited;
    public static ArrayDeque<Robot> deque;
    public static class Robot {
        int r, c, dir, cnt;

        public Robot(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        R = fr.nextInt();
        C = fr.nextInt();
        arr = new int[R + 1][C + 1];
        visited = new boolean[5][R + 1][C + 1];
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                arr[i][j] = fr.nextInt();
            }
        }
        deque = new ArrayDeque<>();
        Robot start = new Robot(fr.nextInt(), fr.nextInt(), fr.nextInt(), 0);
        Robot end = new Robot(fr.nextInt(), fr.nextInt(), fr.nextInt(), 0);
        deque.add(start);
        int min = Integer.MAX_VALUE;
        visited[start.dir][start.r][start.c] = true;
        while(!deque.isEmpty()) {
            Robot current = deque.removeFirst();

            if(current.r == end.r && current.c == end.c) {
                int nCnt;
                if((current.dir <= 2 && end.dir >= 3) || (current.dir >= 3 && end.dir <= 2)) {
                    nCnt = 1;
                } else if(current.dir == end.dir){
                    nCnt = 0;
                } else {
                    nCnt = 2;
                }
                min = Math.min(min, current.cnt + nCnt);
                continue;
            }

            for(int d = 1; d <= 4; d++) {
                // 같으면 전진
                if(current.dir == d) {
                    for(int k = 1; k <= 3; k++) {
                        int nr = current.r + dr[d] * k;
                        int nc = current.c + dc[d] * k;
                        if(nr > 0 && nr <= R && nc > 0 && nc <= C && arr[nr][nc] == 0) {
                            if(!visited[d][nr][nc]) {
                                visited[d][nr][nc] = true;
                                deque.add(new Robot(nr, nc, d, current.cnt + 1));
                            }
                        } else {
                            break;
                        }
                    }
                } else {    // 회전
                    if(!visited[d][current.r][current.c]) {
                        visited[d][current.r][current.c] = true;
                        int nCnt;
                        if((current.dir <= 2 && d >= 3) || (current.dir >= 3 && d <= 2)) {
                            nCnt = 1;
                        } else {
                            nCnt = 2;
                        }
                        deque.add(new Robot(current.r, current.c, d, current.cnt + nCnt));
                    }
                }
            }
        }
        bw.write(min + "");
        bw.flush();
        bw.close();
    }
    // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
    public static int[] dr = {0, 0, 0, 1, -1};
    public static int[] dc = {0, 1, -1, 0, 0};
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
    }
}