import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
// MST 프림 알고리즘
public class Main1922 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static class Node implements Comparable<Node> {
        int vertex;
        int weight;
        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    public static int[][] list;
    public static boolean[] visited;
    public static PriorityQueue<Node> pq;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        list = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        pq = new PriorityQueue<>();
        for(int i = 0; i < M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            int weight = fr.nextInt();
            list[from][to] = weight;
            list[to][from] = weight;
        }

        int sum = 0;
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(!visited[current.vertex]) {
                visited[current.vertex] = true;
                sum += current.weight;
            }

            for(int i = 1; i <= N; i++) {
                if(list[current.vertex][i] > 0 && !visited[i]) {
                    pq.add(new Node(i, list[current.vertex][i]));
                }
            }
        }

        bw.write(Integer.toString(sum));
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