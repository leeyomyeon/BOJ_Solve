import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main10423 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, K;
    public static int[] arr;
    public static ArrayList<Node> list;
    public static class Node implements Comparable<Node> {
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
        K = fr.nextInt();
        arr = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        for(int k = 0; k < K; k++) {
            // 발전소들을 0으로 union시켜서 다른 도시들과 사이클이 되지 않게 함.
            int l = fr.nextInt();
            union(0, l);
            // cost를 0으로 두면 알고리즘 수행 시 항상 연결될 수 있음
            list.add(new Node(0, l, 0));
        }
        for(int m = 0; m < M; m++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            int cost = fr.nextInt();
            list.add(new Node(from, to, cost));
        }
        Collections.sort(list);
        int sum = 0;
        for(Node current : list) {
            int x = find(current.from);
            int y = find(current.to);
            if(x != y) {
                sum += current.cost;
                union(current.from, current.to);
            }
        }
        bw.write(Integer.toString(sum));
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
    public static int find(int target)  {
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