import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main15559 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[][] list;
    public static boolean[][] visited;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N * M];
        for(int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }
        list = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                char c = fr.nextChar();
                if(c == 'S') {
                    list[i][j] = 3;
                } else {
                    list[i][j] = (c - 69) % 8;
                }
            }
        }
        // 싸이클의 갯수 구하기
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < arr.length; i++) {
            int target = find(i);
            set.add(target);
        }
        bw.write(Integer.toString(set.size()));
        bw.flush();
        bw.close();
    }
    public static void dfs(int r, int c) {
        int nr = r + dr[list[r][c]];
        int nc = c + dc[list[r][c]];
        if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
            visited[nr][nc] = true;
            union(r * M + c, nr * M + nc);
            dfs(nr, nc);
        } else if(visited[nr][nc]) {
            union(r * M + c, nr * M + nc);
        }
    }

    // E동 N북 W서 S남
    public static int[] dr = {0, -1, 0, 1};
    public static int[] dc = {1, 0, -1, 0};
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