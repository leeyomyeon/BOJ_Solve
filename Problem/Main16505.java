import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main16505 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, K;
    public static char[][] arr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        K = (int) Math.pow(2, N);
        arr = new char[K + 1][K + 1];
        dnq(1, 1, K);
        for(int i = 1; i <= K; i++) {
            for(int j = 1; j <= K; j++) {
                if(i + j <= K + 1) {
                    bw.write(arr[i][j] == '*' ? '*' : ' ');
                }
            }
            if(i != K) {
                bw.write("\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dnq(int nextR, int nextC, int idx) {
        if(idx == 1) {
            if(nextR % 2 == 0 && nextC % 2 == 0) {
                arr[nextR][nextC] = ' ';
            } else {
                arr[nextR][nextC] = '*';
            }
            return;
        }
        dnq(nextR, nextC, idx / 2); // 1
        dnq(nextR, nextC + idx / 2, idx / 2); // 2
        dnq(nextR + idx / 2, nextC, idx / 2); // 3
    }
}