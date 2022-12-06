import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main2447 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static char[][] arr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(arr[i], ' ');
        }
        dnq(0, 0, N);
        
        for(char[] c : arr) {
            for(char chr : c) {
                bw.write(chr);
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dnq(int r, int c, int dist) throws Exception {
        if(dist == 1) {
            arr[r][c] = '*';
            return;
        }
        int newDist = dist / 3;
        int cnt = 0;
        for(int i = r; i < r + dist; i += newDist) {
            for(int j = c; j < c + dist; j += newDist) {
                cnt++;
                if(cnt != 5) {
                    dnq(i, j, newDist);
                }
            }
        }
    }
}