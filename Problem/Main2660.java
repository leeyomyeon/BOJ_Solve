import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main2660 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static boolean[] visited;
    public static int[] score;
    public static ArrayList<ArrayList<Point>> list;
    public static class Point {
        int next, cnt;

        public Point(int next, int cnt) {
            this.next = next;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        score = new int[N + 1];

        list = new ArrayList<>();
        for(int i = 0; i <= N; i++)  {
            list.add(new ArrayList<>());
        }
        while(true) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            if(from == to && from == -1) {
                break;
            }
            list.get(from).add(new Point(to, 0));
            list.get(to).add(new Point(from, 0));
        }
        ArrayDeque<Point> deque = new ArrayDeque<>();
        int minCnt = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            int MAX = Integer.MIN_VALUE;
            visited = new boolean[N + 1];
            deque.add(new Point(i, 0));
            visited[i] = true;
            while(!deque.isEmpty()) {
                Point current = deque.removeFirst();
                MAX = Math.max(current.cnt, MAX);
                for(Point next : list.get(current.next)) {
                    if(!visited[next.next]) {
                        visited[next.next] = true;
                        deque.add(new Point(next.next, current.cnt + 1));
                    }
                }
            }
            score[i] = MAX;
            if(minCnt > MAX) {
                minCnt = MAX;
                cnt = 1;
            } else if(minCnt == MAX) {
                cnt++;
            }
        }
        bw.write(minCnt + " " + cnt + "\n");
        for(int i = 1; i <= N; i++) {
            if(score[i] == minCnt) {
                bw.write(i + " ");
            }
        }
        bw.flush();
        bw.close();
    }

    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 4;
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