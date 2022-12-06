import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main18809 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] arr;
    public static boolean[][] isOk;
    public static int N, M, G, R, MAX, idx;
    public static ArrayList<Point> oppr;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, time;
        char type;
        void setType(char type) {
            this.type = type;
        }
        public Point(int r, int c, char type, int time) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        isOk = new boolean[N][M];
        MAX = Integer.MIN_VALUE;
        idx = 0;
        oppr = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2) {
                    idx++;
                    oppr.add(new Point(i, j, 'X', 0));
                }
            }
        }
        dfs(0, R, G);
        bw.write(Integer.toString(MAX));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dfs(int next, int remainR, int remainG) {
        if(next == idx) {
            if(remainR == 0 && remainG == 0) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < idx; i++) {
                    sb.append(oppr.get(i).type).append(" ");
                }
                System.out.println(sb);
                bfs();
            }
            return;
        }
        if(remainR > 0) {
            oppr.get(next).setType('R');
            dfs(next + 1, remainR - 1, remainG);
        }
        if(remainG > 0) {
            oppr.get(next).setType('G');
            dfs(next + 1, remainR, remainG - 1);
        }
        oppr.get(next).setType('X');
        dfs(next + 1, remainR, remainG);
    }
    public static void bfs() {
        int[][] visitedR = new int[N][M];
        int[][] visitedG = new int[N][M];
        deque = new ArrayDeque<>();
        for(Point p : oppr) {   // R = 3 G = 4
            if(p.type != 'X') {
                deque.add(p);
                visitedR[p.r][p.c] = -1;
                visitedG[p.r][p.c] = -1;
            }
        }
        int cnt = 0;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(visitedR[current.r][current.c] == -2 && visitedG[current.r][current.c] == -2) {
                continue;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && (arr[nr][nc] == 1 || arr[nr][nc] == 2)) {
                    if(current.type == 'R' && visitedR[nr][nc] == 0 && visitedG[nr][nc] == 0) { // 빈 땅일때
                        visitedR[nr][nc] = current.time + 1;
                        deque.add(new Point(nr, nc, current.type, current.time + 1));
                    } else if(current.type == 'G' && visitedR[nr][nc] == 0 && visitedG[nr][nc] == 0) { // 빈 땅일때
                        visitedG[nr][nc] = current.time + 1;
                        deque.add(new Point(nr, nc, current.type, current.time + 1));
                    } else if(current.type == 'R' && visitedG[nr][nc] == current.time + 1) {
                        visitedR[nr][nc] = -2;
                        visitedG[nr][nc] = -2;
                        cnt++;
                    } else if(current.type == 'G' && visitedR[nr][nc] == current.time + 1) {
                        visitedR[nr][nc] = -2;
                        visitedG[nr][nc] = -2;
                        cnt++;
                    }
                }
            }
        }
        MAX = Math.max(cnt, MAX);
    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}