import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
public class Main27876 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static ArrayList<Integer> oddList, evenList;
    public static final int MOD = 998_244_353;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        oddList = new ArrayList<>();
        evenList = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            long k = (long) i * i;
            if(k / 2 < N && k > 1 && k < N * 2L) {
                if(k % 2 == 0) {
                    evenList.add((int) k);
                } else {
                    oddList.add((int) k);
                }
            }
            arr[i] = i;
        }
        int diff = 1;
        int cnt = 0;
        long res = 1;
        Loop1:
        while (true) {
            if (diff % 2 == 1) {
                for (int k : oddList) {
                    if (diff >= k) {
                        continue;
                    }
                    int left = k / 2 + 1 - (diff / 2 + 1);
                    int right = k / 2 + (diff / 2 + 1);
                    if (left <= 0 || right >= N + 1) {
                        break;
                    }
                    if(find(left) != find(right)) {
                        res = (res * diff) % MOD;
                        cnt++;
                        union(left, right);
                    }
                    if(cnt == N - 1) {
                        break Loop1;
                    }
                }
            } else {
                for (int k : evenList) {
                    if (diff >= k) {
                        continue;
                    }
                    int left = k / 2 - (diff / 2);
                    int right = k / 2 + (diff / 2);
                    if (left <= 0 || right >= N + 1) {
                        break;
                    }
                    if(find(left) != find(right)) {
                        res = (res * diff) % MOD;
                        cnt++;
                        union(left, right);
                    }
                    if(cnt == N - 1) {
                        break Loop1;
                    }
                }
            }
            diff++;
            if(diff > N) {
                break;
            }
        }
        bw.write(cnt == N - 1 ? Long.toString(res) : "-1");
        bw.flush();
        bw.close();
    }
    public static void union(int a, int b) {
        int x = find(a);
        int y = find(b);
        if(x != y) {
            arr[y] = x;
        }
    }
    public static int find(int target) {
        if(target == arr[target]) {
            return arr[target];
        }
        return arr[target] = find(arr[target]);
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 8;
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

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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
