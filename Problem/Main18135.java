import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main18135 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static long[] segTree, lazy;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        M = fr.nextInt();
        int h = (int) Math.ceil(Math.log(M) / Math.log(2)) + 1;
        segTree = new long[1 << h];
        lazy = new long[1 << h];
        for(int m = 1; m <= M; m++) {
            int l = fr.nextInt();
            int r = fr.nextInt();
            int v = fr.nextInt();
            for(int i = l; i <= r; i++) {
                arr[i] = m;
            }
            // l부터 r까지를 m으로 채우는 방법
            update(1, M, 1, m, m, v);
        }
        while(true) {
            int c = fr.nextInt();
            int l = fr.nextInt();
            int r = fr.nextInt();
            if(c == 0) {
                break;
            } else if(c == 1) {
                if(l > r) {
                    long a;
                    long b;
                    if(arr[l] == arr[r] && arr[l] == (arr[r] - 1 == 0 ? M : arr[r] - 1)) {
                        a = find(1, M, 1, arr[l], M);
                        b = 0;
                    } else if(arr[l] == arr[r]) {
                        a = find(1, M, 1, arr[l], M);
                        b = find(1, M, 1, 1, (arr[r] - 1 == 0 ? M : arr[r] - 1));
                    } else {
                        a = find(1, M, 1, arr[l], M);
                        b = find(1, M, 1, 1, arr[r]);
                    }
                    bw.write(Long.toString(a + b));
                } else {
                    long a = find(1, M, 1, arr[l], arr[r]);
                    bw.write(Long.toString(a));
                }
                bw.newLine();
            } else {
                int v = fr.nextInt();
                if(l > r) {
                    if(arr[l] == arr[r] && arr[l] == (arr[r] - 1 == 0 ? M : arr[r] - 1)) {
                        update(1, M, 1, arr[l], arr[l], v);
                    } else if(arr[l] == arr[r]) {
                        update(1, M, 1, arr[l], M, v);
                        update(1, M, 1, 1, (arr[r] - 1 == 0 ? M : arr[r] - 1), v);
                    } else {
                        update(1, M, 1, arr[l], M, v);
                        update(1, M, 1, 1, arr[r], v);
                    }
                } else {
                    update(1, M, 1, arr[l], arr[r], v);
                }
            }
        }
        bw.flush();
        bw.close();
    }

    public static void lazyPropagation(int start, int end, int idx) {
        if(lazy[idx] != 0) {
            segTree[idx] += (end - start + 1L) * lazy[idx];
            if(start != end) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }
    public static void update(int start, int end, int idx, int left, int right, int value) {
        lazyPropagation(start, end, idx);
        if (right < start || end < left) {
            return;
        }
        if(left <= start && end <= right) {
            segTree[idx] += (end - start + 1L) * value;
            if(start != end) {
                lazy[idx * 2] += value;
                lazy[idx * 2 + 1] += value;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, left, right, value);
        update(mid + 1, end, idx * 2 + 1, left, right, value);
        segTree[idx] = segTree[idx * 2] + segTree[idx * 2 + 1];
    }

    public static long find(int start, int end, int idx, int left, int right) {
        lazyPropagation(start, end, idx);
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, left, right) + find(mid + 1, end, idx * 2 + 1, left, right);
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