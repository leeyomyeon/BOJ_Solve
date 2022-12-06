import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main14442 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, K;
    public static boolean[][][] visited;
    public static char[][] arr;
    public static ArrayDeque<Point> queue;
    public static class Point {
        int r;
        int c;
        int cnt;
        int breakCnt;
        public Point(int r, int c, int cnt, int breakCnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.breakCnt = breakCnt;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[K + 1][R + 1][C + 1];
        arr = new char[R + 1][C + 1];
        for(int i = 1; i <= R; i++) {
            String str = br.readLine();
            for(int j = 1; j <= C; j++) {
                arr[i][j] = str.charAt(j - 1);
            }
        }
        queue = new ArrayDeque<>();
        visited[0][1][1] = true;
        queue.add(new Point(1, 1, 1, 0));
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Point current = queue.poll();

            if(current.r == R && current.c == C) {
                min = current.cnt;
                break;
            }

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[current.breakCnt][nr][nc]) {
                    if(current.breakCnt <= K - 1 && arr[nr][nc] == '1' && !visited[current.breakCnt + 1][nr][nc]) {
                        visited[current.breakCnt + 1][nr][nc] = true;
                        queue.add(new Point(nr, nc, current.cnt + 1, current.breakCnt + 1));
                    }
                    if(arr[nr][nc] == '0') {
                        visited[current.breakCnt][nr][nc] = true;
                        queue.add(new Point(nr, nc, current.cnt + 1, current.breakCnt));
                    }
                }
            }
        }
        bw.write(min == Integer.MAX_VALUE ? "-1" : Integer.toString(min));
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}