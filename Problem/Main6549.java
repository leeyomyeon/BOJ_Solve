import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main6549 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr, segTree;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        while(true) {
            N = fr.nextInt();
            if(N == 0) {
                break;
            }
            arr = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                arr[i] = fr.nextInt();
            }
            segTree = new int[N * 4];
            makeSegTree(1, N, 1);
            bw.write(Long.toString(dnq(1, N)));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static long dnq(int start, int end) {
        if(start > end) {
            return -1;
        }
        int lowIdx = find(1, N, 1, start, end);
        long size = (long) (end - start + 1) * arr[lowIdx];
        // lowIdx기준 왼쪽
        if(lowIdx != start) {
            size = Math.max(size, dnq(start, lowIdx - 1));
        }
        // lowIdx기준 오른쪽
        if(lowIdx != end) {
            size = Math.max(size, dnq(lowIdx + 1, end));
        }
        return size;
    }
    public static int makeSegTree(int start, int end, int idx) {
        if(start == end) {
            return segTree[idx] = start;
        }
        int mid = (start + end) / 2;
        int left = makeSegTree(start, mid, idx * 2);
        int right = makeSegTree(mid + 1, end, idx * 2 + 1);
        return segTree[idx] = arr[left] < arr[right] ? left : right;
    }
    public static int find(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return -1;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        int leftIdx = find(start, mid, idx * 2, left, right);
        int rightIdx = find(mid + 1, end, idx * 2 + 1, left, right);
        if(leftIdx == -1) {
            return rightIdx;
        }
        if(rightIdx == -1) {
            return leftIdx;
        }
        return arr[leftIdx] < arr[rightIdx] ? leftIdx : rightIdx;
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