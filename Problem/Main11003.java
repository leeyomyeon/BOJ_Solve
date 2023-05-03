import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class Main11003 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[] arr, segTree;
    public static int N, L;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        L = fr.nextInt();
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        for(int i = 1; i <= 24; i++) {
            if((int) Math.pow(2, i) >= N * 2) {
                segTree = new int[(int) Math.pow(2, i) + 1];
                break;
            }
        }
        makeSegTree(1, N, 1);
        for(int i = 1; i <= N; i++) {
            int left = Math.max(i - L + 1, 1);
            int k = find(1, N, 1, left, i);
            bw.write(Integer.toString(k));
            bw.write(32);
        }
        bw.flush();
        bw.close();
    }
    public static int makeSegTree(int start, int end, int idx) {
        if(start == end) {
            return segTree[idx] = arr[start];
        }
        int mid = (start + end) / 2;
        int left = makeSegTree(start, mid, idx * 2);
        int right = makeSegTree(mid + 1, end, idx * 2 + 1);
        return segTree[idx] = Math.min(left, right);
    }
    public static int find(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return Integer.MAX_VALUE;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return Math.min(find(start, mid, idx * 2, left, right), find(mid + 1, end, idx * 2 + 1, left, right));
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 26;
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
