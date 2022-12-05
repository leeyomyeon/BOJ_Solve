import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1261 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int R, C;
    public static char[][] arr;
    public static boolean[][] visited;
    public static ArrayDeque<Point> deque;
    public static Queue<Point> queue;
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
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new char[R + 1][C + 1];
        deque = new ArrayDeque<>();
        queue = new LinkedList<>();
        visited = new boolean[R + 1][C + 1];
        Point start = new Point(1, 1);
        for(int i = 1; i <= R; i++) {
            String str = br.readLine();
            for(int j = 1; j <= C; j++) {
                arr[i][j] = str.charAt(j - 1);
            }
        }
        arr[R][C] = '*';
        visited[start.r][start.c] = true;
        queue.add(start);
        int cnt = 0;
        Loop1:
        while(true) {
            while(!queue.isEmpty()) {
                Point current = queue.poll();
                if(arr[current.r][current.c] == '*') {
                    break Loop1;
                }
                for(int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[nr][nc]) {
                        if(arr[nr][nc] == '0') {
                            visited[nr][nc] = true;
                            queue.add(new Point(nr, nc));
                        } else if (arr[nr][nc] == '1') {
                            visited[nr][nc] = true;
                            deque.add(new Point(nr, nc));
                        } else if (arr[nr][nc] == '*') {
                            break Loop1;
                        }
                    }
                }
            }
            while(!deque.isEmpty()) {
                Point e = deque.removeFirst();
                arr[e.r][e.c] = '0';
                queue.add(e);
            }
            cnt++;
        }

        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1 ,1};
}