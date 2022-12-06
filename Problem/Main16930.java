import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main16930 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static char[][] arr;
    public static int[][] visited;
    public static int N, M, K, endR, endC, res;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N + 2][M + 2];
        visited = new int[N + 2][M + 2];
        for(int i = 1; i <= N; i++) {
            String str = br.readLine();
            for(int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1);
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        endR = Integer.parseInt(st.nextToken());
        endC = Integer.parseInt(st.nextToken());
        Point start = new Point(startR, startC, 0);
        visited[startR][startC] = 0;
        deque = new ArrayDeque<>();
        res = -1;
        deque.add(start);
        bfs();
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void bfs() {
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            for(int d = 0; d < 4; d++) {    // 방향 정하고
                for(int k = 1; k <= K; k++) { // 최소 1개 부터 K개 까지 이동
                    int nr = current.r + dr[d] * k;
                    int nc = current.c + dc[d] * k;
                    if(nr <= 0 || nc <= 0 || nr > N || nc > M) {
                        break;  // 경계를 넘거나 벽일 땐 더 할 필요 없음
                    }
                    if(arr[nr][nc] == '#') {
                        break;
                    }
                    if(nr == endR && nc == endC) { // 목적지 만나면 끝
                        res = current.cnt + 1;
                        return;
                    }
                    // 내가 온 방향, 왔던 방향을 제외한 방향이 빈 공간이면 큐에 넣음
                    int sr = nr + dr[(d + 1) % 4];
                    int sc = nc + dc[(d + 1) % 4];
                    int kr = nr + dr[(d + 3) % 4];
                    int kc = nc + dc[(d + 3) % 4];
                    // 상하좌우로 1칸씩 padding을 해서 경계체크를 할 필요 없음.
                    if(visited[nr][nc] == Integer.MAX_VALUE && arr[nr][nc] == '.') {
                        if(arr[sr][sc] == '.' || arr[kr][kc] == '.' || k == K) {
                            visited[nr][nc] = current.cnt + 1;
                            deque.add(new Point(nr, nc, current.cnt + 1));
                        }
                    } else if(visited[nr][nc] <= current.cnt) {
                        // 지금보다 더 갈 필요가 없음.
                        break;
                    }
                }
            }
        }
    }
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    /*
     0
    3 1
     2

    0일땐 1, 3
    1일땐 0, 2
    2일땐 1, 3
    3일땐 0, 2

    (d + 1) % 4
    (d + 3) % 4
    */
}