import java.io.*;

public class Main18436 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, Q;
    public static int[] arr;
    public static int[][] segTree;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        segTree = new int[N * 4][2];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        makeSegTree(1, N, 1);
        Q = fr.nextInt();
        for(int q = 0; q < Q; q++) {
            int c = fr.nextInt();
            int a = fr.nextInt();
            int b = fr.nextInt();
            if(c == 1) {
                editSegTree(1, N, 1, a, b);
                arr[a] = b;
            } else if(c == 2) {
                int[] k = find(1, N, 1, a, b);
                bw.write(k[0] + "\n");
            } else {
                int[] k = find(1, N, 1, a, b);
                bw.write(k[1] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
    public static int[] makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx][arr[start] % 2] = 1;
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        int[] left = makeSegTree(start, mid, idx * 2);
        int[] right = makeSegTree(mid + 1, end, idx * 2 + 1);
        segTree[idx][0] += (left[0] + right[0]);
        segTree[idx][1] += (left[1] + right[1]);
        return segTree[idx];
    }
    public static int[] find(int start, int end, int idx, int left, int right) {
        if(left > end || right < start) {
            return new int[]{0, 0};
        }
        if(left <= start && right >= end) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        int[] leftNode = find(start, mid, idx * 2, left, right);
        int[] rightNode = find(mid + 1, end, idx * 2 + 1, left, right);
        return new int[]{leftNode[0] + rightNode[0], leftNode[1] + rightNode[1]};
    }
    // target을 value로 수정
    public static void editSegTree(int start, int end, int idx, int target, int value) {
        if(target < start || target > end) {
            return;
        }
        if(arr[target] % 2 != value % 2) { // 둘다 짝수이거나 홀수이면 갱신할 필요 없음
            if(value % 2 == 0) {
                segTree[idx][0]++;
                segTree[idx][1]--;
            } else {
                segTree[idx][0]--;
                segTree[idx][1]++;
            }
        }
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        editSegTree(start, mid, idx * 2, target, value);
        editSegTree(mid + 1, end, idx * 2 + 1, target, value);
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