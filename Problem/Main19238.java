import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main19238 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, K, startR, startC, findPass, destCnt;
    public static int[][] arr, passenger;
    public static boolean[][] visited;
    public static PriorityQueue<Point> pq;
    public static ArrayDeque<Point> deque;
    public static class Point implements Comparable<Point> {
        int idx;
        int r;
        int c;
        int dist;
        public Point(int idx, int r, int c, int dist) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
        @Override
        public int compareTo(Point o) {
            if(o.dist == this.dist) {
                if(o.r == this.r) {
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.dist - o.dist;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        destCnt = M;
        passenger = new int[M + 1][4];
        arr = new int[N + 1][N + 1];
        findPass = 0;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()) == 1 ? -1 : 0;
            }
        }
        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken());
        startC = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작R 시작C 끝R 끝C
            passenger[i][0] = Integer.parseInt(st.nextToken());
            passenger[i][1] = Integer.parseInt(st.nextToken());
            passenger[i][2] = Integer.parseInt(st.nextToken());
            passenger[i][3] = Integer.parseInt(st.nextToken());
            arr[passenger[i][0]][passenger[i][1]] = i;
        }

        while(true) {
            Point current = find();
            if(current == null) {
                break;
            }
            move(current);
        }
        if(M != 0 || findPass != destCnt) {
            K = -1;
        }
        bw.write(Integer.toString(K));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void move(Point start) {
        startR = start.r;
        startC = start.c;
        arr[start.r][start.c] = 0;
        K -= start.dist;
        if(K <= 0) {
            K = -1;
            return;
        }
        deque = new ArrayDeque<>();
        visited = new boolean[N + 1][N + 1];
        deque.add(new Point(start.idx, startR, startC, 0));
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.r == passenger[current.idx][2] && current.c == passenger[current.idx][3]) {
                findPass++;
                K -= current.dist;
                if(K < 0) {
                    K = -1;
                    return;
                }
                startR = current.r;
                startC = current.c;
                K += current.dist * 2;
                break;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= N && nc > 0 && nc <= N && !visited[nr][nc] && arr[nr][nc] != -1) {
                    visited[nr][nc] = true;
                    deque.add(new Point(current.idx, nr, nc, current.dist + 1));
                }
            }
        }
    }
    public static Point find() {
        pq = new PriorityQueue<>();
        deque = new ArrayDeque<>();
        deque.add(new Point(0, startR, startC, 0));
        visited = new boolean[N + 1][N + 1];
        visited[startR][startC] = true;
        int passCnt = 0;
        if(arr[startR][startC] > 0) {
            passCnt++;
            pq.add(new Point(arr[startR][startC], startR, startC, 0));
        }
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(passCnt == M) {
                break;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr > 0 && nr <= N && nc > 0 && nc <= N && !visited[nr][nc] && arr[nr][nc] != -1) {
                    // idx, r, c, dist
                    visited[nr][nc] = true;
                    if(arr[nr][nc] > 0) {
                        passCnt++;
                        pq.add(new Point(arr[nr][nc], nr, nc, current.dist + 1));
                    }
                    deque.add(new Point(0, nr, nc, current.dist + 1));
                }
            }
        }
        if(pq.size() == 0) {
            return null;
        } else {
            M--;
            return pq.poll();
        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}