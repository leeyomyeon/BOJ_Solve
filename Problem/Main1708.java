import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main1708 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static ArrayList<Point> list;
    public static Stack<Point> stack;
    public static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        list = new ArrayList<>();
        stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            list.add(new Point(fr.nextInt(), fr.nextInt()));
        }
        Collections.sort(list, (Point o1, Point o2) -> o2.y == o1.y ? o2.x - o1.x : o2.y - o1.y);
        Point start = list.get(0);    // y좌표가 가장 작은것, y좌표가 같으면 x좌표가 작은것
        stack.add(start);   // 시작점 잡고
        Collections.sort(list, (p1, p2) -> {    // 시작점 기준 반시계방향으로 정렬
            long res = CCW(start, p1, p2);
            if(start.equals(p1) || start.equals(p2)) {
                return 0;
            }
            if (res > 0) {   // 반시계 방향
                return -1;
            } else if (res < 0) { // 시계방향
                return 1;
            } else {    // 일직선일 경우
                //시작점과 p1의 거리, 시작점과 p2의 거리 중 짧은 순으로 정렬
                long dist1 = dist(start, p1);
                long dist2 = dist(start, p2);
                return dist1 > dist2 ? 1 : -1;
            }
        });
        stack.add(list.get(1));
        for(int i = 2; i < list.size(); i++) {
            Point next = list.get(i);
            while(stack.size() >= 2 && CCW(stack.get(stack.size() - 2), stack.get(stack.size() - 1), next) <= 0) {
                stack.pop();
            }
            stack.push(next);
        }
        bw.write(Integer.toString(stack.size()));
        bw.flush();
        bw.close();
    }
    public static long CCW(Point p1, Point p2, Point p3)  {
        long c = (long) (p2.x - p1.x) * (p3.y - p1.y) - (long) (p3.x - p1.x) * (p2.y - p1.y);
        return Long.compare(c, 0);
    }
    public static long dist(Point p1, Point p2) {
        return (long) (Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
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