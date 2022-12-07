import java.io.*;
import java.util.*;

public class Main13418 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] minArr, maxArr;
    public static ArrayList<Node> minList, maxList;
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
        minArr = new int[N + 1];
        maxArr = new int[N + 1];
        minList = new ArrayList<>();
        maxList = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            minArr[i] = i;
            maxArr[i] = i;
        }
        for(int i = 0; i <= M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            int cost = fr.nextInt() == 0 ? 1 : 0;
            minList.add(new Node(from, to, cost));
            maxList.add(new Node(from, to, cost));
        }
        Collections.sort(minList, Comparator.comparingInt((Node o) -> o.cost));
        Collections.sort(maxList, Comparator.comparingInt((Node o) -> o.cost).reversed());
        long min = 0;
        long max = 0;
        for(int i = 0; i <= M; i++) {
            Node minNode = minList.get(i);
            int minX = find(minNode.from, true);
            int minY = find(minNode.to, true);
            if(minX != minY) { // 내리막 길
                min += minNode.cost;
                union(minNode.from, minNode.to, true);
            }
            Node maxNode = maxList.get(i);
            int maxX = find(maxNode.from, false);
            int maxY = find(maxNode.to, false);
            if(maxX != maxY) {  // 오르막 길
                max += maxNode.cost;
                union(maxNode.from, maxNode.to, false);
            }
        }
        bw.write(Long.toString(Math.abs((long) Math.pow(max, 2) - (long) Math.pow(min, 2))));
        bw.flush();
        bw.close();
    }
    // min = true, max = false
    public static void union(int from, int to, boolean div) {
        int x = find(from, div);
        int y = find(to, div);

        if(div) {
            if(x != y) {
                minArr[y] = x;
            }
        } else {
            if(x != y) {
                maxArr[y] = x;
            }
        }
    }
    public static int find(int target, boolean div) {
        if(target == (div ? minArr[target] : maxArr[target])) {
            return target;
        }

        return div ? (minArr[target] = find(minArr[target], true)) : (maxArr[target] = find(maxArr[target], false));
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