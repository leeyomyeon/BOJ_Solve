import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2583 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[][] arr;
    public static boolean[][] visited;
    public static int M, N, K;
    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static int[] dr = {-1, 1, 0 ,0};
    public static int[] dc = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new boolean[M][N];
        visited = new boolean[M][N];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(st.nextToken());  // 4
            int startN = Integer.parseInt(st.nextToken());  // 0
            int endM = Integer.parseInt(st.nextToken());    // 6
            int endN = Integer.parseInt(st.nextToken());    // 2
            for(int r = startN; r < endN; r++) {
                for(int c = startM; c < endM; c++) {
                    if(!arr[r][c]) {
                        arr[r][c] = true;
                    }
                }
            }
        }
        int cnt = 0;
        LinkedList<Integer> list = new LinkedList<>();
        Queue<Point> queue = new LinkedList<>();
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && !arr[i][j]) {
                    queue.add(new Point(i, j));
                    int sum = 1;
                    while(!queue.isEmpty()) {
                        Point current = queue.poll();
                        visited[current.r][current.c] = true;
                        for(int d = 0; d < 4; d++) {
                            int nr = current.r + dr[d];
                            int nc = current.c + dc[d];
                            if(nr >= 0 && nr < M && nc >= 0 && nc < N && !visited[nr][nc] && !arr[nr][nc]) {
                                visited[nr][nc] = true;
                                sum++;
                                queue.add(new Point(nr, nc));
                            }
                        }
                    }
                    list.add(sum);
                    cnt++;
                }
            }
        }
        Collections.sort(list);
        bw.write(Integer.toString(cnt));
        bw.newLine();
        for(int s : list) {
            bw.write(s + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}