import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main1858 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static ArrayList<Point> pList;
    public static class Point implements Comparable<Point> {
        int x;
        int y;
        int seq;
        public Point (int x, int y, int seq) {
            this.x = x;
            this.y = y;
            this.seq = seq;
        }

        @Override
        public int compareTo(Point o) {
            return this.x - o.x;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        pList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            pList.add(new Point(fr.nextInt(), fr.nextInt(), i + 1));
        }
        Collections.sort(pList);
        double max = -1;
        Point p1 = new Point(0, 0, 0), p2 = new Point(0, 0, 0);
        for(int i = 0; i < pList.size() - 1; i++) {
            Point current, next;
            if(pList.get(i).seq < pList.get(i + 1).seq) {
                current = pList.get(i);
                next = pList.get(i + 1);
            } else {
                current = pList.get(i + 1);
                next = pList.get(i);
            }

            double gradient = getGradient(current, next);
            if(max < gradient) {
                max = gradient; // 기울기 갱신
                p1 = current;
                p2 = next;
            } else if(max == gradient) { // 기울기가 같으면 점을 갱신
                // p1, p2 기존에 기울기가 있는것, current, next 새로 발견된 것
                // 한 점이 같은 경우
                if(p1.seq == current.seq) {
                    /* 1 2 1 3
                       1 2 1 4
                       1 3 1 2
                       1 3 1 4
                       1 4 1 2
                       1 4 1 3
                       2 3 2 4
                       2 4 2 3 */
                    if(p2.seq > next.seq) {
                        p2 = next;
                    }
                    continue;
                } else if(p2.seq == current.seq) {
                    /* 1 2 2 3
                       1 2 2 4
                       1 3 3 4
                       2 3 3 4 */
                    continue;
                } else if(p2.seq == next.seq) {
                    /* 1 3 2 3
                       2 3 1 3
                       1 4 2 4
                       1 4 3 4
                       2 4 3 4
                       3 4 2 4 */
                    if(p1.seq > current.seq) {
                        p2 = p1;
                        p1 = current;
                    } else {
                        p2 = current;
                    }
                    continue;
                } else if(p1.seq == next.seq){
                    /* 3 4 2 3
                       2 4 1 2 */
                    p1 = current;
                    p2 = next;
                    continue;
                }
                double tmp = getGradient(p2, current);
                if(max == tmp) { // 기울기가 전부 다 같으면 네 점 중 seq가 작은 두 점을 고름
                    /* p1 < p2, current < next는 항상 참
                       1    2      3       4
                       1    3      2       4
                       1    4      2       3
                       2    3      1       4
                       2    4      1       3
                       3    4      1       2
                    */
                    if(p1.seq < current.seq) { // 제일 작은 점
                        if(p2.seq > current.seq) {
                            p2 = current;
                        }
                    } else {
                        if(p1.seq < next.seq) {
                            p2 = p1;
                        } else {
                            p2 = next;
                        }
                        p1 = current;
                    }
                } else { // 달라서 current,next와 p1 p2중 갱신해야됨
                    if(current.seq < p1.seq) {
                        p1 = current;
                        p2 = next;
                    }
                }
            }
        }
        bw.write(p1.seq + " " + p2.seq);
        bw.flush();
        bw.close();
    }
    public static double getGradient(Point p1, Point p2) {
        return Math.abs((p2.y - p1.y) / (p2.x - p1.x));
    }

    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 20;
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
