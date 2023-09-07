import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main10999 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, K;
    public static long[] arr, segTree, lazy;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        K = fr.nextInt();
        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = fr.nextLong();
        }
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        segTree = new long[1 << h];
        lazy = new long[1 << h];
        makeSegTree(1, N, 1);
        for(int q = 0; q < M + K; q++) {
            int a = fr.nextInt();
            int l = fr.nextInt();
            int r = fr.nextInt();
            if(a == 1) {
                long v = fr.nextLong();
                update(1, N, 1, l, r, v);
            } else {
                long res = find(1, N, 1, l, r);
                bw.write(res + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static long makeSegTree(int start, int end, int idx) {
        if (start == end) {
            return segTree[idx] = arr[start];
        }
        int mid = (start + end) / 2;
        long left = makeSegTree(start, mid, idx * 2);
        long right = makeSegTree(mid + 1, end, idx * 2 + 1);
        return segTree[idx] = left + right;
    }
    public static void update(int start, int end, int idx, int left, int right, long value) {
        updateLazy(start, end, idx);
        if(right < start || end < left) {
            return;
        }
        if(left <= start && end <= right) {
            // 구간에 있을 땐 부모 노드의 값만 갱신하고 lazy에 값 담아둠, 다음번에 다른 노드가 방문할 때 한번에 갱신
            segTree[idx] += (end - start + 1) * value;
            if(start != end) {
                lazy[idx * 2] += value;
                lazy[idx * 2 + 1] += value;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, left, right, value);
        update(mid + 1, end, idx * 2 + 1, left, right, value);
        segTree[idx] = segTree[idx * 2] + segTree[idx * 2 + 1]; // left + right update
    }
    public static long find(int start, int end, int idx, int left, int right) {
        updateLazy(start, end, idx);
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, left, right) + find(mid + 1, end, idx * 2 + 1, left, right);
    }
    public static void updateLazy(int start, int end, int idx) {
        if(lazy[idx] != 0) {
            segTree[idx] += (end - start + 1) * lazy[idx];
            if(start != end) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 24;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() throws Exception {
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