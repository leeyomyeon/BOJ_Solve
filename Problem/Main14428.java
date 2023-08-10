import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main14428 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, segTree;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        segTree = new int[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        makeSegTree(1, N, 1);
        M = fr.nextInt();
        for(int i = 0; i < M; i++) {
            // 1 i v Ai를 v로 바꾼다
            // 2 i j i ~ j 사이 최소 인덱스 출력 그러한 값이 여러개인 경우 가장 작은 것 출력
            int command = fr.nextInt();
            if(command == 1) {
                int target = fr.nextInt();
                int value = fr.nextInt();
                arr[target] = value;
                update(1, N, 1, target);
            } else {
                int left = fr.nextInt();
                int right = fr.nextInt();
                int idx = find(1, N, 1, left, right);
                bw.write(Integer.toString(idx));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
    public static int makeSegTree(int start, int end, int idx) {
        if(start == end) {
            return segTree[idx] = start;
        }
        int mid = (start + end) / 2;
        int leftIdx = makeSegTree(start, mid, idx * 2);
        int rightIdx = makeSegTree(mid + 1, end, idx * 2 + 1);
        if(arr[leftIdx] == arr[rightIdx]) {
            return segTree[idx] = Math.min(leftIdx, rightIdx);
        } else {
            return segTree[idx] = arr[leftIdx] < arr[rightIdx] ? leftIdx : rightIdx;
        }
    }
    public static int update(int start, int end, int idx, int target) {
        if(target < start || end < target) {
            return segTree[idx];
        }
        if(start == end) {
            return segTree[idx] = target;
        }
        int mid = (start + end) / 2;
        int leftIdx = update(start, mid, idx * 2, target);
        int rightIdx = update(mid + 1, end, idx * 2 + 1, target);
        if(arr[leftIdx] == arr[rightIdx]) {
            return segTree[idx] = Math.min(leftIdx, rightIdx);
        } else {
            return segTree[idx] = arr[leftIdx] < arr[rightIdx] ? leftIdx : rightIdx;
        }
    }
    public static int find(int start, int end, int idx, int left, int right) {
        if(end < left || right < start) {
            return Integer.MAX_VALUE;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        int leftIdx = find(start, mid, idx * 2, left, right);
        int rightIdx = find(mid + 1, end, idx * 2 + 1, left, right);
        if(leftIdx == Integer.MAX_VALUE) return rightIdx;
        if(rightIdx == Integer.MAX_VALUE) return leftIdx;
        if(arr[leftIdx] == arr[rightIdx])  {
            return Math.min(leftIdx, rightIdx);
        } else {
            return arr[leftIdx] < arr[rightIdx] ? leftIdx : rightIdx;
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
