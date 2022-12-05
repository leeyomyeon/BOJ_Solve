import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1780 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int[][] arr;
    public static int r1, r2, r3;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        r1 = 0;
        r2 = 0;
        r3 = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dnq(0, 0, N);
        bw.write(r1 + "\n" + r2 + "\n" + r3);
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dnq(int r, int c, int dist) {
        if(dist == 1) {
            if(arr[r][c] == -1) {
                r1++;
            } else if (arr[r][c] == 0) {
                r2++;
            } else if (arr[r][c] == 1) {
                r3++;
            }
            return;
        }
        int k = chk(r, c, dist);
        if(k == 0) {
            r2 += 1;
        } else if (k == 1) {
            r3 += 1;
        } else if (k == -1) {
            r1 += 1;
        } else {
            int newDist = dist / 3;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    dnq(r + (newDist * i), c + (newDist * j), newDist);
                }
            }
        }
    }

    public static int chk(int r, int c, int dist) {
        int k = arr[r][c];
        for(int i = r; i < r + dist; i++) {
            for(int j = c; j < c + dist; j++) {
                if(k != arr[i][j]) {
                    return 2;
                }
            }
        }

        return k;
    }
}