import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main14923 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int R, C;
    public static char[][] arr;
    public static boolean[][][] visited;
    public static ArrayDeque<Point> queue;
    public static class Point {
        int r;
        int c;
        int cnt;
        boolean isBreak;
        public Point(int r, int c, int cnt, boolean isBreak) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.isBreak = isBreak;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, false);
        st = new StringTokenizer(br.readLine());
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, false);

        arr = new char[R + 1][C + 1];
        visited = new boolean[2][R + 1][C + 1];
        for(int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= C; j++) {
                arr[i][j] = st.nextToken().charAt(0);
            }
        }
        visited[start.isBreak ? 1 : 0][start.r][start.c] = true;
        queue = new ArrayDeque<>();
        queue.add(start);
        int res = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Point current = queue.removeFirst();

            if(current.r == end.r && current.c == end.c) {
                res = Math.min(res, current.cnt);
                break;
            }

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= R && nc > 0 && nc <= C) {
                    if(arr[nr][nc] == '1' && !current.isBreak && !visited[1][nr][nc]) {
                        visited[1][nr][nc] = true;
                        queue.add(new Point(nr, nc, current.cnt + 1, true));
                    }
                    if(arr[nr][nc] == '0' && !visited[current.isBreak ? 1 : 0][nr][nc]) {
                        visited[current.isBreak ? 1 : 0][nr][nc] = true;
                        queue.add(new Point(nr, nc, current.cnt + 1, current.isBreak));
                    }
                }
            }
        }
        bw.write(res == Integer.MAX_VALUE ? "-1" : Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}