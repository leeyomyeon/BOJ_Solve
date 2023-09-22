import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main13976 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long N;
    public static long[][] M = {{4, -1}, {1, 0}};
    public static HashMap<Long, long[][]> map;
    public static long MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextLong();
        if(N % 2 == 1) {
            bw.write("0");
        } else {
            map = new HashMap<>();
            long[][] res = dnq(N);
            bw.write(Long.toString((res[0][0] + res[0][1]) % MOD));
        }
        bw.flush();
        bw.close();
    }
    public static long[][] dnq(long k) {
        if(k == 1) {    // 단위행렬
            return new long[][]{{1, 0}, {0, 1}};
        } else if(k == 2) {
            return M;
        }
        if(map.containsKey(k)) {
            return map.get(k);
        }
        long a = 0;
        for(int i = 1;i <= 60; i++) {
            if(1L << (i + 1) >= k) {
                a = 1L << i;
                break;
            }
        }
        long[][] t1 = dnq(a);
        if(!map.containsKey(a)) {
            map.put(a, t1);
        }
        long[][] t2 = dnq(k - a);
        if(!map.containsKey(k - a)) {
            map.put(k - a, t2);
        }
        return matMul(t1, t2);

    }
    public static long[][] matMul(long[][] a, long[][] b) {
        long[][] tmp = new long[2][2];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                long sum = 0;
                for(int k = 0; k < 2; k++) {
                    sum += a[i][k] * b[k][j];
                    sum += MOD;
                    sum %= MOD;
                }
                tmp[i][j] = sum;
            }
        }
        return tmp;
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
        public long nextLong() throws IOException {
            long ret = 0;
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
