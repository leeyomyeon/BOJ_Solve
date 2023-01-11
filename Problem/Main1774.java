import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main1774 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static ArrayList<Node> nodeList;
    public static ArrayList<Point> list;
    public static class Node {
        int num, x, y;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    public static class Point implements Comparable<Point> {
        double dist;
        int from, to;
        public Point(double dist, int from, int to) {
            this.dist = dist;
            this.from = from;
            this.to = to;
        }
        @Override
        public int compareTo(Point o) {
            if(this.dist > o.dist) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        list = new ArrayList<>();
        arr = new int[N + 1];
        nodeList = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
            nodeList.add(new Node(i, fr.nextInt(), fr.nextInt()));
        }
        list = new ArrayList<>();
        for(int i = 1; i <= M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            int x = find(from);
            int y = find(to);
            if(x != y) {
                union(from, to);
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                Node current = nodeList.get(i);
                Node next = nodeList.get(j);
                if(find(current.num) != find(next.num)) {
                    Point point = new Point(Math.sqrt(Math.pow(current.x - next.x, 2) + Math.pow(current.y - next.y, 2)), current.num, next.num);
                    list.add(point);
                }
            }
        }
        double weight = 0;
        Collections.sort(list);
        for(Point current : list) {
            int x = find(current.from);
            int y = find(current.to);
            if(x != y) {
                weight += current.dist;
                union(current.from, current.to);
            }
        }
        bw.write(String.format("%.2f", weight));
        bw.flush();
        bw.close();
    }
    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x != y) {
            arr[y] = x;
        }
    }
    public static int find(int target) {
        if(target == arr[target]) {
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