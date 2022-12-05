import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main2146 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static boolean[][] visited;
    public static int[][] arr;
    public static int N, minLen;
    public static ArrayDeque<Point> deque, bridge;
    public static class Point {
        int r;
        int c;
        int len;
        int num;
        public Point(int r, int c, int len, int num) {
            this.r = r;
            this.c = c;
            this.len = len;
            this.num = num;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        minLen = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        deque = new ArrayDeque<>();
        // 구역 번호 붙이기
        int num = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && arr[i][j] != 0) {
                    setNumber(i, j, num);
                    num++;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // 육지 하나부터 탐색해서
                if(arr[i][j] != 0) {
                    visited = new boolean[N][N];
                    findBridge(i, j, arr[i][j]);
                    break;
                }
            }
        }
        bw.write(Integer.toString(minLen));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void findBridge(int r, int c, int num) {
        deque.add(new Point(r, c, 0, num));
        visited[r][c] = true;
        bridge = new ArrayDeque<>();
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if(arr[nr][nc] == num) {
                        deque.add(new Point(nr, nc, current.len, num));
                    } else if(arr[nr][nc] == 0) {
                        bridge.add(new Point(nr, nc, current.len + 1, num));
                    }
                }
            }
        }
        while(!bridge.isEmpty()) {
            Point current = bridge.removeFirst();
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if(arr[nr][nc] != num && arr[nr][nc] != 0) {
                        if(current.num != arr[nr][nc]) {
                            minLen = Math.min(minLen, current.len);
                        }
                        bridge.add(new Point(nr, nc, 0, arr[nr][nc]));
                    } else if(arr[nr][nc] == 0) {
                        bridge.add(new Point(nr, nc, current.len + 1, current.num));
                    }
                }
            }
        }
    }
    public static void setNumber(int r, int c, int num) {
        deque.add(new Point(r, c, 0, num));
        visited[r][c] = true;
        arr[r][c] = num;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && arr[nr][nc] != 0) {
                    arr[nr][nc] = num;
                    visited[nr][nc] = true;
                    deque.add(new Point(nr, nc, current.len, num));
                }
            }
        }
    }

    public static int[] dr = {-1, 1, 0 ,0};
    public static int[] dc = {0, 0, -1, 1};
}