import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2740 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int N, M, K;
    public static int[][] A, B, R;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = new int[M][K];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        R = new int[N][K];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < K; j++) {
                R[i][j] = getSum(i, j);
                sb.append(R[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static int getSum(int r, int c) {
        int sum = 0;
        for(int i = 0; i < M; i++) {
            sum += (A[r][i] * B[i][c]);
        }
        return sum;
    }
    /*
    * 00 01   00 01 02   00*00 + 01*10, 00*01 + 01*11, 00*02 + 01*12
    * 10 11 X 10 11 12 = 10*00 + 11*10, 10*01 + 11*11, 10*02 + 11*12
    * 20 21              20*00 + 21*10, 20*01 + 21*11, 20*02 + 21*12
    * */
}