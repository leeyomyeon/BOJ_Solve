import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1395 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] segTree, lazy;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        segTree = new int[1 << h];
        lazy = new int[1 << h];
        for(int q = 0; q < M; q++) {
            int c = fr.nextInt();
            int l = fr.nextInt();
            int r = fr.nextInt();
            if(c == 0) { // A번부터 B번 사이의 스위치 상태를 반전
                update(1, N, 1, l, r);
            } else {    // 켜져 있는 스위치(구간 합)
                bw.write(find(1, N, 1, l, r) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static int find(int start, int end, int idx, int left, int right) {
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
    public static void update(int start, int end, int idx, int left, int right) {
        lazyPropagation(start, end, idx);
        if(right < start || end < left) {
            return;
        }
        if(left <= start && end <= right) {
            segTree[idx] = (end - start + 1) - segTree[idx];
            if(start != end) {
                lazy[idx * 2] = lazy[idx * 2] ^ 1;
                lazy[idx * 2 + 1] = lazy[idx * 2 + 1] ^ 1;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, left, right);
        update(mid + 1 , end,  idx * 2 + 1, left, right);
        segTree[idx] = segTree[idx * 2] + segTree[idx * 2 + 1];
    }
    public static void lazyPropagation(int start, int end, int idx) {
        if(lazy[idx] != 0) {
            segTree[idx] = (end - start + 1) - segTree[idx];
            if(start != end) {  // 0이면 1로, 1이면 0으로
                lazy[idx * 2] = lazy[idx * 2] ^ 1;
                lazy[idx * 2 + 1] = lazy[idx * 2 + 1] ^ 1;
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
