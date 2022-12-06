import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main17836 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, T, res;
    public static int[][] arr;
    public static boolean[][][] visited;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, t;
        boolean g;
        public Point(int r, int c, int t, boolean g) {
            this.r = r;
            this.c = c;
            this.t = t;
            this.g = g;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[R + 1][C + 1];
        visited = new boolean[2][R + 1][C + 1];
        deque = new ArrayDeque<>();
        for(int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        res = -1;
        bfs();
        bw.write(res == -1 || res > T ? "Fail" : Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void bfs() {
        deque.add(new Point(1, 1, 0, false));
        visited[0][1][1] = true;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.r == R && current.c == C) {
                res = current.t;
                return;
            }

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[current.g ? 1 : 0][nr][nc]) {
                    visited[current.g ? 1 : 0][nr][nc] = true;
                    if(arr[nr][nc] == 0) {
                        deque.add(new Point(nr, nc, current.t + 1, current.g));
                    }
                    if(current.g && arr[nr][nc] == 1) {
                        deque.add(new Point(nr, nc, current.t + 1, true));
                    }
                    if(arr[nr][nc] == 2) {
                        deque.add(new Point(nr, nc, current.t + 1, true));
                    }
                }
            }

        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, 1, -1};
}