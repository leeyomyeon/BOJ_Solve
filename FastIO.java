import java.io.*;

/* 출처 : https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/ */
public class FastIO {
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        fr.println(123);
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
        private final byte MINUS = 32;
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
        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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
        private void write(byte b) {
            if(outBufferPointer == outBuffer.length) {
                flushBuffer();
            }
            outBuffer[outBufferPointer++] = b;
        }
        private void flushBuffer() {
            if(outBufferPointer != 0) {
                try {
                    dout.write(outBuffer, 0, outBufferPointer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                outBufferPointer = 0;
            }
        }
        private void println(int i) {
            if(i >= 0 && i <= 9) {
                write((byte) (i + ASCII_ZERO));
            } else {
                if(i < 0) {
                    write(MINUS); // -
                    i = ~i + 1;
                }
                int idx = 0;
                while(i > 0) {
                    byteBuffer[idx++] = (byte) ((i % 10) + 48);
                    i /= 10;
                }
                while(idx-->0) {
                    write(byteBuffer[idx]);
                }
            }
            write(NEW_LINE);
        }
        private void space() {
            write(SPACE);
        }
    }
}