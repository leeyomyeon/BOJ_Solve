import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7569 {
    public static int[][][] tomato;
    // [H][N][M]
    public static int M;
    public static int N;
    public static int H;
    public static int resDay;

    public static class Tomato {
        int m;
        int n;
        int h;
        int day;

        public Tomato(int h, int n, int m, int day) {
            this.m = m;
            this.n = n;
            this.h = h;
            this.day = day;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[H][N][M];
        Queue<Tomato> queue = new LinkedList<>();
        resDay = 0;

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if(tomato[i][j][k] == 1) {
                        queue.add(new Tomato(i, j, k, 0));
                    }
                }
            }
        }
        if(chkArr(tomato)) {
            System.out.println("0");
            return;
        }
        while(!queue.isEmpty()) {
            Tomato current = queue.poll();
            // 6방향 탐색
            for(int i = 0; i < 6; i++) {
                int newH = current.h + dh[i];
                int newN = current.n + dn[i];
                int newM = current.m + dm[i];
                if(chkBorder(newH, newN, newM)) {
                    // 경계선 안에 들어온경우
                    tomato[newH][newN][newM] = 1;
                    queue.add(new Tomato(newH, newN, newM, current.day + 1));
                    resDay = current.day + 1;
                }
            }
        }

        if(chkArr(tomato)) {
            System.out.println(resDay);
        } else {
            System.out.println("-1");
        }
    }
    // 상 하 좌 우 위 아래
    // [H][N][M]
    public static int[] dh = {0, 0, 0, 0, -1, 1};
    public static int[] dn = {1, -1, 0, 0, 0, 0};
    public static int[] dm = {0, 0, -1, 1, 0, 0};
    public static boolean chkBorder(int th, int tn, int tm) {
        if(th >= 0 && th < H && tn >= 0 && tn < N && tm >= 0 && tm < M && tomato[th][tn][tm] == 0 ) { // 경계 안에서 작업 가능한경우 and 토마토가 안익은 경우
            return true;
        } else {
            return false;
        }
    }
    public static boolean chkArr(int[][][] arr) {
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    if(arr[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}