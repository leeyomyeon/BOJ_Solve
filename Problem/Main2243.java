import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
// 합세그, 사탕 순위 찾기
public class Main2243 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, Q;
    public static int[] segTree;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = 1_000_000;
        Q = fr.nextInt();
        segTree = new int[N * 4];
        for(int q = 0; q < Q; q++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            if(a == 2) {
                int c = fr.nextInt();
                update(1, N, 1, b, c);
                // b 꺼낼 사탕의 순위, c 그러한 사탕의 개수
                // c가 얄수일 경우 사탕을 넣고 음수일 때는 빼는 경우
            } else { // 꺼내는 경우
                int k = find(1, N, 1, b);
                // b 꺼낼 사탕의 번호
                bw.write(k + "\n");
                // 하나 꺼낸다
                // 해당 맛의 번호에서 하나 꺼냄
                update(1, N, 1, k, -1);
            }
        }
        bw.flush();
        bw.close();
    }
    public static int find(int start, int end, int idx, int target) {
        if(start == end) {
            return start;   // 찾으면 현재 인덱스 리턴
        }
        int mid = (start + end) / 2;
        if(target <= segTree[idx * 2]) { // 세그트리 값보다 작으면 왼쪽으로 찾음
            return find(start, mid, idx * 2, target);
        } else { // 크면 오른쪽으로 찾아봄
            // target > segTree[idx * 2]일땐 오른쪽 세그는 1부터 mid까지의 값을 모두 갖고있는게 아니라서 target에서 제외해줘야함
            return find(mid + 1, end, idx * 2 + 1, target - segTree[idx * 2]);
        }
    }
    public static void update(int start, int end, int idx, int target, int value) {
        if(target < start || end < target) {
            return;
        }
        segTree[idx] += value;
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, target, value);
        update(mid + 1, end, idx * 2 + 1, target, value);
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