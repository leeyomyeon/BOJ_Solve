import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main1655 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static PriorityQueue<Integer> left;
    public static PriorityQueue<Integer> right;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            int target = fr.nextInt();
            if(left.size() == 0 && right.size() == 0) {
                left.add(target);
                bw.write(target + "\n");
                continue;
            }
            if(left.size() == right.size()) {
                if(left.peek() >= target) {
                    left.add(target);
                } else {
                    right.add(target);
                }
            } else {
                if(left.size() > 0 && target < left.peek()) {
                    left.add(target);
                } else {
                    right.add(target);
                }
                if(left.size() > right.size()) {
                    right.add(left.poll());
                } else if(left.size() < right.size()){
                    left.add(right.poll());
                }
            }
            if((left.size() + right.size()) % 2 == 0) { // 짝수개면 항상 작은쪽
                bw.write(left.peek() + "\n");
            } else { // 홀수개면 크기비교후 가운데 인덱스 찾기
                if(left.size() > right.size()) {
                    bw.write(left.peek() + "\n");
                } else {
                    bw.write(right.peek() + "\n");
                }
            }
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