import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
// TSP
public class Main2098 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static final int MAX = 100000000;
    public static int[][] arr;
    public static int[][] dist;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N][N];
        dist = new int[1 << N + 1][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = fr.nextInt();
            }
        }
        int res = TSP(0, 0);
        bw.write(res == MAX ? "-1" : (res + ""));
        bw.flush();
        bw.close();
    }
    public static int TSP(int visit, int current) {
        visit |= (1 << current);
        if(visit == (1 << N) - 1) {
            return arr[current][0] > 0 ? arr[current][0] : MAX;
        }
        if(dist[visit][current] > 0) {
            return dist[visit][current];
        }
        dist[visit][current] = MAX;
        for(int i = 0; i < N; i++) {
            if( i != current && (visit & (1 << i)) == 0 && arr[current][i] > 0) {
                int tmp = TSP(visit, i) + arr[current][i];
                if(dist[visit][current] > tmp) {
                    dist[visit][current] = tmp;
                }
            }
        }
        return dist[visit][current];
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