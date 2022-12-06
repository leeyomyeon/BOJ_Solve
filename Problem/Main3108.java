import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main3108 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static Point[] list;
    public static class Point {
        int r1, c1, r2, c2;

        public Point(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        list = new Point[N + 1];
        for(int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }
        list[0] = new Point(0, 0, 0, 0); // 시작점을 길이 0 의 사각형으로 둠
        for(int i = 1; i <= N; i++) {
            int r1 = fr.nextInt();
            int c1 = fr.nextInt();
            int r2 = fr.nextInt();
            int c2 = fr.nextInt();
            list[i] = new Point(r1, c1, r2, c2);
        }
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j <= N; j++) {
                if(isOver(list[i], list[j])) {  // 여기서 길이 0 이랑 겹치면 펜을 떼지 않아도 됨
                    union(i, j);
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i <= N; i++) {
            if(i == find(i)) {
                cnt++;
            }
        }
        bw.write(Integer.toString(cnt - 1));
        bw.flush();
        bw.close();
    }
    public static boolean isOver(Point o1, Point o2) {
        // 두 직사각형이 겹치는지 확인
        if(o2.r1 > o1.r2 || o2.r2 < o1.r1 || o2.c1 > o1.c2 || o2.c2 < o1.c1) {
            return false;
        }
        if(o1.r1 < o2.r1 && o2.r2 < o1.r2 && o1.c1 < o2.c1 && o2.c2 < o1.c2) {
            return false;
        }
        if(o2.r1 < o1.r1 && o1.r2 < o2.r2 && o2.c1 < o1.c1 && o1.c2 < o2.c2) {
            return false;
        }

        return true;
    }
    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);

        if(x < y) {
            arr[y] = x;
        } else {
            arr[x] = y;
        }
    }

    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
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