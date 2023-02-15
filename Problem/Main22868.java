import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Main22868 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, S, E, res;
    public static ArrayList<ArrayList<Integer>> list;
    public static int[] track;
    public static boolean[] visited;
    public static ArrayDeque<Node> deque;
    public static class Node {
        int num, cnt;
        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        list = new ArrayList<>();
        track = new int[N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            track[i] = Integer.MAX_VALUE;
            list.add(new ArrayList<>());
        }
        for(int m = 0; m < M; m++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            list.get(from).add(to);
            list.get(to).add(from);
        }
        for(int i = 1; i <= N; i++) {
            Collections.sort(list.get(i));
        }
        S = fr.nextInt();
        E = fr.nextInt();
        res = 0;
        deque = new ArrayDeque<>();
        visited[S] = true;
        bfs(S, E);
        visited = new boolean[N + 1];
        // 왔던 경로 출력
        tracking(E);
        deque.clear();
        bfs(E, S);
        bw.write(res + "");
        bw.flush();
        bw.close();
    }
    public static void bfs(int start, int end) {
        deque.add(new Node(start, 0));
        while(!deque.isEmpty()) {
            Node current = deque.removeFirst();
            if(current.num == end) {
                res += current.cnt;
                break;
            }

            for(int next : list.get(current.num)) {
                if(!visited[next]) {
                    visited[next] = true;
                    if(track[next] == Integer.MAX_VALUE) {
                        track[next] = current.num;
                    }
                    deque.add(new Node(next, current.cnt + 1));
                }
            }
        }
    }
    public static void tracking(int target) {
        if(target == S) {
            return;
        }
        visited[target] = true;
        tracking(track[target]);
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