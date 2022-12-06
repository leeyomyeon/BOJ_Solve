import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2206 {
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][][] visited;
    static int min;
    public static class Move {
        int p;
        int q;
        int count;
        boolean isBreak;
        public Move(int i, int j, int count, boolean isBreak){
            this.p = i;
            this.q = j;
            this.count = count;
            this.isBreak = isBreak;
        }
    }

    static int[] dp = {-1, 1, 0, 0};
    static int[] dq = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = sb.charAt(j) - '0';
            }
        }

        visited = new boolean[n][m][2];
        Queue<Move> queue = new LinkedList<>();

        queue.add(new Move(0, 0, 1, false));
        visited[0][0][0] = true;

        min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Move move = queue.poll();
            if(move.p == n - 1 && move.q == m - 1) {
                if(move.count < min) {
                    min = move.count;
                }
                break;
            }

            for(int i = 0; i < 4; i++) {
                int np = move.p + dp[i];
                int nq = move.q + dq[i];

                if(np < 0 || nq < 0 || np >= n || nq >= m) {
                    continue;
                }
                if(visited[np][nq][move.isBreak ? 1 : 0]) {
                    continue;
                }
                if(arr[np][nq] == 0) {
                    visited[np][nq][move.isBreak ? 1 : 0] = true;
                    queue.add(new Move(np, nq, move.count + 1, move.isBreak));
                }
                if(arr[np][nq] == 1 && !move.isBreak) {
                    visited[np][nq][1] = true;
                    queue.add(new Move(np, nq, move.count + 1, true));
                }
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
