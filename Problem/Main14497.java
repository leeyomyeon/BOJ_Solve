import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14497 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new char[R + 1][C + 1];
        deque = new ArrayDeque<>();
        queue = new LinkedList<>();
        visited = new boolean[R + 1][C + 1];
        Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for(int i = 1; i <= R; i++) {
            String str = br.readLine();
            for(int j = 1; j <= C; j++) {
                arr[i][j] = str.charAt(j - 1);
            }
        }
        visited[start.r][start.c] = true;
        queue.add(start);
        int cnt = 1;
        Loop1:
        while(true) {
            while(!queue.isEmpty()) {
                Point current = queue.poll();

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
                        } else if (arr[nr][nc] == '#') {
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