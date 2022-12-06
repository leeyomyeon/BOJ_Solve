import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main16724 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, res;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[][] loc;
    public static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        loc = new int[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                char c = str.charAt(j);
                if(c == 'D') {
                    arr[i][j] = 0;
                } else if(c == 'U') {
                    arr[i][j] = 1;
                } else if(c == 'R') {
                    arr[i][j] = 2;
                } else if(c == 'L') {
                    arr[i][j] = 3;
                }
            }
        }
        res = 0;
        int cnt = 1;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(!visited[i][j]) {
                    dfs(i, j, cnt);
                    cnt++;
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dfs(int r, int c, int cnt) {
        visited[r][c] = true;
        loc[r][c] = cnt;
        int nr = r + dr[arr[r][c]];
        int nc = c + dc[arr[r][c]];
        if(visited[nr][nc] && loc[nr][nc] == cnt) {
            // 사이클이 형성되는경우
            res++;
            return;
        } else if(visited[nr][nc] && loc[nr][nc] != cnt) {
            // 다른 사이클로 이동할경우
            return;
        }
        if(!visited[nr][nc]) {
            dfs(nr, nc, cnt);
        }
    }
    public static int[] dr = {1, -1, 0, 0};
    public static int[] dc = {0, 0, 1, -1};
}