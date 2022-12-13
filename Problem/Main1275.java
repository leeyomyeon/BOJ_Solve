import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1275 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, Q;
    public static int[] arr;
    public static long[] segTree;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        Q = fr.nextInt();
        arr = new int[N + 1];
        segTree = new long[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        makeSegTree(1, N, 1);
        for(int q = 0; q < Q; q++) {
            int x = fr.nextInt();
            int y = fr.nextInt();
            long res = find(1, N, 1, Math.min(x, y), Math.max(x, y));
            bw.write(res + "\n");
            int a = fr.nextInt();
            int b = fr.nextInt();
            update(1, N, 1, a, (long) b - arr[a]);
            arr[a] = b;
        }
        bw.write("");
        bw.flush();
        bw.close();
    }
    public static long makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx] = arr[start];
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        segTree[idx] = makeSegTree(start, mid, idx * 2) + makeSegTree(mid + 1, end, idx * 2 + 1);
        return segTree[idx];
    }
    public static long find(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return 0;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, left, right) + find(mid + 1, end, idx * 2 + 1, left, right);
    }
    public static void update(int start, int end, int idx, int target, long value) {
        if(target < start || target > end) {
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