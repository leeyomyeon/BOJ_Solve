import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main21609 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, res;
    public static int[][] arr;
    public static class Point implements Comparable<Point> {
        int r, c, color, rCnt, bCnt;
        public Point(int r, int c, int color, int rCnt, int bCnt) {
            this.r = r;
            this.c = c;
            this.color = color;
            this.rCnt = rCnt;
            this.bCnt = bCnt;
        }
        @Override
        public int compareTo(Point o) {
            if(o.bCnt == this.bCnt) {
                if(o.rCnt == this.rCnt) {
                    if(o.r == this.r) {
                        return o.c - this.c;
                    }
                    return o.r - this.r;
                }
                return o.rCnt - this.rCnt;
            }
            return o.bCnt - this.bCnt;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = 0;
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        play();
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void play() {
        while (true) {
            Point current = find();
            if(current == null) {
                break;
            }
            remove(current.r, current.c);
            res += Math.pow(current.bCnt, 2);
            moveDown();
            rotate();
            moveDown();
        }
    }
    public static Point find() {
        boolean[][][] visited = new boolean[M + 1][N][N];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] >= 1 && !visited[arr[i][j]][i][j]) {
                    ArrayDeque<Point> deque = new ArrayDeque<>();
                    deque.add(new Point(i, j, arr[i][j], 0, 0));
                    visited[arr[i][j]][i][j] = true;
                    int rainbowCnt = 0;
                    int blockCnt = 1;
                    while(!deque.isEmpty()) {
                        Point current = deque.removeFirst();
                        for(int d = 0; d < 4; d++) {
                            int nr = current.r + dr[d];
                            int nc = current.c + dc[d];
                            if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[current.color][nr][nc] && (arr[nr][nc] == current.color || arr[nr][nc] == 0)) {
                                if(arr[nr][nc] == 0) rainbowCnt++;
                                blockCnt++;
                                visited[current.color][nr][nc] = true;
                                deque.add(new Point(nr, nc, current.color, 0, 0));
                            }
                        }
                    }
                    if(blockCnt > 1) {
                        pq.add(new Point(i, j, arr[i][j], rainbowCnt, blockCnt));
                    }
                }
            }
        }
        if(pq.isEmpty()) {
            return null;
        }
        return pq.poll();
    }
    public static void remove(int r, int c) {
        boolean[][] visited = new boolean[N][N];
        visited[r][c] = true;
        ArrayDeque<Point> deque = new ArrayDeque<>();
        Point start = new Point(r, c, arr[r][c], 0, 0);
        arr[r][c] = -2;
        deque.add(start);
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && (arr[nr][nc] == current.color || arr[nr][nc] == 0)) {
                    visited[nr][nc] = true;
                    arr[nr][nc] = -2;
                    deque.add(new Point(nr, nc, current.color, 0, 0));
                }
            }
        }
    }
    public static void rotate() {
        // 90도 반시계 회전
        int[][] tmp = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tmp[i][j] = arr[j][N - i - 1];
            }
        }
        arr = tmp;
    }
    /*
    00 01 02 03    03 13 23 33
    10 11 12 13 -> 02 12 22 32
    20 21 22 23    01 11 21 31
    30 31 32 33    00 10 20 30
    */
    public static void moveDown() {
        // -2 = 빈칸
        Stack<Integer> stack = new Stack<>();
        for(int j = 0; j < N; j++) {
            for(int i = 0; i < N; i++) {
                if(arr[i][j] >= 0) {
                    stack.add(arr[i][j]);
                    arr[i][j] = -2;
                }
                if(arr[i][j] >= -1) {
                    int size = stack.size();
                    for(int k = i - 1; k >= i - size; k--) {
                        arr[k][j] = stack.pop();
                    }
                }
                if(i == N - 1) {
                    int idx = N - 1;
                    while(!stack.isEmpty()) {
                        arr[idx][j] = stack.pop();
                        idx--;
                    }
                }
            }
        }
    }
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
}