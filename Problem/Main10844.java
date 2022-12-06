import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main10844 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static long[][] memorize;
    public static final int MOD = 1000000000;
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        memorize = new long[n + 1][11];

        /* 
        1 = 9
        1 2 3 4 5 6 7 8 9
        */
        for(int i = 1; i <= 9; i++) {
            memorize[1][i] = 1;
        }
        /* 
        --- 2 = 17 ---
        10 12 
        21 23
        32 34
        43 45
        54 56
        65 67
        76 78
        87 89
        98
        --- 3 = 32 ---
        101 121 123
        210 212 232 234
        ...
        765 767 787 789
        876 878 898 
        987 989
        */
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j <= 9; j++) {
                if(j == 0) {
                    memorize[i][j] = memorize[i - 1][j + 1];
                } else if (j == 9) {
                    memorize[i][j] = memorize[i - 1][j - 1];
                } else {
                    memorize[i][j] = (memorize[i - 1][j - 1] + memorize[i - 1][j + 1]) % MOD;
                }
            }
        }

        long sum = 0;
        for(int i = 0; i <= 9; i++) {
            sum += memorize[n][i];
            sum %= MOD;
        }
        bw.write(Long.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }
}
