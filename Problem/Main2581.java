import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2581 {
    public static int sum;
    public static int min;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        sum = 0;
        min = Integer.MAX_VALUE;
        int cnt = 0;

        for(int i = m; i <= n; i++) {
            if(isPrime(i)) {
                cnt++;
            }
        }
        if(cnt == 0) {
            System.out.println("-1");
        } else {
            System.out.println(sum);
            System.out.println(min);
        }
    }
    public static boolean isPrime(int num) {
        if(num == 1) {
            return false;
        } else if(num <= 3) {
            sum += num;
            if(num <= min){
                min = num;
            } 
            return true;
        } else {
            for(int j = 2; j < num; j++) {
                if(num % j == 0) {
                    return false;
                }
            }
            sum += num;
            if(num <= min){
              min = num;
            } 
            return true;
        }
    }
}