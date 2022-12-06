import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14503 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C;
    public static int[][] arr;
    public static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        visited = new boolean[R][C];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        visited[r][c] = true;   // 현재 위치 청소
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        execute(r, c, d, 1);
    }
/*
앞   우            뒤           좌
d / (d + 1) % 4 /(d + 2) % 4 / (d + 3) % 4
 */
    public static void execute(int r, int c, int d, int cnt) throws Exception{
        Loop1:
        while(true) {
            if( (arr[r + dr[d]][c + dc[d]] == 1 || visited[r + dr[d]][c + dc[d]]) &&
                (arr[r + dr[(d + 1) % 4]][c + dc[(d + 1) % 4]] == 1 || visited[r + dr[(d + 1) % 4]][c + dc[(d + 1) % 4]]) &&
                arr[r + dr[(d + 2) % 4]][c + dc[(d + 2) % 4]] == 1 &&
                (arr[r + dr[(d + 3) % 4]][c + dc[(d + 3) % 4]] == 1 || visited[r + dr[(d + 3) % 4]][c + dc[(d + 3) % 4]])
            ) {
                bw.write(Integer.toString(cnt));
                br.close();
                bw.flush();
                bw.close();
                return;
            }
            // 4방향 확인
            for(int i = 1; i <= 4; i++) {
                int nd = (d + 4 - i) % 4;
                if(!visited[r + dr[nd]][c + dc[nd]] && arr[r + dr[nd]][c + dc[nd]] == 0) {  // 청소되지 않은 빈칸 발견했을 경우
                    d = nd;
                    visited[r + dr[d]][c + dc[d]] = true;
                    r += dr[d];
                    c += dc[d];
                    cnt++;
                    continue Loop1;
                }
            }
            // 빈칸 없거나 벽인경우 한 칸 후진한다
            r += dr[(d + 2) % 4];
            c += dc[(d + 2) % 4];
        }
    }
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
}
