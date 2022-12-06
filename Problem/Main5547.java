import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main5547 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, res;
    public static int[][] arr;
    public static boolean[][] visited;
    public static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R + 1][C + 1];
        visited = new boolean[R + 1][C + 1];
        for(int j = 1; j <= C; j++) {
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= R; i++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        postProcessing();
        res = 0;
        find();
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void find() {
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                if(!visited[i][j] && arr[i][j] == 1) {
                    visited[i][j] = true;
                    ArrayDeque<Point> deque = new ArrayDeque<>();
                    deque.add(new Point(i, j));
                    while(!deque.isEmpty()) {
                        Point current = deque.removeFirst();
                        for(int d = 0; d < 6; d++) {
                            int nr = current.r + (current.c % 2 == 0 ? der[d] : dor[d]);
                            int nc = current.c + (current.c % 2 == 0 ? dec[d] : doc[d]);
                            if(nr == 0 || nc == 0 || nr == R + 1 || nc == C + 1) {
                                res++;
                                continue;
                            }
                            if(nr > 0 && nr <= R && nc > 0 && nc <= C) {
                                if(!visited[nr][nc] && arr[nr][nc] == 1) {
                                    visited[nr][nc] = true;
                                    deque.add(new Point(nr, nc));
                                }
                                if(arr[nr][nc] == 2) {
                                    res++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    // 가장자리의 값을 bfs 수행해 인접한 공간 전처리
    public static void postProcessing() {
        for(int j = 1; j <= C; j++) {
            bfs(1, j);
            bfs(R, j);
        }
        for(int i = 1; i <= R; i++) {
            bfs(i, 1);
            bfs(i, C);
        }
    }
    public static void bfs(int r, int c) {
        if(visited[r][c] || arr[r][c] == 1) {
            return;
        }
        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(r, c));
        visited[r][c] = true;
        arr[r][c] = 2;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            for(int d = 0; d < 6; d++) {
                int nr = current.r + (current.c % 2 == 0 ? der[d] : dor[d]);
                int nc = current.c + (current.c % 2 == 0 ? dec[d] : doc[d]);
                if(nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[nr][nc] && arr[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    arr[nr][nc] = 2;
                    deque.add(new Point(nr, nc));
                }
            }
        }
    }
    public static int[] der = {-1, 0, 1, 0, -1, -1};
    public static int[] dec = {-1, -1, 0, 1, 1, 0};
    public static int[] dor = {0, 1, 1, 1, 0, -1};
    public static int[] doc = {-1, -1, 0, 1, 1, 0};
}