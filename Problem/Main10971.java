import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main10971 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] arr;
    public static boolean[] visited;
    public static int N, MIN;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N][N];
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = fr.nextInt();
            }
        }
        MIN = Integer.MAX_VALUE;
        for(int i = 0; i < N;  i++) {
            visited[i] = true;
            travel(i, 1, 0, i);
            visited[i] = false;
        }
        bw.write(Integer.toString(MIN));
        bw.flush();
        bw.close();
    }
    public static void travel(int next, int idx, int cost, int start) {
        if(idx == N) {
            if(arr[next][start] != 0) {
                MIN = Math.min(MIN, cost + arr[next][start]);
            }
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i] && arr[next][i] > 0) {
                if(MIN != Integer.MAX_VALUE && cost + arr[next][i] > MIN) {
                    return;
                }
                visited[i] = true;
                travel(i, idx + 1, cost + arr[next][i], start);
                visited[i] = false;
            }
        }
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