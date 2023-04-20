import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1064 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        Point a = new Point(fr.nextInt(), fr.nextInt());
        Point b = new Point(fr.nextInt(), fr.nextInt());
        Point c = new Point(fr.nextInt(), fr.nextInt());
        if(isCCW(a, b, c)) {    // 세 점이 일직선인지 체크
            bw.write("-1");
        } else {
            double ab = dist(a, b);
            double bc = dist(b, c);
            double ca = dist(c, a);
            double MIN = Math.min(2 * bc + 2 * ca, Math.min(2 * ab + 2 * bc, 2 * ab + 2 * ca));
            double MAX = Math.max(2 * bc + 2 * ca, Math.max(2 * ab + 2 * bc, 2 * ab + 2 * ca));
            bw.write(String.format("%.9f", (MAX - MIN)));
        }
        bw.flush();
        bw.close();
    }
    public static double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
    public static boolean isCCW(Point p1, Point p2, Point p3)  {
        int c = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
        return c == 0;
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 10;
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
