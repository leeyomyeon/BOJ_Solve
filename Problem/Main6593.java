import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main6593 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int L, R, C;
    public static char[][][] arr;
    public static boolean[][][] visited;
    public static Queue<Point> queue;
    public static class Point {
        int l;
        int r;
        int c;
        int cnt;
        public Point(int l,int r, int c, int cnt){
            this.l = l;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0 && R == 0 && C == 0) {
                break;
            }
            arr = new char[L][R][C];
            visited = new boolean[L][R][C];
            Point start = new Point(0, 0, 0, 0);
            Point end = new Point(0, 0, 0, 0);
            for(int l = L - 1; l >= 0; l--) {   // 입력
                for(int r = 0; r < R; r++) {
                    String str = br.readLine();
                    for(int c = 0; c < C; c++) {
                        arr[l][r][c] = str.charAt(c);
                        if(arr[l][r][c] == 'S') {
                            start = new Point(l, r, c, 0);
                            arr[l][r][c] = '.';
                        } else if (arr[l][r][c] == 'E') {
                            end = new Point(l, r, c, 0);
                            arr[l][r][c] = '.';
                        }
                    }
                }
                br.readLine();
            }

            queue = new LinkedList<>();
            queue.add(start);
            visited[start.l][start.r][start.c] = true;
            int res = -1;
            while(!queue.isEmpty()) {
                Point current = queue.poll();
                if(current.l == end.l && current.r == end.r && current.c == end.c) {
                    res = current.cnt;
                    break;
                }

                for(int d = 0; d < 6; d++) {
                    int nl = current.l + dl[d];
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nl >= 0 && nl < L && nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nl][nr][nc] && arr[nl][nr][nc] == '.') {
                        queue.add(new Point(nl, nr, nc, current.cnt + 1));
                        visited[nl][nr][nc] = true;
                    }
                }
            }
            if(res == -1) {
                bw.write("Trapped!");
            } else {
                bw.write("Escaped in " + res + " minute(s).");
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dl = {-1, 1, 0, 0, 0, 0};
    public static int[] dr = {0, 0, -1, 1, 0 ,0};
    public static int[] dc = {0, 0, 0, 0, 1, -1};
}