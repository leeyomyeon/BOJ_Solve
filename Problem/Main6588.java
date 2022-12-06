import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main6588 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static ArrayList<Integer> primeList = new ArrayList<>();
    public static boolean[] isPrime;
    public static void main(String[] args) throws Exception {
        isPrime = new boolean[1000001];
        primeList = new ArrayList<>();
        init();
        Loop1:
        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) {
                break;
            }
            for(int k : primeList) {
                if(k > n) {
                    break;
                } else if(!isPrime[n - k]){
                    bw.write(n + " = " + k + " + " + (n - k) + "\n");
                    continue Loop1;
                }
            }
            bw.write("Goldbach's conjecture is wrong.\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void init() {
        // prime number = false
        // not prime number = true
        for(int i = 2; i <= 1000; i++) {
            if(!isPrime[i]) {
                int k = 2;
                while(i * k <= 1000000) {
                    isPrime[i * k] = true;
                    k++;
                }
            }
        }
        for(int i = 3; i <= 1000000; i++) {
            if(!isPrime[i]) {
                primeList.add(i);
            }
        }
    }
}