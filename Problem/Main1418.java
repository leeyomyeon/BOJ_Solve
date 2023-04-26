import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
오세준은 어떤 자연수의 소인수중 최댓값이 K보다 크지 않을때 그 수를 K-세준수라고 부른다.

N보다 작거나 같은 자연수 중에 K-세준수가 총 몇 개인지 구하는 프로그램을 작성하시오.
100000
100
17442

10
3
7

1 2 3 4 6 8 9
 */
public class Main1418 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, K;
    public static boolean[] isPrime;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        K = fr.nextInt();
        isPrime = new boolean[101];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for(int i = 2; i <= 100; i++) {
            for(int j = 2; j * i <= 100; j++) {
                isPrime[i * j] = false;
            }
        }
        int res = 1;
        for(int i = 2; i <= N; i++) {
            int tmp = i;
            for(int j = 1; j <= 100; j++) {
                if(isPrime[j] && tmp % j == 0) {    // 소수이고 나누어떨어지면 계속 나눔
                    while(tmp % j == 0) {
                        tmp /= j;
                    }
                }
                if(tmp == 1) {
                    if(j <= K) {
                        res++;
                    }
                    break;
                }
            }
        }
        bw.write(Integer.toString(res));
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
