import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main20927 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, sum;
    public static int[] arr;
    public static int[] weight;
    public static int[] selected;
    public static ArrayList<Node> list;
    public static int[] result;
    public static class Node {
        int from, to, cost;
        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        weight = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            weight[i] = fr.nextInt();
        }
        for(int i = 1; i <= M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            int cost = fr.nextInt();
            list.add(new Node(from, to, cost));
        }
        sum = Integer.MAX_VALUE;
        selected = new int[N - 1];
        result = null;
        Collections.sort(list, Comparator.comparingInt((Node o) -> o.cost));
        combination(0, 0);
        if(result == null) {
            bw.write("NO");
        } else {
            bw.write("YES\n");
            for(int i : result) {
                Node current = list.get(i);
                bw.write(Math.min(current.from, current.to) + " " + Math.max(current.from, current.to));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
    public static void combination(int idx, int cnt) {
        if(cnt == N - 1) {
            // 6개의 간선이 선택되었을 때
            int tmp = 0;
            arr = new int[N + 1];
            for(int i = 1; i <= N; i++) {
                arr[i] = i;
            }
            for (int i : selected) {
                Node current = list.get(i);
                int x = find(current.from);
                int y = find(current.to);
                if (x != y) {
                    tmp += current.cost;
                    union(x, y);
                } else {
                    return;
                }
            }
            // 정상 연결 되었고 MST면
            if(sum > tmp) {
                sum = tmp;
                result = selected.clone();
            }
            return;
        }
        for(int i = idx; i < M; i++) {
            selected[cnt] = i;
            Node current = list.get(i);
            // i번 간선을 골랐을 경우
            weight[current.from]--;
            weight[current.to]--;
            if(weight[current.from] >= 0 && weight[current.to] >= 0) {
                combination(i + 1, cnt + 1);
            }
            weight[current.from]++;
            weight[current.to]++;
        }
    }

    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x != y) {
            arr[y] = x;
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