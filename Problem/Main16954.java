import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main16954 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static char[][][] arr;
    public static boolean[][][] visited;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c, cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static String empty = "........";
    public static void main(String[] args) throws Exception {
        arr = new char[9][8][8];
        for(int i = 0; i < 8; i++) {
            arr[0][i] = br.readLine().toCharArray();
            arr[8][i] = empty.toCharArray();
        }
        for(int i = 1; i < 8; i++) {
            for(int j = 0; j < i; j++) {
                arr[i][j] = empty.toCharArray();
            }
            System.arraycopy(arr[i - 1], i - 1, arr[i], i, 8 - i);
        }
        deque = new ArrayDeque<>();
        visited = new boolean[9][8][8];
        Point start = new Point(7, 0, 0);
        deque.add(start);
        int res = 0;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.r == 0 && current.c == 7) {
                res = 1;
                break;
            }
            for(int d = 0; d < dr.length; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < 8 && nc >= 0 && nc < 8
                    && arr[current.cnt >= 9 ? 8 : current.cnt][nr][nc] == '.'
                    && arr[current.cnt + 1 >= 9 ? 8 : current.cnt + 1][nr][nc] == '.'
                    && !visited[current.cnt + 1 >= 9 ? 8 : current.cnt + 1][nr][nc]
                ) {
                    visited[current.cnt + 1 >= 9 ? 8 : current.cnt + 1][nr][nc] = true;
                    deque.add(new Point(nr, nc, current.cnt + 1));
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    public static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
}