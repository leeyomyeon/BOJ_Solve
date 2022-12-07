import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main2406 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static int[][] cost;
    public static boolean[][] selected;
    public static ArrayList<Node> list;
    public static class Node implements Comparable<Node>{
        int from, to, cost;
        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N + 1];
        list = new ArrayList<>();
        cost = new int[N + 1][N + 1];
        selected = new boolean[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        for(int i = 0; i < M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            list.add(new Node(from, to, 0));
            selected[from][to] = true;
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                cost[i][j] = fr.nextInt();
                if(i >= 2 && j >= i + 1) {
                    if(!selected[i][j]) {
                        list.add(new Node(i, j, cost[i][j]));
                    }
                }
            }
        }
        Collections.sort(list);
        int sum = 0;
        int cnt = 0;
        for(Node current : list) {
            int x = find(current.from);
            int y = find(current.to);
            if(x != y) {
                sum += current.cost;
                if(current.cost != 0) {
                    bw2.write(current.from + " " + current.to + "\n");
                    cnt++;
                }
                union(current.from, current.to);
            }
        }

        bw.write(sum + " " + cnt);
        bw.newLine();
        bw.flush();
        bw2.flush();
        bw.close();
        bw2.close();
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