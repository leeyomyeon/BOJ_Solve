import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main17780 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, K, turn;
    public static ArrayDeque<Integer>[][] arr;
    public static int[][] map;
    public static Point[] points;
    public static class Point {
        int r, c;
        int num;
        int dir;
        void setR (int r){
            this.r = r;
        }
        void setC (int c) {
            this.c = c;
        }
        void setDir(int dir) {
            this.dir = dir;
        }
        public Point(int r, int c, int num, int dir) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new ArrayDeque[N][N];
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = new ArrayDeque<>();
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        points = new Point[K + 1];
        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            points[i] = new Point(r, c, i, d);
            arr[r][c].add(i);
        }
        execute();
        bw.write(Integer.toString(turn));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void execute() {
        turn = 1;
        Loop1:
        while(true) {
            if(turn >= 1000) {
                turn = -1;
                break;
            }
            for(int i = 1; i <= K; i++) {
                if(!arr[points[i].r][points[i].c].isEmpty() && arr[points[i].r][points[i].c].peekFirst() == points[i].num) {
                    int r = points[i].r;
                    int c = points[i].c;
                    // 이동 가능
                    int nr = r + dr[points[i].dir];
                    int nc = c + dc[points[i].dir];
                    boolean chk = false;
                    if(nr >= 0 && nr < N && nc >= 0 && nc < N) {    // 범위 내일 경우
                        if(map[nr][nc] == 1) {  // 빨간지역일 경우
                            chk = move(r, c, nr, nc, 1); // 뒤집고 이동함
                        } else if(map[nr][nc] == 2) { // 파란지역일 경우
                            points[i].setDir(nd[points[i].dir]);  // 방향전환하고
                            nr = points[i].r + dr[points[i].dir];     // 전환한 방향으로 이동
                            nc = points[i].c + dc[points[i].dir];
                            if(nr >= 0 && nr < N && nc >= 0 && nc < N) {    // 전환한 방향이 범위 내일 경우만 이동
                                if(map[nr][nc] == 0) {  // 흰칸인 경우 그냥이동
                                    chk = move(r, c, nr, nc, 0);
                                } else if(map[nr][nc] == 1) {   // 빨간칸인 경우 역 이동
                                    chk = move(r, c, nr, nc, 1);
                                }
                            }
                        } else { // 흰 지역일 경우 그냥 이동만
                            chk = move(r, c, nr, nc, 0);
                        }
                    } else {    // 범위 밖일 경우 방향 반대로 함
                        points[i].setDir(nd[points[i].dir]);  // 방향전환하고
                        nr = points[i].r + dr[points[i].dir];     // 전환한 방향으로 이동
                        nc = points[i].c + dc[points[i].dir];
                        if(nr >= 0 && nr < N && nc >= 0 && nc < N) {    // 전환한 방향이 범위 내일 경우만 이동
                            if(map[nr][nc] == 0) {  // 흰칸인 경우 그냥이동
                                chk = move(r, c, nr, nc, 0);
                            } else if(map[nr][nc] == 1) {   // 빨간칸인 경우 역 이동
                                chk = move(r, c, nr, nc, 1);
                            }
                        }
                    }
                    if(chk) {
                        break Loop1;
                    }
                }
            }
            turn++;
        }
    }
    public static boolean move(int r, int c, int nr, int nc, int color) {
        int size = arr[r][c].size();
        for(int s = 0; s < size; s++) {
            int next = color == 1 ? arr[r][c].removeLast() : arr[r][c].removeFirst();
            points[next].setR(nr);
            points[next].setC(nc);
            arr[nr][nc].addLast(next);
        }
        return arr[nr][nc].size() >= 4;
    }
    // →, ←, ↑, ↓
    public static int[] dr = {0, 0, -1, 1};
    public static int[] dc = {1, -1, 0, 0};
    public static int[] nd = {1, 0, 3, 2};
}