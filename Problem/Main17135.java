import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main17135 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, D, count;
    public static int[] arc;
    public static int[][] arr, copyArr;
    public static boolean[][] visited;
    public static class Point implements Comparable<Point>{
        int r;
        int c;
        int dist;
        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
        @Override
        public int compareTo(Point o) {
            if(o.dist == this.dist) {
                return this.c - o.c;
            } else {
                return this.dist - o.dist;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M];
        arc = new int[3];
        count = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTrack(0, 0);
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void backTrack(int idx, int cnt) {
        if(cnt == 3) {
            // 0 ~ M - 1개의 칸 중 3개를 고르는 조합
            exec();
            return;
        }
        for(int i = idx; i < M; i++) {
            arc[cnt] = i;
            backTrack(i + 1, cnt + 1);
        }
    }
    public static int[] dr = {-1, -1, -1};
    public static int[] dc = {0, -1, 1};
    public static void exec() {
        copy();
        int tmpCnt = 0;
        int up = 0;
        while(up <= N) {
            Queue<Point> queue = new LinkedList<>();
            LinkedList<Point> enemy = new LinkedList<>();
            // 사거리 내에 위치한 적 담는 리스트
            LinkedList<Point> delEnemy = new LinkedList<>();
            // 그 중 가장 가깝고 가까운 적이 여러명일시 가장 왼쪽인 적의 좌표를 저장할 리스트
            for(int o = 0; o < 3; o++) {
                queue.add(new Point (N - up, arc[o], 0));
                visited = new boolean[N + 1][M];
                visited[N - up][arc[o]] = true;
                int minDist = D; // 최소 거리 저장(최소 거리보다 크다면 탐색할 필요 없음)
                while(!queue.isEmpty()) { // 잡을수 있는 적 탐색
                    Point current = queue.poll();
                    for(int d = 0; d < 3; d++) {
                        int nr = current.r + dr[d];
                        int nc = current.c + dc[d];
                        int dist = Math.abs(N - up - nr) + Math.abs(arc[o] - nc); // 거리 계산
                        if(nr >= 0 && nc >= 0 && nc < M && !visited[nr][nc] && dist <= minDist) {
                            queue.add(new Point(nr, nc, dist));
                            visited[nr][nc] = true;
                            if(copyArr[nr][nc] == 1) {  // 적이 있을 시 최소 거리값 갱신
                                minDist = Math.min(dist, minDist);
                                enemy.add(new Point(nr, nc, dist));
                            }
                        }
                    }
                }
                if(enemy.size() > 0) {
                    // 사거리 내에 포함된 적들을 조건에 맞게 정렬하고 맨 앞에 적을 따로 저장
                    Collections.sort(enemy);
                    delEnemy.add(enemy.get(0));
                    enemy.clear();
                }
            }
            // 적 한번에 죽이고
            while(!delEnemy.isEmpty()) {
                Point current = delEnemy.poll();
                if(copyArr[current.r][current.c] == 1) { // 동시에 같은 적 때릴 때 카운트 방지
                    tmpCnt++;
                    copyArr[current.r][current.c] = 0;
                }
            }
            up++; // 적들이 올라가는게 아닌 궁수가 올라감
        }

        count = Math.max(tmpCnt, count);
    }
//    public static boolean check() {
//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < M; j++) {
//                if(copyArr[i][j] == 1) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    public static void copy() {
        copyArr = new int[N + 1][M];
        for(int i = 0; i <= N; i++) {
            copyArr[i] = Arrays.copyOf(arr[i], M);
        }
    }
}