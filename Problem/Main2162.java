import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main2162 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static Line[] list;
    public static class Line {
        Point p, q;

        public Line(Point p, Point q) {
            this.p = p;
            this.q = q;
        }
    }
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
        arr = new int[N + 1];
        list = new Line[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
            int x1 = fr.nextInt();
            int y1 = fr.nextInt();
            int x2 = fr.nextInt();
            int y2 = fr.nextInt();
            list[i] = new Line(new Point(x1, y1), new Point(x2, y2));
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i != j && find(i) != find(j)) {
                    if(isValid(list[i], list[j])) { // 두 선분이 교차하면
                        union(i, j);
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            arr[i] = find(i);
        }
        for(int i = 1; i <= N; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            max = Math.max(map.get(arr[i]), max);
        }
        bw.write(map.size() + "\n" + max);
        bw.flush();
        bw.close();
    }

    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x < y) {
            arr[y] = x;
        }
    }
    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
    }
    public static long CCW(Point p, Point q, Point r) {
        long c = (long) (q.y - p.y) * (r.x - q.x) - (long) (q.x - p.x) * (r.y - q.y);
        if(c == 0) return 0;
        return c > 0 ? 1 : -1;
    }
    public static boolean isValid(Line l1, Line l2) {
        long ab = CCW(l1.p, l1.q, l2.p) * CCW(l1.p, l1.q, l2.q);
        long cd = CCW(l2.p, l2.q, l1.p) * CCW(l2.p, l2.q, l1.q);
        if(ab == 0 && cd == 0) {    // 두 직선이 일직선 상에 있을때
            return checkDist(l1, l2);
        }
        return ab <= 0 && cd <= 0;
    }
    public static boolean checkDist(Line l1, Line l2) {
        if(Math.max(l1.p.x, l1.q.x) < Math.min(l2.p.x, l2.q.x)) {
            return false;
        }
        if(Math.max(l2.p.x, l2.q.x) < Math.min(l1.p.x, l1.q.x)) {
            return false;
        }
        if(Math.max(l1.p.y, l1.q.y) < Math.min(l2.p.y, l2.q.y)) {
            return false;
        }
        if(Math.max(l2.p.y, l2.q.y) < Math.min(l1.p.y, l1.q.y)) {
            return false;
        }
        return true;
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