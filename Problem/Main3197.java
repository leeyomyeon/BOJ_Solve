import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main3197 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[][] visited;
    public static boolean[][] iceVisited;
    public static char[][] arr;
    public static int R, C;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, 1, -1};
    public static Deque<Point> nextDq, queue, moveQueue;

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
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        nextDq = new ArrayDeque<>();    // 얼음이 녹을 곳 저장
        queue = new ArrayDeque<>();     // 시작위치
        moveQueue = new ArrayDeque<>();
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'L') {
                    arr[i][j] = '.';
                    queue.add(new Point(i, j));
                }
            }
        }
        iceVisited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(arr[i][j] == '.') {
                    for(int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] == 'X' && !iceVisited[nr][nc]) {
                            iceVisited[nr][nc] = true;
                            arr[nr][nc] = 'x';
                            nextDq.add(new Point(nr, nc));
                        }
                    }
                }
            }
        }
        
        int cnt = 0;
        Point to = queue.pollLast();
        visited = new boolean[R][C];
        
        Loop1:
        while(true) {
            cnt++;
            int k = nextDq.size();
            for(int i = 0; i < k; i++) { // 다음에 녹을 빙판 큐에 넣어둠
                Point current = nextDq.poll();
                arr[current.r][current.c] = '.';
                for(int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr >= 0 && nr < R && nc >= 0 && nc < C && !iceVisited[nr][nc] && arr[nr][nc] == 'X') {
                        nextDq.add(new Point(nr, nc));
                        iceVisited[nr][nc] = true;
                        arr[nr][nc] = 'x';
                    }
                }
            }

            while(!queue.isEmpty()) {
                Point current = queue.poll();
                if(current.r == to.r && current.c == to.c) {
                    break Loop1;
                }
                visited[current.r][current.c] = true;
                for(int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != 'X' && !visited[nr][nc]) {
                        if(arr[nr][nc] == 'x') {
                            moveQueue.add(new Point(nr, nc));
                        } else {
                            queue.add(new Point(nr, nc));
                        }
                        visited[nr][nc] = true;
                    }
                }
            }
            while(!moveQueue.isEmpty()) {
                queue.add(moveQueue.poll());
            }
            moveQueue.clear();
        }

        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}