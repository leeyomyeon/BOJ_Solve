import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main17398 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, Q;
    public static int[] arr, cost;
    public static Node[] remove;
    public static Stack<Integer> stack;
    public static class Node {
        int from, to;
        boolean isConnect;
        public Node(int from, int to)  {
            this.from = from;
            this.to = to;
            this.isConnect = true;
        }
        public void setIsConnect(boolean isConnect) {
            this.isConnect = isConnect;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        Q = fr.nextInt();
        arr = new int[N + 1];
        cost = new int[N + 1];
        remove = new Node[M + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
            cost[i] = 1;
        }
        for(int i = 1; i <= M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            remove[i] = new Node(from, to);
        }
        stack = new Stack<>();
        for(int q = 0; q < Q; q++) {
            int idx = fr.nextInt();
            remove[idx].setIsConnect(false);
            stack.add(idx);
        }
        for(int i = 1; i <= M; i++) {
            Node current = remove[i];
            if(current.isConnect) {
                int x = find(current.from);
                int y = find(current.to);
                if(x != y) {
                    union(current.from, current.to);
                }
            }
        }
        long sum = 0;
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            Node current = remove[idx];
            int x = find(current.from);
            int y = find(current.to);
            if(x == y) {
                continue;
            }
            sum += ((long) cost[x] * cost[y]);
            union(current.from, current.to);
        }
        bw.write(Long.toString(sum));
        bw.flush();
        bw.close();
    }

    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);

        if(x < y) {
            arr[y] = x;
            cost[x] += cost[y];
            cost[y] = 0;
        } else {
            arr[x] = y;
            cost[y] += cost[x];
            cost[x] = 0;
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