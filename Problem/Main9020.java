import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main9020 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static boolean[] isPrime = new boolean[10001];
    public static void main(String[] args) throws Exception {
        makePrime();
        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++) {
            int target = Integer.parseInt(br.readLine());
            for(int i = target / 2, j = target / 2; i >= 0 && j <= target; i--, j++) {
                    if(!isPrime[i] && !isPrime[j] && i + j == target) {
                        sb.append(i).append(" ").append(j).append("\n");
                        break;
                    }
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static void makePrime() {
        for(int i = 2; i <= 10000; i++) {
            if(!isPrime[i]) {
                int cnt = 2;
                while(true) {
                    int k =  i * cnt;
                    if(k >= 10000) {
                        break;
                    }
                    isPrime[k] = true;
                    cnt++;
                }
            }
        }
    }
}