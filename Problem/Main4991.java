import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main4991 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, N, MIN;
    public static char[][] arr;
    public static ArrayDeque<Point> deque;
    public static ArrayList<Point> clean;
    public static boolean[][] visited;
    public static boolean[] selected;
    public static int[] selectIdx;
    public static int[][] dp;
    public static StringBuilder sb;
    public static class Point {
        int r, c, move;
        public Point(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        Loop1:
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            if(R == 0 && C == 0) {
                break;
            }
            arr = new char[R][C];
            clean = new ArrayList<>();
            int next = 0;
            Point start = new Point(-1, -1, 0);
            for(int i = 0; i < R; i++) {
                String str = br.readLine();
                for(int j = 0; j < C; j++) {
                    arr[i][j] = str.charAt(j);
                    if(arr[i][j] == 'o') { // 시작점
                        arr[i][j] = '.';
                        start = new Point(i, j, 0);
                    }
                    if(arr[i][j] == '*') { // 청소할 것 카운트
                        arr[i][j] = (char) ('0' + next);
                        clean.add(new Point(i, j, 0));
                        next++;
                    }
                }
            }
            N = clean.size();
            // 전처리
            dp = new int[N + 1][N + 1];
            for(int i = N; i >= 0; i--){
                Point p = i == N ? start : clean.get(i);
                visited = new boolean[R][C];
                visited[p.r][p.c] = true;
                deque = new ArrayDeque<>();
                deque.add(p);
                int cnt = i == N ? 0 : 1;
                while(!deque.isEmpty()) {
                    Point current = deque.removeFirst();

                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];
                        if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && arr[nr][nc] != 'x') {
                            visited[nr][nc] = true;
                            if('0' <= arr[nr][nc] && arr[nr][nc] <= '9') {
                                cnt++;
                                dp[i][arr[nr][nc] - '0'] = current.move + 1;
                            }
                            deque.add(new Point(nr, nc, current.move + 1));
                        }
                    }
                }
                if(cnt != N) {
                    sb.append("-1").append("\n");
                    continue Loop1;
                }
            }
            selected = new boolean[N];
            selectIdx = new int[N];
            MIN = Integer.MAX_VALUE;
            permutation(0);
            sb.append(MIN).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static void permutation(int idx) {
        if(idx == N) {
            int sum = dp[N][selectIdx[0]];
            for(int i = 0; i < N - 1; i++) {
                sum += dp[selectIdx[i]][selectIdx[i + 1]];
            }
            MIN = Math.min(MIN, sum);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!selected[i]) {
                selected[i] = true;
                selectIdx[idx] = i;
                permutation(idx + 1);
                selected[i] = false;
            }
        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    /*
    0 1 2 3 4 5 6 7 8 9
    0 9 8 7 6 5 4 3 2 1

    9 8 7 6 5 4 3 2 1 0
    1 2 3 4 5 6 7 8 9 0
     */
}