import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main3190 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] arr;
    public static int N, K, L, count, dir;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        deque = new ArrayDeque<>();
        deque.add(new Point(1, 1));
        arr = new int[N + 2][N + 2];
        arr[1][1] = 1;
        count = 0;
        dir = 1;
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[r][c] = 4;
        }
        L = Integer.parseInt(br.readLine());
        int[][] command = new int[L][2];
        for(int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            command[i][0] = Integer.parseInt(st.nextToken());
            command[i][1] = st.nextToken().charAt(0) == 'L' ? -1 : 1;
        }
        int idx = 0;
        int x = command[0][0];
        int c = command[0][1];
        while(true) {
            int move = move(x, c);
            if (move == 0 && idx < L - 1) {
                idx++;
                x = command[idx][0];
                c = command[idx][1];
            }
            if (move == -1) {
                break;
            }
        }

        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }
    public static int move(int X, int d) {
        count++;
        Point head = deque.peek();
        int nr = head.r + dr[dir];
        int nc = head.c + dc[dir];
        if(nr == 0 || nc == 0 || nr == N + 1 || nc == N + 1 || arr[nr][nc] == 1) {
            return -1;
        }
        if(arr[nr][nc] == 0) {
            Point tail = deque.removeLast();
            arr[tail.r][tail.c] = 0;
        }
        deque.addFirst(new Point(nr, nc));
        arr[nr][nc] = 1;
        if(count == X) {
            dir = (dir + 4 + d) % 4;
            return 0;
        }
        return 1;
    }
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
}