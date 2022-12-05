import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1629 {
    public static long A;
    public static long B;
    public static long C;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(pow(A, B));
    }

    public static long pow(long num, long exp) {
        if(exp == 1) {
            return num % C;
        }

        long tmp = pow(num, exp / 2);

        if(exp % 2 == 1) {
            return (tmp * tmp % C) * num % C;
        } else {
            return tmp * tmp % C;
        }
    }
}