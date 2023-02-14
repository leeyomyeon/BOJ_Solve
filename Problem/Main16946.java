import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main16946 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C;
    public static int[][] arr, sum, visited;
    public static ArrayList<Node> list;
    public static class Node {
        int r, c, area;

        public Node(int r, int c, int area) {
            this.r = r;
            this.c = c;
            this.area = area;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        R = fr.nextInt();
        C = fr.nextInt();
        arr = new int[R][C];
        sum = new int[R][C];
        visited = new int[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(fr.nextChar() == '1') {
                    arr[i][j] = -1;
                }
            }
        }
        list = new ArrayList<>();
        list.add(null);
        int cnt = 1;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(arr[i][j] == 0) {
                    ArrayDeque<Node> deque = new ArrayDeque<>();
                    int area = 1;
                    arr[i][j] = cnt;
                    deque.add(new Node(i, j, 0));
                    while(!deque.isEmpty()) {
                        Node current = deque.removeFirst();
                        for(int d = 0; d < 4; d++) {
                            int nr = current.r + dr[d];
                            int nc = current.c + dc[d];
                            if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 0) {
                                arr[nr][nc] = cnt;
                                deque.add(new Node(nr, nc, 0));
                                area++;
                            }
                        }
                    }
                    list.add(new Node(i, j, area));
                    cnt++;
                }
            }
        }
        ArrayDeque<Node> deque = new ArrayDeque<>();
        for(int i = 1; i < list.size(); i++) {
            Node node = list.get(i);
            visited[node.r][node.c] = i;
            deque.add(node);
            while(!deque.isEmpty()) {
                Node current = deque.removeFirst();

                for(int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr >= 0 && nr < R && nc >= 0 && nc < C && visited[nr][nc] != i) {
                        visited[nr][nc] = i;
                        if(arr[nr][nc] == -1) {
                            sum[nr][nc] += node.area;
                        } else {
                            deque.add(new Node(nr, nc, node.area));
                        }
                    }
                }
            }
        }
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                bw.write(arr[i][j] == -1 ? ((sum[i][j] + 1) % 10) + 48 : 48);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

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

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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