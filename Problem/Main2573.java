import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2573 {
    public static int R;
    public static int C;
    public static boolean[][] visited;
    public static int[][] arr;
    public static Queue<Point> ice;
    public static Queue<Point> queue;
    public static class Point {
        int r;
        int c;
        int bound;
        public Point(int r, int c, int bound) {
            this.r = r;
            this.c = c;
            this.bound = bound;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                visited = new boolean[R][C];
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 0;
        while(true) {
            int bergCnt = 0;
            ice = new LinkedList<>();
            int bound = 0;
            for(int i = 0; i < R; i++) {      // 바다 면적에 경계면 개수 찾기
                for(int j = 0; j < C; j++) {
                    if(arr[i][j] != 0) {
                        bound = countBoundary(i, j);
                        ice.add(new Point(i, j, bound));
                    }
                }
            } 
            // 빙산 녹음
            while(!ice.isEmpty()) {
                Point current = ice.poll();
                arr[current.r][current.c] = (arr[current.r][current.c] - current.bound < 0) ? 0 : (arr[current.r][current.c] - current.bound);
            }
            // 빙산 갯수 찾기
            visited = new boolean[R][C];
            for(int i = 0; i < R; i++) {        
                for(int j = 0; j < C; j++) {
                    if(arr[i][j] != 0 && !visited[i][j]) {
                        findIceberg(i, j);
                        bergCnt++;
                    }
                }
            } 
            res++;
            if(bergCnt >= 2) {
                break;
            } else {
                boolean allMelt = true;
                for(int i = 0; i < R; i++) {        
                    for(int j = 0; j < C; j++) {
                        if(arr[i][j] != 0) {
                            allMelt = false;
                        }
                    }
                } 
                if(allMelt) {
                    res = 0;
                    break;
                }
            }
        }
        System.out.println(res);
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static int countBoundary(int r, int c) {
        int sum = 0;
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 0) {
                sum++;
            }
        }
        return sum;
    }

    public static void findIceberg(int r, int c) { // 빙산 찾기
        queue = new LinkedList<>();
        queue.add(new Point(r, c, 0));
        visited[r][c] = true;

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];

                if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && arr[nr][nc] != 0) {
                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc, 0));
                }
            }
        }
    }
}
/*
5 5
0 0 0 0 0
0 6 9 6 0
0 3 3 9 0
0 6 1 6 0
0 0 0 0 0

0 0 0 0 0
0 4 8 4 0
0 2 3 8 0
0 4 0 4 0
0 0 0 0 0

0 0 0 0 0
0 2 7 2 0
0 1 3 7 0
0 2 0 2 0
0 0 0 0 0

0 0 0 0 0
0 0 6 0 0
0 0 2 6 0
0 0 0 0 0
0 0 0 0 0

0 0 0 0 0
0 0 1 0 0
0 0 0 1 0
0 0 0 0 0
0 0 0 0 0
*/