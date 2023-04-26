import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1652 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        char[][] arr = new char[N + 1][N + 1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = fr.nextChar();
            }
        }
        int rCnt = 0;
        int cCnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N - 1; j++) {
                if(arr[i][j] == '.') {
                    int cnt = 0;
                    while(arr[i][j++] == '.') {
                        cnt++;
                    }
                    j--;
                    if(cnt >= 2) {
                        rCnt++;
                    }
                }
            }
        }
        for(int j = 0; j < N; j++) {
            for(int i = 0; i < N - 1; i++) {
                if(arr[i][j] == '.') {
                    int cnt = 0;
                    while(arr[i++][j] == '.') {
                        cnt++;
                    }
                    i--;
                    if(cnt >= 2) {
                        cCnt++;
                    }
                }
            }
        }
        bw.write(rCnt + " " + cCnt);
        bw.flush();
        bw.close();
    }

    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 9;
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
