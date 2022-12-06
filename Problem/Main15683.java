import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
public class Main15683 {
    static int N, M;
    static int[][] arr;
    static List<CCTV> list;
    static int[] number;
    static int min = Integer.MAX_VALUE;
    static class CCTV {
        int r;
        int c;
        int status;

        public CCTV(int r, int c, int status) {
            this.r = r;
            this.c = c;
            this.status = status;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        list = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] >= 1 && arr[i][j] <= 5) {
                    list.add(new CCTV(i, j, arr[i][j]));
                }
            }
        }

        number = new int[list.size()];
        dfs(0);
        System.out.println(min);
    }

    public static void dfs(int cnt) {
        if(cnt == list.size()) {
            bfs(number);
            return;
        }

        for(int i = 0; i < 4; i++) {
            number[cnt] = i;
            dfs(cnt + 1);
        }
    }
    static int[] cr = {-1, 1, 0, 0};
    static int[] cc = {0, 0, -1, 1};
    static int[][][] cdir = {
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 3}, {3, 1}, {1, 2}, {2, 0}},
            {{2, 0, 3}, {0, 3, 1}, {3, 1, 2}, {1, 2, 0}},
            {{0, 1, 2, 3}}
    };
    public static void bfs(int[] number) {
        for(int i = 0; i < list.size();i++) {
            CCTV current = list.get(i);
            int r = number[i];
            if(current.status == 2) {
                r = number[i] % 2;
            } else if (current.status == 5) {
                r = 0;
            }
            for(int d = 0; d < cdir[current.status - 1][r].length; d++) {
                int nr = current.r;
                int nc = current.c;
                int dir = cdir[current.status - 1][r][d];

                while (true) {
                    nr += cr[dir];
                    nc += cc[dir];
                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || arr[nr][nc] == 6) {
                        break;
                    }
                    if(arr[nr][nc] == 0) {
                        arr[nr][nc] = 7;
                    }
                }
            }
        }
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 0) {
                    count++;
                } else if (arr[i][j] == 7) {
                    arr[i][j] = 0;
                }
            }
        }
        min = Math.min(min, count);
    }
}
