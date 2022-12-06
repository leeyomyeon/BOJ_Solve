import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main10026 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static boolean[][][] visited;
    public static char[][][] arr;
    public static int[][] res;
    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[2][N][N];
        arr = new char[2][N][N];
        res = new int[2][1];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                char c = str.charAt(j);
                arr[0][i][j] = c;
                if(c == 'G') {
                    arr[1][i][j] = 'R';
                } else {
                    arr[1][i][j] = c;
                }
            }
        }
        findArea(0);
        findArea(1);
        // div = 0 > 정상 div = 1 > 비정상
        bw.write(res[0][0] + " " + res[1][0]);
        br.close();
        bw.flush();
        bw.close();
    }

    public static void findArea(int div) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[div][i][j]) {
                    bfs(i, j, div);
                    res[div][0]++;
                }
            }
        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static void bfs(int cr, int cc, int div) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(cr, cc));
        visited[div][cr][cc] = true;
        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[div][nr][nc] && arr[div][current.r][current.c] == arr[div][nr][nc]) {
                    queue.add(new Point(nr, nc));
                    visited[div][nr][nc] = true;
                }
            }
        }
    }
}