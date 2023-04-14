import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main2780 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] arr;
    public static int[] sum;
    public static final int MOD = 1_234_567;
    public static void main(String[] args) throws Exception {
        arr = new int[1001][10];
        sum = new int[1001];
        Arrays.fill(arr[1], 1);
        sum[1] = 10;
        for(int i = 2; i <= 1000; i++) {
            arr[i][0] = arr[i - 1][7] % MOD;
            arr[i][1] = (arr[i - 1][2] + arr[i - 1][4]) % MOD;
            arr[i][2] = (arr[i - 1][1] + arr[i - 1][3] + arr[i - 1][5]) % MOD;
            arr[i][3] = (arr[i - 1][2] + arr[i - 1][6]) % MOD;
            arr[i][4] = (arr[i - 1][1] + arr[i - 1][5] + arr[i - 1][7]) % MOD;
            arr[i][5] = (arr[i - 1][2] + arr[i - 1][4] + arr[i - 1][6] + arr[i - 1][8]) % MOD;
            arr[i][6] = (arr[i - 1][3] + arr[i - 1][5] + arr[i - 1][9]) % MOD;
            arr[i][7] = (arr[i - 1][4] + arr[i - 1][8] + arr[i - 1][0]) % MOD;
            arr[i][8] = (arr[i - 1][7] + arr[i - 1][5] + arr[i - 1][9]) % MOD;
            arr[i][9] = (arr[i - 1][8] + arr[i - 1][6]) % MOD;
            sum[i] = arr[i][0] + arr[i][1] + arr[i][2] + arr[i][3] + arr[i][4] +
                     arr[i][5] + arr[i][6] + arr[i][7] + arr[i][8] + arr[i][9];
        }
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        for(int t = 0; t < T; t++) {
            bw.write((sum[fr.nextInt()] % MOD)+ "\n");
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
