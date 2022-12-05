import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main1748 {
    public static int N;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int len = Integer.toString(N).length();

        int res = 0;
        for(int i = 0; i < len - 1; i++) {
            res += (i + 1) * (9 * (int) Math.pow(10, i));
        }
        int k = N - (int) Math.pow(10, len - 1);
        res += (len * (k + 1));
        System.out.println(res);
        /*
        1~9 1       * 9개
        10 ~ 99 2   * 90개
        100 ~ 999 3 * 900개
        1000 ~ 9999 4 * 45개
        
        1044 

        120 3 * 20
        */
    }
}