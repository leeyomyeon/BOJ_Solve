import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1987 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[] isChar;
    public static boolean[][] visited;
    public static char[][] arr;
    public static int R, C;
    public static int count, res;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        isChar = new boolean[26];
        visited = new boolean[R][C];
        arr = new char[R][C];

        for(int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        res = 1;
        visited[0][0] = true;
        isChar[arr[0][0] - 'A'] = true;
        count = 1;

        dfs(0, 0);

        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void dfs(int r, int c) {
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && !isChar[arr[nr][nc] - 'A']) {
                count++;
                visited[nr][nc] = true;
                isChar[arr[nr][nc] - 'A'] = true;
                dfs(nr, nc);
                res = Math.max(count, res);
                count--;
                visited[nr][nc] = false;
                isChar[arr[nr][nc] - 'A'] = false;
            }
        }
    }
}