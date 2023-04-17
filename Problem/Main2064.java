import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main2064 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static long subnetMask;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        subnetMask = 0;
        arr = new int[32];
        String str = "";
        for(int i = 0; i < N; i++) {
            String ip = fr.readLine();
            if(str.equals("")) {
                str = ip;
            }
            long ipv4Addr = ipv4ToLongConv(ip);
            for(int j = 0; j < 32; j++) {
                if((((long) 1 << j) & ipv4Addr) == ((long) 1 << j)) {
                    arr[j]++;
                }
            }
        }
        for(int i = 31; i >= 0; i--) {
            if(arr[i] == N || arr[i] == 0) {
                subnetMask |= ((long) 1 << i);
            } else {
                break;
            }
        }
        bw.write(longToIpv4Conv(subnetMask & ipv4ToLongConv(str)) + "\n");
        bw.write(longToIpv4Conv(subnetMask));
        bw.flush();
        bw.close();
    }
    public static String longToIpv4Conv(long target) {
        long v1 = (((long) 1 << 32) - 1) - (((long) 1 << 24) - 1);
        long v2 = (((long) 1 << 24) - 1) - (((long) 1 << 16) - 1);
        long v3 = (((long) 1 << 16) - 1) - (((long) 1 << 8) - 1);
        long v4 = ((long) 1 << 8) - 1;
        int p1 = (int) ((target & v1) >> 24);
        int p2 = (int) ((target & v2) >> 16);
        int p3 = (int) ((target & v3) >> 8);
        int p4 = (int) (target & v4);
        return p1 + "." + p2 + "." + p3 + "." + p4;
    }
    public static long ipv4ToLongConv(String ipv4) {
        String[] ipAddr = ipv4.split("\\.");
        long tmp = 0;
        tmp |= Long.parseLong(ipAddr[0]) << 24;
        tmp |= Long.parseLong(ipAddr[1]) << 16;
        tmp |= Long.parseLong(ipAddr[2]) << 8;
        tmp |= Long.parseLong(ipAddr[3]);
        return tmp;
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
