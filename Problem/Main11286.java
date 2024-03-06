import java.io.*;
import java.util.PriorityQueue;

public class Main11286 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static PriorityQueue<Number> pq;
    public static int N;
    public static class Number implements Comparable<Number>{
        int origin;
        int abs;
        public Number(int origin, int abs) {
            this.origin = origin;
            this.abs = abs;
        }
        @Override
        public int compareTo(Number o) {
            if(o.abs == this.abs) {
                return Integer.compare(this.origin, o.origin);
            }
            return Integer.compare(this.abs, o.abs);
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int num = fr.nextInt();
            if(num == 0) {
                if(pq.size() == 0) {
                    sb.append("0");
                } else {
                    sb.append(pq.poll().origin);
                }
                sb.append("\n");
            } else {
                pq.add(new Number(num, Math.abs(num)));
            }
        }
        bw.write(sb.toString());
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
/*
8
-2147483647
2147483646
-2147483646
2147483645
0
0
0
0
 */