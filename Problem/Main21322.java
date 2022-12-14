import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main21322 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[][] arr, idx, beltIdx;
    public static int[] rotate;
    public static int[][] belt;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N][N];
        idx = new int[N][N];
        beltIdx = new int[N][N];
        rotate = new int[N / 2];    // 몇번째 벨트가 얼마나 회전했는지
        belt = new int[N / 2][];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = fr.nextInt();
            }
        }
        // 인덱스 저장하는 배열
        for(int i = 0; i < N / 2; i++) {    //  바깥부터
            // 벨트 길이 구하기
            int len = (N - (i * 2) - 1) * 4;
            belt[i] = new int[len];
            int r = i;
            int c = i;
            int j = 0;
            for(; c < N - i - 1; c++) {
                belt[i][j] = arr[r][c];
                idx[r][c] = j;
                beltIdx[r][c] = i;
                j++;
            }
            for(; r < N - i - 1; r++) {
                belt[i][j] = arr[r][c];
                idx[r][c] = j;
                beltIdx[r][c] = i;
                j++;
            }
            for(; c > i; c--) {
                belt[i][j] = arr[r][c];
                idx[r][c] = j;
                beltIdx[r][c] = i;
                j++;
            }
            for(; r > i; r--) {
                belt[i][j] = arr[r][c];
                idx[r][c] = j;
                beltIdx[r][c] = i;
                j++;
            }
        }
        // 전처리
        for(int m = 0; m < M; m++) {
            int command = fr.nextInt();
            int a = fr.nextInt();
            int b = fr.nextInt();

            if(command == 1) {  // 바깥에서 a번째 위치한 컨베이어 벨트를 시계 방향으로 b칸 만큼 회전시킨다.
                rotate[a - 1] = (rotate[a - 1] - (b % belt[a - 1].length) + belt[a - 1].length) % belt[a - 1].length; // 벨트 길이로 MOD연산
            } else if(command == 2) {
                /*
                a - 1, b - 1이 몇번째 벨트인지
                벨트 번호 = beltIdx[a - 1][b - 1]
                해당 벨트 인덱스는 ? idx[a - 1][b - 1]
                회전한 만큼 이동하면 (idx[a - 1][b - 1] + rotate[a - 1]) % belt[a - 1].length
                 */
                int tmp = -1, tmpBeltNum = 0, tmpBeltIdx = 0;
                for(int d = 1; d < 4; d++) {
                    int prevNum = beltIdx[a + dr[d - 1]][b + dc[d - 1]];
                    int prevIdx = (idx[a + dr[d - 1]][b + dc[d - 1]] + rotate[prevNum]) % belt[prevNum].length;
                    if(d == 1) {
                        tmp = belt[prevNum][prevIdx];
                    }
                    int nextNum = beltIdx[a + dr[d]][b + dc[d]];
                    int nextIdx = (idx[a + dr[d]][b + dc[d]] + rotate[nextNum]) % belt[nextNum].length;
                    belt[prevNum][prevIdx] = belt[nextNum][nextIdx];
                    if(d == 3) {
                        tmpBeltNum = nextNum;
                        tmpBeltIdx = nextIdx;
                    }
                }
                belt[tmpBeltNum][tmpBeltIdx] = tmp;
            } else {
                // (e, f) 칸 위에 올려져 있는 수를 물어본다. 태영이는 이 질문에 정확하게 답을 해야 한다.
                int r = a - 1;
                int c = b - 1;
                int beltNum = beltIdx[r][c];
                int beltIdx = (idx[r][c] + rotate[beltNum]) % belt[beltNum].length;
                bw.write(belt[beltNum][beltIdx] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 0, 0, -1};
    public static int[] dc = {-1, -1, 0, 0};

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