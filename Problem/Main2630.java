import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2630 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[][] arr;
    public static int N;
    public static int white;
    public static int blue;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                arr[i][j] = (1 == Integer.parseInt(st.nextToken()));
            }
        }
        white = 0;
        blue = 0;
        divAndQun(1, 1, N);

        bw.write(white + "\n" + blue);
        br.close();
        bw.flush();
        bw.close();
    }

    public static void divAndQun(int r, int c, int dist) {
        if(Math.pow(dist, 2) == arrCnt(r, c, dist)) {  // 현재 칸이 전부 1이거나
            blue++;
        } else if (arrCnt(r, c, dist) == 0) {  // 현재 칸이 전부 0일때
            white++;
        } else {
            int newDist = dist / 2;
            divAndQun(r, c, newDist);
            divAndQun(r + newDist, c, newDist);
            divAndQun(r, c + newDist, newDist);
            divAndQun(r + newDist, c + newDist, newDist);
        }
    }

    public static int arrCnt(int r, int c, int dist) {
        int cnt = 0;
        for(int i = r; i < r + dist; i++) {
            for(int j = c; j < c + dist; j++) {
                if(arr[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}