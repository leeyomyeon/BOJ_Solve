import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main9082 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] cnt;
    public static char[] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        for(int t = 0; t < T; t++) {
            N = fr.nextInt();
            cnt = new int[N];
            arr = new char[N];
            for(int i = 0; i < N; i++) {
                cnt[i] = fr.nextChar() - '0';
            }
            int res = 0;
            for(int i = 0; i < N; i++) {
                arr[i] = fr.nextChar();
                if(arr[i] == '*') {
                    res++;
                    for(int d = 0; d < 3; d++) {
                        int ni = i + di[d];
                        if(ni >= 0 && ni < N) {
                            if(cnt[ni] > 0) {
                                cnt[ni]--;
                            }
                        }
                    }
                }
            }
            for(int i = 0; i < N; i++) {
                if(arr[i] == '#') {
                    boolean chk = true;
                    for(int d = 0; d < 3; d++) {
                        int ni = i + di[d];
                        if(ni >= 0 && ni < N) {
                            if(cnt[ni] == 0) {
                                chk = false;
                                break;
                            }
                        }
                    }
                    if(chk) {
                        res++;
                        for(int d = 0; d < 3; d++) {
                            int ni = i + di[d];
                            if(ni >= 0 && ni < N) {
                                if(cnt[ni] > 0) {
                                    cnt[ni]--;
                                }
                            }
                        }
                    }
                }
            }
            bw.write(Integer.toString(res));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static int[] di = {-1, 0, 1};
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
