import java.io.*;
import java.util.ArrayList;

public class SWEA1767 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static class Core {
        int r, c;
        ArrayList<Integer> dir;
        public Core(int r, int c) {
            this.r = r;
            this.c = c;
            dir = new ArrayList<>();
        }
        public void addDir(int d) {
            dir.add(d);
        }
        public int dirSize() {
            return dir.size();
        }
    }
    public static ArrayList<Core> list;
    public static int N, minLen, maxCnt, connectCnt, coreCnt;
    public static int[][] arr;
    public static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = fr.nextInt();
            arr = new int[N][N];
            list = new ArrayList<>();
            visited = new boolean[N][N];
            minLen = Integer.MAX_VALUE;
            maxCnt = Integer.MIN_VALUE; // 최대한 연결시킨 갯수
            connectCnt = 0; // 현재 연결되어 있는 갯수
            coreCnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    arr[i][j] = fr.nextInt();
                    if(arr[i][j] == 1) {
                        coreCnt++;
                        visited[i][j] = true;
                    }
                }
            }
            for(int i = 1; i < N - 1; i++) {
                for(int j = 1; j < N - 1; j++) {
                    if(arr[i][j] == 1) {
                        Core core = new Core(i, j);
                        for(int d = 0; d < 4; d++) {
                            int ni = i;
                            int nj = j;
                            while(true) {
                                ni += dr[d];
                                nj += dc[d];
                                if(arr[ni][nj] == 1) {
                                    break;
                                }
                                if((ni == 0 || ni == N - 1 || nj == 0 || nj == N - 1) && arr[ni][nj] == 0) {
                                    core.addDir(d);
                                    break;
                                }
                            }
                        }
                        list.add(core);
                    }
                }
            }
            connectCnt = coreCnt - list.size();
            dfs(0, 0, connectCnt, 0);
            bw.write("#" + tc + " " + minLen);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int idx, int next, int cnt, int sum) {
        if(idx == list.size()) {
            if(cnt > maxCnt) {
                maxCnt = cnt;
                minLen = sum;
            } else if(cnt == maxCnt) {
                minLen = Math.min(sum, minLen);
            }
            return;
        }

        for(int i = next; i < list.size(); i++) {
            Core current = list.get(i);
            boolean noConnect = true;
            for(int d : current.dir) {
                int K = 1;
                boolean isConnect = false;
                while(true) {
                    if(visited[current.r + (dr[d] * K)][current.c + (dc[d] * K)]) {
                        // 전선 있으면
                        isConnect = true;
                        break;
                    }
                    if(current.r + (dr[d] * K) == 0 || current.r + (dr[d] * K) == N - 1 || current.c + (dc[d] * K) == 0 || current.c + (dc[d] * K) == N - 1) {
                        // 벽에 닿았으면
                        break;
                    }
                    K++;
                }
                if(!isConnect) {// 전선 없으면 연결 가능
                    noConnect = false;
                    // 연결 많이되면
                    for(int k = 1; k <= K; k++) {
                        int nr = current.r + dr[d] * k;
                        int nc = current.c + dc[d] * k;
                        visited[nr][nc] = true;
                    }
                    dfs(idx + 1, i + 1, cnt + 1, sum + K);
                    for(int k = 1; k <= K; k++) {
                        int nr = current.r + dr[d] * k;
                        int nc = current.c + dc[d] * k;
                        visited[nr][nc] = false;
                    }
                }
            }
            if(current.dirSize() == 0 || noConnect) {
                dfs(idx + 1, i + 1, cnt, sum);
            }
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
    }
}