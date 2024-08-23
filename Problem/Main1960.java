import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Main1960 {
    public static int N;
    public static ArrayList<Node> row, col;
    public static int[] or, oc;
    public static int[][] res;
    public static class Node {
        int cnt;
        int idx;
        public Node(int cnt, int idx) {
            this.cnt = cnt;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        row = new ArrayList<>();
        col = new ArrayList<>();
        or = new int[N];
        oc = new int[N];
        res = new int[N][N];
        for(int i = 0; i < N; i++) {
            int k = fr.nextInt();
            or[i] = k;
            row.add(new Node(k, i));
        }
        for(int i = 0; i < N; i++) {
            int k = fr.nextInt();
            oc[i] = k;
            col.add(new Node(k, i));
        }
        row.sort((Node o1, Node o2) -> {
            if(o1.cnt == o2.cnt)  return o1.idx - o2.idx;
            return o2.cnt - o1.cnt;
        });


        for(Node r : row) {
            col.sort((Node o1, Node o2) -> {
                if(o1.cnt == o2.cnt) return o1.idx - o2.idx;
                return o2.cnt - o1.cnt;
            });
            for(Node c : col) {
                if(or[r.idx] > 0 && oc[c.idx] > 0) {
                    res[r.idx][c.idx] = 1;
                    c.cnt--;
                    or[r.idx]--;
                    oc[c.idx]--;
                }
            }
        }
        int chk = 0;
        for(int i = 0; i < N; i++) {
            chk += or[i];
            chk += oc[i];
        }
        if(chk == 0) {
            fr.println(1);
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    fr.print(res[i][j]);
                }
                fr.newLine();
            }
        } else {
            fr.print(-1);
        }
        fr.flushBuffer();
    }
    public static class FastReader {
        private final DataInputStream din;
        private final DataOutputStream dout;
        private final int BUFFER_SIZE = 1 << 14;
        private final int OUT_BUFFER_SIZE = 1 << 18;
        private final byte[] buffer;
        private final byte[] outBuffer, byteBuffer;
        private int bufferPointer, outBufferPointer, bytesRead;
        private final byte SPACE = 32;
        private final byte MINUS = 45;
        private final byte ASCII_ZERO = 48;
        private final byte NEW_LINE = 10;

        public FastReader() {
            din = new DataInputStream(System.in);
            dout = new DataOutputStream(System.out);
            buffer = new byte[BUFFER_SIZE];
            outBuffer = new byte[OUT_BUFFER_SIZE];
            bufferPointer = bytesRead = outBufferPointer = 0;
            byteBuffer = new byte[20];
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

        private void write(byte b) {
            if (outBufferPointer == outBuffer.length) {
                flushBuffer();
            }
            outBuffer[outBufferPointer++] = b;
        }

        private void flushBuffer() {
            if (outBufferPointer != 0) {
                try {
                    dout.write(outBuffer, 0, outBufferPointer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                outBufferPointer = 0;
            }
        }

        private void println(int i) {
            if (i >= 0 && i <= 9) {
                write((byte) (i + ASCII_ZERO));
            } else {
                if (i < 0) {
                    write(MINUS); // -
                    i = ~i + 1;
                }
                int idx = 0;
                while (i > 0) {
                    byteBuffer[idx++] = (byte) ((i % 10) + 48);
                    i /= 10;
                }
                while (idx-- > 0) {
                    write(byteBuffer[idx]);
                }
            }
            write(NEW_LINE);
        }

        private void print(int i) {
            if (i >= 0 && i <= 9) {
                write((byte) (i + ASCII_ZERO));
            } else {
                if (i < 0) {
                    write(MINUS); // -
                    i = ~i + 1;
                }
                int idx = 0;
                while (i > 0) {
                    byteBuffer[idx++] = (byte) ((i % 10) + 48);
                    i /= 10;
                }
                while (idx-- > 0) {
                    write(byteBuffer[idx]);
                }
            }
        }

        private void space() {
            write(SPACE);
        }
        private void newLine() {
            write(NEW_LINE);
        }
    }
}
