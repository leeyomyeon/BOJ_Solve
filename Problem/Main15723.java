import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main15723 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[26];
        Arrays.fill(arr, -1);
        for(int i = 0; i < N; i++) {
            int from = fr.nextCharToInt();
            fr.nextCharToInt();
            fr.nextCharToInt();
            int to = fr.nextCharToInt();
            arr[from] = to;
        }
        M = fr.nextInt();
        for(int i = 0; i < M; i++) {
            int from = fr.nextCharToInt();
            fr.nextCharToInt();
            fr.nextCharToInt();
            int to = fr.nextCharToInt();
            bw.write(dfs(from, to) ? "T" : "F");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static boolean dfs(int from, int target) {
        if(from == target) {
            return true;
        } else if(arr[from] == -1) {
            return false;
        }
        return dfs(arr[from], target);
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
        public int nextCharToInt() throws IOException {
            byte c = read();
            while(c <= ' ') {
                c = read();
            }
            return (char) c - 'a';
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