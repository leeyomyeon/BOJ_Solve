import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1572 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, Q, K;
    public static int[] arr, segTree;
    // 카운팅 정렬 세그먼트 트리
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = 65535;
        Q = fr.nextInt();
        K = fr.nextInt();
        arr = new int[Q + 1];
        segTree = new int[N * 4];
        long sum = 0;
        for(int i = 1; i <= Q; i++) {
            arr[i] = fr.nextInt();
            // 온도는 0부터 65535라 시작값이 0이되어야 한다
            update(0, N, 1, arr[i], 1); // 새로운 수 넣음
            if(i >= K) {
                int x = find(0, N, 1, (K + 1) / 2);     // 중앙값 찾음
                sum += x;
                update(0, N, 1, arr[i - K + 1], -1);    // 지나간 수는 뺌
            }
        }
        bw.write(Long.toString(sum));
        bw.flush();
        bw.close();
    }
    public static int find(int start, int end, int idx, int target) {
        if(start == end) {
            return start;
        }
        int mid = (start + end) / 2;
        if(target <= segTree[idx * 2]) {
            return find(start, mid, idx * 2, target);
        } else {
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