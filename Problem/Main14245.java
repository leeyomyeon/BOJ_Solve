import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main14245 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, segTree, lazy;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        segTree = new int[1 << h];
        lazy = new int[1 << h];
        makeSegTree(1, N, 1);
        M = fr.nextInt();
        for(int q = 0; q < M; q++) {
            int c = fr.nextInt();
            if(c == 1) {
                int l = fr.nextInt() + 1;
                int r = fr.nextInt() + 1;
                int v = fr.nextInt();
                update(1, N, 1, l, r, v);
            } else {
                int target = fr.nextInt() + 1;
                bw.write(find(1, N, 1, target) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static int makeSegTree(int start, int end, int idx) {
        if (start == end) {
            return segTree[idx] = arr[start];
        }
        int mid = (start + end) / 2;
        int left = makeSegTree(start, mid, idx * 2);
        int right = makeSegTree(mid + 1, end, idx * 2 + 1);
        return segTree[idx] = left ^ right;
    }

    public static void update(int start, int end, int idx, int left, int right, int value) {
        updateLazy(start, end, idx);
        if (right < start || end < left) {
            return;
        }
        if(left <= start && end <= right) {
            // 구간에 있을 땐 부모 노드의 값만 갱신하고 lazy에 값 담아둠, 다음번에 다른 노드가 방문할 때 한번에 갱신
            segTree[idx] ^= value;
            if(start != end) {
                lazy[idx * 2] ^= value;
                lazy[idx * 2 + 1] ^= value;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, left, right, value);
        update(mid + 1, end, idx * 2 + 1, left, right, value);
        segTree[idx] = segTree[idx * 2] ^ segTree[idx * 2 + 1];
    }

    public static int find(int start, int end, int idx, int target) {
        updateLazy(start, end, idx);
        if (target < start || end < target) {
            return 0;
        }
        if (target <= start && end <= target) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        int l = find(start, mid, idx * 2, target);
        int r = find(mid + 1, end, idx * 2 + 1, target);
        return l ^ r;
    }
    public static void updateLazy(int start, int end, int idx) {
        if(lazy[idx] != 0) {
            segTree[idx] ^= lazy[idx];
            if(start != end) {
                lazy[idx * 2] ^= lazy[idx];
                lazy[idx * 2 + 1] ^= lazy[idx];
            }
            lazy[idx] = 0;
        }
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
                ret = (ret << 3) + (ret << 1) + (c & 15);
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

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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
