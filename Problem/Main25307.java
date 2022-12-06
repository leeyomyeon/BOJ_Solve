import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main25307 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int R, C, K;
    public static int[][] arr;
    public static boolean[][] visited;
    public static ArrayDeque<Point> queue, mQueue;
    public static class Point {
        int r;
        int c;
        int cnt;
        public Point (int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        visited = new boolean[R][C];
        queue = new ArrayDeque<>();
        mQueue = new ArrayDeque<>();
        Point start = null;
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 4) {
                    start = new Point(i, j, 0);
                    queue.add(start);
                    // 시작점
                } else if (arr[i][j] == 3) {
                    mQueue.add(new Point(i, j, 0));
                    // 마네킹
                }
            }
        }
        // 마네킹자리 세팅
        while(!mQueue.isEmpty()) {
            Point current = mQueue.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr >= 0 && nr < R && nc >= 0 && nc < C && (arr[nr][nc] == 0 || arr[nr][nc] == 2) && current.cnt + 1 <= K) {
                    arr[nr][nc] = 3;
                    mQueue.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
        int res = -1;
        Loop1:
        while(!queue.isEmpty()) {
            Point current = queue.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc]) {
                    if(arr[nr][nc] == 0) {
                        visited[nr][nc] = true;
                        queue.add(new Point(nr, nc, current.cnt + 1));
                    } else if(arr[nr][nc] == 2) {
                        res = current.cnt + 1;
                        break Loop1;
                    }
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }

    public static int[] dr = {-1, 1, 0 , 0};
    public static int[] dc = {0, 0, 1, -1};
}