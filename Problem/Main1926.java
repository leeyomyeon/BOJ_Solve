import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1926 {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;

    static int count;
    static int maxArea;
    static int area;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;
        maxArea = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    area = 1;
                    visited[i][j] = true;
                    find(i, j);
                    if(area > maxArea) {
                        maxArea = area;
                    }
                    count++;
                }
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }

    public static void find(int r, int c) {
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nc >= 0 && nr < N && nc < M && !visited[nr][nc] && arr[nr][nc] == 1) {
                visited[nr][nc] = true;
                area++;
                find(nr, nc);
            }
        }
    }
}
