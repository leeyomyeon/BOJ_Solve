import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1978 {
    static boolean[] arr = new boolean[1001];
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        Arrays.fill(arr, true);
        arr[1] = false;
        primeArr();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int k = Integer.parseInt(st.nextToken());
            if(arr[k]) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static void primeArr() {
        for(int i = 4; i <= 1000; i++) {
            for(int j = 2; j < i; j++) {
                if(i % j == 0) {
                    arr[i] = false;
                    break;
                }
            }
        }
    }
}