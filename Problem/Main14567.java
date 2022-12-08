import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main14567 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] degree;
    public static int[] res;
    public static ArrayList<ArrayList<Integer>> list;
    public static ArrayDeque<Node> deque;
    public static class Node {
        int n;
        int cnt;
        public Node(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        degree = new int[N + 1];
        res = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            list.get(from).add(to);
            degree[to]++;
        }
        deque = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(degree[i] == 0) {
                deque.add(new Node(i, 1));
            }
        }
        while(!deque.isEmpty()) {
            Node current = deque.removeFirst();
            res[current.n] = current.cnt;
            for(int k : list.get(current.n)) {
                degree[k]--;
                if(degree[k] == 0) {
                    deque.add(new Node(k, current.cnt + 1));
                }
            }
        }
        for(int i = 1; i <= N; i++) {
            bw.write(Integer.toString(res[i]));
            if(i != N) {
                bw.write(32);
            }
        }
        bw.flush();
        bw.close();
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