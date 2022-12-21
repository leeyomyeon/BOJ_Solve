import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main15949 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[][] arr;
    public static int[][] group;
    public static ArrayList<Codel[][]> sortedCodel;
    public static class Codel {
        int r, c;
        int dp; // 상우하좌
        int cc; // 상우하좌
        public Codel (int r, int c, int dp, int cc) {
            this.r = r;
            this.c = c;
            this.dp = dp;
            this.cc = cc;
        }
        void rotateDp() { // 시계방향으로 회전, cc도 같이 움직임
            this.dp = (this.dp + 1) % 4;
            this.cc = (this.cc + 1) % 4;
        }
        void reverseCc() {
            this.cc = (this.cc + 2) % 4;
        }
        public void setDp(int dp) {
            this.dp = dp;
        }
        public void setCc(int cc) {
            this.cc = cc;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N][M];
        group = new int[N][M]; // 구역의 번호 저장
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                arr[i][j] = fr.nextChar();
                group[i][j] = -1;
            }
        }
        init();
        Codel start = new Codel(0, 0, 1, 0);
        Loop1:
        for(int s = 0; s <= sortedCodel.size(); s++) {
            bw.write(arr[start.r][start.c]);
            for(int p = 0; p < 4; p++) {
                for(int k = 0; k < 2; k++) {
                    Codel next = sortedCodel.get(group[start.r][start.c])[start.dp][start.cc];
                    int nr = next.r + dr[start.dp];
                    int nc = next.c + dc[start.dp];
                    if(nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] != 'X') { // 다음으로 갈수 있으면
                        start = new Codel(nr, nc, start.dp, start.cc);
                        continue Loop1;
                    }
                    //갈수 없으면 CC반대로 가고
                    if(k == 0) {
                        start.reverseCc();
                    }
                }
                // 그래도 안되면
                start.rotateDp();
            }
            break;
        }
        bw.flush();
        bw.close();
    }
    public static void init() {
        boolean[][] visited = new boolean[N][M];
        sortedCodel = new ArrayList<>();
        int num = 0;
        ArrayDeque<Codel> deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && arr[i][j] != 'X') {
                    visited[i][j] = true;
                    group[i][j] = num;
                    deque.add(new Codel(i, j, 0, 0));
                    ArrayList<Codel> selected = new ArrayList<>();
                    selected.add(new Codel(i, j, 0, 0));
                    while(!deque.isEmpty()) {
                        Codel current = deque.removeFirst();

                        for(int d = 0; d < 4; d++) {
                            int nr = current.r + dr[d];
                            int nc = current.c + dc[d];
                            if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && arr[i][j] == arr[nr][nc]) {
                                visited[nr][nc] = true;
                                group[nr][nc] = num;
                                Codel next = new Codel(nr, nc, current.dp, current.cc);
                                deque.add(next);
                                selected.add(next);
                            }
                        }
                    }
                    Codel[][] codelList = new Codel[4][4];
                    for(int d = 0; d < 4; d++) {
                        for(int k = 1; k <= 3; k += 2) {
                            int nk = (d + k) % 4;
                            int finalD = d;
                            Collections.sort(selected, (o1, o2) -> {
                                if(finalD == 0) {  // 상
                                    if(nk == 1 && o1.r == o2.r) {  // 우
                                        return o2.c - o1.c;
                                    } else if(nk == 3 && o1.r == o2.r) {    // 좌
                                        return o1.c - o2.c;
                                    }
                                    return o1.r - o2.r;
                                } else if(finalD == 1) {   // 우
                                    if(nk == 0 && o1.c == o2.c) { // 상
                                        return o1.r - o2.r;
                                    } else if (nk == 2 && o1.c == o2.c) {    // 하
                                        return o2.r - o1.r;
                                    }
                                    return o2.c - o1.c;
                                } else if(finalD == 2) {   // 하
                                    if(nk == 1 && o1.r == o2.r) {  // 우
                                        return o2.c - o1.c;
                                    } else if (nk == 3 && o1.r == o2.r) {    // 좌
                                        return o1.c - o2.c;
                                    }
                                    return o2.r - o1.r;
                                } else {    // 좌
                                    if(nk == 0 && o1.c == o2.c) { // 상
                                        return o1.r - o2.r;
                                    } else if(nk == 2 && o1.c == o2.c) {    // 하
                                        return o2.r - o1.r;
                                    }
                                    return o1.c - o2.c;
                                }
                            });
                            Codel sorted = selected.get(0);
                            codelList[d][nk] = sorted;
                        }
                    }
                    sortedCodel.add(codelList);
                    num++;
                }
            }
        }
    }
    // 상우하좌
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
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
        public int nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return c;
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
/*
8 8
RRRGGGGG
RRYYGGGP
RYYYYYYP
RRRYYYYP
RRYYYYGP
RYYRRYGP
BBRRRGGG
BBBBRRGG

→↑↓←
1 →↑
2 →↓
3 ↓←
4 ↓→
5 ←↓
6 ←↑
7 ↑→
8 ↑←

8 8
XXXRRRRR
GYRRGGGG
GYRRYGGY
GYRYYYYY
GYYYYRRR
BBBXXXRR
BBBBGGGG
BBBBBGGG
 */