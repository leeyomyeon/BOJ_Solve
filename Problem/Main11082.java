import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main11082 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        String str = fr.readLine();
        int dot = -1;
        int circularStart = -1;
        StringBuilder newNum = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '.') {
                dot = i;
            } else if(str.charAt(i) == '(') {
                circularStart = i;
            } else if(str.charAt(i) != ')') {
                newNum.append(str.charAt(i));
            }
        }

        if(dot == -1) { // 소수점이 없을경우
            bw.write(str);
            bw.write("/1");
        } else if(circularStart == -1) {    // 소수점인 경우
            long u = Long.parseLong(newNum.toString());
            long l = (long) Math.pow(10, (str.length() - dot - 1));
            long g = u > l ? gcd(u, l) : gcd(l, u);
            bw.write( u / g + "/" + l / g);
        } else {    // 순환소수인 경우
            long k = (long) Math.pow(10, (str.length() - 2 - dot - 1));
            long n = (long) Math.pow(10, (circularStart - dot - 1));
            long r = Long.parseLong(str.substring(0, dot) + str.substring(dot + 1, circularStart));
            long p = Long.parseLong(newNum.toString());
            long u = p - r;
            long l = k - n;
            long g = u > l ? gcd(u, l) : gcd(l, u);
            bw.write( u / g + "/" + l / g);
        }
        bw.flush();
        bw.close();
    }
    public static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
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
                ret = (ret << 3) + (ret << 1) + (c & 15);
            }
            while ((c = read()) >= '0' && c <= '9');
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
/*
17.04(752)
x = 17.04(752)
100000x = 1704752.(752) k = 100000
100x = 1704.(752)   n = 100
 */