import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main10993 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] R, C;
    public static boolean[][] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        if(N == 1) {
            bw.write("*");
        } else {
            R = new int[N + 1];
            C = new int[N + 1];
            R[1] = 1;
            C[1] = 1;
            for(int i = 2; i <= N; i++) {
                R[i] = (R[i - 1] * 2) + 1; // 세로 최대크기 구하기
                C[i] = (C[i - 1] * 2) + 3; // 가로 최대크기 구하기
            }
            arr = new boolean[R[N] + 1][C[N] + 1];
            dnq(1, 1, N);
            for(int i = 1; i <= R[N]; i++) {
                for(int j = 1; j <= (N % 2 == 0 ? C[N] - i + 1 : C[N] / 2 + i); j++) {
                    bw.write(arr[i][j] ? 42 : 32);
                }
                if(i != R[N]) {
                    bw.newLine();
                }
            }
        }
        bw.flush();
        bw.close();
    }
    public static void dnq(int startR, int startC, int value) {
        if(value == 0) {
            arr[startR][startC] = true;
            return;
        } else {
            int idx = 0;
            for(int i = startR; i < R[value] + startR; i++) {
                if(value % 2 == 0) {
                    if(i == startR) {
                        for(int j = startC; j < startC + C[value]; j++) {
                            arr[i][j] = true;
                        }
                    } else {
                        arr[i][startC + idx] = true;
                        arr[i][C[N] - startC - idx + 1] = true;
                    }
                } else {
                    if(i == (R[value] + startR - 1)) {
                        for(int j = startC; j < startC + C[value]; j++) {
                            arr[i][j] = true;
                        }
                    } else {
                        arr[i][startC + C[value] / 2 - idx] = true;
                        arr[i][startC + C[value] / 2 + idx] = true;
                    }
                }
                idx++;
            }
        }
        dnq((value - 1) % 2 == 1 ? startR + 1 : startR + (R[value] / 2), startC + (C[value] / 4) + 1, value - 1);
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