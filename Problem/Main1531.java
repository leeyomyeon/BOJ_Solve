import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1531 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] arr;
    public static int N, M;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[101][101];
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());
            for(int r = sr; r <= er; r++) {
                for(int c = sc; c <= ec; c++) {
                    arr[r][c]++;
                }
            }
        }
        for(int i = 1; i <= 100; i++) {
            for(int j = 1; j <= 100; j++) {
                if(arr[i][j] > M) {
                    cnt++;
                }
            }
        }
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}