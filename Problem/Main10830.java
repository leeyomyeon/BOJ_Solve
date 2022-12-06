import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10830 {
    public static int N;
    public static long B;
    public static long[][] arr;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new long[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        long[][] res = matrixPow(arr, B); 

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print((res[i][j] % 1000) + " ");
            }
            System.out.println();
        }
    }

    public static long[][] matrixPow(long[][] tmp, Long exp) {
        if(exp == 1) {
            return tmp;
        }

        long[][] res = matrixPow(tmp, exp / 2);

        if(exp % 2 == 1) {
            return matMul(matMul(res, res), tmp);
        } else {
            return matMul(res, res);
        }
    }

    public static long[][] matMul(long[][] tmp, long[][] tmp1) {
        long[][] res = new long[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                long sum = 0;
                for(int k = 0; k < N; k++) {
                    sum += (tmp[i][k] * tmp1[k][j]) % 1000 ;
                }
                res[i][j] = sum;
            }
        }
        return res;
    }
}