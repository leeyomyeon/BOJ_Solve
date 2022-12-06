import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main4195 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static HashMap<String, Integer> map;
    public static int N;
    public static int[] arr, sum;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int tc = fr.nextInt();
        for(int t = 0; t < tc; t++) {
            N = fr.nextInt();
            map = new HashMap<>();
            arr = new int[N * 2 + 1];
            sum = new int[N * 2 + 1];
            for(int i = 1; i < arr.length; i++) {
                arr[i] = i;
            }
            int num = 1;
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(fr.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                if(!map.containsKey(f1)) {
                    map.put(f1, num);
                    num++;
                }
                if(!map.containsKey(f2)) {
                    map.put(f2, num);
                    num++;
                }
                int x = map.get(f1);
                int y = map.get(f2);
                union(x, y);
                fastNumberWrite(sum[find(x)]);
                if(i != N - 1) {
                    bw.newLine();
                }
            }
        }
        bw.flush();
        bw.close();
    }
    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x != y) {
            if(x < y) {
                arr[y] = x;
                if(sum[x] == 0) {
                    sum[x] = 2;
                } else {
                    sum[x] += sum[y] == 0 ? 1 : sum[y];
                    sum[y] = 0;
                }
            } else {
                arr[x] = y;
                if(sum[y] == 0) {
                    sum[y] = 2;
                } else {
                    sum[y] += sum[x] == 0 ? 1 : sum[x];
                    sum[x] = 0;
                }
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

    public static void fastNumberWrite(int current) throws Exception {
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

    public static void fastNumberWrite(long current) throws Exception {
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