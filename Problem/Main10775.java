import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main10775 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int G, P;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        G = fr.nextInt();
        P = fr.nextInt();
        arr = new int[G + 1];
        for(int i = 1; i <= G; i++) {
            arr[i] = i;
        }
        int res = 0;
        for(int i = 0; i < P; i++) {
            // 1번 부터 gi번까지 남는 자리에 도킹
            int g = fr.nextInt();
            int target = find(g);
            if(target != 0) {
                res++;
                union(target - 1, target);
            } else {
                break;
            }
        }
        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x < y) {
            arr[y] = x;
        } else {
            arr[x] = y;
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