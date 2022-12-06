import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main18404 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static StringBuffer sb = new StringBuffer();
    public static int N;
    public static int M;
    public static ArrayDeque<Point> deque;
    public static int[][] arr;
    public static boolean[][] visited;
    public static class Point {
        int r;
        int c;
        int cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        Point start = new Point(r, c, 0);
        visited[r][c] = true;
        deque.add(start);
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(int d = 0; d < 8; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= N && nc > 0 && nc <= N && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    arr[nr][nc] = current.cnt + 1;
                    deque.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            sb.append(arr[r][c]).append(" ");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static int[] dr = {-2, -2, -1, -1, 1, 1, 2, 2};
    public static int[] dc = {-1, 1, -2, 2, -2, 2, -1, 1};
}