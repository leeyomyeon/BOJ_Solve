import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main3745 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] lis;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        byte in;
        while((in = fr.read()) > 0) {
            N = fr.nextInt(in);
            if(N <= 0) {    // EOF 처리
                break;
            }
            lis = new int[N];
            lis[0] = fr.nextInt();
            int idx = 1;
            for(int i = 1; i < N; i++) {
                int k = fr.nextInt();
                if(lis[idx - 1] < k) { // 증가하면 lis 다음 값에 넣음
                    lis[idx] = k;
                    idx++;
                } else { // 작으면 이분 탐색으로 0부터 idx까지 어디에 들어가야 하는지 인덱스를 찾음
                    int chgIdx = find(0, idx, k);
                    lis[chgIdx] = k;
                }
            }
            bw.write(idx + "\n");
            bw.flush();
        }
        bw.close();
    }
    public static int find(int start, int end, int target) {
        while(start < end) {
            int mid = (start + end) / 2;
            if(lis[mid] < target) { // 중앙값이 찾으려는 값보다 작으면
                start = mid + 1; // 시작값을 중앙값 + 1로 잡음
            } else { // 중앙값이 찾으려는 값보다 크면 끝값을 중앙으로 잡음
                end = mid;
            }
        }
        return start;
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
        public int nextInt(byte c) throws IOException {
            int ret = 0;
            if(c <= ' ') {
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