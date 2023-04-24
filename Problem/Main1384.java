import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
/*
3
ANN N N
BOB N N
CLIVE P P
0
 */
public class Main1384 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = 1;
        while(true) {
            int N = fr.nextInt();
            if(N == 0) {
                break;
            }
            ArrayDeque<String> deque = new ArrayDeque<>();
            String[][] arr = new String[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(fr.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = st.nextToken();
                }
            }
            for(int i = 0; i < N; i++) {
                for(int j = 1; j < N; j++) {
                    if(arr[i][j].equals("N")) {
                        int target = ((i + N) - j) % N;
                        deque.add(arr[target][0] + " was nasty about " + arr[i][0]);
                    }
                }
            }
            bw.write("Group " + T + "\n");
            if(deque.isEmpty()) {
                bw.write("Nobody was nasty\n");
            } else {
                while(!deque.isEmpty()) {
                    bw.write(deque.removeFirst());
                    bw.newLine();
                }
            }
            bw.newLine();
            T++;
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
