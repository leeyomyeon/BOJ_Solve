import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main21610 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[][] arr;
    public static boolean[][] cloud;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        cloud = new boolean[N][N];
        deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 비바라기를 시전하면 (N-1, 0), (N-1, 1), (N-2, 0), (N-2, 1)에 비구름이 생긴다
        deque.add(new Point(N - 2, 0));
        deque.add(new Point(N - 2, 1));
        deque.add(new Point(N - 1, 0));
        deque.add(new Point(N - 1, 1));
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            move(d, s);
        }
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt += arr[i][j];
            }
        }
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void move(int d, int s) {
        int size = deque.size();
        for(int n = 0; n < size; n++) {
            Point current = deque.removeFirst();
            // 모든 구름이 di 방향으로 si칸 이동한다.
            int r = (current.r + N + ((dr[d] * s) % N)) % N;
            int c = (current.c + N + ((dc[d] * s) % N)) % N;
            cloud[r][c] = true;
            arr[r][c]++;
            deque.add(new Point(r, c));
        }
        while(!deque.isEmpty()) {
            // 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
            Point current = deque.removeFirst();
            for(int t = 0; t < 4; t++) {
                // 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다.
                // 물복사버그 마법을 사용하면,
                // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
                int nr = current.r + tr[t];
                int nc = current.c + tc[t];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && arr[nr][nc] > 0) {
                    arr[current.r][current.c]++;
                }
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!cloud[i][j] && arr[i][j] >= 2) {
                    deque.add(new Point(i, j));
                    arr[i][j] -= 2;
                }
                if(cloud[i][j]) {
                    cloud[i][j] = false;
                }
            }
        }
    }
    //              1부터 순서대로 ←, ↖,  ↑,  ↗,  →, ↘, ↓, ↙
    public static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] tr = {-1, -1, 1, 1};
    public static int[] tc = {-1, 1, 1, -1};
}