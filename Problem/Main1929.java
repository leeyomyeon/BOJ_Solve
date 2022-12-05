import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1929 {
    public static int M;
    public static int N;
    public static boolean[] arr;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 1   50
        N = Integer.parseInt(st.nextToken()); // 100 100
        arr = new boolean[N + 1];
        Arrays.fill(arr, false);
        StringBuffer sb = new StringBuffer();

        for(int j = 2; j <= N; j++) {
            if(j >= 2 && !arr[j]) {
                int i = 2;
                while(!(j * i > N)) {
                    arr[j * i] = true;
                    i++;
                }
            }
            if(j >= M && !arr[j]) {
                sb.append(j+"\n");
            }
        }

        System.out.println(sb.toString());
    }
}