import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main25189 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static boolean[][][] visited;
    public static boolean[] visitedRow, visitedCol;
    public static int[][] arr;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, d, cnt;
        boolean isIgnore;
        public Point(int r, int c, int d, int cnt, boolean isIgnore) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;
            this.isIgnore = isIgnore;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[2][N + 1][M + 1];
        arr = new int[N + 1][M + 1];
        visitedRow = new boolean[N + 1];
        visitedCol = new boolean[M + 1];
        deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        int endR = Integer.parseInt(st.nextToken());
        int endC = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Point start = new Point(startR, startC, arr[startR][startC], 0, false);
        visited[0][startR][startC] = true;
        deque.add(start);
        int res = -1;
        while(!deque.isEmpty()){
            Point current = deque.removeFirst();
            if(current.r == endR && current.c == endC) {
                res = current.cnt;
                break;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d] * current.d;
                int nc = current.c + dc[d] * current.d;
                if(nr <= 0 || nr > N || nc <= 0 || nc > M) { continue; }
                if(visited[current.isIgnore ? 1 : 0][nr][nc]) { continue; }
                visited[current.isIgnore ? 1 : 0][nr][nc] = true;
                deque.add(new Point(nr, nc, arr[nr][nc], current.cnt + 1, current.isIgnore));
            }
            if(!current.isIgnore) { // 개구리밥을 아직 무시하지 않았으면
                if(!visitedRow[current.r]) {
                    visitedRow[current.r] = true;
                    for(int c = 1; c <= M; c++) {
                        if(!visited[1][current.r][c]) {
                            visited[1][current.r][c] = true;
                            deque.add(new Point(current.r, c, arr[current.r][c], current.cnt + 1, true));
                        }
                    }
                }
                if(!visitedCol[current.c]) {
                    visitedCol[current.c] = true;
                    for(int r = 1; r <= N; r++) {
                        if(!visited[1][r][current.c]) {
                            visited[1][r][current.c] = true;
                            deque.add(new Point(r, current.c, arr[r][current.c], current.cnt + 1, true));
                        }
                    }
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}