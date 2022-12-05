import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1986 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int R, C;
    public static int[][] visited;
    public static Queue<Point> queue;
    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new int[R + 1][C + 1];
        queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());
        for(int q = 0; q < Q; q++) { // 퀸의 개수
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited[x][y] = 2;
            queue.add(new Point(x, y));
        }
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int k = 0; k < K; k++) {    // 나이트의 개수
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited[x][y] = 2;
            for(int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx > 0 && nx <= R && ny > 0 && ny <= C) {
                    if(visited[nx][ny] == 2) {
                        continue;
                    }
                    visited[nx][ny] = 1;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        for(int p = 0; p < P; p++) { // 폰의 개수
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited[x][y] = 2;
        }
        while(!queue.isEmpty()) {   // 퀸 이동경로
            Point current = queue.poll();
            for(int i = current.x - 1; i >= 1; i--) {
                if(visited[i][current.y] == 2) { break; }
                visited[i][current.y] = 1;
            }
            for(int i = current.x + 1; i <= R; i++) {
                if(visited[i][current.y] == 2) { break; }
                visited[i][current.y] = 1;
            }
            for(int i = current.y - 1; i >= 1; i--) {
                if(visited[current.x][i] == 2) { break; }
                visited[current.x][i] = 1;
            }
            for(int i = current.y + 1; i <= C; i++) {
                if(visited[current.x][i] == 2) { break; }
                visited[current.x][i] = 1;
            }
            for(int i = 1; i <= 1000; i++) {    // ↖
                if(current.x - i <= 0 || current.y - i <= 0 || visited[current.x - i][current.y - i] == 2) { break; }
                visited[current.x - i][current.y - i] = 1;
            }
            for(int i = 1; i <= 1000; i++) {    // ↘
                if(current.x + i > R || current.y + i > C || visited[current.x + i][current.y + i] == 2) { break; }
                visited[current.x + i][current.y + i] = 1;
            }
            for(int i = 1; i <= 1000; i++) {    // ↗
                if(current.x - i <= 0 || current.y + i > C || visited[current.x - i][current.y + i] == 2) { break; }
                visited[current.x - i][current.y + i] = 1;
            }
            for(int i = 1; i <= 1000; i++) {    // ↙
                if(current.x + i > R || current.y - i <= 0 || visited[current.x + i][current.y - i] == 2) { break; }
                visited[current.x + i][current.y - i] = 1;
            }
        }
        int cnt = 0;
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                if(visited[i][j] == 0) {
                    cnt++;
                }
            }
        }

        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}