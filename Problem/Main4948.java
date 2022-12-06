import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main4948 {
    public static boolean[] isPrime;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isPrime = new boolean[246913];
        Arrays.fill(isPrime, false);
        settingPrime();
        isPrime[1] = true;
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) {
                break;
            }
            int cnt = 0;
            for(int i = (N + 1); i <= N * 2; i++) {
                if(!isPrime[i]) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    public static void settingPrime() {
        for(int j = 2; j <= 246912; j++) {
            if(!isPrime[j]) {
                int i = 2;
                while(!(j * i > 246912)) {
                    isPrime[j * i] = true;
                    i++;
                }
            }
        }
    }
}
