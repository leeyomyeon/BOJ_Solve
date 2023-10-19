import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main1504 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, E;
    public static final int MAX = 300_000_000;
    public static ArrayList<ArrayList<Node>> list;
    public static PriorityQueue<Node> pq;

    public static int[] cost;
    public static class Node implements Comparable<Node> {
        int num;
        int weight;
        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        E = fr.nextInt();
        cost = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < E; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            int c = fr.nextInt();
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }
        // 반드시 거쳐야하는 두 점
        int u = fr.nextInt();
        int v = fr.nextInt();
        dijk(u);    // 각 점에서 u까지 가는 목적지 거리
        int u1 = cost[1];
        int uv = cost[v];
        int un = cost[N];
        dijk(v);    // 각 점에서 v까지 가는 목적지 거리
        // v > u는 u > v와 같으므로 위에서 재사용
        int v1 = cost[1];
        int vn = cost[N];
        int r1 = u1 + uv + vn; //      start > u, u > v, v > N
        int r2 = v1 + uv + un; //      start > v, v > u, u > N
        int res = Math.min(r1, r2);
        if(res >= MAX) {
            res = -1;
        }
        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
    public static void dijk(int dest) {
        pq = new PriorityQueue<>();
        Arrays.fill(cost, MAX);
        cost[dest] = 0;
        pq.add(new Node(dest, 0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for(Node next : list.get(current.num)) {
                if(current.weight + next.weight < cost[next.num]) {
                    cost[next.num] = current.weight + next.weight;
                    pq.add(new Node(next.num, cost[next.num]));
                }
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
                ret = (ret << 3) + (ret << 1) + (c & 15);
            } while ((c = read()) > 32);

            return neg ? ~ret + 1 : ret;
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
/*
      3
    1---2
   4|5X5| 3
    4---3
      1
 */