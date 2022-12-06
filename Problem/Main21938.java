import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main21938 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, T;
    public static int[][] arr;
    public static boolean[][] visited;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int sum = 0;
                for(int k = 0; k < 3; k++) {
                    sum += Integer.parseInt(st.nextToken());
                }
                arr[i][j] = sum / 3;
            }
        }
        T = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && arr[i][j] >= T) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void bfs(int r, int c) {
        deque.add(new Point(r, c));
        visited[r][c] = true;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && arr[nr][nc] >= T) {
                    deque.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}