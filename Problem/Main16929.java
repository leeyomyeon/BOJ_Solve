import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main16929 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static char[][] arr;
    public static int[][] loc;
    public static boolean[][] visited;
    public static boolean res;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new boolean[N][M];
        loc = new int[N][M];
        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        res = false;
        int cycle = 1;
        Loop1:
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) {
                    dfs(i, j, cycle, 0);
                    cycle++;
                }
                if(res) {
                    break Loop1;
                }
            }
        }
        bw.write(res ? "Yes" : "No");
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dfs(int r, int c, int cycle, int next) {
        visited[r][c] = true;
        loc[r][c] = cycle;
        for(int d = 0; d < 4; d++) {
            if(d == (next + 2) % 4) {
                continue;
            }
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
                continue;
            }
            if(visited[nr][nc] && loc[nr][nc] == cycle) {
                res = true;
                return;
            }
            if(!visited[nr][nc] && arr[nr][nc] == arr[r][c]) {
                dfs(nr, nc, cycle, d);
            }
        }
    }
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    /*
      0
     3 1
      2
      0 갔을땐 0, 1, 3만 2 배제
      1 갔을땐 0, 1, 2만 3 배제
      2 갔을땐 1, 2, 3만 0 배제
      3 갔을땐 0, 2, 3만 1 배제

    */
}