import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main21608 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, res;
    public static int[][] arr, student;
    public static HashMap<Integer, Integer> mapR;
    public static HashMap<Integer, Integer> mapC;
    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        student = new int[N * N][5];
        mapR = new HashMap<>();
        mapC = new HashMap<>();
        for(int n = 0; n < N * N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int s = 0; s < 5; s++) {
                student[n][s] = Integer.parseInt(st.nextToken());
            }
            setStudent(n);
        }
        getSatisfy();
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void setStudent(int n) {
        int cnt = 0;
        int[][] tmp = new int[N][N];
        for(int s = 1; s <= 4; s++) {
            if(mapR.containsKey(student[n][s])) {
                int targetR = mapR.get(student[n][s]);
                int targetC = mapC.get(student[n][s]);
                tmp[targetR][targetC] = -1;
                for(int d = 0; d < 4; d++) {
                    int nr = targetR + dr[d];
                    int nc = targetC + dc[d];
                    if(nr >= 0 && nr < N && nc >= 0 && nc < N && tmp[nr][nc] >= 0 && arr[nr][nc] == 0) {
                        cnt++;
                        tmp[nr][nc]++;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int r = -1;
        int c = -1;
        ArrayDeque<Point> deque = new ArrayDeque<>();
        boolean isMax = false;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(cnt == 0 && arr[i][j] == 0) { // 인접한 학생 없을시
                    int empty = 0;
                    for(int d = 0; d < 4; d++) {
                        int ni = i + dr[d];
                        int nj = j + dc[d];
                        if(ni >= 0 && ni < N && nj >= 0 && nj < N && arr[ni][nj] == 0) {
                            empty++;
                        }
                    }
                    if(max < empty) {
                        max = empty;
                        r = i;
                        c = j;
                    }
                } else if(cnt >= 1 && arr[i][j] == 0) {
                    if(tmp[i][j] > 0 && tmp[i][j] > max) {
                        deque.clear();
                        isMax = false;
                        max = tmp[i][j];
                        r = i;
                        c = j;
                        deque.add(new Point(i, j));
                    } else if(tmp[i][j] == max) {
                        deque.add(new Point(i, j));
                        isMax = true;
                    }
                }
            }
        }
        if(isMax) {
            int maxEmpty = Integer.MIN_VALUE;
            while(!deque.isEmpty()) {
                Point current = deque.removeFirst();
                int cntEmpty = 0;
                for(int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr >= 0 && nr < N && nc >= 0 && nc < N && arr[nr][nc] == 0) {
                        cntEmpty++;
                    }
                }
                if(maxEmpty < cntEmpty) {
                    maxEmpty = cntEmpty;
                    r = current.r;
                    c = current.c;
                }
            }
        }
        if(r == -1 && c == -1) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(arr[i][j] == 0) {
                        r = i;
                        c = j;
                        break;
                    }
                }
            }
        }
        arr[r][c] = student[n][0];
        mapR.put(student[n][0], r);
        mapC.put(student[n][0], c);
    }
    public static void getSatisfy() {
        res = 0;
        for(int i = 0; i < N * N; i++) {
            int r = mapR.get(student[i][0]);
            int c = mapC.get(student[i][0]);
            int cnt = 0;
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if(arr[nr][nc] == student[i][1] || arr[nr][nc] == student[i][2] || arr[nr][nc] == student[i][3] || arr[nr][nc] == student[i][4]) cnt++;
                }
            }
            if(cnt >= 1) {
                res += Math.pow(10, cnt - 1);
            }
        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
}