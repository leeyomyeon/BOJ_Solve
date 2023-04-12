import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main2993 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int E;
    public static StringBuilder str, MIN;
    public static void main(String[] args) throws Exception {
        // 0 ~ 49까지니 1 ~ 48까지 2개의 숫자를 골라 Reverse하고 조합하여 사전 순으로 가장 앞서는 단어 찾기
        // N은 최소 3이니
        FastReader fr = new FastReader();
        str = new StringBuilder(fr.readLine()).reverse();   // 셋 다 뒤집을거면 한번에 뒤집는게 나음
        E = str.length() - 1; // E 까지 DFS
        MIN = new StringBuilder("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");  // 사전순으로 가장 뒤로오는 문자열
        for(int i = 1; i < E; i++) {
            for(int j = i + 1; j <= E; j++) {
                String a = str.substring(0, i);
                String b = str.substring(i, j);
                StringBuilder c = new StringBuilder(str.substring(j, E + 1));
                c.append(b).append(a);
                if(MIN.toString().compareTo(c.toString()) > 0) {    // 사전순으로 앞서면
                    MIN = c;
                }
            }
        }
        bw.write(MIN.toString());
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
