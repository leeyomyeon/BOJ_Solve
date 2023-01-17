import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main1874 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static Stack<Integer> stack;
    public static StringBuffer sb;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = fr.nextInt();
        }
        sb = new StringBuffer();
        stack = new Stack<>();
        int idx = 0;
        int next = 1;
        boolean res = true;
        while(idx != N) {
            if(next > N + 1) {
                res = false;
                break;
            }
            if(stack.isEmpty() || stack.peek() != arr[idx]) {
                stack.add(next++);
                sb.append('+');
            } else if(stack.peek() == arr[idx]){
                stack.pop();
                sb.append('-');
                idx++;
            }
            sb.append('\n');
        }
        if(res) {
            bw.write(sb.toString());
        } else {
            bw.write("NO");
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