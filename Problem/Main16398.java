import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main16398 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static ArrayList<Point> list;
    public static class Point implements Comparable<Point> {
        int from, to, weight;

        public Point(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int w = fr.nextInt();
                if(i < j) {
                    list.add(new Point(i, j, w));
                }
            }
        }
        Collections.sort(list);
        int nodeCnt = 0;
        long sum = 0;
        for(Point current : list) {
            int x = find(current.from);
            int y = find(current.to);
            if(x != y) {
                union(current.from, current.to);
                sum += current.weight;
                nodeCnt++;
            }
            if(nodeCnt == N - 1) {
                break;
            }
        }
        bw.write(Long.toString(sum));
        bw.flush();
        bw.close();
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
            return arr[target];
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