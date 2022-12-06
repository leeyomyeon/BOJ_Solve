import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main16956 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C;
    public static char[][] arr;
    public static boolean[][] visited;
    public static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        ArrayDeque<Point> deque = new ArrayDeque<>();
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == 'W') {
                    deque.add(new Point(i, j));
                }
            }
        }
        boolean res = false;
        Loop1:
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
                    if(arr[nr][nc] == '.') {
                        arr[nr][nc] = 'D';
                    }
                    if(arr[nr][nc] == 'S') {
                        res = true;
                        break Loop1;
                    }
                }
            }
        }
        if(res) {
            bw.write("0");
        } else {
            bw.write("1\n");
            for(char[] a : arr) {
                for(char b : a) {
                    bw.write(b);
                }
                bw.newLine();
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}