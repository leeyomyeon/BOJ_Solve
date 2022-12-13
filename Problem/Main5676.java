import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main5676 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, K;
    public static int[] arr, segTree;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        byte input;
        while((input = fr.read()) > 0) {
            N = fr.nextInt(input);
            if(N <= 0)  {
                break;
            }
            K = fr.nextInt();
            arr = new int[N + 1];
            segTree = new int[N * 4];
            for(int i = 1; i <= N; i++) {
                int in = fr.nextInt();
                if(in < 0) {
                    arr[i] = -1;
                } else if(in > 0) {
                    arr[i] = 1;
                }
            }
            makeSegTree(1, N, 1);
            for(int k = 0; k < K; k++) {
                int command = fr.nextInt();
                int a = fr.nextInt();
                int b = fr.nextInt();
                if(command == 19) { // 변경
                    if(b == 0) {
                        update(1, N, 1, a, 0);
                        arr[a] = 0;
                    } else if(b < 0) {
                        update(1, N, 1, a, -1);
                        arr[a] = -1;
                    } else {
                        update(1, N, 1, a, 1);
                        arr[a] = 1;
                    }
                } else { // 곱셈
                    int res = find(1, N, 1, a, b);
                    if(res == -1) {
                        bw.write('-');
                    } else if(res == 1) {
                        bw.write('+');
                    } else {
                        bw.write('0');
                    }
                }
            }
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
    public static int makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx] = arr[start];
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        segTree[idx] = makeSegTree(start, mid, idx * 2) * makeSegTree(mid + 1, end, idx * 2 + 1);
        return segTree[idx];
    }
    public static int find(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return 1;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, left, right) * find(mid + 1, end, idx * 2 + 1, left, right);
    }
    public static int update(int start, int end, int idx, int target, int value) {
        if(target < start || end < target) {
            return segTree[idx];
        }
        if(start == end) {
            segTree[idx] = value;
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        segTree[idx] = update(start, mid, idx * 2, target, value) * update(mid + 1, end, idx * 2 + 1, target, value);
        return segTree[idx];
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

        public int nextInt(byte c) throws IOException {
            int ret = 0;
            if(c <= ' ') {
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
            return (buffer[bufferPointer++]);
        }

        private boolean hasNext() {
            return false;
        }
    }
}