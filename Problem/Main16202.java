import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main16202 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, K;
    public static int[] arr;
    public static boolean[] visited;
    public static ArrayList<Node> list;
    public static class Node {
        int from, to, weight;
        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        K = fr.nextInt();
        list = new ArrayList<>();

        for(int i = 1; i <= M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            list.add(new Node(from, to, i));
        }
        boolean flag = true;
        visited = new boolean[M + 1];
        for(int k = 0; k < K; k++) {
            visited[k] = true;
            if(flag) {
                arr = new int[N + 1];
                for(int i = 0; i <= N; i++) {
                    arr[i] = i;
                }
                int res = 0;
                int size = 0;
                for(Node current : list) {
                    if(visited[current.weight]) {
                        continue;
                    }
                    int x = find(current.from);
                    int y = find(current.to);
                    if(x == y) { // 사이클이 생기면
                        continue;
                    }
                    res += current.weight;
                    size++;
                    union(x, y);
                }
                if(size != N - 1) {
                    bw.write(48);   // 0
                    flag = false;
                } else {
                    bw.write(Integer.toString(res));
                }
            } else {
                bw.write(48);   // 0
            }
            if(k != K - 1) {
                bw.write(32);   // 공백
            }
        }
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

        public int nextCharToInt() throws IOException {
            byte c = read();
            if (c == '\n') {
                c = read();
            }
            return c - 'A';
        }

        public void fastNumberWrite(int current) throws Exception {
            boolean abs = current < 0;
            if (abs) {
                current *= -1;
                bw.write(45);
            }
            int size = current == 0 ? 0 : (int) Math.log10(current);
            while (size >= 0) {
                int nextSize = current == 0 ? 0 : (int) Math.log10(current);
                int div = (int) Math.pow(10, nextSize);
                while (size > nextSize) {
                    bw.write(48);
                    size--;
                }
                bw.write((current / div) + 48);
                current %= div;
                size--;
            }
        }

        public void fastNumberWrite(long current) throws Exception {
            boolean abs = current < 0;
            if (abs) {
                current *= -1;
                bw.write(45);
            }
            int size = current == 0 ? 0 : (int) Math.log10(current);
            while (size >= 0) {
                int nextSize = current == 0 ? 0 : (int) Math.log10(current);
                long div = (int) Math.pow(10, nextSize);
                while (size > nextSize) {
                    bw.write(48);
                    size--;
                }
                bw.write((int) ((current / div) + 48));
                current %= div;
                size--;
            }
        }
    }
}