import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main1743 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, K, cnt, res;
    public static boolean[][] visited;
    public static int[][] arr;
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
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        deque = new ArrayDeque<>();
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            arr[r][c] = 1;
        }
        res = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    cnt = 1;
                    deque.add(new Point(i, j));
                    visited[i][j] = true;
                    while(!deque.isEmpty()) {
                        Point current = deque.removeFirst();
                        for(int d = 0; d < 4; d++) {
                            int nr = current.r + dr[d];
                            int nc = current.c + dc[d];
                            if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && arr[nr][nc] == 1) {
                                cnt++;
                                visited[nr][nc] = true;
                                deque.add(new Point(nr, nc));
                            }
                        }
                    }
                    res = Math.max(cnt, res);
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