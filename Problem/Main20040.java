import java.io.DataInputStream;
import java.io.IOException;

public class Main20040 {
    public static int N, M;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        Reader r = new Reader();
        N = r.nextInt();
        M = r.nextInt();
        arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        int turn = 0;
        for(int i = 1; i <= M; i++) {
            int from = r.nextInt();
            int to = r.nextInt();
            if(union(from, to)) {
                turn = i;
                break;
            }
        }
        System.out.println(turn);
    }
    public static boolean union(int from, int to) {
        int x = find(from);
        int y = find(to);

        if(x == y) { // 사이클 생성
            arr[x] = 0;
            return true;
        }
        if(x < y) {
            arr[y] = x;
        } else {
            arr[x] = y;
        }
        return false;
    }
    public static int find(int target) {    // 최상단 부모노드 찾기
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
    }
    public static class Reader {
        private DataInputStream din;
        private final int BUFFER_SIZE = 1 << 16;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while((c = read()) != -1) {
                if(c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while(c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if(neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while((c = read()) >= '0' && c <= '9');

            if(neg) {
                return -ret;
            }
            return ret;
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if(bytesRead == -1) {
                buffer[0] = -1;
            }
        }
        private byte read() throws IOException {
            if(bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
    }
}