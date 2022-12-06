import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main16933 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int R, C, K;
    public static boolean[][][] visited;
    public static char[][] arr;
    public static ArrayDeque<Point> queue;
    public static class Point {
        int r;
        int c;
        int cnt;
        int breakCnt;
        boolean isAfter;
        public Point(int r, int c, int cnt, int breakCnt, boolean isAfter) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.breakCnt = breakCnt;
            this.isAfter = isAfter;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[K + 1][R + 1][C + 1];
        arr = new char[R + 1][C + 1];
        queue = new ArrayDeque<>();
        for(int i = 1; i <= R; i++) {
            String str = br.readLine();
            for(int j = 1; j <= C; j++) {
                arr[i][j] = str.charAt(j - 1);
            }
        }
        queue.add(new Point(1,1 ,1 ,0 ,true));
        visited[0][1][1] = true;
        int res = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Point current = queue.removeFirst();
            if(current.r == R && current.c == C) {
                res = Math.min(res, current.cnt);
                break;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[current.breakCnt][nr][nc]) {
                    if(current.breakCnt <= K - 1 && arr[nr][nc] == '1' && current.isAfter) {
                        // 벽 부술수 있고 낮일때
                        visited[current.breakCnt + 1][nr][nc] = true;
                        queue.add(new Point(nr, nc, current.cnt + 1, current.breakCnt + 1, false));
                    }
                    if (current.breakCnt <= K - 1 && arr[nr][nc] == '1' && !current.isAfter && !visited[current.breakCnt + 1][nr][nc]) {
                        // 벽 부술수 있지만 밤일때(기존에 부쉈던 벽인지 확인)
                        visited[current.breakCnt + 1][nr][nc] = true;
                        queue.add(new Point(current.r, current.c, current.cnt + 1, current.breakCnt, true));
                    }
                    if (arr[nr][nc] == '0') {
                        // 빈 칸일때
                        visited[current.breakCnt][nr][nc] = true;
                        queue.add(new Point(nr, nc, current.cnt + 1, current.breakCnt, !current.isAfter));
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