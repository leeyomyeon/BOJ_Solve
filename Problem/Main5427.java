import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main5427 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int R, C;
    public static char[][] arr;
    public static boolean[][] visited, fireVisited;
    public static Queue<Point> queue;
    public static Deque<Point> fireQueue;
    public static class Point {
        int r;
        int c;
        int cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            arr = new char[R + 2][C + 2];
            visited = new boolean[R + 2][C + 2];
            fireVisited = new boolean[R + 2][C + 2];
            queue = new LinkedList<>();
            fireQueue = new ArrayDeque<>();
            for(int i = 1; i <= R; i++) {
                String str = br.readLine();
                for(int j = 1; j <= C; j++) {
                    char c = str.charAt(j - 1);
                    if(c == '@') {
                        c = 'J';
                    } else if (c == '*') {
                        c = 'F';
                    }
                    if(c == 'J') {
                        queue.add(new Point(i, j, 0));
                        visited[i][j] = true;
                        arr[i][j] = '.';
                        continue;
                    }
                    if(c == 'F') {
                        fireQueue.add(new Point(i, j, 0));
                        fireVisited[i][j] = true;
                        for(int d = 0; d < 4; d++) {
                            int ni = i + dr[d];
                            int nj = j + dc[d];
                            if(ni >= 1 && ni <= R && nj >= 1 && nj <= C && arr[ni][nj] != '#' && arr[ni][nj] != 'J' && !fireVisited[ni][nj]) {
                                arr[ni][nj] = 'f';
                            }
                        }
                    }
                    arr[i][j] = c;
                }
            }
            String res = "IMPOSSIBLE";
            Loop1:
            while(true) {
                int q = queue.size();
                if(q == 0) {
                    break;
                }
                for(int i = 0; i < q; i++) {
                    Point current = queue.poll();
                    for(int d = 0; d < 4; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];
        
                        if(nr >= 0 && nr <= R + 1 && nc >= 0 && nc <= C + 1 && !visited[nr][nc] && arr[nr][nc] != '#' && arr[nr][nc] != 'F' && !fireVisited[nr][nc] && arr[nr][nc] != 'f') {
                            if(nr == 0 || nr == R + 1 || nc == 0 || nc == C + 1) {
                                res = Integer.toString(current.cnt + 1);
                                break Loop1;
                            }
                            queue.add(new Point(nr, nc, current.cnt + 1));
                            arr[nr][nc] = 'J';
                            visited[nr][nc] = true;
                        }
                    }
                }
                int s = fireQueue.size();
                for(int i = 0; i < s; i++) {
                    Point fire = fireQueue.poll();
    
                    for(int d = 0; d < 4; d++) {
                        int nr = fire.r + dr[d];
                        int nc = fire.c + dc[d];
    
                        if(nr >= 1 && nr <= R && nc >= 1 && nc <= C && !fireVisited[nr][nc] && arr[nr][nc] != '#') {
                            fireQueue.add(new Point(nr, nc, 0));
                            fireVisited[nr][nc] = true;
                            arr[nr][nc] = 'F';
                            for(int dd = 0; dd < 4; dd++) {
                                int nnr = nr + dr[dd];
                                int nnc = nc + dc[dd];
                                if(nnr >= 1 && nnr <= R && nnc >= 1 && nnc <= C && !fireVisited[nnr][nnc] && arr[nnr][nnc] != '#') {
                                    arr[nnr][nnc] = 'f';
                                }
                            }
                        }
                    }
                }
            }
            bw.write(res);
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}