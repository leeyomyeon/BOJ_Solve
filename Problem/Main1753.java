import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main1753 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int V, E, start;
    public static ArrayList<Node>[] list;
    public static int[] d;
    public static boolean[] visited;
    public static FastReader fr;
    public static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws Exception {
        fr = new FastReader();

        V = fr.nextInt();
        E = fr.nextInt();
        start = fr.nextInt() - 1;
        list = new ArrayList[V];
        visited = new boolean[V];
        d =  new int[V];
        for(int i = 0; i < V; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; i++) {
            int u = fr.nextInt() - 1;
            int v = fr.nextInt() - 1;
            int w = fr.nextInt();
            list[u].add(new Node(v, w));
        }

        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;

        for(int i = 0; i < V; i++) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for(int j = 0; j < V; j++) {
                if(!visited[j] && d[j] < min) {
                    minIdx = j;
                    min = d[j];
                }
            }

            if(minIdx == -1) {
                break;
            }
            visited[minIdx] = true;

            for(int j = 0; j < list[minIdx].size(); j++) {
                Node current = list[minIdx].get(j);
                if(!visited[current.vertex] && current.weight != 0 && current.weight + d[minIdx] < d[current.vertex]) {
                    d[current.vertex] = current.weight + d[minIdx];
                }
            }
        }

        for(int j = 0; j < V; j++) {
            if(d[j] == Integer.MAX_VALUE) {
                bw.write("INF");
            } else {
                bw.write(Integer.toString(d[j]));
            }
            bw.newLine();
        }

        bw.write("");
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