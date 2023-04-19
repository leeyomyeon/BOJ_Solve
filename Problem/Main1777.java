import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
/*
1부터 N번까지로 수로 이루어진 순열이 있다.
그리고 이 순열과 연관된 "Inversion sequence"라고 부르는 수열이 있다.
이 수열의 i번째 원소에는 순열에서 i보다 뒤에 나오면서 i보다 작은 수의 개수가 들어간다.
2  4  5  1  7  6  3  8
위의 순열이 있다면 이것의 Inversion sequence는
0  1  0  2  2  1  2  0 이 된다.
문제는 역으로 어떤 Inversion sequence가 주어지면 이것에 해당하는 순열을 찾는 프로그램을 작성하는 것이다.

8
0 1 0 2 2 1 2 0
2 4 5 1 7 6 3 8

8
0 1 2 3 4 5 6 7
8 7 6 5 4 3 2 1
 */
public class Main1777 {
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
            update(1, N, 1, i, 1);
        }
        for(int i = N; i >=1; i--) {
            int idx = find(1, N, 1, arr[i] + 1);
            result[idx] = i;
        }
        for(int i = N; i >= 1; i--) {
            bw.write(result[i] + " ");
        }
        bw.write("");
        bw.flush();
        bw.close();
    }
    public static int find(int start, int end, int idx, int target) {
        if(start == end) {
            segTree[idx]--;
            return start;
        }
        int mid = (start + end) / 2;    // 좌우 나눠서 탐색
        int r;
        if(target <= segTree[idx * 2]) {
            r = find(start, mid, idx * 2, target);
        } else {
            r = find(mid + 1, end, idx * 2 + 1, target - segTree[idx * 2]);
        }
        segTree[idx]--;
        return r;
    }
    public static void update(int start, int end, int idx, int target, int value) {
        if(target < start || end < target) {
            return;
        }
        segTree[idx] += value;   // update
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, target, value);
        update(mid + 1, end, idx * 2 + 1, target, value);
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
