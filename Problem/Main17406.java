import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main17406 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr, copyArr;
    public static int N, M, K, min;
    public static boolean[] visited;
    public static int[] selected;
    public static HashMap<Integer, Point> map;
    public static class Point {
        int r;
        int c;
        int s;
        public Point(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        selected = new int[K + 1];
        visited = new boolean[K + 1];
        arr = new int[N + 1][M + 1];
        map = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            map.put(i, new Point(r, c, s));
        }
        min = Integer.MAX_VALUE;
        permutataion(1);
        bw.write(Integer.toString(min));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void permutataion(int cnt) {
        if(cnt == K + 1) {
            rotate(selected);
            return;
        }

        for(int i = 1; i <= K; i++) {
            if(visited[i]) {
                continue;
            }
            selected[cnt] = i;
            visited[i] = true;
            permutataion(cnt + 1);
            visited[i] = false;
        }
    }

    public static void rotate(int[] order) {
        copy();
        for(int i = 1; i < order.length; i++) {
            Point current = map.get(order[i]);
            rotateArr(current.r, current.c, current.s);
        }
        findMin();
    }
    public static void rotateArr(int r, int c, int s) {
        for(int d = s; d > 0; d--) {
            int nr = r - d;
            int nc = c - d;
            int tmp = copyArr[nr][nc];
            for(; nr < r + d; nr++) {
                copyArr[nr][nc] = copyArr[nr + 1][nc];
            }
            for(; nc < c + d; nc++) {
                copyArr[nr][nc] = copyArr[nr][nc + 1];
            }
            for(; nr > r - d; nr--) {
                copyArr[nr][nc] = copyArr[nr - 1][nc];
            }
            for(; nc > c - d; nc--) {
                copyArr[nr][nc] = copyArr[nr][nc - 1];
            }
            copyArr[nr][nc + 1] = tmp;
        }
    }
    public static void copy() {
        copyArr = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            copyArr[i] = Arrays.copyOf(arr[i], M + 1);
        }
    }

    public static void findMin() {
        for(int i = 1; i <= N; i++) {
            int sum = 0;
            for(int j = 1; j <= M; j++) {
                sum += copyArr[i][j];
            }
            min = Math.min(min, sum);
        }
    }
}