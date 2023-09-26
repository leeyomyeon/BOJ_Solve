import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1027 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static Point[] arr;
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
        arr = new Point[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = new Point(i, fr.nextInt());
        }
        int max = 0;
        for(int start = 1; start <= N; start++) {
            int res = 0;
            // 왼쪽
            Loop1:
            for(int end = start - 1; end >= 1; end--) {
                for(int i = start - 1; i > end; i--) {
                    if(CCW(start, end, i)) {
                        continue Loop1;
                    }
                }
                res++;
            }
            // 오른쪽
            Loop2:
            for(int end = start + 1; end <= N; end++) {
                for(int i = start + 1; i < end; i++) {
                    if(CCW(start, end, i)) {
                        continue Loop2;
                    }
                }
                res++;
            }
            max = Math.max(max, res);
        }
        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }
    public static boolean CCW(int i, int j, int k) {
        if(i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }
        Point p1 = arr[i];
        Point p2 = arr[j];
        Point p3 = arr[k];
        long res = (long) p1.x * p2.y + (long) p2.x * p3.y + (long) p3.x * p1.y;
        res = res - (long) p1.y * p2.x - (long) p2.y * p3.x - (long) p3.y * p1.x;
        return res >= 0;
        // 반시계 방향을 나타내면 양수, 시계 방향이면 음수, 일직선이면 0
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
