import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1672 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[][] table;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N];
        table = new int[][]{{0, 2, 0, -1, 1}, {2, 1, 4, -1, 0}, {0, 4, 2, -1, 1}, {-1, -1, -1, -1, -1}, {1, 0, 1, -1, 4}};
        for(int i = 0; i < N; i++) {
            arr[i] = fr.getChars();
        }
        /*
        A = 0
        G = 1
        C = 2
        T = 4
         */
        for(int i = N - 1; i >= 1; i--) {
            arr[i - 1] = table[arr[i - 1]][arr[i]];
        }
        if(arr[0] == 0) {
            bw.write('A');
        } else if(arr[0] == 1) {
            bw.write('G');
        } else if(arr[0] == 2) {
            bw.write('C');
        } else {
            bw.write('T');
        }
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

        public int getChars() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return c % 5;
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