import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main16991 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static Point[] list;
    public static double[][] arr;
    public static double[][] dist;
    public static final double MAX = 10_0000_0000;
    public static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        list = new Point[N];
        arr = new double[N][N];
        dist = new double[1 << N][N];
        for(int i = 0; i < N; i++) {
            list[i] = new Point(fr.nextInt(), fr.nextInt());
        }
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                arr[i][j] = getDist(list[i], list[j]);
                arr[j][i] = arr[i][j];
            }
        }
        double res = TSP(0, 0);
        bw.write(res == MAX ? "-1" : res + "");
        bw.flush();
        bw.close();
    }
    public static double getDist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
    }
    public static double TSP(int visit, int current) {
        visit |= (1 << current);
        if(visit == (1 << N) - 1) {
            return arr[current][0] > 0 ? arr[current][0] : MAX;
        }
        if(dist[visit][current] > 0) {
            return dist[visit][current];
        }
        dist[visit][current] = MAX;
        for(int i = 0; i < N; i++) {
            if(i != current && (visit & (1 << i)) == 0 && arr[current][i] > 0) {
                double tmp = TSP(visit, i) + arr[current][i];
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