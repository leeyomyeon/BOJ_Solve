import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1799 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int[][] arr;
    public static int[] res;
    public static boolean[] hor, ver;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        res = new int[2];
        hor = new boolean[N * 2];   // /방향
        ver = new boolean[N * 2];   // \방향
        dfs(0, 0, 0, 0);
        dfs(0, 1, 0, 1);

        bw.write(Integer.toString(res[0] + res[1]));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int r, int c, int cnt, int color) {
        if(c >= N) {
            r += 1;
            c = c % 2 == 0 ? 1 : 0;
        }
        if (r >= N) {
            res[color] = Math.max(res[color], cnt);
            return;
        }

        if(arr[r][c] == 1 && !hor[r + c] && !ver[N - r + c - 1]) {
            hor[r + c] = true;
            ver[N - r + c - 1] = true;
            dfs(r, c + 2, cnt + 1, color);
            hor[r + c] = false;
            ver[N - r + c - 1] = false;
        }
        dfs(r, c + 2, cnt, color);
    }
}