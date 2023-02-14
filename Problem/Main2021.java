import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main2021 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, L;
    public static ArrayList<ArrayList<Integer>> list;
    public static boolean[] visited;
    public static ArrayDeque<Node> deque;
    public static class Node {
        int next, cnt;

        public Node(int next, int cnt) {
            this.next = next;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        L = fr.nextInt();
        list = new ArrayList<>();
        for(int i = 0; i <= N + L; i++) {
            list.add(new ArrayList<>());
        }
        visited = new boolean[N + L + 1];
        for(int l = 1; l <= L; l++) {
            while(true) {
                int k = fr.nextInt();
                if(k == -1) {
                    break;
                }
                list.get(N + l).add(k);
                list.get(k).add(N + l);
            }
        }
        int start = fr.nextInt();
        int end = fr.nextInt();
        deque = new ArrayDeque<>();
        visited[start] = true;
        deque.add(new Node(start, 0));
        int res = -1;
        while(!deque.isEmpty()) {
            Node current = deque.removeFirst();
            if(current.next == end) {
                res = Math.max(0, (current.cnt - 1));
                break;
            }
            for(int next : list.get(current.next)) {
                if(!visited[next]) {
                    visited[next] = true;
                    deque.add(new Node(next, next > N ? current.cnt + 1 : current.cnt));
                }
            }
        }
        bw.write(res + "");
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
                ret = (ret << 3) + (ret << 1) + (c & 15);
            } while ((c = read()) > 32);

            return neg ? ~ret + 1 : ret;
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