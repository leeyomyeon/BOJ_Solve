import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main3372 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[][] arr;
    public static BigInteger[][] dp;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1][N + 1];
        dp = new BigInteger[N + 1][N + 1];

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                if(i >= 1 && j >= 1) {
                    arr[i][j] = fr.nextInt();
                }
                dp[i][j] = BigInteger.ZERO;
            }
        }
        dp[1][1] = BigInteger.ONE;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == N && j == N) {
                    bw.write(dp[N][N].toString());
                    break;
                }
                if(!dp[i][j].equals(BigInteger.ZERO) && i + arr[i][j] <= N) { // 세로 체크
                    dp[i + arr[i][j]][j] = dp[i + arr[i][j]][j].add(dp[i][j]);
                }
                if(!dp[i][j].equals(BigInteger.ZERO) && j + arr[i][j] <= N) { // 가로 체크
                    dp[i][j + arr[i][j]] = dp[i][j + arr[i][j]].add(dp[i][j]);
                }
            }
        }
        bw.flush();
        bw.close();
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
