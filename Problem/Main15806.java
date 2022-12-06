import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main15806 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static boolean[][][] visited;
    public static Queue<Point> queue;
    public static int N, M, K, T;
    public static class Point {
        int cnt;
        int r;
        int c;
        public Point(int cnt, int r, int c) {
            this.cnt = cnt;
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        visited = new boolean[2][N + 1][N + 1];
        queue = new LinkedList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            queue.add(new Point(0, r, c));
        }
        for(int t = 0; t < T; t++) {
            int size = queue.size();
            for(int s = 0; s < size; s++) {
                Point current = queue.poll();
                for(int d = 0; d < 8; d++) {
                    int nCnt = (current.cnt + 1) % 2;
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr > 0 && nr <= N && nc > 0 && nc <= N && !visited[nCnt][nr][nc]) {
                        visited[nCnt][nr][nc] = true;
                        queue.add(new Point(nCnt, nr, nc));
                    }
                }
            }
        }
        boolean isOK = false;
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(visited[T % 2][r][c]) {
                isOK = true;
                break;
            }
        }
        bw.write(isOK ? "YES" : "NO");
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {-2, -2, -1, 1, 2, 2, 1, -1};
    public static int[] dc = {-1, 1, 2, 2, 1, -1, -2, -2};
}