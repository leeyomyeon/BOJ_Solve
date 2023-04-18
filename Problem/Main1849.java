import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1849 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr, segTree, result;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        segTree = new int[N * 4];
        result = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
            // 리프노드를 1로 초기화 및 누적합 Segment Tree 생성
            update(1, N, 1, i, 1);
        }
        for(int i = 1; i <= N; i++) {
            //  왼쪽부터 몇 개의 숫자가 있는지 찾음, find 내에서 찾은만큼 누적합에서 1씩 뺌
            int idx = find(1, N, 1, arr[i] + 1);
            result[idx] = i;
        }
        for(int i = 1; i <= N; i++) {
            bw.write(Integer.toString(result[i]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void update(int start, int end, int idx, int target, int value) {
        if(target < start || end < target) {    // 범위 밖이면
            return;
        }
        segTree[idx] += value;
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, target, value);
        update(mid + 1, end, idx * 2 + 1, target, value);
    }
    public static int find(int start, int end, int idx, int target) {
        if(start == end) {
            segTree[idx]--;
            return start;
        }
        int mid = (start + end) / 2;
        int resIdx;
        if(target <= segTree[idx * 2]) {
            resIdx = find(start, mid, idx * 2, target);
        } else {
            resIdx = find(mid + 1, end, idx * 2 + 1, target - segTree[idx * 2]);
        }
        segTree[idx]--;
        return resIdx;
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
