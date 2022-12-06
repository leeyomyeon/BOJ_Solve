import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main15809 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, cost;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N + 1];
        cost = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            cost[i] = fr.nextInt();
            arr[i] = i;
        }
        for(int i = 0; i < M; i++) {
            int command = fr.nextInt();
            int from = fr.nextInt();
            int to = fr.nextInt();
            union(from, to, command);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if(cost[i] > 0) {
                pq.add(cost[i]);
            }
        }
        int s = pq.size();
        bw.write(Integer.toString(s));
        bw.newLine();
        while(!pq.isEmpty()) {
            int current = pq.poll();
            boolean abs = current < 0;
            if(abs) {
                current *= -1;
                bw.write(45);
            }
            int size = current == 0 ? 0 : (int) Math.log10(current);
            while(size >= 0) {
                int nextSize = current == 0 ? 0 : (int) Math.log10(current);
                int div = (int) Math.pow(10, nextSize);
                while(size > nextSize) {
                    bw.write(48);
                    size--;
                }
                bw.write((current / div) + 48);
                current %= div;
                size--;
            }
            if(s > 1) {
                bw.write(32);
            }
            s--;
        }
        bw.flush();
        bw.close();
    }
    public static void union(int from, int to, int command) {
        int x = find(from);
        int y = find(to);

        if(command == 1) {
            if(x < y) {
                arr[y] = arr[x];
                cost[x] += cost[y];
                cost[y] = 0;
            } else {
                arr[x] = arr[y];
                cost[y] += cost[x];
                cost[x] = 0;
            }
        } else {
            if(cost[x] == cost[y]) {
                arr[x] = 0;
                arr[y] = 0;
                cost[x] = 0;
                cost[y] = 0;
            } else if(cost[x] < cost[y]) {
                arr[x] = arr[y];
                cost[y] -= cost[x];
                cost[x] = 0;
            } else {
                arr[y] = arr[x];
                cost[x] -= cost[y];
                cost[y] = 0;
            }
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