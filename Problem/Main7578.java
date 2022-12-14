import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main7578 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arrA, segTree;
    public static int[] factory;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        factory = new int[1000001];  // 각 번호에 맞는 인덱스 저장
        arrA = new int[N + 1];
        segTree = new int[N * 4];
        for(int i = 1; i <= N; i++) {
            arrA[i] = fr.nextInt();
        }
        for(int i = 1; i <= N; i++) {
            factory[fr.nextInt()] = i;   // 반대편 공장 인덱스가 어디?
        }
        long sum = 0;
        for(int i = 1; i <= N; i++) {
            int k = find(1, N, 1, factory[arrA[i]], N);
            sum += k;
            update(1, N, 1, factory[arrA[i]], 1);
        }
        bw.write(Long.toString(sum));
        bw.flush();
        bw.close();
    }
    public static int find(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return 0;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, left, right) + find(mid + 1, end, idx * 2 + 1, left, right);
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