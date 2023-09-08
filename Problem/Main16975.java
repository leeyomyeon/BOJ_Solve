import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main16975 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static long[] segTree, lazy;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        segTree = new long[1 << h];
        lazy = new long[1 << h];
        makeSegTree(1, N, 1);
        M = fr.nextInt();
        for(int q = 0; q < M; q++) {
            int c = fr.nextInt();
            if(c == 1) {
                int l = fr.nextInt();
                int r = fr.nextInt();
                int v = fr.nextInt();
                update(1, N, 1, l, r, v);
            } else {
                int target = fr.nextInt();
                bw.write(find(1, N, 1, target)  + "\n");
            }
        }
        bw.write("");
        bw.flush();
        bw.close();
    }
    public static void makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        makeSegTree(start, mid, idx * 2);
        makeSegTree(mid + 1, end, idx * 2 + 1);
    }
    public static void lazyPropagation(int start, int end, int idx) {
        if(lazy[idx] != 0) {
            if(start == end) {  // 구간의 합을 구하는게 아닌 target의 값만 얻으면 되니 맨 아래 노드에만 갱신
                segTree[idx] += lazy[idx];
            }
            if(start != end) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }
    public static void update(int start, int end, int idx, int left, int right, int value) {
        lazyPropagation(start, end, idx);
        if(right < start || end < left) {
            return;
        }
        if(left <= start && end <= right) {
            if(start == end) { // 구간의 합을 구하는게 아닌 target의 값만 얻으면 되니 맨 아래 노드에만 갱신
                segTree[idx] += value;
            }
            if(start != end) {
                lazy[idx * 2] += value;
                lazy[idx * 2 + 1] += value;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, left, right, value);
        update(mid + 1, end, idx * 2 + 1, left, right, value);
    }
    public static long find(int start, int end, int idx, int target) {
        lazyPropagation(start, end, idx);
        if(target < start || end < target) {
            return 0;
        }
        if(target <= start && end <= target) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        long l = find(start, mid, idx * 2, target);
        long r = find(mid + 1, end, idx * 2 + 1, target);
        return l + r;
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
