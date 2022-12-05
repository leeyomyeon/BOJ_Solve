import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1996 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr;
    public static int N;
    public static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if(c != '.') {
                    int n = c - '0';
                    for(int d = 0; d < 8; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr >= 0 && nr < N && nc >= 0 && nc < N && arr[nr][nc] != -1) {
                            arr[nr][nc] += n;
                        }
                    }
                    arr[i][j] = -1;
                }
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] >= 10) {
                    bw.write("M");
                } else if(arr[i][j] == -1) {
                    bw.write("*");
                } else {
                    bw.write(Integer.toString(arr[i][j]));
                }
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}