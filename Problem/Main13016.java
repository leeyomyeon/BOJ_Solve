import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main13016 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, target, maxLen;
    public static int[] arr1, arr2;
    public static ArrayList<ArrayList<Node>> list;
    public static boolean[] visited;
    public static class Node {
        int to;
        int weight;
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        list = new ArrayList<>();
        N = fr.nextInt();
        arr1 = new int[N + 1];
        arr2 = new int[N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < N - 1; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            int weight = fr.nextInt();
            list.get(from).add(new Node(to, weight));
            list.get(to).add(new Node(from, weight));
        }
        // 시작점을 아무데나 잡아도 됨
        visited[1] = true;
        maxLen = 0;
        dfs(1, 0);
        // 가장 길이가 긴 시작점 찾음
        int start = target;

        Arrays.fill(visited, false);
        target = 0;
        maxLen = 0;
        dfs(start, 0);
        int end = target;
        // start부터 가장 거리가 먼 정점 찾음
        // start, end = 트리의 지름을 이루는 두 정점
        if (N >= 0) System.arraycopy(arr1, 1, arr2, 1, N);
        Arrays.fill(arr1, 0);

        Arrays.fill(visited, false);
        dfs(end, 0);

        for(int i = 1; i <= N; i++) {
            // i 일때 가장 먼 거리 출력
            bw.write(Integer.toString(Math.max(arr1[i], arr2[i])));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int current, int weight) {
        arr1[current] = weight;
        if(maxLen < weight) {
            maxLen = weight;
            target = current;
        }
        for(Node next : list.get(current)) {
            if(!visited[next.to]) {
                visited[next.to] = true;
                dfs(next.to, weight + next.weight);
            }
        }
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