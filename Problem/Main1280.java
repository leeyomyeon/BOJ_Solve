import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main1280 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, cntSegTree;
    public static long[] sumSegTree;
    public static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = 200000;
        arr = new int[N + 1];
        cntSegTree = new int[M * 4];
        sumSegTree = new long[M * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();  // i번 나무의 좌표
        }
        long res = 1;
        for(int i = 1; i <= N; i++) {
            int low = findLoc(0, M, 1, 0, arr[i] - 1); // 현재 위치보다 작은 좌표에 있는 나무 개수
            long lowSum = findSum(0, M, 1, 0, arr[i] - 1); // 현재 위치보다 작은 좌표에 있는 나무들의 좌표합

            int high = findLoc(0, M, 1, arr[i], M);     // 현재 위치보다 크거나 같은 좌표에 있는 나무 개수
            long highSum = findSum(0, M, 1, arr[i], M);       // 현재 위치보다 크거나 같은 좌표에 있는 나무들의 좌표합
            long left = ((long) arr[i] * low) - lowSum;
            long right = highSum - ((long) arr[i] * high);
            if(i >= 2) {
                res *= (left + right) % MOD;
                res %= MOD;
            }
            updateLocSegTree(0, M, 1, arr[i], 1);
            updateSumSegTree(0, M, 1, arr[i], arr[i]);
        }
        bw.write(Long.toString(res));
        bw.flush();
        bw.close();
    }
    public static void updateSumSegTree(int start, int end, int idx, int target, int value) { // arr[i] = 좌표
        if(target < start || end < target) {
            return;
        }
        sumSegTree[idx] += value;
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        updateSumSegTree(start, mid, idx * 2, target, value);
        updateSumSegTree(mid + 1, end, idx * 2 + 1, target, value);
    }
    public static void updateLocSegTree(int start, int end, int idx, int target, int value) {
        if(target < start || end < target) {
            return;
        }
        cntSegTree[idx] += value;
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        updateLocSegTree(start, mid, idx * 2, target, value);
        updateLocSegTree(mid + 1, end, idx * 2 + 1, target, value);
    }
    public static int findLoc(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return 0;
        }
        if(left <= start && end <= right) {
            return cntSegTree[idx];
        }
        int mid = (start + end) / 2;
        return findLoc(start, mid, idx * 2, left, right) + findLoc(mid + 1, end, idx * 2 + 1, left, right);
    }
    public static long findSum(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return 0;
        }
        if(left <= start && end <= right) {
            return sumSegTree[idx];
        }
        int mid = (start + end) / 2;
        return findSum(start, mid, idx * 2, left, right) + findSum(mid + 1, end, idx * 2 + 1, left, right);
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