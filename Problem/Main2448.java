import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main2448 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, K;
    public static boolean[][] arr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1][N * 2];
        dnq(N, 1, N);
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j < N * 2; j++) {
                bw.write(arr[i][j] ? 42 : 32);
            }
            if(i < N) {
                bw.newLine();
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dnq(int idx, int row, int col) {
        if(idx == 3) {
            arr[row][col] = true;
            arr[row + 1][col - 1] = true;
            arr[row + 1][col + 1] = true;
            arr[row + 2][col - 2] = true;
            arr[row + 2][col - 1] = true;
            arr[row + 2][col] = true;
            arr[row + 2][col + 1] = true;
            arr[row + 2][col + 2] = true;
            return;
        }

        dnq(idx / 2, row, col);
        dnq(idx / 2, row + idx / 2, col - idx / 2);
        dnq(idx / 2, row + idx / 2, col + idx / 2);
    }
}