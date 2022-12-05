import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1193 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int k = 1;
        int sum = 0;
        while(true) {
            if(n <= sum) {
                sum -= n;
                break;
            }

            sum += k;
            k++;
        }
        String str = "";
        if(k % 2 == 0) {
            str = (1 + sum) + "/" + (k - sum - 1);
        } else {
            str = (k - sum - 1) + "/" + (1 + sum);
        }

        System.out.println(str);
    }
}