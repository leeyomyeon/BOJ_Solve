import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main2887 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static ArrayList<Node> xList, yList, zList;
    public static ArrayList<Point> list;
    public static class Node implements Comparable<Node> {
        int num;
        int loc;
        public Node(int num, int loc) {
            this.num = num;
            this.loc = loc;
        }
        @Override
        public int compareTo (Node o) {
            return this.loc - o.loc;
        }
    }
    public static class Point implements Comparable<Point> {
        int dist;
        int from;
        int to;

        public Point(int dist, int from, int to) {
            this.dist = dist;
            this.from = from;
            this.to = to;
        }

        public int compareTo(Point o) {
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        xList = new ArrayList<>();
        yList = new ArrayList<>();
        zList = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
           int x = fr.nextInt();
           int y = fr.nextInt();
           int z = fr.nextInt();
           // 각 좌표별 짧은 거리순으로 그래프 만듬
           xList.add(new Node(i, x));
           yList.add(new Node(i, y));
           zList.add(new Node(i, z));
        }
        Collections.sort(xList);
        Collections.sort(yList);
        Collections.sort(zList);
        list = new ArrayList<>();
        for(int i = 1; i < N; i++) {
            // 간선의 가중치, union할 간선 번호
            list.add(new Point(Math.abs(xList.get(i - 1).loc - xList.get(i).loc), xList.get(i - 1).num, xList.get(i).num));
            list.add(new Point(Math.abs(yList.get(i - 1).loc - yList.get(i).loc), yList.get(i - 1).num, yList.get(i).num));
            list.add(new Point(Math.abs(zList.get(i - 1).loc - zList.get(i).loc), zList.get(i - 1).num, zList.get(i).num));
        }
        int cost = 0;
        Collections.sort(list);
        for (Point current : list) {
            int x = find(current.from);
            int y = find(current.to);
            if (x == y) {
                continue;
            }
            cost += current.dist;
            union(current.from, current.to);
        }
        bw.write(Integer.toString(cost));
        bw.flush();
        bw.close();
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