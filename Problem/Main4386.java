import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main4386 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static ArrayList<Node> nodeList;
    public static ArrayList<Point> list;
    public static class Point implements Comparable<Point> {
        int from, to;
        double dist;
        public Point(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    public static class Node {
        int idx;
        double x, y;

        public Node(int idx, double x, double y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = i;
        }
        nodeList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            double x = fr.nextDouble();
            double y = fr.nextDouble();
            nodeList.add(new Node(i, x, y));
        }
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                Node from = nodeList.get(i);
                Node to = nodeList.get(j);
                double dist = getDist(from.x, from.y, to.x, to.y);
                list.add(new Point(from.idx, to.idx, dist));
            }
        }
        Collections.sort(list);
        double res = 0;
        for (Point current : list) {
            int x = find(current.from);
            int y = find(current.to);
            if (x == y) {
                continue;
            }
            res += current.dist;
            union(current.from, current.to);
        }
        bw.write(String.format("%.2f", res));
        bw.flush();
        bw.close();
    }
    public static double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }
    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);

        if(x < y) {
            arr[y] = x;
        } else {
            arr[x] = y;
        }
    }
    public static int find(int target) {
        if(arr[target] == target) {
            return target;
        }
        return arr[target] = find(arr[target]);
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
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
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
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

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