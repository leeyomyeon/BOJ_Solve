import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
/*
s(i,k)를 배열 A의 i번째 원소부터 시작하는 연속된 k개 수의 합이라고 정의하자.

s(i,k) = A[i] + A[i+1] + ... + A[i+k-1]

i+k ≤ j일 때, s(i,k) 와 s(j,k)의 차이 (abs(s(i, k) - s(j, k)))를 최소로 하는 프로그램을 작성하시오.
즉, 동일한 길이의 겹치지 않는 두 부분 수열의 차이를 최소로 하는 것이다.

첫째 줄에 값이 가장 작을 때 k를 출력하고, 둘째 줄에 차이의 최솟값을 출력한다.
만약 최솟값이 여러 개일 경우에는 k를 최대로 한다.

N = 100
k = 50
1 ~ 50 : 51 ~ 100
k = 49
1 ~ 49 : 50 ~ 98, 51 ~ 99, 52 ~ 100
2 ~ 50 : 51 ~ 99, 52 ~ 100
3 ~ 51 : 52 ~ 100
k = 48
1 ~ 48 : 49 ~ 96, 50 ~ 97, 51 ~ 98, 52 ~ 99, 53 ~ 100

100
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1

1 ~ 6
 */
public class Main1549 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static long[] sum;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        sum = new long[N + 1];
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
            sum[i] = sum[i - 1] + arr[i];
        }

        long min = Long.MAX_VALUE;
        long maxK = 0;
        int k = N / 2;

        while(k-->0) {
            for(int i = 1; i + (k * 2) + 1 <= N; i++) {
                long left = sum[i + k] - sum[i - 1];
                for(int j = i + k + 1; j + k <= N; j++) {
                    long right = sum[j + k] - sum[j - 1];
                    if(Math.abs(left - right) < min) {
                        min = Math.abs(left - right);
                        maxK = k;
                    }
                }
            }
        }
        bw.write((maxK + 1) + "\n" + min);
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
