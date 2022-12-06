import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14500 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int[][] arr;
    public static int N, M, anMax, arrMax;
    public static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        anMax = Integer.MIN_VALUE;
        arrMax = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                arrMax = Math.max(arrMax, arr[i][j]);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                bfs(i, j, arr[i][j], 1, new StringBuilder(arr[i][j] + " "), new StringBuilder(i+","+ j+"/"));
                visited[i][j] = false;
            }
        }
        bw.write(Integer.toString(anMax));
        br.close();
        bw.flush();
        bw.close();
    }
    public static char[][] checkArr;
    public static int[] dr = {1, 0, -1, 0};
    public static int[] dc = {0, 1, 0, -1};
    public static void bfs(int r, int c, int next, int cnt, StringBuilder sb, StringBuilder sb2) throws Exception {
        if(anMax >= next + arrMax * (4 - cnt)) {
            return;
        }
        if(cnt == 4) {
            checkArr = new char[N][M];
            anMax = Math.max(next, anMax);
            return;
        }
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                StringBuilder nsb = new StringBuilder(sb).append(arr[nr][nc]).append(" ");
                StringBuilder nsb2 = new StringBuilder(sb2).append(nr).append(",").append(nc).append("/");
                if(cnt == 2) {
                    visited[nr][nc] = true;
                    bfs(r, c, next + arr[nr][nc], cnt + 1, nsb, nsb2);
                    visited[nr][nc] = false;
                }
                visited[nr][nc] = true;
                bfs(nr, nc, next + arr[nr][nc], cnt + 1, nsb, nsb2);
                visited[nr][nc] = false;
            }
        }
    }
}