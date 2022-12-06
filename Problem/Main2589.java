import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2589 {
    public static int R, C;
    public static char[][] arr;
    public static Queue<Point> queue;
    public static boolean[][] visited;
    public static int max = Integer.MIN_VALUE;
    public static class Point {
        int r;
        int c;
        int move;
        public Point(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        queue = new LinkedList<>();

        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == 'L') {
                    queue.add(new Point(i, j, 0));
                }
            }
        }

        while(!queue.isEmpty()) {
            bfs(queue.poll());
        }
        System.out.println(max);
    }

    public static int[] dr = {-1, 1, 0 ,0};
    public static int[] dc = {0, 0, -1, 1};
    public static void bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.add(p);

        visited = new boolean[R][C];
        visited[p.r][p.c] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();

            for(int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];

                if(nr >= 0 && nc >= 0 && nr < R && nc < C && arr[nr][nc] == 'L' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, current.move + 1));
                    max = Math.max(max, current.move + 1);
                }
            }
        }
    }
}
