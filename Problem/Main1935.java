import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main1935 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static Stack<Double> stack;
    public static int N;
    public static double[] arr;
    public static String str;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        str = fr.readLine();
        arr = new double[N];
        stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            arr[i] = fr.nextDouble();
        }
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if('A' <= c && c <= 'Z') {
                stack.add(arr[c - 'A']);
            } else {    // 연산자일 경우
                if(c == '+') {
                    double a = stack.pop();
                    double b = stack.pop();
                    stack.add(b + a);
                } else if(c == '-') {
                    double a = stack.pop();
                    double b = stack.pop();
                    stack.add(b - a);
                } else if(c == '*') {
                    double a = stack.pop();
                    double b = stack.pop();
                    stack.add(b * a);
                } else {
                    double a = stack.pop();
                    double b = stack.pop();
                    stack.add(b / a);
                }
            }
        }
        bw.write(String.format("%.2f", stack.peek()));
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