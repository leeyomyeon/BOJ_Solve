import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main20052 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[] segTree, sum;
    public static int N, Q;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        // 배열의 크기를 N + 1을 잡는 것과 같은 방법으로 공백을 넣어줌
        String str = " " + fr.readLine();
        N = str.length() - 1;
        Q = fr.nextInt();
        segTree = new int[N * 4];
        sum = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + (str.charAt(i) == '(' ? 1 : -1); // 제대로 된 괄호인지 누적합 배열 생성
        }
        makeSegTree(1, N, 1);
        int res = 0;
        for(int q = 0; q < Q; q++) { // 쿼리 개수
            int i = fr.nextInt();
            int j = fr.nextInt();
            // i ~ j값에서 최소값 찾음
            int value = find(1, N, 1, i, j);
            // 누적합 배열의 구간 [i, j]의 최솟값이 누적합 배열의 i-1번째 값보다 크거나 같은가?
            res += (sum[j] - sum[i - 1] == 0 && value >= sum[i - 1]) ? 1 : 0;
        }
        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
    public static int makeSegTree(int start, int end, int idx) {
        if(start == end) {
            return segTree[idx] = sum[start];
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
        private final int BUFFER_SIZE = 1 << 17;
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
