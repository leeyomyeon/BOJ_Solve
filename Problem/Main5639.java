import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main5639 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[] pre;
    public static int N;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        byte k;
        pre = new int[10001];
        N = 0;
        while((k = fr.read()) > 0) {
            pre[N] = fr.nextInt(k);
            if(pre[N] <= 0) {
                break;
            }
            N++;
        }
        postOrder(0, N);
        bw.flush();
        bw.close();
    }
    public static void postOrder(int start, int end) throws Exception{
        if(start >= end) {
            return;
        }
        if(start == end - 1) {
            bw.write(pre[start] + "\n");
            return;
        }
        int idx = start + 1;
        while(idx < end) {
            if(pre[start] < pre[idx]) {
                break;
            }
            idx++;
        }
        postOrder(start + 1, idx);
        postOrder(idx, end);
        bw.write(pre[start] + "\n");
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

        public int nextInt(byte c) throws IOException {
            int ret = 0;
            if(c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
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