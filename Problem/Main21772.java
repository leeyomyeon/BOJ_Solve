import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main21772 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, T, startR, startC, MAX;
    public static char[][] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == 'G') {
                    startR = i;
                    startC = j;
                }
            }
        }
        MAX = Integer.MIN_VALUE;
        backTrack(startR, startC, 0, 0);
        bw.write(Integer.toString(MAX));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void backTrack(int r, int c, int cnt, int idx) {
        if(idx == T) {
            MAX = Math.max(cnt, MAX);
            return;
        }

        for(int d = 0; d < 5; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != '#') {
                if(arr[nr][nc] == 'S') {
                    arr[nr][nc] = '.';
                    backTrack(nr, nc, cnt + 1, idx + 1);
                    arr[nr][nc] = 'S';
                } else {
                    backTrack(nr, nc, cnt, idx + 1);
                }
            }
        }
    }
    public static int[] dr = {-1, 1, 0, 0, 0};
    public static int[] dc = {0, 0, -1, 1, 0};
}